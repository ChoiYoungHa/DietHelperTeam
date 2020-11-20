package poly.persistance.redis;

import poly.dto.RedisDTO;

public interface IMyRedisMapper {

	// 세션 저장
	public int insertSession(RedisDTO pDTO) throws Exception;

	// 세션 가져오기
	public int getSession(RedisDTO pDTO) throws Exception;

	// 데이터 저장 유효시간을 분 단위로 설정
	public boolean setTimeOutMinute(String SessionKey, int minutes) throws Exception;

	// 데이터 저장 유효시간 가져오기
	Long getTimeOut() throws Exception;

	public int delectSession(RedisDTO pDTO) throws Exception;
}
