package teamProject.reservation;

import java.util.ArrayList;
import java.util.List;

import teamProject.check.CheckVO;

public class ReservController {
	private static ReservController instance = new ReservController();

	public static ReservController getInstance() {
		return instance;
	}

	private ReservController() {
	}

	private ReservService service = ReservService.getInstance();

	/**
	 * country index 1. 베이징 2. 동경 3. 하노이 date index 1. 2022/09/14 2. 2022/09/15
	 * 3.2022/09/16
	 * 
	 * @param country 나라 인덱스
	 * @param date    날짜 인덱스
	 * @param num     탑승자수
	 * @return 항공편 리스트
	 * @throws Exception
	 */
	public List<CourseInfoVO> getCourseInfo(int country, int date, int num) throws Exception {
		CourseInfoVO vo = new CourseInfoVO();
		// 도착지
		switch (country) {
		case 1:
			vo.setAirportID("PEK");
			break;
		case 2:
			vo.setAirportID("NRT");
			break;
		case 3:
			vo.setAirportID("HAN");
			break;
		}
		// 날짜
		switch (date) {
		case 1:
			vo.setDepartDate("2022/09/14");
			break;
		case 2:
			vo.setDepartDate("2022/09/15");
			break;
		case 3:
			vo.setDepartDate("2022/09/16");
			break;
		}
		// 탑승자수
		vo.setNumPassengers(num);
		return service.getCourseInfo(vo);
	}

	/**
	 * 좌석 번호를 행렬 인덱스로 변환하여 리스트 반환
	 * 
	 * @param courseID
	 * @return
	 * @throws Exception
	 */
	public List<SeatNumVO> getSeatsIndex(String courseID) throws Exception {
		List<String> strlist = service.getSeatsNo(courseID);
		List<SeatNumVO> volist = new ArrayList<>();
		for (String seat : strlist) {
			volist.add(new SeatNumVO(seat));
		}

		return volist;
	}

	/**
	 * 좌석 번호 반환
	 * 
	 * @param courseID
	 * @return
	 * @throws Exception
	 */
	public List<String> getSeatsNo(String courseID) throws Exception {
		return service.getSeatsNo(courseID);
	}

	/**
	 * 예약정보 list 입력
	 * 
	 * @param passengers
	 * @return
	 * @throws Exception
	 */
	public int insertReservInfo(List<ReservInfoVO> passengers) throws Exception {
		int resultTotal = 0;
		for (int i = 0; i < passengers.size(); i++) {
			resultTotal += service.insertReservInfo(passengers.get(i));
		}
		// 비행 T 잔여좌석 update
		updateSeatRemain(passengers.get(0).getCourseID());
		return resultTotal;
	}
	
	
	/**
	 * 회원번호 받아서 마일리지 update
	 * @param reservID
	 * @return
	 * @throws Exception
	 */
	public int updateMileage(int numPassengers, String courseId, String memId) throws Exception {
		return service.updateMileage(numPassengers, courseId, memId);
	}
	
	public List<CheckVO> printConfirmReserve(String memId, String courseID) throws Exception {
		return service.printConfirmReserve(memId, courseID);
	}
	
	public int updateSeatRemain(String courseId) throws Exception {
		return service.updateSeatRemain(courseId);
	}
}
