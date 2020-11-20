package poly.dto;

public class FoodDTO {
	
	// 목표칼로리를 가져오기 위한 key
	private String user_no;
	
	//초기 음식이름
	private int food_no;
	private String food_name;
	private int food_gram;
	private float food_kcal;
	
	//아침 점심 저녁의 식단이름
	private String bf_food;
	private String lf_food;
	private String df_food;
	
	
	//가져올 탄단지 음식
	//탄수화물
	private String tan_name;
	private int tan_gram;
	private float tan_kcal;
	
	//단백질
	private String dan_name;
	private int dan_gram;
	private float dan_kcal;
	
	//지방
	private String fat_name;
	private int fat_gram;
	private float fat_kcal;
	
	//전달 parameter
	private String result_tan;
	private String result_dan;
	private String result_fat;
	
	//kcal 전달
	private float today_tan;
	private float today_protein;
	private float today_fat;
	private String today_kcal;
	
	
	
	
	public String getToday_kcal() {
		return today_kcal;
	}
	public void setToday_kcal(String today_kcal) {
		this.today_kcal = today_kcal;
	}
	public float getToday_tan() {
		return today_tan;
	}
	public void setToday_tan(float today_tan) {
		this.today_tan = today_tan;
	}
	public float getToday_protein() {
		return today_protein;
	}
	public void setToday_protein(float today_protein) {
		this.today_protein = today_protein;
	}
	public float getToday_fat() {
		return today_fat;
	}
	public void setToday_fat(float today_fat) {
		this.today_fat = today_fat;
	}
	public String getResult_tan() {
		return result_tan;
	}
	public void setResult_tan(String result_tan) {
		this.result_tan = result_tan;
	}
	public String getResult_dan() {
		return result_dan;
	}
	public void setResult_dan(String result_dan) {
		this.result_dan = result_dan;
	}
	public String getResult_fat() {
		return result_fat;
	}
	public void setResult_fat(String result_fat) {
		this.result_fat = result_fat;
	}
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public int getFood_no() {
		return food_no;
	}
	public void setFood_no(int food_no) {
		this.food_no = food_no;
	}
	public int getTan_gram() {
		return tan_gram;
	}
	public void setTan_gram(int tan_gram) {
		this.tan_gram = tan_gram;
	}
	public float getTan_kcal() {
		return tan_kcal;
	}
	public void setTan_kcal(float tan_kcal) {
		this.tan_kcal = tan_kcal;
	}
	public int getDan_gram() {
		return dan_gram;
	}
	public void setDan_gram(int dan_gram) {
		this.dan_gram = dan_gram;
	}
	public float getDan_kcal() {
		return dan_kcal;
	}
	public void setDan_kcal(float dan_kcal) {
		this.dan_kcal = dan_kcal;
	}
	public int getFat_gram() {
		return fat_gram;
	}
	public void setFat_gram(int fat_gram) {
		this.fat_gram = fat_gram;
	}
	public float getFat_kcal() {
		return fat_kcal;
	}
	public void setFat_kcal(float fat_kcal) {
		this.fat_kcal = fat_kcal;
	}
	public int getFood_gram() {
		return food_gram;
	}
	public void setFood_gram(int food_gram) {
		this.food_gram = food_gram;
	}
	public float getFood_kcal() {
		return food_kcal;
	}
	public void setFood_kcal(float food_kcal) {
		this.food_kcal = food_kcal;
	}
	
	public String getTan_name() {
		return tan_name;
	}
	public void setTan_name(String tan_name) {
		this.tan_name = tan_name;
	}
	
	public String getDan_name() {
		return dan_name;
	}
	public void setDan_name(String dan_name) {
		this.dan_name = dan_name;
	}
	
	public String getFat_name() {
		return fat_name;
	}
	public void setFat_name(String fat_name) {
		this.fat_name = fat_name;
	}
	
	public String getBf_food() {
		return bf_food;
	}
	public void setBf_food(String bf_food) {
		this.bf_food = bf_food;
	}
	public String getLf_food() {
		return lf_food;
	}
	public void setLf_food(String lf_food) {
		this.lf_food = lf_food;
	}
	public String getDf_food() {
		return df_food;
	}
	public void setDf_food(String df_food) {
		this.df_food = df_food;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	

}
