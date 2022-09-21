package teamProject.cancel;

import java.util.List;

public class CancelController {
	private static CancelController instance = new CancelController();
	private CancelController() { }
	public static CancelController getInstance() {
		return instance;
	}
	
   //서비스라는 객체를 생성해주고
   CancelService service = CancelService.getInstance();
   //예약정보 목록을 보여준다
   public int updateMileage(String deleteRe) throws Exception {
		return service.updateMileage(deleteRe);
	}
   
   //예약정보를 삭제해준다
   public int updateReserveList(String deleteRe) throws Exception{
      return service.updateReserveList(deleteRe);
   }
   
   public String getCourseID(String reservID) throws Exception {
		return service.getCourseID(reservID);
	}
}