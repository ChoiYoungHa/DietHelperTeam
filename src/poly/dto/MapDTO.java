package poly.dto;

public class MapDTO {
	private String user_no; //유저번호
	private String addr2;  //자치구
	
	private String map_name; //시설명
	private String map_pointx; //시설좌표_경도
	private String map_pointy; // 시설좌표_위도
	
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getMap_name() {
		return map_name;
	}
	public void setMap_name(String map_name) {
		this.map_name = map_name;
	}
	public String getMap_pointx() {
		return map_pointx;
	}
	public void setMap_pointx(String map_pointx) {
		this.map_pointx = map_pointx;
	}
	public String getMap_pointy() {
		return map_pointy;
	}
	public void setMap_pointy(String map_pointy) {
		this.map_pointy = map_pointy;
	}

	
}
