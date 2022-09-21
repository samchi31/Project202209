package teamProject.mypage;

import java.util.List;

public class MypageController {
	private static MypageController instance = new MypageController();

	public static MypageController getInstance() {
		return instance;
	}

	private MypageController() {
	}
	MypageService service = MypageService.getInstance();

	public List<MypageVO> printMypage(String id) throws Exception {
		return service.printMypage(id);
	}

}