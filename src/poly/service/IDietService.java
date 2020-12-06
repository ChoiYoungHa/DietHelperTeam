package poly.service;

import java.util.List;

import poly.dto.DietDTO;
import poly.dto.FoodDTO;
import poly.dto.MapDTO;

public interface IDietService {
	
	//설정된 기초대사량
	DietDTO myMetabolism(DietDTO pDTO);
	//음식 설정
	FoodDTO getbf_food(FoodDTO fDTO);
	//지도 설정
	List<MapDTO> DietMap(MapDTO pDTO) throws Exception;
	//기초대사량 입력
	int metabolismPost(DietDTO pDTO);
	
}
