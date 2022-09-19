package teamProject.join;

public class JoinController {
	public static JoinController instance = new JoinController();
	
	public static JoinController getInstance() {
		return instance;
	}
	
	private JoinController() {
	}
	
	static JoinService service = JoinService.getInstance();
	
	public static int insertJoin(JoinVO vo) throws Exception { // int?
		return service.insertJoin(vo);
	}
}
