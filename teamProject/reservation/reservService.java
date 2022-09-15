package teamProject.reservation;

public class reservService {
	reservDAO dao = new reservDAO();
	
	public int insertReservInfo(ReservInfo reservinfo) throws Exception {
		return dao.insertReservInfo(reservinfo);
	}
}
