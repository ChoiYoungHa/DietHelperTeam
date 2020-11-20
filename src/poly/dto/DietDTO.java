package poly.dto;

public class DietDTO {
	//user_info
	private String email; //이메일
	private String password; //비밀번호
	private String user_an; //닉네임
	private String user_name;  //이름
	
	//food_info
	private String food_name; //음식이름
	private String gram;  //그램
	private String food_kcal; //칼로리
	
	//foodlist_info
	private String foodlist_no; //식단번호
	
	//body_info
	private String keep_kcal; //유지칼로리(현칼로리)
	private String activity; //활동량
	private String basic;  //기초대사량
	private String weight; //몸무게
	private String tall; //키
	private String age; //나이
	private String how_exer;  //운동목적
	private String sex;  //성별
	
	//foodlist_info,body_info
	private String goal_kcal; //목표칼로리(식단전체칼로리)
	
	//food_info,foodlist_info
	private String carbo; //탄수화물
	private String fat; //지방
	private String protein; //단백질
	
	//user_info,food_info,body_info
	private String user_no; //유저번호
	
	//food_info,foodlist_info
	private String food_no; //음식번호
	
	//
	private float today_tan; //오늘 탄수화물
	private float today_protein; //오늘 단백질
	private float today_fat; //오늘 지방
	private float today_kcal; // 오늘 한끼 칼로리
	
	//all
	private String reg_id; //작성자
	private String chd_id; //수정사
	private String reg_dt; //작성일
	private String chg_dt; //수정일
	
	
	
	public float getToday_kcal() {
		return today_kcal;
	}
	public void setToday_kcal(float today_kcal) {
		this.today_kcal = today_kcal;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUser_an() {
		return user_an;
	}
	public void setUser_an(String user_an) {
		this.user_an = user_an;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getHow_exer() {
		return how_exer;
	}
	public void setHow_exer(String how_exer) {
		this.how_exer = how_exer;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public String getGram() {
		return gram;
	}
	public void setGram(String gram) {
		this.gram = gram;
	}
	public String getFood_kcal() {
		return food_kcal;
	}
	public void setFood_kcal(String food_kcal) {
		this.food_kcal = food_kcal;
	}
	public String getFoodlist_no() {
		return foodlist_no;
	}
	public void setFoodlist_no(String foodlist_no) {
		this.foodlist_no = foodlist_no;
	}
	public String getKeep_kcal() {
		return keep_kcal;
	}
	public void setKeep_kcal(String keep_kcal) {
		this.keep_kcal = keep_kcal;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getBasic() {
		return basic;
	}
	public void setBasic(String basic) {
		this.basic = basic;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getTall() {
		return tall;
	}
	public void setTall(String tall) {
		this.tall = tall;
	}
	public String getGoal_kcal() {
		return goal_kcal;
	}
	public void setGoal_kcal(String goal_kcal) {
		this.goal_kcal = goal_kcal;
	}
	public String getCarbo() {
		return carbo;
	}
	public void setCarbo(String carbo) {
		this.carbo = carbo;
	}
	public String getFat() {
		return fat;
	}
	public void setFat(String fat) {
		this.fat = fat;
	}
	public String getProtein() {
		return protein;
	}
	public void setProtein(String protein) {
		this.protein = protein;
	}
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public String getFood_no() {
		return food_no;
	}
	public void setFood_no(String food_no) {
		this.food_no = food_no;
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
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getChd_id() {
		return chd_id;
	}
	public void setChd_id(String chd_id) {
		this.chd_id = chd_id;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getChg_dt() {
		return chg_dt;
	}
	public void setChg_dt(String chg_dt) {
		this.chg_dt = chg_dt;
	}
	
	
}
