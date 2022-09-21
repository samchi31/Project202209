package teamProject.reservation;

public class ReservInfoVO {
	//private String reservID; 	//RESERV_ID
	private String memID;		//MEM_ID
	private String courseID;	//COURSE_ID
	private String seatNO;		//SEAT_NO
	private String name;		//PASS_NAME
	private String phone;		//PASS_PHONE
	private String reg;			//PASS_REG
	private String cancel = "N";	//CANCEL
	public ReservInfoVO(String memID, String courseID, String seatNO, String name, String phone, String reg) {
		this.memID = memID;
		this.courseID = courseID;
		this.seatNO = seatNO;
		this.name = name;
		this.phone = phone;
		this.reg = reg;
	}
	public String getMemID() {
		return memID;
	}
	public void setMemID(String memID) {
		this.memID = memID;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getSeatNO() {
		return seatNO;
	}
	public void setSeatNO(String seatNO) {
		this.seatNO = seatNO;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public String getCancel() {
		return cancel;
	}
	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
	
}
