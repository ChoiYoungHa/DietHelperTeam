package poly.persistance.mapper;


import config.Mapper;
import poly.dto.DietDTO;
import poly.dto.FoodDTO;
import poly.dto.MapDTO;

@Mapper("DietMapper")
public interface IDietMapper {

	
	//설정된 기초대사량
	DietDTO myMetabolism(DietDTO pDTO);
	//기초대사량 입력 및 수정
	int metabolismPost(DietDTO pDTO);
	//각 메뉴 가져오기
	//고구마, 닭가슴살, 아몬드 메뉴 가져오기
	FoodDTO getgodack();
	//서비스단에서 목표칼로리 가져오기
	DietDTO getmenugoalkcal(FoodDTO fDTO);
	// 잡곡밥, 홍두께살, 올리브유 메뉴 가져오기
	FoodDTO getjobhong();
	// 통밀빵, 훈제연어, 땅콩버터 메뉴 가져오기
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
