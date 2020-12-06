package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.DietDTO;
import poly.persistance.mapper.IUserMapper;
import poly.service.IUserService;

@Service("UserService")
public class UserService implements IUserService {
	@Resource(name="UserMapper")
	private IUserMapper userMapper;
	
private Logger log = Logger.getLogger(this.getClass());
	
	//로그인
	@Override
	public DietDTO loginPost(DietDTO pDTO) {
		return userMapper.loginPost(pDTO);
	}
	
	//회원가입
	@Override
	public int insertMember(DietDTO pDTO) {	
		return userMapper.insertMember(pDTO);
	}
	
	//기초대사량 생성 <회원가입할때 돌아감>
	@Override
	public int insertMetabolism(DietDTO pDTO) {	
		return userMapper.insertMetabolism(pDTO);
	}
	
	//기존 비밀번호 가져오기
	@Override
	public DietDTO getpwd(DietDTO pDTO) {
		return userMapper.getpwd(pDTO);
	}
	
	//비밀번호 수정
	@Override
	public int pwdChange(DietDTO pDTO) {
		return userMapper.pwdChange(pDTO);
	}
	
	//email 중복 확인
	@Override
	public DietDTO emailCheck(String email) {
		return userMapper.emailCheck(email);
	}
	
	//임시비밀번호 발급
	@Override
	public int setTmpPwd(DietDTO pDTO) {
		return userMapper.setTmpPwd(pDTO);
	}
	
	//email, name 확인 작업 
	@Override
	public DietDTO getemailnmchk(DietDTO pDTO) {
		return userMapper.getemailnmchk(pDTO);
	}
	
	
	
	//관리자
	@Override
	public List<DietDTO> rootPage() {
		// TODO Auto-generated method stub
		return userMapper.rootPage();
	}

	@Override
	public int deleteUser(DietDTO pDTO) {
		// TODO Auto-generated method stub
		return userMapper.deleteUser(pDTO);
	}

	@Override
	public int deleteMetabolism(DietDTO pDTO) {
		// TODO Auto-generated method stub
		return userMapper.deleteMetabolism(pDTO);
	}
	

}
