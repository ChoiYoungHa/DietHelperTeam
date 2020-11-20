package poly.persistance.redis.impl;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import poly.comm.ICommCont;
import poly.dto.RedisDTO;
import poly.persistance.redis.IMyRedisMapper;
import poly.util.CmmUtil;

@Component("MyRedisMapper")
public class MyRedisMapper implements IMyRedisMapper, ICommCont {

	@Autowired
	public RedisTemplate<String, Object> redisDB;

	private Logger log = Logger.getLogger(this.getClass());

	//세션저장
	@Override
	public int insertSession(RedisDTO pDTO) throws Exception {

		log.info(this.getClass().getName() + ".insertSession start!");

		int res = 0;

		if (pDTO == null) {
			pDTO = new RedisDTO();
		}

		redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입
		redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisDTO.class));

		// 해시 테이블의 경우, 시리얼라이즈 추가
		redisDB.setHashKeySerializer(new StringRedisSerializer()); // 키
		redisDB.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RedisDTO.class)); // 값

		// 회원번호
		String user_no = CmmUtil.nvl(pDTO.getUser_no());

		log.info("user_no : " + user_no);

		// Key가 존재한다면.... 즉 데이터가 1개이상이 존재한다면 있든 없든 일단 넣고 본다
		if (redisDB.hasKey(ICommCont.redisKey)) {
			log.info("Redis Exists : " + ICommCont.redisKey);
			
			//테이블 명, 키, 값
			redisDB.opsForHash().put(ICommCont.redisKey, user_no, pDTO);
			res = 1;
			
		} else {
			log.info("Redis Not Exists : " + ICommCont.redisKey);

			//테이블 명, 키, 값
			redisDB.opsForHash().put(ICommCont.redisKey, user_no, pDTO);
			res = 1;
			
		}

		log.info(this.getClass().getName() + ".insertSession End!");


		return res;
	}

	//세션가져오기
	@Override
	public int getSession(RedisDTO pDTO) throws Exception {
		log.info("--------------------------------------------");
		log.info(this.getClass().getName() + ".getSession start!");

		if (pDTO == null) {
			pDTO = new RedisDTO();
		}

		redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입
		redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		// 결과값 받는 DTO
		RedisDTO rDTO = null;

		// 회원번호
		String user_no = CmmUtil.nvl(pDTO.getUser_no());

		log.info("user_no : " + user_no);
		
		int res = 1;
		
		// Key가 존재한다면.... 즉 데이터가 1개이상이 존재한다면 rDTO에 로그인 여부를 넣는다.
		if (redisDB.hasKey(ICommCont.redisKey)) {
			log.info("Redis Exists : " + ICommCont.redisKey);
			
			rDTO = (RedisDTO) redisDB.opsForHash().get(ICommCont.redisKey, user_no);
			log.info("rDTO : "+ rDTO == null );
			
			if (rDTO != null) {
				log.info("rDTO User_no : " + rDTO.getUser_no());
				res = 0; //로그인이 되어있으면 0
			}
		} /*
			 * else {
			 * 
			 * rDTO = (RedisDTO) redisDB.opsForHash().get(ICommCont.redisKey, user_no);
			 * 
			 * log.info("Redis Not Exists : " + ICommCont.redisKey);
			 * 
			 * }
			 */

		if (rDTO == null) {
			rDTO = new RedisDTO();
		}

		log.info(this.getClass().getName() + ".getSession end!");
		return res;

	}

	// 세션 유지시간 설정 분단위
	@Override
	public boolean setTimeOutMinute(String tablename, int minutes) throws Exception {
		log.info(this.getClass().getName() + ".setTimeOutMinute end!");
		log.info("getExpire : "+redisDB.getExpire(redisKey));
		return redisDB.expire(tablename, minutes, TimeUnit.MINUTES);
	}
	
	//세션 남은 시간 가져오기
	@Override
	public Long getTimeOut() throws Exception {
		
		log.info("getTimeOut end!");
		log.info("getExpire : "+redisDB.getExpire(redisKey));
		
		return redisDB.getExpire(redisKey);
	}
	
	//세션 삭제
	public int delectSession(RedisDTO pDTO) {
		
		log.info(this.getClass().getName() + ".delectSession start!");
		
		if (pDTO == null) {
			pDTO = new RedisDTO();
		}
		
		int res =0;
		// 회원번호
		String user_no = CmmUtil.nvl(pDTO.getUser_no());

		log.info("user_no : " + user_no);
		
		redisDB.opsForHash().delete(ICommCont.redisKey, user_no);
		
		res = 1;
		log.info(this.getClass().getName() + ".delectSession end!");
		return res;
	}
}
