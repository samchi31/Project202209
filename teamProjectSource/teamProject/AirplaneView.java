package teamProject;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import teamProject.cancel.CancelController;
import teamProject.check.CheckController;
import teamProject.check.CheckVO;
import teamProject.login.LoginController;
import teamProject.reservation.CourseInfoVO;
import teamProject.reservation.ReservController;
import teamProject.reservation.ReservInfoVO;
import teamProject.reservation.SeatNumVO;
import teamProject.utils.ScanUtil;

public class AirplaneView {
	private LoginController loginController = LoginController.getInstance();
	private ReservController reservController = ReservController.getInstance();
	private CheckController checkController = CheckController.getInstance();
	private CancelController cancelController = CancelController.getInstance();

	private String id;
	private String passwd;
	private boolean isLogin = false; // 로그인한 상태인지

	/**
	 * 로그인 화면 scanner로 로그인 정보 받음 db에 저장된 로그인 정보랑 비교해서 로그인 성공 여부 리턴
	 * 
	 * @return 로그인 성공 여부
	 * @throws Exception
	 */
	public boolean login() throws Exception {
//		if (!isLogin) {
//			System.out.println(">>>>>>>>>로그인<<<<<<<<<");
//			System.out.print("아이디 > ");
//			id = ScanUtil.nextLine();
//			System.out.print("비밀번호 > ");
//			passwd = ScanUtil.nextLine();
//			if (loginController.isLoginSuccess(id, passwd)) {
//				System.out.println("로그인 성공");
//				isLogin = true;
//				return true;
//			} else {
//				System.out.println("로그인 실패");
//				isLogin = false;
//				return false;
//			}
//		}
		return true;
	}

	/**
	 * 메인메뉴
	 * 
	 * @return 선택한 메뉴번호
	 */
	public int menu() {
		System.out.println(">>>>>>>>>메뉴<<<<<<<<<");
		System.out.println("| 1. 예약 | 2. 예약취소 | 3. 예약확인 | 4. 로그아웃 | 0. 종료 |");
		System.out.print("메뉴 선택 >> ");
		return ScanUtil.nextInt();
	}

	/**
	 * 로그인 상태 제어
	 * 
	 * @param isLogin true : 로그인 중 , false : 로그아웃
	 */
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	/**
	 * 예약 진행
	 * 
	 * @throws Exception
	 */
	public void reservation() throws Exception {
		System.out.println(">>>>>>>>>예약<<<<<<<<<");

		System.out.println(">>>>>>>>>행선지선택<<<<<<<<<");
		int country = 0;
		while (true) {
			try {
				System.out.println("1. 베이징");
				System.out.println("2. 동경");
				System.out.println("3. 하노이");
				System.out.print("도착지 선택 >> ");
				country = ScanUtil.nextInt();
				if (country == 1 || country == 2 || country == 3) {
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요");
				}
			} catch (InputMismatchException ie) {
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
				break;
			}
		}

		System.out.println(">>>>>>>>>날짜선택<<<<<<<<<");
		int date = 0;
		while (true) {
			try {
				System.out.println("1. 2022/09/14");
				System.out.println("2. 2022/09/15");
				System.out.println("3. 2022/09/16");
				System.out.print("날짜 선택 >> ");
				date = ScanUtil.nextInt();
				if (date == 1 || date == 2 || date == 3) {
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요");
				}
			} catch (InputMismatchException ie) {
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
				break;
			}
		}

		System.out.println(">>>>>>>>>탑승객 수 입력<<<<<<<<<");
		int num = 0;
		while (true) {
			try {
				System.out.print(" >> ");
				num = ScanUtil.nextInt();
				break;
			} catch (InputMismatchException ie) {
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
				break;
			}
		}

		// 항공편 리스트 출력
		System.out.println(">>>>>>>>>항공편 선택 <<<<<<<<<");
		printCourseList(country, date, num);
		String courseID = "";
		while (true) {
			try {
				System.out.print("항공편ID  입력 >> ");
				courseID = ScanUtil.nextLine();
				if (courseID.matches("^P[0-9]{7}") && existCourseID(country, date, num, courseID)) {
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요");
				}
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
				break;
			}
		}

		// 탑승자 수만큼
		List<ReservInfoVO> passengers = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			// 좌석 리스트 출력
			System.out.println(">>>>>>>>>좌석 선택<<<<<<<<<");
			printSeats(courseID);
			System.out.print("좌석번호(A1) 입력 >> ");
			String seatNum = ScanUtil.nextLine();
			if (!seatNum.matches("^[A-D][1-6]$")) {
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
				i--;
				continue;
			}

			// 좌석 번호 중복확인
			if (duplicateSeat(seatNum, courseID)) {
				System.out.println("이미 선택된 좌석입니다. 다시 선택해주세요");
				i--;
				continue;
			}

			System.out.println(">>>>>>>>>탑승자 정보입력<<<<<<<<<");
			System.out.printf("탑승자%d 이름 입력 >> ", i + 1);
			String name = ScanUtil.nextLine();
			System.out.printf("탑승자%d 전화번호(하이픈생략) 입력 >> ", i + 1);
			String call = ScanUtil.nextLine();
			System.out.printf("탑승자%d 생년월일(yymmdd) 입력 >> ", i + 1);
			String birth = ScanUtil.nextLine();

			passengers.add(new ReservInfoVO(id, courseID, seatNum, name, call, birth));
		}
		// db 에 저장
		if (reservController.insertReservInfo(passengers) > 0) {
			System.out.println("예약이 완료되었습니다.");
		} else {
			System.out.println("예약에 실패했습니다.");
		}

