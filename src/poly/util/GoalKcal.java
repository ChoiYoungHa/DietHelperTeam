package poly.util;

import org.apache.log4j.Logger;

import poly.dto.DietDTO;

public class GoalKcal {
	
	public DietDTO getgetgoalk(DietDTO pDTO) {
		Logger log = Logger.getLogger(this.getClass());
		
		//목표칼로리 잘 가져왔는지 확인
		log.info("user goal_kcal : " +pDTO.getGoal_kcal());
		
		//목표칼로리 잘 가져왔으니, 저장
		String goal_kcal = CmmUtil.nvl(pDTO.getGoal_kcal());
		
		//계산을 위한 목표칼로리 형변환
		float rgoal_kcal = Float.parseFloat(goal_kcal);
		
		//한끼 칼로리
		float today_kcal = rgoal_kcal/3;
		log.info("한끼 칼로리 : " + today_kcal);
		
		//탄단지 비율 계산
		float today_tan = (float) (today_kcal * 0.5);
		log.info("탄수화물 비율 50% : " + today_tan);
		float today_protein = (float) (today_kcal * 0.3);
		log.info("단백질 비율 30% : " + today_protein);
		float today_fat = (float) (today_kcal * 0.2);
		log.info("지방 비율 20% : " + today_fat);
		
		//한끼 탄수화물, 단백질, 지방, 칼로리 세팅
		pDTO.setToday_tan(today_tan);
		pDTO.setToday_protein(today_protein);
		pDTO.setToday_fat(today_fat);
		pDTO.setToday_kcal(today_kcal);

		return pDTO;
		
		
	}

}
