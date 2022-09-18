package teamProject;

public class AirplaneApplication {
	public static void main(String[] args) throws Exception {
		AirplaneView airplaneView = new AirplaneView();

		while (true) {
			// 로그인

			// 로그인 성공
			if (airplaneView.login()) {
				// 메뉴
				switch (airplaneView.menu()) {
				case 1:
					// 예약
					airplaneView.reservation();
					break;
				case 2:
					// 예약취소
					airplaneView.cancel();
					break;
				case 3:
					// 예약확인
					airplaneView.check();
					break;
				case 4:
					// 로그아웃
					airplaneView.setLogin(false);
					break;
				case 0:
					// 프로그램 종료
					return;
				}
			}
		}

	}
}
