package teamProject.join;

public class JoinService {
	private static JoinService instance = new JoinService();
	
	public static JoinService getInstance() {
		return instance;
	}
	
	private JoinService() {
	}
	
	static JoinDAO dao = new JoinDAO();
	
	public static int insertJoin(JoinVO vo) throws Exception { // int?
		return dao.insertJoin(vo);
	}
}