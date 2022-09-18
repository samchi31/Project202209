package teamProject.login;

public class LoginController {
	private static LoginController instance = new LoginController();
	public static LoginController getInstance() {
		return instance;
	}
	private LoginController() {}
	

	private LoginService service = LoginService.getInstance();

	public boolean isLoginSuccess(String id, String passwd) throws Exception {
		LoginVO vo = service.getLoginInfo(id, passwd);
		if(vo == null) {
			return false;
		}
		if (vo.getId().equals(id) && vo.getPasswd().equals(passwd)) {
			return true;
		}
		return false;
	}
}
