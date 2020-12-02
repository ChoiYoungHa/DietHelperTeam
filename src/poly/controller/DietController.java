package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//임시비밀번호 발송을 위한 임포트
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.DietDTO;
import poly.dto.FoodDTO;
import poly.dto.MailDTO;
import poly.dto.MapDTO;
import poly.dto.RedisDTO;
import poly.service.IDietService;
import poly.service.IMailService;
import poly.service.IMyRedisService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

import poly.util.TemporaryMail;

@Controller
public class DietController {

	@Resource(name = "DietService")
	IDietService dietService;

	@Resource(name = "MailService")
	private IMailService mailService;

	@Resource(name = "MyRedisService")
	private IMyRedisService myRedisService;

	private Logger log = Logger.getLogger(this.getClass());

	// 회원 로그인을 위한 Login
	@RequestMapping(value = "/Login")
	public String login() {
		log.info("DietHelper Start!");
		return "/user/loginForm";
	}

	// 로그인 체크
	@RequestMapping(value = "/LoginPost", method = RequestMethod.POST)
	public String loginPost(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		log.info("#############################################");
		log.info("LoginPost Start!");

		String email = CmmUtil.nvl(EncryptUtil.encAES128CBC(request.getParameter("email")));
		String password = CmmUtil.nvl(EncryptUtil.enHashSHA256(request.getParameter("password")));

		log.info("email :" + email);
		log.info("pwd :" + password);

		DietDTO pDTO = new DietDTO();

		pDTO.setEmail(email);
		pDTO.setPassword(password);

		pDTO = dietService.loginPost(pDTO);
		log.info("pDTO null? : " + (pDTO == null));

		String msg = "";
		String url = "";

		// 로그인 안된
		if (pDTO == null) {

			msg = "로그인 실패";
			url = "/Login.do";

		} else { // 로그인된거
			log.info("pDTO.getEmail : " + pDTO.getEmail());
			log.info("pDTO.getPassword() : " + pDTO.getPassword());
			msg = "로그인 성공";

			String user_no = CmmUtil.nvl(pDTO.getUser_no());
			String userEmail = CmmUtil.nvl(pDTO.getEmail());
			String userPassword = CmmUtil.nvl(pDTO.getPassword());

			log.info("user_no :" + user_no);
			log.info("userEmail :" + userEmail);
			log.info("userPassword :" + userPassword);

			// 세션에 올림
			session.setAttribute("user_no", user_no);
			// 닉네임을 표시해야 하기 때문에
			session.setAttribute("user_an", pDTO.getUser_an());
			session.setMaxInactiveInterval(60 * 30);

			log.info("session MaxTime : " + session.getMaxInactiveInterval());

			url = "/MainPage.do";

			// Redis에 세션 저장
			RedisDTO tDTO = new RedisDTO();
			tDTO.setUser_no(user_no);

			int res = myRedisService.getSession(tDTO);

			// 로그인 정보가 없기 때문에 로그인 해야됨
			if (res > 0) {
				myRedisService.insertSession(tDTO);
				// 중복 로그인 할려하거임
			} else {
				msg = "해당아이디는 로그인 중입니다.";
				url = "/Login.do";

				model.addAttribute("msg", msg);
				model.addAttribute("url", url);

				return "/redirect/loginRedirect";
			}

			tDTO = null;

			log.info("session(user_no) : " + pDTO.getUser_no());
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		pDTO = null;

		log.info("LoginPost end!");
		log.info("DietHelper end!");
		log.info("#############################################");
		return "/redirect";
	}

	// 로그아웃을 위한 Logout
	@RequestMapping(value = "/Logout")
	public String logout(HttpSession session, ModelMap model) throws Exception {
		log.info("Logout Start!");

		RedisDTO pDTO = new RedisDTO();

		pDTO.setUser_no((String) session.getAttribute("user_no"));

		int res = myRedisService.delectSession(pDTO); // redis 키 지우기

		if (res == 0) {
			log.info("delectSession not Start");
		} else {
			log.info("delectSession Start!");
		}
		pDTO = null;

		session.removeAttribute("user_no");
		session.removeAttribute("user_an");

		String msg = "로그아웃 되었습니다";
		String url = "/Login.do";

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		log.info("Logout End!");
		return "/redirect";
	}

	// redis 데이터 timecheck
	@RequestMapping(value = "/timecheck")
	@ResponseBody
	public Long timecheck(HttpSession session) throws Exception {
		log.info("timecheck Start!");

		long time = myRedisService.getTimeOut();
		log.info("time : " + time);

		log.info("timecheck End!");
		return time;
	}

	// redis 데이터 timeupdate
	@RequestMapping(value = "/timeupdate")
	@ResponseBody
	public boolean timeupdate(HttpSession session) throws Exception {
		log.info("timeupdate Start!");
		boolean res = myRedisService.timeUpdate();
		session.setMaxInactiveInterval(15 * 60);
		log.info("timeupdate End!");
		return res;
	}

	// 회원가입 입력화면
	@RequestMapping(value = "/Login/UserRegForm")
	public String userRegForm() {
		log.info("userRegInfo Start!");

		return "/user/userRegForm";
	}

	// 이메일 중복 확인 작업 @ResponseBody 사용시 http에 값을 전달함
	@ResponseBody
	@RequestMapping(value = "/UserRegForm/EmailCheck", method = RequestMethod.POST)
	public int emailCheck(HttpServletRequest request) throws Exception {
		log.info("EmailCheck 시작");

		String email = CmmUtil.nvl(EncryptUtil.encAES128CBC(request.getParameter("email")));
		log.info("email : " + email);

		log.info("DietService.EmailCheck 시작");
		DietDTO pDTO = dietService.emailCheck(email);
		log.info("pDTO : " + pDTO);
		log.info("DietService.EmailCheck 종료");

		int result = 0;

		log.info("if 시작");

		// 1이면 값이 있는거임
		if (pDTO != null) {
			result = 1;
		}
		log.info("result : " + result);
		log.info("if 종료");

		log.info("EmailCheck 종료");
		return result;
	}

	// 회원가입 실행
	@RequestMapping(value = "/insertMember", method = RequestMethod.POST)
	public String insertMember(HttpServletRequest request, ModelMap model) throws Exception {
		log.info("insertMember Start!");
		// jsp에서 입력값을 가져옴
		String email = CmmUtil.nvl(EncryptUtil.encAES128CBC(request.getParameter("email")));
		String password = CmmUtil.nvl(EncryptUtil.enHashSHA256(request.getParameter("password")));
		String user_an = CmmUtil.nvl(request.getParameter("user_an"));
		String user_name = CmmUtil.nvl(request.getParameter("user_name"));
		String addr = CmmUtil.nvl(request.getParameter("addr"));

		String[] addrsplit = CmmUtil.nvl(request.getParameter("addr")).split(" ", 3);
		String addr2 = addrsplit[1].trim();

		DietDTO pDTO = new DietDTO();

		// jsp에서 가져온 값을 저장
		pDTO.setEmail(email);
		pDTO.setPassword(password);
		pDTO.setUser_an(user_an);
		pDTO.setUser_name(user_name);
		pDTO.setAddr(addr);
		pDTO.setAddr2(addr2);

		log.info("email : " + email);
		log.info("password : " + password);
		log.info("user_an : " + user_an);
		log.info("user_name : " + user_name);
		log.info("addr : " + addr);
		log.info("addr2 : " + addr2);

		// DB에 저장 및 저장이 되었는지 확인
		int res = dietService.insertMember(pDTO);

		String msg;
		String url = "/Login.do";

		if (res > 0) {
			// 회원가입 이메일 발송
			log.info("회원가입 이메일 발송 준비");

			MailDTO mDTO = new MailDTO();

			mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));
			mDTO.setTitle("회원가입을 축하드립니다.");
			mDTO.setContents(CmmUtil.nvl(pDTO.getUser_name()) + "님의 회원가입을 축하드립니다.");

			mailService.doSendMail(mDTO);
			mDTO = null;
			log.info("회원가입 이메일 발송 완료");

			// 회원가입시 BODY PROFILE 생성
			log.info("BODY PROFILE 생성 시작 ");

			res = dietService.bodyprofile(pDTO);
			log.info("BODY PROFILE 생성 진행중 ");
			if (res > 0) {
				msg = "회원가입에 성공했습니다.";
			} else {
				msg = "회원가입에 실패했습니다.";
			}

			log.info("BODY PROFILE 생성 종료");

		} else {
			msg = "회원가입에 실패했습니다.";
		}

