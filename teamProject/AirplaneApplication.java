package teamProject;

public class AirplaneApplication {
	public static void main(String[] args) {
		AirplaneView airplaneView = new AirplaneView();
		
		
		while(true) {
			//로그인
			if(airplaneView.login()) {
				//로그인 실패시 재로그인 화면으로
				continue;
			} 
			
			//로그인 성공
			//메뉴
			switch (airplaneView.menu()) {
			case 1:
				//예약
				airplaneView.reservation();
				break;
			case 2:
				//예약취소
				break;
			case 3:
				//예약확인
				break;
			case 0:
				//프로그램 종료
				return;
			}
		}
	}
}
