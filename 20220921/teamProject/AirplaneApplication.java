package teamProject;

import teamProject.utils.ASCIIArt;

public class AirplaneApplication {
	public static void main(String[] args) throws Exception {
		AirplaneView airplaneView = new AirplaneView();
		AirplaneApplication airplaneApplication = new AirplaneApplication();

//		airplaneView.printAllArt();
		ASCIIArt.airplaneArt();
		while (true) {
			// 첫 화면
			switch (airplaneView.welcome()) {
			case 1:
				// login
				airplaneApplication.applicationMainMenu(airplaneView);
				break;
			case 2:
				// 회원가입
				ASCIIArt.join();
				airplaneView.join();
				break;
			case 3:
				// 아이디찾기
				ASCIIArt.findId();
				airplaneView.FindId();
				break;
			case 4:
				// 비밀번호찾기
				ASCIIArt.findPassword();
				airplaneView.FindPassword();
				break;
			case 0:
				// 종료
				ASCIIArt.goodByeArt();
				return;
			}
		}

	}
	
	public void applicationMainMenu(AirplaneView airplaneView) throws Exception {
		// 로그인 성공
		if (airplaneView.login()) {
			// 메뉴
			ASCIIArt.menuArt();
			switch (airplaneView.menu()) {
			case 1:
				// 예약
				ASCIIArt.reservArt();
				airplaneView.reservation();
				break;
			case 2:
				// 예약취소
				ASCIIArt.cancelArt();
				airplaneView.cancel();
				break;
			case 3:
				// 예약확인
				ASCIIArt.checkArt();
				airplaneView.check();
				break;
			case 4:
				// 로그아웃
				ASCIIArt.logout();
				airplaneView.setLogin(false);
				break;
			case 5:
				// 회원정보 확인
				airplaneView.printMypage();
				break;
			case 0:
				// 프로그램 종료
				ASCIIArt.goodByeArt();
				System.out.println("종료되었습니다.");
				return;
			}
		}
	}
}
