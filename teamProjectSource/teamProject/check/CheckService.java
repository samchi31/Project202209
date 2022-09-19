package teamProject.check;

import java.sql.SQLException;
import java.util.List;

public class CheckService {
	private static CheckService instance = new CheckService();

	public static CheckService getInstance() {
		return instance;
	}

	private CheckService() {
	}

	CheckDAO dao = new CheckDAO();

	public List<CheckVO> printTotalList(String memId) throws Exception {
		return dao.printTotalList(memId);
	}

	public List<CheckVO> printReservList(String memId) throws Exception {
		return dao.printReservList(memId);
	}

	public CheckVO printReservDetail(String typeReservId) throws Exception {
		return dao.printReservDetail(typeReservId);
	}
	
	public List<CheckVO> getReserveIdList() throws Exception{
		return dao.getReserveIdList();
	}
}