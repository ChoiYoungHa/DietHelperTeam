package poly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import poly.dto.DietDTO;
import poly.dto.FoodDTO;
import poly.dto.MapDTO;
import poly.persistance.mapper.IDietMapper;
import poly.service.IDietService;
import poly.util.CmmUtil;
import poly.util.GoalKcal;

@Service("DietService")
public class DietService implements IDietService {
	@Resource(name="DietMapper")
	IDietMapper DietMapper;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	//로그인
	@Override
	public DietDTO loginPost(DietDTO pDTO) {
		return DietMapper.loginPost(pDTO);
	}
	
	//회원가입
	@Override
	public int insertMember(DietDTO pDTO) {	
		return DietMapper.insertMember(pDTO);
	}
	
	//기초대사량 생성 <회원가입할때 돌아감>
	@Override
	public int bodyprofile(DietDTO pDTO) {	
		return DietMapper.bodyprofile(pDTO);
	}
	
	//기존 비밀번호 가져오기
	@Override
	public DietDTO getpwd(DietDTO pDTO) {
		return DietMapper.getpwd(pDTO);
	}
	
	//비밀번호 수정
	@Override
	public int pwdChange(DietDTO pDTO) {
		return DietMapper.pwdChange(pDTO);
	}
	
	//개인정보 수정
	@Override
	public int metabolismPost(DietDTO pDTO) {
		return DietMapper.metabolismPost(pDTO);
	}
	
	//email 중복 확인
	@Override
	public DietDTO emailCheck(String email) {
		return DietMapper.emailCheck(email);
	}
	
	//임시비밀번호 발급
	@Override
	public int setTmpPwd(DietDTO pDTO) {
		return DietMapper.setTmpPwd(pDTO);
	}
	
	//email, name 확인 작업 
	@Override
	public DietDTO getemailnmchk(DietDTO pDTO) {
		return DietMapper.getemailnmchk(pDTO);
	}

	//설정된 기초대사량
	@Override
	public DietDTO myMetabolism(DietDTO pDTO) {
		return DietMapper.myMetabolism(pDTO);
	}

	//목표 칼로리 가져오기
	@Override
	public DietDTO getGoalKcal(DietDTO pDTO) {
		return DietMapper.getGoalKcal(pDTO);
	}
	
