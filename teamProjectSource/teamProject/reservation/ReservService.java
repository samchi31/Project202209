package teamProject.reservation;

import java.util.List;

import teamProject.check.CheckVO;

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
	
	
	public int updateMileage(int numPassengers, String courseId, String memId) throws Exception {
		return dao.updatePlusMileage(numPassengers, courseId, memId);
	}
	
	public int updateSeatRemain(String courseId) throws Exception {
		return dao.updateSeatRemain(courseId);
	}
	
	public List<CheckVO> printConfirmReserve(String memId, String courseID) throws Exception {
		return dao.printConfirmReserve(memId, courseID);
	}
}
