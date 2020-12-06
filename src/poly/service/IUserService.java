package poly.service;

import java.util.List;

import poly.dto.DietDTO;

public interface IUserService {
	
		//로그인
		DietDTO loginPost(DietDTO pDTO);
		//회원가입
		int insertMember(DietDTO pDTO);
		//기초대사량 생성 <회원가입할때 돌아감>
		int insertMetabolism(DietDTO pDTO);
		//현재 비밀번호 가져오기
		DietDTO getpwd(DietDTO pDTO);
		//비밀번호 수정
		int pwdChange(DietDTO pDTO);
		//중복 회원가입 확인
		DietDTO emailCheck(String email);
		//임시 비밀번호 발급
		int setTmpPwd(DietDTO pDTO);
		//email, name 확인 작업 
		DietDTO getemailnmchk(DietDTO pDTO);	
		
		/// 관리자
		//관리자 페이지
		List<DietDTO> rootPage();
		//회원삭제
		int deleteUser(DietDTO pDTO);
		//바디인포삭제
		int deleteMetabolism(DietDTO pDTO);

}
