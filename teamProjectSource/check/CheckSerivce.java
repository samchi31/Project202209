package teamProject.check;

import java.util.List;

public class CheckSerivce {
	CheckDAO dao = new CheckDAO();

	public List<CheckVO> printList(String memId) throws Exception {
		return dao.printList(memId);
	}

	public CheckVO printReservDetail(String typeReservId) throws Exception {
		return dao.printReservDetail(typeReservId);
	}

}