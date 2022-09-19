package teamProject.findPassword;

import teamProject.findid.FindIdDAO;
import teamProject.findid.FindIdService;
import teamProject.findid.FindIdVO;

public class FindPasswordService {
	private static FindPasswordService instance = new FindPasswordService();
	
	public static FindPasswordService getInstance() {
		return instance;
	}
	private FindPasswordService() {
		
	}
	
	FindPasswordDAO dao = new FindPasswordDAO();
	
	public FindPasswordVO bringPassword(String typeId, String typeName, int typeBir, String typeAccount) throws Exception {
		return dao.bringPassword(typeId, typeName, typeBir, typeAccount);
	}
}