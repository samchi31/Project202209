package teamProject.check;

import java.util.List;

public class CheckController {
	private static CheckController instance = new CheckController();

	public static CheckController getInstance() {
		return instance;
	}

	private CheckController() {
	}
	CheckSerivce service = CheckSerivce.getInstance();

	public List<CheckVO> printTotalList(String memId) throws Exception {
		return service.printTotalList(memId);
	}

	public List<CheckVO> printReservList(String memId) throws Exception {
		return service.printReservList(memId);
	}
	
	public CheckVO printReservDetail(String typeReservId) throws Exception {
		return service.printReservDetail(typeReservId);
	}
}