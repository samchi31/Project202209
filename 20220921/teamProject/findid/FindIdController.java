package teamProject.findid;

public class FindIdController {
	private static FindIdController instance = new FindIdController();
	
	public static FindIdController getInstance() {
		return instance;
	}
	private FindIdController() {
		
	}
	
	FindIdService service = FindIdService.getInstance();
	
	public FindIdVO bringId(String typeName, int typeBir, String typeAccount) throws Exception {
		return service.bringId(typeName, typeBir, typeAccount);
	}
}