package poly.service;

import java.util.List;

import poly.dto.DietDTO;
import poly.dto.FoodDTO;
import poly.dto.MapDTO;

public interface IDietService {
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
	//중복 회원가입 확인
	DietDTO emailCheck(String email);
	//임시 비밀번호 발급
	int setTmpPwd(DietDTO pDTO);
	//email, name 확인 작업 
	DietDTO getemailnmchk(DietDTO pDTO);	
	//설정된 기초대사량
	DietDTO myMetabolism(DietDTO pDTO);
	//목표 칼로리 가져오기
	DietDTO getGoalKcal(DietDTO pDTO);
	//음식 설정
	FoodDTO getbf_food(FoodDTO fDTO);
	
	List<MapDTO> DietMap(MapDTO pDTO) throws Exception;


}
