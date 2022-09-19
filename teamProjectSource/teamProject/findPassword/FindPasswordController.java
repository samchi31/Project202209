package teamProject.findPassword;

import teamProject.findid.FindIdController;
import teamProject.findid.FindIdService;
import teamProject.findid.FindIdVO;

public class FindPasswordController {
	private static FindPasswordController instance = new FindPasswordController();
	
	public static FindPasswordController getInstance() {
		return instance;
	}
	private FindPasswordController() {
		
	}
	
	FindPasswordService service = FindPasswordService.getInstance();
	
	public FindPasswordVO bringPassword(String typeId, String typeName, int typeBir, String typeAccount) throws Exception {
		return service.bringPassword(typeId, typeName, typeBir, typeAccount);
	}
}