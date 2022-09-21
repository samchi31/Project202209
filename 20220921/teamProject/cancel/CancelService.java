package teamProject.cancel;

import java.util.List;

public class CancelService {
	private static CancelService instance = new CancelService();
	private CancelService() { }
	public static CancelService getInstance() {
		return instance;
	}
	
	//예약을 취소해주기 위한 동작을 서비스해주는 것
	CancelDAO dao = new CancelDAO();
	

	public int updateMileage(String deleteRe) throws Exception {
		return dao.updateMileage(deleteRe);
	}
	
	public int updateReserveList(String deleteRe) throws Exception {
		return dao.updateReserveList(deleteRe);
	}

	public String getCourseID(String reservID) throws Exception {
		return dao.getCourseID(reservID);
	}
}
