package teamProject.cancel;


public class CancelVO {
	private String reservId;
	private String memId;
	private String passName;
	private String passPhone;
	private String passReg;
	private String seatNo;
	private String depLocation;
	private String depDate;
	private String depTime;
	private String airportId;
	private String arrTime;
	private String airline;
	private String airplaneId;
	private int price;
	private int account;
	private String bank;
	private int mileage;

	public CancelVO(String reservId, String passName, String seatNo, String depLocation, String depDate, String depTime,
			String airportId, String arrTime, String airline, String airplaneId) {
		this.reservId = reservId;
		this.passName = passName;
		this.seatNo = seatNo;
		this.depLocation = depLocation;
		this.depDate = depDate;
		this.depTime = depTime;
		this.airportId = airportId;
		this.arrTime = arrTime;
		this.airline = airline;
		this.airplaneId = airplaneId;
	}

	public CancelVO(String reservId, String memId, String passName, String passPhone, String passReg, String seatNo,
			String depLocation, String depDate, String depTime, String airportId, String arrTime, String airline, String airplaneId,
			int price, int account, String bank, int mileage) {
		this.reservId = reservId;
		this.memId = memId;
		this.passName = passName;
		this.passPhone = passPhone;
		this.passReg = passReg;
		this.seatNo = seatNo;
		this.depLocation = depLocation;
		this.depDate = depDate;
		this.depTime = depTime;
		this.airportId = airportId;
		this.arrTime = arrTime;
		this.airline = airline;
		this.airplaneId = airplaneId;
		this.price = price;
		this.account = account;
		this.bank = bank;
		this.mileage = mileage;
	}

	public String getReservId() {
		return reservId;
	}

	public void setReservId(String reservId) {
		this.reservId = reservId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getPassName() {
		return passName;
	}

	public void setPassName(String passName) {
		this.passName = passName;
	}

	public String getPassPhone() {
		return passPhone;
	}

	public void setPassPhone(String passPhone) {
		this.passPhone = passPhone;
	}

	public String getPassReg() {
		return passReg;
	}

	public void setPassReg(String passReg) {
		this.passReg = passReg;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getDepLocation() {
		return depLocation;
	}

	public void setDepLocation(String depLocation) {
		this.depLocation = depLocation;
	}

	public String getDepDate() {
		return depDate;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getAirportId() {
		return airportId;
	}

	public void setAirportId(String airportId) {
		this.airportId = airportId;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getAirplaneId() {
		return airplaneId;
	}

	public void setAirplaneId(String airplaneId) {
		this.airplaneId = airplaneId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	
}
