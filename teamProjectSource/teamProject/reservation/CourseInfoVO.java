package teamProject.reservation;

public class CourseInfoVO {
	private String courseId;		// 행선지 id
	private String departure;		// 출발지
	private	String departDate;		// 출발 날짜
	private	String departTime;		// 출발 시간
	private	String airportID;		// 도착지 id
	private	String arrivalTime;		// 도착 시간
	private String airplaneID;		// 비행기 id
	private	int price;				// 가격
	private	String distance;			// 거리
	private	String airline;			// 항공사
	private	int remainSeat;		// 잔여좌석
	
	private int numPassengers;		// 탑승자 수
		
	public CourseInfoVO() {
	}	

	public CourseInfoVO(String courseId, String departure, String departDate, String departTime, String airportID,
			String arrivalTime, String airplaneID, int price, String distance, String airline, int remainSeat) {
		this.courseId = courseId;
		this.departure = departure;
		this.departDate = departDate;
		this.departTime = departTime;
		this.airportID = airportID;
		this.arrivalTime = arrivalTime;
		this.airplaneID = airplaneID;
		this.price = price;
		this.distance = distance;
		this.airline = airline;
		this.remainSeat = remainSeat;
	}


	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getAirportID() {
		return airportID;
	}

	public void setAirportID(String airportID) {
		this.airportID = airportID;
	}

	public String getDepartDate() {
		return departDate;
	}

	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}

	public String getDepartTime() {
		return departTime;
	}

	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}

	public int getRemainSeat() {
		return remainSeat;
	}

	public void setRemainSeat(int remainSeat) {
		this.remainSeat = remainSeat;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumPassengers() {
		return numPassengers;
	}

	public void setNumPassengers(int numPassengers) {
		this.numPassengers = numPassengers;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getAirplaneID() {
		return airplaneID;
	}

	public void setAirplaneID(String airplaneID) {
		this.airplaneID = airplaneID;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
	
}
