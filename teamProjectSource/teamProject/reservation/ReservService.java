package teamProject.reservation;

import java.util.List;

public class ReservService {
	private static ReservService instance = new ReservService();
	
	public static ReservService getInstance() {
		return instance;
	}
	
	private ReservService() { }
	
	
	private ReservDAO dao = new ReservDAO();
	
	public List<CourseInfoVO> getCourseInfo(CourseInfoVO courseInfo) throws Exception {
		return dao.getCourseInfo(courseInfo);
	}
	
	public List<String>	getSeatsNo(String courseID) throws Exception {
		List<String> list = dao.getSeatsNo(courseID);
		
		return dao.getSeatsNo(courseID);
	}
	
	public int insertReservInfo(ReservInfoVO reservInfo) throws Exception {
		return dao.insertReservInfo(reservInfo);
	}
	
	public int updateSeatRemain() throws Exception {
		return dao.updateSeatRemain();
	}
	
	public int updateMileage(String reservID) throws Exception {
		return dao.updatePlusMileage(reservID);
	}
}
