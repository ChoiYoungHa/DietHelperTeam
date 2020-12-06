package poly.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.DietDTO;
import poly.dto.MailDTO;
import poly.dto.RedisDTO;
import poly.service.IMailService;
import poly.service.IMyRedisService;
import poly.service.IUserService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;
import poly.util.TemporaryMail;

@Controller
public class UserController {
	
	@Resource(name = "MailService")
	private IMailService mailService;
	
	@Resource(name = "MyRedisService")
	private IMyRedisService myRedisService;
	
	@Resource(name = "UserService")
	private IUserService userService;
	
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
			
			String email = CmmUtil.nvl(EncryptUtil.encAES128CBC(request.getParameter("email"))); //id역활을 대신 할거기 떄문에 디코딩이 가능한 AES128 사용
			String password = CmmUtil.nvl(EncryptUtil.enHashSHA256(request.getParameter("password")));

			log.info("email :" + email);
			log.info("pwd :" + password);

			DietDTO pDTO = new DietDTO();

			pDTO.setEmail(email);
			pDTO.setPassword(password);
			
			DietDTO rDTO = userService.loginPost(pDTO);
			
			pDTO = null;
			log.info("rDTO null? : " + (rDTO == null));

			String msg = "";
			String url = "";

			// 로그인 안된
			if (rDTO == null) {

				msg = "로그인 실패";
				url = "/Login.do";

			} else { // 로그인된거
				log.info("rDTO.getEmail : " + rDTO.getEmail());
				log.info("rDTO.getPassword : " + rDTO.getPassword());
				msg = "로그인 성공";

				String user_no = CmmUtil.nvl(rDTO.getUser_no());
				String userEmail = CmmUtil.nvl(rDTO.getEmail());
				String userPassword = CmmUtil.nvl(rDTO.getPassword());
				String user_an = CmmUtil.nvl(rDTO.getUser_an());
				
				log.info("user_no :" + user_no);
				log.info("userEmail :" + userEmail);
				log.info("userPassword :" + userPassword);

				// 세션에 올림
				session.setAttribute("user_no", user_no);
				// 닉네임을 표시해야 하기 때문에
				session.setAttribute("user_an", user_an);
				//세션 시간 설정 부분
				session.setMaxInactiveInterval(60 * 30);
				//현제 세션 유지 시간이 얼마로 설정되어있는지 확인
				log.info("session MaxTime : " + session.getMaxInactiveInterval());

				url = "/MainPage.do";
				//관리자로 로그인 했을 경우
				if(user_no.equals("0")) {
					url = "/rootPage.do";
				}
				
				// Redis에 세션 저장
				RedisDTO tDTO = new RedisDTO();
				tDTO.setUser_no(user_no);
				
				//Redis에 해당 기본키로 설정된 값이 있는지 체크
				int res = myRedisService.getSession(tDTO);
				
				// 로그인 정보가 없기 때문에 로그인 해야됨
				if (res > 0) {
					//Redis에 해당 기본키 올림
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
				
				log.info("session(user_no) : " + user_no);
			}

			model.addAttribute("msg", msg);
			model.addAttribute("url", url);

			rDTO = null;

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

			pDTO.setUser_no(CmmUtil.nvl((String) session.getAttribute("user_no")));

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

		// redis 데이터 남은 timecheck
		@RequestMapping(value = "/timecheck")
		@ResponseBody
		public Long timecheck(HttpSession session) throws Exception {
			log.info("timecheck Start!");
			
			//long인 이유는 int로 했을경우 길이 부족으로 오류가 생기기 떄문
			long time = myRedisService.getTimeOut();
			log.info("time : " + time);

			log.info("timecheck End!");
			return time;
		}

		// redis 데이터 남은 timeupdate
		@RequestMapping(value = "/timeupdate")
		@ResponseBody
		public boolean timeupdate(HttpSession session) throws Exception {
			log.info("timeupdate Start!");
			//boolean인 이유는 자바스크립트에 있는 아작스에서 있는지 없는지에 대한 체크를 하기 쉽게 사용 
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

			log.info("userService.EmailCheck 시작");
			DietDTO pDTO = userService.emailCheck(email);
			log.info("pDTO : " + pDTO);
			log.info("userService.EmailCheck 종료");

			int result = 0;

			log.info("if 시작");

			// 1이면 값이 있는거임
			if (pDTO != null) {
				result = 1;
			}
			
			pDTO = null;
			
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

			String[] addrsplit = CmmUtil.nvl(request.getParameter("addr")).split(" ", 3); //주소에서 거주 구를 가져오기 위해서
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
			int res = userService.insertMember(pDTO);

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

				// 회원가입시 metabolism 생성
				log.info("metabolism 생성 시작 ");
				
				res = userService.insertMetabolism(pDTO);
				
				log.info("metabolism 생성 진행중 ");
				
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

			return "/user/temporaryPwd"; 
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

			log.info("userService.emailnmchk 시작");

			DietDTO pDTO = new DietDTO();
			pDTO.setEmail(email);
			pDTO.setUser_name(user_name);

			pDTO = userService.getemailnmchk(pDTO);
			log.info("pDTO : " + pDTO);
			log.info("userService.emailnmchk 종료");

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
				userService.setTmpPwd(pDTO);

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

			return "/user/pwdChange"; 
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

			log.info("userService.pwdchk 시작");

			DietDTO pDTO = new DietDTO();
			pDTO.setUser_no(user_no);
			pDTO.setPassword(password);

			pDTO = userService.getpwd(pDTO);
			log.info("pDTO : " + pDTO);
			log.info("userService.pwdchk 종료");

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
			tDTO = userService.getpwd(tDTO);
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
				int res = userService.pwdChange(pDTO);
				
				//성공시
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
		
		// 관리자 화면
		@RequestMapping(value = "/rootPage")
		public String rootPage(ModelMap model) {
			log.info("rootPage Start!");
			
			List<DietDTO> rlist = userService.rootPage();
			
			if(rlist == null) {
				rlist = new ArrayList<DietDTO>();
			}
			
			log.info("rlist :"+rlist);
			
			model.addAttribute("rList", rlist);
			
			rlist = null;
			return "/diet/rootPage";
		}
		
		// 관리자 화면
		@ResponseBody
		@RequestMapping(value = "/userdelete", method = RequestMethod.POST)
		public int deleteUser(HttpServletRequest request,ModelMap model) {
			log.info("deleteUser Start!");
				
			String user_no = CmmUtil.nvl(request.getParameter("user_no"));
			
			log.info("user_no : "+ user_no);
			
			DietDTO pDTO = new DietDTO();
			pDTO.setUser_no(user_no);
			
			int res = userService.deleteUser(pDTO);
			//user_info 데이터 삭제 성공시
			if(res>0) {
				log.info("deleteUser 성공");
				//body_info있는 내용도 지워라
			res = userService.deleteMetabolism(pDTO);
				if(res>0) {
					log.info("deleteMetabolism 성공");
				}else {
					log.info("deleteMetabolism 실패");
				}
			}else {
				log.info("deleteUser 실패");
			}
			pDTO = null;
			log.info("res :"+res);
			
			log.info("deleteUser end!");
			
			return res;
		}


}
