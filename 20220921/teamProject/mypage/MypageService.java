package teamProject.mypage;

import java.util.List;

public class MypageService {
	private static MypageService instance = new MypageService();
	
	public static MypageService getInstance() {
		return instance;
	}
	
	private MypageService() {
	}
	
	MypageDAO dao = new MypageDAO();
	
	public List<MypageVO> printMypage(String id) throws Exception{
		return dao.printMypage(id);
	}

}