	//메뉴가져오기 로직
	@Override
	public FoodDTO getbf_food(FoodDTO fDTO) {
		
		String bf_food = CmmUtil.nvl(fDTO.getBf_food()); //아침메뉴 가져오기
		String lf_food = CmmUtil.nvl(fDTO.getLf_food()); //점심메뉴 가져오기
		String df_food = CmmUtil.nvl(fDTO.getDf_food()); //저녁메뉴 가져오기
		String user_no = CmmUtil.nvl(fDTO.getUser_no()); //회원번호 가져오기
		
		log.info("service bf_food : " + bf_food);
		log.info("service user_no : " + user_no);
	
		//목표칼로리 가져오기
		DietDTO pDTO = new DietDTO();
		
		//메뉴 가져오기
		FoodDTO gDTO = new FoodDTO();
		
		//결과값 전달 DTO
		FoodDTO rDTO = new FoodDTO();
		
		
		pDTO = DietMapper.getmenugoalkcal(fDTO); // 목표칼로리 가져오기
		if(pDTO == null) {
			pDTO = new DietDTO();
		}
		
		//jsp에 표시할 한끼칼로리 가져오기 
		GoalKcal util = new GoalKcal();
		
		util.getgetgoalk(pDTO);
		float today_tan = pDTO.getToday_tan();
		float today_protein = pDTO.getToday_protein();
		float today_fat = pDTO.getToday_fat();
		float ftoday_kcal = pDTO.getToday_kcal();
		int itoday_kcal = (int)ftoday_kcal;
		// 소수점 제거를 위한 정수 형변환
		int igoal_kcal = Integer.parseInt(pDTO.getGoal_kcal());
		// DTO전달 및 HashMap 전달을 위한 String 변환
		String goal_kcal = Integer.toString(igoal_kcal);
		
		String today_kcal = Integer.toString(itoday_kcal);
		log.info("today_tan : " + today_tan);
		log.info("today_protein : " + today_protein);
		log.info("today_fat : " + today_fat);
		log.info("today : " + today_kcal);
		
		//jsp에 표시할 한끼 칼로리에 표시할 한끼 칼로리, 목표칼로리
		rDTO.setToday_kcal(today_kcal);
		rDTO.setGoal_kcal(goal_kcal);
		
		if(bf_food.equals("고닭아") || lf_food.equals("고닭아") || df_food.equals("고닭아")) { 
	
			gDTO = DietMapper.getgodack(); //고구마 닭가슴살 아몬드 메뉴 영양소 정보 가져오기
			if(gDTO == null) {
				gDTO = new FoodDTO();
			}
			
			//gDTO에 요청한 영양소 확인
			log.info("tan_name : "+gDTO.getTan_name());
			log.info("tan_kcal : "+gDTO.getTan_kcal());
			log.info("dan_name : "+gDTO.getDan_name());
			log.info("dan_kcal : "+gDTO.getDan_kcal());
			log.info("fat_name : "+gDTO.getFat_name());
			log.info("fat_kcal : "+gDTO.getFat_kcal());
			
			//계산을 위한 각 음식 칼로리 세팅
			Float sweetpt_kcal = gDTO.getTan_kcal();
			Float dak_kcal = gDTO.getDan_kcal();
			Float amond_kcal = gDTO.getFat_kcal();
			
			//음식이름 세팅
			String sweetpt = CmmUtil.nvl(gDTO.getTan_name());
			String dak = CmmUtil.nvl(gDTO.getDan_name());
			String amond = CmmUtil.nvl(gDTO.getFat_name());
			rDTO.setTan_name(CmmUtil.nvl(sweetpt));
			rDTO.setDan_name(CmmUtil.nvl(dak));
			rDTO.setFat_name(CmmUtil.nvl(amond));
		
			//개인 칼로리에 맞춘 한끼 탄단지 결과값
			int result_tan = (int) (today_tan / sweetpt_kcal);
			int result_dan = (int) (today_protein / dak_kcal);
			int result_fat = (int) (today_fat / amond_kcal);
			
			
			
			//잘계산 되었는지 확인
			log.info("고구마 : " +result_tan + "g");
			log.info("닭가슴살 : " +result_dan + "g");
			log.info("아몬드 : " +result_fat + "g");
			
			//계산 결과값 전달
			rDTO.setResult_tan(CmmUtil.nvl(Integer.toString(result_tan)));
			rDTO.setResult_dan(CmmUtil.nvl(Integer.toString(result_dan)));
			rDTO.setResult_fat(CmmUtil.nvl(Integer.toString(result_fat)));

		}
		
		else if(bf_food.equals("잡홍올")|| lf_food.equals("잡홍올")|| df_food.equals("잡홍올")) {
		
			gDTO = DietMapper.getjobhong(); //고구마 닭가슴살 아몬드 메뉴 영양소 정보 가져오기
			if(gDTO == null) {
				gDTO = new FoodDTO();
			}
			
			//잡곡밥 홍두께살 올리브유 영양소 확인
			log.info("tan_name : "+gDTO.getTan_name());
			log.info("tan_kcal : "+gDTO.getTan_kcal());
			log.info("dan_name : "+gDTO.getDan_name());
			log.info("dan_kcal : "+gDTO.getDan_kcal());
			log.info("fat_name : "+gDTO.getFat_name());
			log.info("fat_kcal : "+gDTO.getFat_kcal());
			
			//계산을 위한 각 음식 칼로리 세팅
			Float sweetpt_kcal = gDTO.getTan_kcal();
			Float dak_kcal = gDTO.getDan_kcal();
			Float amond_kcal = gDTO.getFat_kcal();
			
			//음식이름 세팅
			String sweetpt = CmmUtil.nvl(gDTO.getTan_name());
			String dak = CmmUtil.nvl(gDTO.getDan_name());
			String amond = CmmUtil.nvl(gDTO.getFat_name());
			
			rDTO.setTan_name(CmmUtil.nvl(sweetpt));
			rDTO.setDan_name(CmmUtil.nvl(dak));
			rDTO.setFat_name(CmmUtil.nvl(amond));	
			
			
			int result_tan = (int) (today_tan / sweetpt_kcal);
			int result_dan = (int) (today_protein / dak_kcal);
			int result_fat = (int) (today_fat / amond_kcal);
			
			
			//잘계산 되었는지 확인
			log.info("잡곡밥 : " +result_tan + "g");
			log.info("홍두께살 : " +result_dan + "g");
			log.info("올리브유 : " +result_fat + "g");
			
			//계산 결과값 전달
			rDTO.setResult_tan(CmmUtil.nvl(Integer.toString(result_tan)));
			rDTO.setResult_dan(CmmUtil.nvl(Integer.toString(result_dan)));
			rDTO.setResult_fat(CmmUtil.nvl(Integer.toString(result_fat)));
			
		}
		else if(bf_food.equals("통훈땅")|| lf_food.equals("통훈땅")|| df_food.equals("통훈땅")) {
		
			gDTO = DietMapper.gettonghun(); //통밀빵 훈제연어 땅콩버터 메뉴 영양소 정보 가져오기
			if(gDTO == null) {
				gDTO = new FoodDTO();
				
			}
			
			//잡곡밥 홍두께살 올리브유 영양소 확인
			log.info("tan_name : "+gDTO.getTan_name());
			log.info("tan_kcal : "+gDTO.getTan_kcal());
			log.info("dan_name : "+gDTO.getDan_name());
			log.info("dan_kcal : "+gDTO.getDan_kcal());
			log.info("fat_name : "+gDTO.getFat_name());
			log.info("fat_kcal : "+gDTO.getFat_kcal());
			
			//계산을 위한 각 음식 칼로리 세팅
			Float sweetpt_kcal = gDTO.getTan_kcal();
			Float dak_kcal = gDTO.getDan_kcal();
			Float amond_kcal = gDTO.getFat_kcal();
			
			//음식이름 세팅
			String sweetpt = CmmUtil.nvl(gDTO.getTan_name());
			String dak = CmmUtil.nvl(gDTO.getDan_name());
			String amond = CmmUtil.nvl(gDTO.getFat_name());
			
			rDTO.setTan_name(CmmUtil.nvl(sweetpt));
			rDTO.setDan_name(CmmUtil.nvl(dak));
			rDTO.setFat_name(CmmUtil.nvl(amond));
			
			
			int result_tan = (int) (today_tan / sweetpt_kcal);
			int result_dan = (int) (today_protein / dak_kcal);
			int result_fat = (int) (today_fat / amond_kcal);
			
			
			//잘계산 되었는지 확인
			log.info("통밀빵 : " +result_tan + "g");
			log.info("훈제연어 : " +result_dan + "g");
			log.info("땅콩버터 : " +result_fat + "g");
			
			//계산 결과값 전달
			rDTO.setResult_tan(CmmUtil.nvl(Integer.toString(result_tan)));
			rDTO.setResult_dan(CmmUtil.nvl(Integer.toString(result_dan)));
			rDTO.setResult_fat(CmmUtil.nvl(Integer.toString(result_fat)));
			
		}
		
		else if(bf_food.equals("흰계아")|| lf_food.equals("흰계아")|| df_food.equals("흰계아")) {
			
			
			gDTO = DietMapper.geteggkado(); //흰쌀밥 계란 아보카도 메뉴 영양소 정보 가져오기
			if(gDTO == null) {
				gDTO = new FoodDTO();
				
			}
			
			
			log.info("tan_name : "+gDTO.getTan_name());
			log.info("tan_kcal : "+gDTO.getTan_kcal());
			log.info("dan_name : "+gDTO.getDan_name());
			log.info("dan_kcal : "+gDTO.getDan_kcal());
			log.info("fat_name : "+gDTO.getFat_name());
			log.info("fat_kcal : "+gDTO.getFat_kcal());
			
			//계산을 위한 각 음식 칼로리 세팅
			Float sweetpt_kcal = gDTO.getTan_kcal();
			Float dak_kcal = gDTO.getDan_kcal();
			Float amond_kcal = gDTO.getFat_kcal();
			
			//음식이름 세팅
			String sweetpt = CmmUtil.nvl(gDTO.getTan_name());
			String dak = CmmUtil.nvl(gDTO.getDan_name());
			String amond = CmmUtil.nvl(gDTO.getFat_name());
			
			rDTO.setTan_name(CmmUtil.nvl(sweetpt));
			rDTO.setDan_name(CmmUtil.nvl(dak));
			rDTO.setFat_name(CmmUtil.nvl(amond));
		
			
			int result_tan = (int) (today_tan / sweetpt_kcal);
			int result_dan = (int) (today_protein / dak_kcal);
			int result_fat = (int) (today_fat / amond_kcal);
			
			
			//잘계산 되었는지 확인
			log.info("흰쌀밥 : " +result_tan + "g");
			log.info("계란 : " +result_dan + "g");
			log.info("아보카도 : " +result_fat + "g");
			
			//계산 결과값 전달
			rDTO.setResult_tan(CmmUtil.nvl(Integer.toString(result_tan)));
			rDTO.setResult_dan(CmmUtil.nvl(Integer.toString(result_dan)));
			rDTO.setResult_fat(CmmUtil.nvl(Integer.toString(result_fat)));
			
		}
		
		
		else if(bf_food.equals("현우올")|| lf_food.equals("현우올")|| df_food.equals("현우올")) {
			
			gDTO = DietMapper.hyunwu();
			if(gDTO == null) {
				gDTO = new FoodDTO();
				
			}
			
			
			log.info("tan_name : "+gDTO.getTan_name());
			log.info("tan_kcal : "+gDTO.getTan_kcal());
			log.info("dan_name : "+gDTO.getDan_name());
			log.info("dan_kcal : "+gDTO.getDan_kcal());
			log.info("fat_name : "+gDTO.getFat_name());
			log.info("fat_kcal : "+gDTO.getFat_kcal());
			
			//계산을 위한 각 음식 칼로리 세팅
			Float sweetpt_kcal = gDTO.getTan_kcal();
			Float dak_kcal = gDTO.getDan_kcal();
			Float amond_kcal = gDTO.getFat_kcal();
			
			//음식이름 세팅
			String sweetpt = CmmUtil.nvl(gDTO.getTan_name());
			String dak = CmmUtil.nvl(gDTO.getDan_name());
			String amond = CmmUtil.nvl(gDTO.getFat_name());
			
			rDTO.setTan_name(CmmUtil.nvl(sweetpt));
			rDTO.setDan_name(CmmUtil.nvl(dak));
			rDTO.setFat_name(CmmUtil.nvl(amond));
						
		
			
			int result_tan = (int) (today_tan / sweetpt_kcal);
			int result_dan = (int) (today_protein / dak_kcal);
			int result_fat = (int) (today_fat / amond_kcal);
			
			
			//잘계산 되었는지 확인
			log.info("현미밥 : " +result_tan + "g");
			log.info("우둔살 : " +result_dan + "g");
			log.info("올리브유 : " +result_fat + "g");
			
			//계산 결과값 전달
			rDTO.setResult_tan(CmmUtil.nvl(Integer.toString(result_tan)));
			rDTO.setResult_dan(CmmUtil.nvl(Integer.toString(result_dan)));
			rDTO.setResult_fat(CmmUtil.nvl(Integer.toString(result_fat)));
		}
		
		
		else if(bf_food.equals("바계아")|| lf_food.equals("바계아")|| df_food.equals("바계아")) {
			
			
			gDTO = DietMapper.baegg();
			if(gDTO == null) {
				gDTO = new FoodDTO();
				
			}
			
			
			log.info("tan_name : "+gDTO.getTan_name());
			log.info("tan_kcal : "+gDTO.getTan_kcal());
			log.info("dan_name : "+gDTO.getDan_name());
			log.info("dan_kcal : "+gDTO.getDan_kcal());
			log.info("fat_name : "+gDTO.getFat_name());
			log.info("fat_kcal : "+gDTO.getFat_kcal());
			
			//계산을 위한 각 음식 칼로리 세팅
			Float sweetpt_kcal = gDTO.getTan_kcal();
			Float dak_kcal = gDTO.getDan_kcal();
			Float amond_kcal = gDTO.getFat_kcal();
			
			//음식이름 세팅
			String sweetpt = CmmUtil.nvl(gDTO.getTan_name());
			String dak = CmmUtil.nvl(gDTO.getDan_name());
			String amond = CmmUtil.nvl(gDTO.getFat_name());
			
			rDTO.setTan_name(CmmUtil.nvl(sweetpt));
			rDTO.setDan_name(CmmUtil.nvl(dak));
			rDTO.setFat_name(CmmUtil.nvl(amond));
			
			// 최종 메뉴의 g 구하기
			int result_tan = (int) (today_tan / sweetpt_kcal);
			int result_dan = (int) (today_protein / dak_kcal);
			int result_fat = (int) (today_fat / amond_kcal);
			
			
			//잘계산 되었는지 확인
			log.info("바나나 : " +result_tan + "g");
			log.info("계란 : " +result_dan + "g");
			log.info("아몬드 : " +result_fat + "g");
			
			//계산 결과값 전달
			rDTO.setResult_tan(CmmUtil.nvl(Integer.toString(result_tan)));
			rDTO.setResult_dan(CmmUtil.nvl(Integer.toString(result_dan)));
			rDTO.setResult_fat(CmmUtil.nvl(Integer.toString(result_fat)));
		}
		
		else if(bf_food.equals("현돼스")|| lf_food.equals("현돼스")|| df_food.equals("현돼스")) {
			
			gDTO = DietMapper.gethyunsae(); 
			if(gDTO == null) {
				gDTO = new FoodDTO();
				
			}
			
			
			log.info("tan_name : "+gDTO.getTan_name());
			log.info("tan_kcal : "+gDTO.getTan_kcal());
			log.info("dan_name : "+gDTO.getDan_name());
			log.info("dan_kcal : "+gDTO.getDan_kcal());
			log.info("fat_name : "+gDTO.getFat_name());
			log.info("fat_kcal : "+gDTO.getFat_kcal());
			
			//계산을 위한 각 음식 칼로리 세팅
			Float sweetpt_kcal = gDTO.getTan_kcal();
			Float dak_kcal = gDTO.getDan_kcal();
			Float amond_kcal = gDTO.getFat_kcal();
			
			//음식이름 세팅
			String sweetpt = CmmUtil.nvl(gDTO.getTan_name());
			String dak = CmmUtil.nvl(gDTO.getDan_name());
			String amond = CmmUtil.nvl(gDTO.getFat_name());
			
			rDTO.setTan_name(CmmUtil.nvl(sweetpt));
			rDTO.setDan_name(CmmUtil.nvl(dak));
			rDTO.setFat_name(CmmUtil.nvl(amond));
			
			// 최종 메뉴의 g 구하기
			int result_tan = (int) (today_tan / sweetpt_kcal);
			int result_dan = (int) (today_protein / dak_kcal);
			int result_fat = (int) (today_fat / amond_kcal);
			
			
			//잘계산 되었는지 확인
			log.info("현미밥 : " +result_tan + "g");
			log.info("돼지안심 : " +result_dan + "g");
			log.info("스트링치즈 : " +result_fat + "g");
			
			//계산 결과값 전달
			rDTO.setResult_tan(CmmUtil.nvl(Integer.toString(result_tan)));
			rDTO.setResult_dan(CmmUtil.nvl(Integer.toString(result_dan)));
			rDTO.setResult_fat(CmmUtil.nvl(Integer.toString(result_fat)));
		}
		
		else if(bf_food.equals("듀새바")|| lf_food.equals("듀새바")|| df_food.equals("듀새바")) {
			
			gDTO = DietMapper.getdudack(); //듀럼밀면, 새우, 바질페스토 메뉴 영양소 정보 가져오기
			if(gDTO == null) {
				gDTO = new FoodDTO();
				
			}
			
			//잡곡밥 홍두께살 올리브유 영양소 확인
			log.info("tan_name : "+gDTO.getTan_name());
			log.info("tan_kcal : "+gDTO.getTan_kcal());
			log.info("dan_name : "+gDTO.getDan_name());
			log.info("dan_kcal : "+gDTO.getDan_kcal());
			log.info("fat_name : "+gDTO.getFat_name());
			log.info("fat_kcal : "+gDTO.getFat_kcal());
			
			//계산을 위한 각 음식 칼로리 세팅
			Float sweetpt_kcal = gDTO.getTan_kcal();
			Float dak_kcal = gDTO.getDan_kcal();
			Float amond_kcal = gDTO.getFat_kcal();
			
			//음식이름 세팅
			String sweetpt = CmmUtil.nvl(gDTO.getTan_name());
			String dak = CmmUtil.nvl(gDTO.getDan_name());
			String amond = CmmUtil.nvl(gDTO.getFat_name());
			
			rDTO.setTan_name(CmmUtil.nvl(sweetpt));
			rDTO.setDan_name(CmmUtil.nvl(dak));
			rDTO.setFat_name(CmmUtil.nvl(amond));
			
			// 최종 메뉴의 g 구하기
			int result_tan = (int) (today_tan / sweetpt_kcal);
			int result_dan = (int) (today_protein / dak_kcal);
			int result_fat = (int) (today_fat / amond_kcal);
			
			
			//잘계산 되었는지 확인
			log.info("듀럼밀면 : " +result_tan + "g");
			log.info("새우 : " +result_dan + "g");
			log.info("바질페스토 : " +result_fat + "g");
			
			//계산 결과값 전달
			rDTO.setResult_tan(CmmUtil.nvl(Integer.toString(result_tan)));
			rDTO.setResult_dan(CmmUtil.nvl(Integer.toString(result_dan)));
			rDTO.setResult_fat(CmmUtil.nvl(Integer.toString(result_fat)));
		}
		
		
		
		return rDTO;
	}
	
    // tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
	
	@Override
	public List<MapDTO> DietMap(MapDTO pDTO) throws Exception{
		List<MapDTO> rlist = new ArrayList<MapDTO>();
		
		MapDTO rDTO = new MapDTO();
		//로그인 유저의 정보 기본키를 가져온뒤 해당유저의 회원가입할때 받은 동주소를 가져온다
		rDTO = DietMapper.getaddr(pDTO);
		
		String emdNm = rDTO.getAddr2();
		rDTO = null;
		
        log.info("service user_no : "+pDTO.getUser_no());
        log.info("emdNm : "+emdNm);
		
        int page = 1;	// 페이지 초기값 
		int check = 0; //10개만 찍기
		
		try{
			while(true){
				log.info("while START");
				// parsing할 url 지정(API 키 포함해서)
				String url =  "http://openapi.seoul.go.kr:8088/465354437532636834325a7048424c/xml/TbPublicSptCenter2019/"+page+"/100/";
				
				log.info("1");
				
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				
				Document doc = dBuilder.parse(url);
				log.info("2");
				// root tag 
				doc.getDocumentElement().normalize();
				log.info("Root element :" + doc.getDocumentElement().getNodeName());
				
				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("row");
				log.info("파싱할 리스트 수 : "+ nList.getLength());
				
				for(int temp = 0; temp < nList.getLength(); temp++){
					Node nNode = nList.item(temp);
					
					if(nNode.getNodeType() == Node.ELEMENT_NODE){
					
						Element eElement = (Element) nNode;
						
						if(emdNm.equals(getTagValue("GU_NM", eElement))) {
							
							rDTO = new MapDTO();
							
							log.info("######################");
							//System.out.println(eElement.getTextContent());
							log.info("시설명  : " + getTagValue("NM", eElement)); 
							log.info("경도  : " + getTagValue("XCODE", eElement));
							log.info("위도 : " + getTagValue("YCODE", eElement));
							log.info("######################");
							
							rDTO.setMap_name(getTagValue("NM", eElement));
							rDTO.setMap_pointx(getTagValue("XCODE", eElement));
							rDTO.setMap_pointy(getTagValue("YCODE", eElement));
						
							rlist.add(rDTO);
							check++;
							rDTO = null;
						
						}
					}
				}
				
				page += 1;
				
				log.info("page number : "+page);
				log.info("check number : "+check);
				
				if(check > 5){	
					break;
					
				}
			}	// while end
			
			rDTO = null;
		} catch (Exception e){	
			e.printStackTrace();
		}
		return rlist;
	}


}