		log.info("model.addAttribute 시작");
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		pDTO = null;

		log.info("insertMember end!");
		log.info("userRegInfo end!");
		return "/redirect";
	}

	// 비밀번호 찾기화면
	@RequestMapping(value = "/Temporarypwd")
	public String temporarypwd() {
		log.info("Temporary Start!");

		return "/user/temporaryPwd"; // jsp없음
	}

	// email, name 확인 작업 @ResponseBody 사용시 http에 값을 전달함
	@ResponseBody
	@RequestMapping(value = "/Temporarypwd/emailnmchk", method = RequestMethod.POST)
	public int emailnmchk(HttpServletRequest request) throws Exception {
		log.info("emailnmchk 시작");

		String email = EncryptUtil.encAES128CBC(CmmUtil.nvl(request.getParameter("email")));
		String user_name = CmmUtil.nvl(request.getParameter("user_name"));
		log.info("email : " + email);
		log.info("user_name : " + user_name);

		log.info("DietService.emailnmchk 시작");

		DietDTO pDTO = new DietDTO();
		pDTO.setEmail(email);
		pDTO.setUser_name(user_name);

		pDTO = dietService.getemailnmchk(pDTO);
		log.info("pDTO : " + pDTO);
		log.info("DietService.emailnmchk 종료");

		int result = 0;

		log.info("if 시작");
		// 일치하는 email and name가 있으면
		if (pDTO != null) {
			result = 1;
		}
		log.info("result : " + result);
		log.info("if 종료");

		log.info("emailnmchk 종료");
		return result;
	}

	// 임시 비밀번호 발급 tmppwd == Temporary password
	@RequestMapping(value = "/Temporarypwd/tmppwd")
	public String tmppwd(HttpServletRequest request, ModelMap model, HttpSession session)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		log.info("####################################################");
		log.info("임시비밀번호 문자 발송 START!!");

		String email = EncryptUtil.encAES128CBC(CmmUtil.nvl(request.getParameter("email")));
		String user_name = CmmUtil.nvl(request.getParameter("user_name"));
		String tmppwd = "";
		String msg = "";
		String url = "";

		// 랜덤한 8자 임시 비밀번호 값 담기
		tmppwd = TemporaryMail.SendTemporaryMail();

		// 임시비밀번호
		log.info("임시 비밀번호 : " + tmppwd);

		MailDTO mDTO = new MailDTO();
		DietDTO pDTO = null;

		try {
			pDTO = new DietDTO();

			// 임시비밀번호 저장
			pDTO.setPassword(CmmUtil.nvl(EncryptUtil.enHashSHA256(tmppwd)));
			// where 사용을 위함임
			pDTO.setUser_name(user_name);
			pDTO.setEmail(email);

			// pDTO 값을 데이터베이스 업데이트
			dietService.setTmpPwd(pDTO);

			// 이메일 보내기 위해 다시 암호화 디코딩
			email = EncryptUtil.decAES128CBC(email);

			log.info("이메일 : " + email);
			log.info("DTO is Null?" + (pDTO == null));
			log.info("임시 비밀번호: " + pDTO.getPassword());

			// 랜덤문자 메일 발송 로직
			mDTO.setToMail(email);
			mDTO.setTitle("DietHelper 회원 임시 비밀번호 발급 이메일 입니다.");
			mDTO.setContents("임시 비밀번호는 :  " + tmppwd + "  입니다.");

			// 최종 전송
			mailService.doSendMail(mDTO);

			msg = "이메일로 임시 비밀번호를 발송하였습니다.";
			url = "/Login.do";

			model.addAttribute("msg", msg);
			model.addAttribute("url", url);

			// 변수와 메모리 초기화
			msg = "";
			url = "";
			mDTO = null;
			pDTO = null;
			log.info("임시비밀번호 문자 발송 END!!");
			log.info("Temporary END!");
			log.info("####################################################");

			return "/redirect/tmpRedirect";

		} catch (Exception e) {
			msg = "실패하였습니다. : " + e.toString();
			url = "/";
			log.info(e.toString());
			e.printStackTrace();

			model.addAttribute("msg", msg);
			model.addAttribute("url", url);

		} finally {
			// 변수와 메모리 초기화
			msg = "";
			url = "";
			mDTO = null;
			pDTO = null;
		}

		log.info("임시비밀번호 문자 발송 [ERROR] END!!");
		log.info("Temporary END!");
		log.info("####################################################");
		return "/redirect";
	}

	// 비밀번호 수정화면
	@RequestMapping(value = "/pwdchange")
	public String pwdchange() {
		log.info("pwdchange Start!");

		return "/user/pwdChange"; // jsp없음
	}

	// 비밀번호 중복 확인 작업 @ResponseBody 사용시 http에 값을 전달함
	@ResponseBody
	@RequestMapping(value = "/pwdchange/pwdchk", method = RequestMethod.POST)
	public int pwdchk(HttpSession session, HttpServletRequest request) throws Exception {
		log.info("pwdchk 시작");

		String user_no = CmmUtil.nvl((String) session.getAttribute("user_no"));
		String password = CmmUtil.nvl(EncryptUtil.enHashSHA256(request.getParameter("originpwd")));
		log.info("user_no : " + user_no);
		log.info("originpwd : " + password);

		log.info("DietService.pwdchk 시작");

		DietDTO pDTO = new DietDTO();
		pDTO.setUser_no(user_no);
		pDTO.setPassword(password);

		pDTO = dietService.getpwd(pDTO);
		log.info("pDTO : " + pDTO);
		log.info("DietService.pwdchk 종료");

		int res = 0;

		log.info("if 시작");
		// 일치하는 비밀번호가 있으면
		if (pDTO != null) {
			res = 1;
		}
		log.info("result : " + res);
		log.info("if 종료");

		log.info("pwdchk 종료");
		return res;
	}

	// 비밀번호 수정
	@RequestMapping(value = "/pwdchgPost")
	public String pwdchkPost(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception {
		log.info("pwdchgPost Start ");
		String user_no = CmmUtil.nvl((String) session.getAttribute("user_no"));
		String password = CmmUtil.nvl(EncryptUtil.enHashSHA256(request.getParameter("password")));
		String originpwd = CmmUtil.nvl(EncryptUtil.enHashSHA256(request.getParameter("originpwd")));

		log.info("기존 비밀번호 가져오기 시작");
		// 비밀번호
		DietDTO tDTO = new DietDTO();
		tDTO.setUser_no(user_no);
		tDTO.setPassword(originpwd);
		tDTO = dietService.getpwd(tDTO);
		log.info("tDTO null?" + (tDTO == null));
		originpwd = tDTO.getPassword();
		log.info("기존 비밀번호 가져오기 완료");

		String msg = "";
		String url = "";
		if (originpwd.equals(password)) {
			log.info("기존 비밀번호와 동일");
			log.info("user_no : " + user_no);
			log.info("originpwd: " + originpwd);
			log.info("pwdchange: " + password);

			msg = "기존 비밀번호와 동일합니다.";
			url = "/pwdchange.do"; // 수정입력창으로 이동

			model.addAttribute("msg", msg);
			model.addAttribute("url", url);

		} else {

			DietDTO pDTO = new DietDTO();

			log.info("기존 비밀번호와 다름");
			log.info("user_no : " + user_no);
			log.info("originpwd: " + originpwd);
			log.info("pwdchange: " + password);

			pDTO.setUser_no(user_no);
			pDTO.setPassword(password);

			// 비밀번호 수정
			int res = dietService.pwdChange(pDTO);
			log.info("기존 비밀번호와 다름2");
			if (res > 0) {
				RedisDTO eDTO = new RedisDTO();

				pDTO.setUser_no((String) session.getAttribute("user_no"));

				int result = myRedisService.delectSession(eDTO); // redis 키 지우기

				if (result == 0) {
					log.info("delectSession not Start");
				} else {
					log.info("delectSession Start!");
				}
				eDTO = null;

				session.removeAttribute("user_no");
				session.removeAttribute("user_an");

				msg = "비밀번호 수정 성공";
				url = "/Logout.do"; // 재로그인
			} else {
				msg = "비밀번호 수정 실패";
				url = "/pwdchange.do"; // 수정입력창으로 이동
			}

			model.addAttribute("msg", msg);
			model.addAttribute("url", url);

			pDTO = null;
			tDTO = null;
		}

		log.info("pwdchkPost end!");
		log.info("pwdchange end!");

		return "/redirect";
	}

	// 개인 정보 입력화면
	@RequestMapping(value = "/Metabolism")
	public String metabolism() {
		log.info("Metabolism Start!");

		return "/diet/metabolism"; //
	}

	// 개인 정보 입력 실행
	@RequestMapping(value = "/MetabolismPost", method = RequestMethod.POST)
	public String metabolismPost(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception {
		log.info("MetabolismPost start!");

		String user_no = CmmUtil.nvl((String) session.getAttribute("user_no"));

		// jsp에서 입력값을 가져옴
		String age = CmmUtil.nvl(request.getParameter("age")); // 나이
		String sex = CmmUtil.nvl(request.getParameter("sex")); // 성별
		String activity = CmmUtil.nvl(request.getParameter("activity")); // 활동량
		String basic = CmmUtil.nvl(request.getParameter("basic")); // 기초대사량
		String keep_kcal = CmmUtil.nvl(request.getParameter("keep_kcal")); // 유지칼로리(현칼로리)
		String goal_kcal = CmmUtil.nvl(request.getParameter("goal_kcal")); // 목표칼로리(식단전체칼로리)
		String how_exer = CmmUtil.nvl(request.getParameter("how_exer")); // 운동목적
		String weight = CmmUtil.nvl(request.getParameter("weight")); // 몸무게
		String tall = CmmUtil.nvl(request.getParameter("tall")); // 키

		log.info("user_no : " + user_no);
		log.info("age : " + age);
		log.info("sex : " + sex);
		log.info("activity : " + activity);
		log.info("basic : " + basic);
		log.info("keep_kcal : " + keep_kcal);
		log.info("goal_kcal : " + goal_kcal);
		log.info("how_exer : " + how_exer);
		log.info("weight : " + weight);
		log.info("tall : " + tall);

		DietDTO pDTO = new DietDTO();

		pDTO.setUser_no(user_no);

		// jsp에서 가져온 값을 저장
		pDTO.setAge(age);
		pDTO.setSex(sex);
		pDTO.setActivity(activity);
		pDTO.setBasic(basic);
		pDTO.setKeep_kcal(keep_kcal);
		pDTO.setGoal_kcal(goal_kcal);
		pDTO.setHow_exer(how_exer);
		pDTO.setWeight(weight);
		pDTO.setTall(tall);

		// DB에 저장 및 저장이 되었는지 확인
		int res = dietService.metabolismPost(pDTO);

		String msg = "";
		String url = "/Diet/MyMetabolism.do";

		if (res < 1) {
			msg = "등록에 실패했습니다";
		} else {
			msg = "등록에 성공했습니다";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		log.info("MetabolismPost end!");
		log.info("Metabolism end!");

		return "/redirect";
	}

	// 설정된 기초대사량
	@RequestMapping(value = "/Diet/MyMetabolism")
	public String myMetabolism(ModelMap model, HttpSession session) {

		log.info("MyMetabolism start");

		DietDTO pDTO = new DietDTO();

		pDTO.setUser_no(CmmUtil.nvl((String) session.getAttribute("user_no")));

		// 공지사항 리스트 가져오기
		DietDTO rDTO = dietService.myMetabolism(pDTO);

		pDTO = null;

		log.info("1 : " + rDTO);

		// 가져올 값이 없다면
		if (rDTO == null) {
			rDTO = new DietDTO();// 메모리에 올려줘라
		}
		log.info("2 : " + rDTO);

		if (rDTO.getSex().equals("1")) {
			rDTO.setSex("남자");
		} else {
			rDTO.setSex("여자");
		}

		// 조회된 리스트 결과값 넣어주기
		model.addAttribute("rDTO", rDTO);

		// 기초 설정된 값이라면
		if (rDTO.getAge().equals("0")) {
			String msg = "기초대사량 계산을 위해 정보를 입력해주세요.";
			String url = "/Metabolism.do";

			model.addAttribute("msg", msg);
			model.addAttribute("url", url);

			rDTO = null;

			return "/redirect";
		}
		// 변수 초기화(메모리 효율화 시키기 위해 사용함)
		rDTO = null;

		log.info("MyMetabolism end");
		// 함수 처리가 끝나고 보여줄 JSP 파일명(/WEB-INF/view/Diet/MyMetabolism.jsp)
		return "/mypage/myMetabolism";
	}

	// 푸드리스트 화면
	@RequestMapping(value = "/MyFoodList")
	public String myFoofList(HttpServletRequest request, ModelMap model, HttpSession session) {
		log.info("MyFoodList Start!");
		String user_no = CmmUtil.nvl((String) session.getAttribute("user_no")); // 유저no 가져오기
		FoodDTO pDTO = new FoodDTO();

		pDTO.setUser_no(user_no); // 목표칼로리를 가져오기 위한 user_no 세팅
		FoodDTO rDTO = dietService.getbf_food(pDTO); // 점심메뉴 가져오기

		String goal_kcal = CmmUtil.nvl(rDTO.getGoal_kcal());
		model.addAttribute("goal_kcal", goal_kcal);
		pDTO = null;
		rDTO = null;

		return "/mypage/myFoodList";
	}

	// 메인페이지 화면
	@RequestMapping(value = "/MainPage")
	public String Mainpage() {
		log.info("MainPage Start!");
		return "/diet/mainPage";
	}

	// 지도 화면
	@RequestMapping(value = "/MapPage")
	public String MapPage() {
		log.info("MapPage Start!");
		return "/diet/MapPage";
	}

	// 내페이지 화면
	@RequestMapping(value = "/MyPage")
	public String myPage() {
		log.info("MyPage Start!");
		return "/mypage/myPage";
	}

	// 아침, 점심, 저녁 메뉴세팅
	@RequestMapping(value = "/getmenu")
	public String getmenu(HttpServletRequest request, ModelMap model, HttpSession session) {
		log.info("getmenu Start!");

		String lf_food = CmmUtil.nvl(request.getParameter("lf_food")); // 점심
		String df_food = CmmUtil.nvl(request.getParameter("df_food")); // 저녁

		FoodDTO fDTO = new FoodDTO(); // 목표칼로리, 아침 점심 저녁을 각 각 버튼을 누를때 마다 다르게 저장되는 DTO

		fDTO.setDf_food(df_food); // 점심메뉴 세팅
		fDTO.setLf_food(lf_food); // 저녁메뉴 세팅

		FoodDTO lnDTO = dietService.getbf_food(fDTO); // 점심메뉴 가져오기
		if (lnDTO == null) { // null값 방지
			lnDTO = new FoodDTO();
		}

		FoodDTO dnDTO = dietService.getbf_food(fDTO); // 저녁메뉴 가져오기
		if (dnDTO == null) { // null값 방지
			dnDTO = new FoodDTO();
		}

		log.info("getmenu end!");
		return "/mypage/myFoodList";
	}

	// 아침 메뉴 구하기
	@RequestMapping(value = "/getMorningList")
	@ResponseBody
	public Map<String, String> getMorning(HttpServletRequest request, HttpSession session, Model model) {

		String user_no = CmmUtil.nvl((String) session.getAttribute("user_no")); // 유저no 가져오기
		String bf_food = CmmUtil.nvl(request.getParameter("bf_food")); // 아침

		FoodDTO fDTO = new FoodDTO();

		fDTO.setUser_no(user_no); // 목표칼로리를 가져오기 위한 user_no 세팅
		fDTO.setBf_food(bf_food); // 아침메뉴 세팅

		FoodDTO bkDTO = dietService.getbf_food(fDTO); // 아침메뉴 가져오기
		if (bkDTO == null) { // null값 방지
			bkDTO = new FoodDTO();
		}

		// jsp에 표시할 한끼칼로리, 목표칼로리 잘 가져왔는지 확인
		log.info(bkDTO.getToday_kcal());
		log.info("goal_kcal :" + bkDTO.getGoal_kcal());

		// 결과값이 Controller에 잘 전달 됐는지 확인
		log.info("Controller result_tan_kcal : " + bkDTO.getResult_tan());
		log.info("Controller result_dan_kcal : " + bkDTO.getResult_dan());
		log.info("Controller result_fat_kcal : " + bkDTO.getResult_fat());

		log.info("Controller tan_name : " + bkDTO.getTan_name());

		Map<String, String> rMap = new HashMap<String, String>();

		rMap.put("result_tan", bkDTO.getResult_tan());
		rMap.put("result_dan", bkDTO.getResult_dan());
		rMap.put("result_fat", bkDTO.getResult_fat());
		rMap.put("tan_name", bkDTO.getTan_name());
		rMap.put("dan_name", bkDTO.getDan_name());
		rMap.put("fat_name", bkDTO.getFat_name());
		rMap.put("today_kcal", bkDTO.getToday_kcal());
		rMap.put("back_goal_kcal", bkDTO.getGoal_kcal());

		return rMap;
	}

	// 점심 메뉴 구하기
	@RequestMapping(value = "/getLunchList")
	@ResponseBody
	public Map<String, String> getLunchList(HttpServletRequest request, HttpSession session, Model model) {

		String user_no = CmmUtil.nvl((String) session.getAttribute("user_no")); // 유저no 가져오기
		String lf_food = CmmUtil.nvl(request.getParameter("lf_food")); // 점심

		FoodDTO fDTO = new FoodDTO();

		fDTO.setUser_no(user_no); // 목표칼로리를 가져오기 위한 user_no 세팅
		fDTO.setBf_food(lf_food); // 아침메뉴 세팅

		FoodDTO bkDTO = dietService.getbf_food(fDTO); // 점심메뉴 가져오기
		if (bkDTO == null) { // null값 방지
			bkDTO = new FoodDTO();
		}

		// 결과값이 Controller에 잘 전달 됐는지 확인
		log.info("Controller result_tan_kcal : " + bkDTO.getResult_tan());
		log.info("Controller result_dan_kcal : " + bkDTO.getResult_dan());
		log.info("Controller result_fat_kcal : " + bkDTO.getResult_fat());

		log.info("Controller tan_name : " + bkDTO.getTan_name());

		Map<String, String> rMap = new HashMap<String, String>();

		rMap.put("result_tan", bkDTO.getResult_tan());
		rMap.put("result_dan", bkDTO.getResult_dan());
		rMap.put("result_fat", bkDTO.getResult_fat());
		rMap.put("tan_name", bkDTO.getTan_name());
		rMap.put("dan_name", bkDTO.getDan_name());
		rMap.put("fat_name", bkDTO.getFat_name());
		rMap.put("today_kcal", bkDTO.getToday_kcal());

		return rMap;
	}

	// 저녁 메뉴 구하기
	@RequestMapping(value = "/getdinnerList")
	@ResponseBody
	public Map<String, String> getdinnerList(HttpServletRequest request, HttpSession session, Model model) {

		String user_no = CmmUtil.nvl((String) session.getAttribute("user_no")); // 유저no 가져오기
		String df_food = CmmUtil.nvl(request.getParameter("df_food")); // 저녁

		FoodDTO fDTO = new FoodDTO();

		fDTO.setUser_no(user_no); // 목표칼로리를 가져오기 위한 user_no 세팅
		fDTO.setBf_food(df_food); // 저녁메뉴 세팅

		FoodDTO bkDTO = dietService.getbf_food(fDTO); // 점심메뉴 가져오기
		if (bkDTO == null) { // null값 방지
			bkDTO = new FoodDTO();
		}

		// 결과값이 Controller에 잘 전달 됐는지 확인
		log.info("Controller result_tan_kcal : " + bkDTO.getResult_tan());
		log.info("Controller result_dan_kcal : " + bkDTO.getResult_dan());
		log.info("Controller result_fat_kcal : " + bkDTO.getResult_fat());

		log.info("Controller tan_name : " + bkDTO.getTan_name());

		Map<String, String> rMap = new HashMap<String, String>();

		rMap.put("result_tan", bkDTO.getResult_tan());
		rMap.put("result_dan", bkDTO.getResult_dan());
		rMap.put("result_fat", bkDTO.getResult_fat());
		rMap.put("tan_name", bkDTO.getTan_name());
		rMap.put("dan_name", bkDTO.getDan_name());
		rMap.put("fat_name", bkDTO.getFat_name());
		rMap.put("today_kcal", bkDTO.getToday_kcal());

		return rMap;
	}

	// 지도 마커
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String Map(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		log.info(this.getClass().getName() + "MAP start!");
		MapDTO pDTO = new MapDTO();
		pDTO.setUser_no((String) session.getAttribute("user_no"));
		
		List<MapDTO> rList = dietService.DietMap(pDTO);
		
		if(rList == null) {
			rList = new ArrayList<MapDTO>();
		}
		
		model.addAttribute("rList", rList);
		
		log.info("rList :"+rList);
		
		rList = null;
		
		return "/diet/mapPage";
	}

}
