package teamProject.check;

import java.util.List;

public class CheckController {
	CheckSerivce service = new CheckSerivce();

	public List<CheckVO> printList(String memId) throws Exception {
		return service.printList(memId);
	}

	public CheckVO printReservDetail(String typeReservId) throws Exception {
		return service.printReservDetail(typeReservId);
	}
}