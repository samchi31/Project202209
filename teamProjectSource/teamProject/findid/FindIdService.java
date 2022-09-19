package teamProject.findid;

public class FindIdService {
	private static FindIdService instance = new FindIdService();
	
	public static FindIdService getInstance() {
		return instance;
	}
	private FindIdService() {
		
	}
	
	FindIdDAO dao = new FindIdDAO();
	
	public FindIdVO bringId(String typeName, int typeBir, String typeAccount) throws Exception {
		return dao.bringId(typeName, typeBir, typeAccount);
	}
}