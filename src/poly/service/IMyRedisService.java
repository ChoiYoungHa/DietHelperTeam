package poly.service;

import poly.dto.RedisDTO;

public interface IMyRedisService {

	//세션 저장
	public int insertSession(RedisDTO pDTO) throws Exception;

	//세션 가져오기
	public int getSession(RedisDTO pDTO) throws Exception;

	Long getTimeOut() throws Exception;

	public boolean timeUpdate() throws Exception;

	public int delectSession(RedisDTO pDTO) throws Exception;
}
