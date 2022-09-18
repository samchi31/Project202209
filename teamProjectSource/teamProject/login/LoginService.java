package teamProject.login;

public class LoginService {
	private static LoginService instance = new LoginService();
	
	public static LoginService getInstance() {
		return instance;
	}
	
	private LoginService() {
		
	}
	
	private LoginDAO dao = new LoginDAO();
	
	public LoginVO getLoginInfo(String id, String passwd) throws Exception {
		return dao.getLoginInfo(id, passwd);
	}
}
