package teamProject.check;

import java.util.List;

public class CheckController {
	private static CheckController instance = new CheckController();
	private CheckController() { }
	public static CheckController getInstance() {
		return instance;
	}
	CheckSerivce service = CheckSerivce.getInstance();

	public List<CheckVO> printList(String memId) throws Exception {
		return service.printList(memId);
	}

	public CheckVO printReservDetail(String typeReservId) throws Exception {
		return service.printReservDetail(typeReservId);
	}
}