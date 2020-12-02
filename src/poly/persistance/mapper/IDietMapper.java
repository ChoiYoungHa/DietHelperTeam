package poly.persistance.mapper;

import config.Mapper;
import poly.dto.DietDTO;
import poly.dto.FoodDTO;
import poly.dto.MapDTO;

@Mapper("DietMapper")
public interface IDietMapper {
	//로그인
	DietDTO loginPost(DietDTO pDTO);
	//회원가입
	int insertMember(DietDTO pDTO);
	//기초대사량 생성 <회원가입할때 돌아감>
	int bodyprofile(DietDTO pDTO);
	//현재 비밀번호 가져오기
	DietDTO getpwd(DietDTO pDTO);
	//비밀번호 수정
	int pwdChange(DietDTO pDTO);
	//기초대사량 입력
	int metabolismPost(DietDTO pDTO);
	//중복회원가입 방지
	DietDTO emailCheck(String email);
	//임시 비밀번호 발급 및 수정
	int setTmpPwd(DietDTO pDTO);
	//email, name 확인 작업 
	DietDTO getemailnmchk(DietDTO pDTO);
	//설정된 기초대사량
	DietDTO myMetabolism(DietDTO pDTO);
	//목표칼로리 가져오기
	DietDTO getGoalKcal(DietDTO pDTO);	
	
	//각 메뉴 가져오기
	//고닭아 메뉴 가져오기
	FoodDTO getgodack();
	//서비스단에서 목표칼로리 가져오기
	DietDTO getmenugoalkcal(FoodDTO fDTO);
	// 잡홍올 메뉴 가져오기
	FoodDTO getjobhong();
	// 통훈땅 메뉴 가져오기
	FoodDTO gettonghun();
	// 흰쌀밥, 계란, 아보카도 가져오기
	FoodDTO geteggkado();
	// 현미밥, 우둔살, 올리브유 
	FoodDTO hyunwu();
	// 바나나, 계란 , 아몬드
	FoodDTO baegg();
	// 현미밥, 새우, 스트링치즈
	FoodDTO gethyunsae();
	// 듀럼밀면, 닭안심, 바질페스토 
	FoodDTO getdudack();
	
	//동주소가져오기
	MapDTO getaddr(MapDTO pDTO);
}
