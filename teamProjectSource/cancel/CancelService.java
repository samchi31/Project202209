package teamProject.cancel;

import java.util.List;

public class CancelService {
	//예약을 취소해주기 위한 동작을 서비스해주는 것
	CancelDAO dao = new CancelDAO();
	
	public List<CancelVO> cancelRes() throws Exception {
		return dao.cancelRes();
	}

	public int updateMileage(String deleteRe) throws Exception {
		return dao.updateMileage(deleteRe);
	}
	
	public int deleteReserve(String deleteRe) throws Exception {
		return dao.deleteReserve(deleteRe);
	}

	
}
