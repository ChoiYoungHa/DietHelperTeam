package poly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.DietDTO;
import poly.dto.FoodDTO;
import poly.dto.MapDTO;
import poly.service.IDietService;
import poly.service.IMailService;
import poly.service.IMyRedisService;
import poly.util.CmmUtil;

@Controller
public class DietController {
	//싱글톤 패턴 적용
	@Resource(name = "DietService")
	private IDietService dietService;

	@Resource(name = "MailService")
	private IMailService mailService;

	@Resource(name = "MyRedisService")
	private IMyRedisService myRedisService;

	private Logger log = Logger.getLogger(this.getClass());

	// 신체 정보 입력화면
	@RequestMapping(value = "/Metabolism")
	public String metabolism() {
		log.info("Metabolism Start!");

		return "/diet/metabolism"; 
	}

	// 신체 정보 입력 실행
	@RequestMapping(value = "/MetabolismPost", method = RequestMethod.POST)
	public String metabolismPost(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception {
		log.info("MetabolismPost start!");

		String user_no = CmmUtil.nvl((String) session.getAttribute("user_no")); //기본키

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
		
		pDTO = null;
		
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

		log.info("rDTO : " + rDTO);

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

		//음식이름 확인
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
		
		pDTO.setUser_no(CmmUtil.nvl((String) session.getAttribute("user_no")));
		
		List<MapDTO> rList = dietService.DietMap(pDTO);
		
		pDTO = null;
		
		if(rList == null) {
			rList = new ArrayList<MapDTO>();
		}
		
		model.addAttribute("rList", rList);
		
		log.info("rList :"+rList);
		
		rList = null;
		
		return "/diet/mapPage";
	}
	

}