		// 예약 내역 출력
		printReservList();
		// 마일리지 update
		reservController.updateMileage(id);
	}

	/**
	 * 항공편 리스트 표로 출력
	 * 
	 * @param country 도착지
	 * @param date    날짜
	 * @param num     탑승수
	 * @throws Exception
	 */
	public void printCourseList(int country, int date, int num) throws Exception {
		List<CourseInfoVO> list = reservController.getCourseInfo(country, date, num);
		System.out.println("| 항공편ID | 출발지 |  출발일자  | 출발시간 | 도착지 | 도착시간 | 잔여 좌석 |    항공사   |  거리  |   금액   |");
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("| %s |  %s   | %s |  %s  |  %s  |  %s  |    %d   | %s | %s | %d |\n",
					list.get(i).getCourseId(), list.get(i).getDeparture(), list.get(i).getDepartDate(),
					list.get(i).getDepartTime(), list.get(i).getAirportID(), list.get(i).getArrivalTime(),
					list.get(i).getRemainSeat(), list.get(i).getAirline(), list.get(i).getDistance(),
					list.get(i).getPrice());
		}
	}

	/**
	 * 입력받은 courseID가 있는지 확인
	 * 
	 * @param country
	 * @param date
	 * @param num
	 * @param courseID 입력받은
	 * @return 있으면 true
	 * @throws Exception
	 */
	public boolean existCourseID(int country, int date, int num, String courseID) throws Exception {
		boolean isExist = false;
		List<CourseInfoVO> list = reservController.getCourseInfo(country, date, num);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCourseId().equals(courseID)) {
				isExist = true;
			}
		}
		return isExist;
	}

	/**
	 * 잔여 좌석 출력
	 * 
	 * @throws Exception
	 */
	public void printSeats(String courseID) throws Exception {
		String[][] seats = new String[6][4];
		List<SeatNumVO> vo = reservController.getSeatsIndex(courseID);
		resetSeats(seats);

		for (int k = 0; k < vo.size(); k++) {
			seats[vo.get(k).getRow()][vo.get(k).getCol()] = "■";
		}
		System.out.printf("\n-----------------------------------------------\n");
		System.out.printf("\t    A B C D\n");
		for (int i = 0; i < seats.length; i++) {
			System.out.printf("\t%d : ", i);
			for (int j = 0; j < seats[i].length; j++) {
				System.out.print(seats[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.printf("\n-----------------------------------------------\n");
	}

	/**
	 * 좌석 초기화
	 * 
	 * @param seats
	 */
	public void resetSeats(String[][] seats) {
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				seats[i][j] = "□";
			}
		}
	}

	/**
	 * 좌석 중복 확인
	 * 
	 * @param seat
	 * @param courseID
	 * @return 중복이면 true
	 * @throws Exception
	 */
	public boolean duplicateSeat(String seat, String courseID) throws Exception {
		List<String> seats = reservController.getSeatsNo(courseID);
		for (String seatNo : seats) {
			if (seat.equals(seatNo)) {
				return true;
			}
		}
		return false;
	}

	// -------------------------------------------------------------------------------------------------------------------------

	/**
	 * 취소 진행
	 * 
	 * @throws Exception
	 */
	public void cancel() throws Exception {
		pringCancelList();
		getCancelNumber();
	}

	public void pringCancelList() throws Exception {
		// 예약목록 확인
		System.out.println("예약취소<<<<<<<<<");
		System.out.println(">예약목록<");
		printReservList();
	}

	/**
	 * 예약번호로 취소하기
	 * 
	 * @throws Exception
	 */
	public void getCancelNumber() throws Exception {
		// 취소할 예약번호 받기
		System.out.print("취소할 예약번호를 입력해주세요 ");
		String reservID = ScanUtil.nextLine();
		// 마일리지 차감
		cancelController.updateMileage(reservID);
		// 예약내역 취소
		if (cancelController.updateReserveList(reservID) > 0) {
			System.out.println("예약을 취소합니다");
		} else {
			System.out.println("예약취소를 실패했습니다");
		}
		// 비행기 T 잔여좌석 update
		reservController.updateSeatRemain();
	}

	// 목록
	// 확인--------------------------------------------------------------------------------------------------------------------------//
	/**
	 * 예약 목록 menu()에서 3번을 받으면 회원ID에 따른 예약 목록을 보여준다. 거기서 예약의 세부정보, 메인 메뉴(menu()), 종료로
	 * 나뉘고 세부 정보로 들어가면 예약 목록, 메인 메뉴(menu()), 종료로 나뉜다.
	 * 
	 * @throws Exception
	 */
	public void check() throws Exception {
		while (true) {
			//printTotalList();
			switch (selectChoice1()) {
			case 1:
				//printReservDetail();
				switch (selectChoice2()) {
				case 1:
					continue;// 위에 printTotalList();로 돌아가기
				case 2:
					menu();
					break;
				case 3:
					System.out.println("종료되었습니다."); // 나중에 종료 메소도 만들어지는 것 넣기
					return;
				}
			case 2:
				menu();
				break;
			case 3:
				System.out.println("종료되었습니다."); // 나중에 종료 메소드 만들어지는 것 넣기
				return;
			}
		}

//		// printTotalList();
//		switch (selectChoice1()) {
//		case 1:
//			// printReservDetail();
//			break;
//		case 2:
//			break;
//		case 3:
//			System.out.println("종료되었습니다.");
//			System.exit(0);
//			break;
//		}
	}

	/**
	 * 회원 아이디를 토대로 나오는 예약 목록(간이) 10개
	 * 
	 * @param controller
	 * @throws Exception
	 */
	public void printTotalList() throws Exception {
		List<CheckVO> list = checkController.printTotalList(id);
		System.out.println(
				"| 취소여부 |     예약번호    | 탑승자 |   항공편  | 좌석번호 | 출발지  |    출발일   | 출발시간 |  도착지 | 도착시간 |    항공사   | 비행기 번호 |");
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("|    %s   | %s | %s | %s |   %s   |  %s  | %s |  %s |  %s  |  %s | %s |   %s   |\n",
					list.get(i).getCancel(), list.get(i).getReservId(), list.get(i).getPassName(),
					list.get(i).getCourseId(), list.get(i).getSeatNo(), list.get(i).getDepLocation(),
					list.get(i).getDepDate(), list.get(i).getDepTime(), list.get(i).getAirportId(),
					list.get(i).getArrTime(), list.get(i).getAirline(), list.get(i).getAirplaneId());
		}
	}

	/**
	 * 회원 아이디를 토대로 나오는 예약 목록(간이) 10개
	 * 
	 * @param controller
	 * @throws Exception
	 */
	public void printReservList() throws Exception {
		List<CheckVO> list = checkController.printReservList(id);
		System.out.println(
				"| 취소여부 |     예약번호    | 탑승자 |   항공편  | 좌석번호 | 출발지  |    출발일   | 출발시간 |  도착지 | 도착시간 |    항공사   | 비행기 번호 |");
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("|    %s   | %s | %s | %s |   %s   |  %s  | %s |  %s |  %s  |  %s | %s |   %s   |\n",
					list.get(i).getCancel(), list.get(i).getReservId(), list.get(i).getPassName(),
					list.get(i).getCourseId(), list.get(i).getSeatNo(), list.get(i).getDepLocation(),
					list.get(i).getDepDate(), list.get(i).getDepTime(), list.get(i).getAirportId(),
					list.get(i).getArrTime(), list.get(i).getAirline(), list.get(i).getAirplaneId());
		}
	}

	/**
	 * 예약 목록에 들어가면 목록과 함께 어떤 행동을 할지 정하게 한다. 세부내역 보기, 메인 메뉴로 돌아가기, 종료하기
	 * 
	 * @return 1, 2, 3
	 */
	public int selectChoice1() {
		System.out.println("1. 세부내역 | 2. 메인 메뉴 | 3. 종료");
		System.out.print("메뉴 선택 >> ");
		return ScanUtil.nextInt();
	}

	/**
	 * 세부정보로 들어가면 예약내역과 함께 어떤 행동을 할지 정하게 한다. 예약 목록으로 돌아가기, 메인 메뉴로 가기, 종료하기
	 * 
	 * @return 1, 2, 3
	 */
	public int selectChoice2() {
		System.out.println("1. 예약목록 | 2. 메인 메뉴 | 3. 종료");
		System.out.print("메뉴 선택 >> ");
		return ScanUtil.nextInt();
	}

	/**
	 * 예약 목록을 통해 얻은 예약번호를 통해 세부 예약 정보 출력
	 * 
	 * @param controller
	 * @param scanner
	 * @throws Exception
	 */
	public void printReservDetail() throws Exception {
		System.out.print("보고 싶은 예약번호 입력: ");
		String typeReservId = ScanUtil.nextLine();
		CheckVO vo = checkController.printReservDetail(typeReservId);
		System.out.println("-------------------------");
		System.out.printf(
				"취소여부\t: %s\n-------------------------\n예약번호\t: %s\n회원번호\t: %s\n-------------------------\n탑승자\t: %s\n전화번호\t: %s\n생년월일\t: %s\n-------------------------\n항공편\t: %s\n좌석번호\t: %s\n출발지\t: %s\n출발일\t: %s\n출발시간\t: %s\n도착지\t: %s\n도착시간\t: %s\n항공사\t: %s\n비행기번호\t: %s\n-------------------------\n가격\t: %d\n계좌번호\t: %s\n은행\t: %s\n",
				vo.getCancel(), vo.getReservId(), vo.getMemId(), vo.getPassName(), vo.getPassPhone(), vo.getPassReg(),
				vo.getCourseId(), vo.getSeatNo(), vo.getDepLocation(), vo.getDepDate(), vo.getDepTime(),
				vo.getAirportId(), vo.getArrTime(), vo.getAirline(), vo.getAirplaneId(), vo.getPrice(), vo.getAccount(),
				vo.getBank());
		System.out.println("-------------------------");
	}
}
