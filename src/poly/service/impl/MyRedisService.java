package poly.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.comm.ICommCont;
import poly.dto.RedisDTO;
import poly.persistance.redis.IMyRedisMapper;
import poly.service.IMyRedisService;

@Service("MyRedisService")
public class MyRedisService implements IMyRedisService, ICommCont {

	@Resource(name = "MyRedisMapper")
	private IMyRedisMapper myRedisMapper;

	private Logger log = Logger.getLogger(this.getClass());
	
	//세션 저장
	@Override
	public int insertSession(RedisDTO pDTO) throws Exception {
		log.info("--------------------------------------------");
		log.info(this.getClass().getName() + "start");

		if (pDTO == null) {
			pDTO = new RedisDTO();
		}
		
		int res = 1;
		
		//최초 로그인이라면
		if (myRedisMapper.insertSession(pDTO) == 1) {
			log.info("success 성공");

			//데이터 저장 이후 만료시간 로그인 이후 30분으로 설정
			myRedisMapper.setTimeOutMinute(ICommCont.redisKey, 30);
		
		} else {
			log.info("fail 실패");
			res = 0;
		}
		
		log.info(this.getClass().getName() + "end");

		return res;
	}
	
	//세션 가져오기
	@Override
	public int getSession(RedisDTO pDTO) throws Exception {
		log.info(this.getClass().getName() + " Start!");

		if (pDTO == null) {
			pDTO = new RedisDTO();
		}

		int res = myRedisMapper.getSession(pDTO);

		if (res > 0) {
			log.info("로그인 정보가 없음");
		}else {
			log.info("해당 아이디는 이미 로그인 중");
		}
		

		log.info(this.getClass().getName() + " End!");

		return res;

	}

	
	// 데이터 저장 유효시간 가져오기 
	@Override
	public Long getTimeOut() throws Exception {
		return myRedisMapper.getTimeOut();
	}

	@Override
	public boolean timeUpdate() throws Exception {
		
		return myRedisMapper.setTimeOutMinute(ICommCont.redisKey, 15);
	}

	@Override
	public int delectSession(RedisDTO pDTO) throws Exception {
		
		return myRedisMapper.delectSession(pDTO);
	}


}
