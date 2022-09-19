package teamProject;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import teamProject.cancel.CancelController;
import teamProject.check.CheckController;
import teamProject.check.CheckVO;
import teamProject.findPassword.FindPasswordController;
import teamProject.findPassword.FindPasswordVO;
import teamProject.findid.FindIdController;
import teamProject.findid.FindIdVO;
import teamProject.join.JoinController;
import teamProject.join.JoinVO;
import teamProject.login.LoginController;
import teamProject.reservation.CourseInfoVO;
import teamProject.reservation.ReservController;
import teamProject.reservation.ReservInfoVO;
import teamProject.reservation.SeatNumVO;
import teamProject.utils.ASCIIArt;
import teamProject.utils.ScanUtil;

public class AirplaneView {
	private LoginController loginController = LoginController.getInstance();
	private ReservController reservController = ReservController.getInstance();
	private CheckController checkController = CheckController.getInstance();
	private CancelController cancelController = CancelController.getInstance();
	private FindIdController findIdController = FindIdController.getInstance();
	private FindPasswordController findPasswordController = FindPasswordController.getInstance();

	private String id;
	private String passwd;
	private boolean isLogin = false; // 로그인한 상태인지

	// sss
	/**
	 * 첫 화면 메뉴
	 * 
	 * @return
	 * @throws Exception
	 */
	public int welcome() throws Exception {
		// art.airplaneArt();
		// art.introduce();

		if (!isLogin) {
			ASCIIArt.loginArt();
			System.out.println("::::::::::::: 서비스를 이용하기 위해 로그인해주세요. :::::::::::::");
			int menuChoice = -1;
			while (true) {// sy
				try {
					System.out.println("1. 로그인    2. 회원가입    3. 아이디 찾기    4. 비밀번호 찾기   0.종료");
					System.out.println();
					System.out.print("메뉴 선택 > ");
					menuChoice = ScanUtil.nextInt();
					if (menuChoice == 1 || menuChoice == 2 || menuChoice == 3 || menuChoice == 4) {
						break;
					} else {
						System.out.println("잘못된 입력입니다. 다시 입력해주세요");
					}
				} catch (NumberFormatException ie) {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요");
				} catch (Exception e) {
					System.out.println("오류 발생 : ");
					e.printStackTrace();
					break;
				}
			}
			return menuChoice;
		}
		return 1;
	}

	/**
	 * 회원가입
	 * 
	 * @param controller
	 * @throws Exception
	 */
	public void join() throws Exception {
		String memId = null;
		int memBir = 0;
		while (true) {
			try {
				System.out.print("아이디 : ");
				memId = ScanUtil.nextLine();
				if (memId.matches("\\w+")) {
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				}
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
				break;
			}
		}
		System.out.println("비밀번호 : java");
		System.out.print("이름 : ");
		String memName = ScanUtil.nextLine();
		while (true) {
			try {
				System.out.print("생년월일 6자리) : ");
				String memBirString = ScanUtil.nextLine();
				if (memBirString.matches("\\d{6}")) {
					memBir = Integer.parseInt(memBirString);
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				}
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
				break;
			}
		}
		String account;
		while (true) {
			try {
				System.out.print("계좌번호 : ");
				account = ScanUtil.nextLine();
				if (account.matches("\\d{9,}")) {
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				}
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
			}
		}
		System.out.print("은행 : ");
		String bank = ScanUtil.nextLine();

		int insertJoin = JoinController.insertJoin(new JoinVO(memId, memName, memBir, account, bank));
		if (insertJoin > 0) {
			System.out.println("회원가입이 완료됐습니다.");
		} else {
			System.out.println("회원가입이 실패했습니다.");
		}
	}

	public void FindId() throws Exception {
		System.out.println(">>>>>>>>>아이디 찾기<<<<<<<<<");
		System.out.print("이름 : ");
		String typeName = ScanUtil.nextLine();
		int typeBir = 0;
		while (true) {
			try {
				System.out.print("생년월일 6자리) : ");
				String memBirString = ScanUtil.nextLine();
				if (memBirString.matches("\\d{6}")) {
					typeBir = Integer.parseInt(memBirString);
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				}
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
				break;
			}
		}
		String typeAccount;
		while (true) {
			try {
				System.out.print("계좌번호 : ");
				typeAccount = ScanUtil.nextLine();
				if (typeAccount.matches("\\d{9,}")) {
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				}
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
			}
		}

		FindIdVO vo = findIdController.bringId(typeName, typeBir, typeAccount);
		System.out.println("-------------------------");
		System.out.printf("찾은 아이디 : %s\n", vo.getMemId());
		System.out.println("-------------------------");
	}
	// sss

	// sss
	/**
	 * 비밀번호 찾기
	 * 
	 * @throws Exception
	 */
	public void FindPassword() throws Exception {
		System.out.println(">>>>>>>>>비밀번호 찾기<<<<<<<<<");
		String typeId = null;
		while (true) {
			try {
				System.out.print("아이디 : ");
				typeId = ScanUtil.nextLine();
				if (typeId.matches("\\w+")) {
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				}
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
				break;
			}
		}
		System.out.print("이름 : ");
		String typeName = ScanUtil.nextLine();
		int typeBir = 0;
		while (true) {
			try {
				System.out.print("생년월일 6자리) : ");
				String memBirString = ScanUtil.nextLine();
				if (memBirString.matches("\\d{6}")) {
					typeBir = Integer.parseInt(memBirString);
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				}
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
				break;
			}
		}
		String typeAccount = null;
		while (true) {
			try {
				System.out.print("계좌번호 : ");
				typeAccount = ScanUtil.nextLine();
				if (typeAccount.matches("\\d{9,20}")) {
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				}
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
			}
		}

		FindPasswordVO vo = findPasswordController.bringPassword(typeId, typeName, typeBir, typeAccount);
		System.out.println("-------------------------");
		System.out.printf("찾은 아이디 : %s\n", vo.getPassword());
		System.out.println("-------------------------");
	}

	// sss
	/**
	 * 로그인 화면 scanner로 로그인 정보 받음 db에 저장된 로그인 정보랑 비교해서 로그인 성공 여부 리턴
	 * 
	 * @return 로그인 성공 여부
	 * @throws Exception
	 */
	public boolean login() throws Exception {
		if (!isLogin) {
			System.out.print("아이디 > ");
			id = ScanUtil.nextLine();
			System.out.print("비밀번호 > ");
			passwd = ScanUtil.nextLine();
			if (loginController.isLoginSuccess(id, passwd)) {
				System.out.println("로그인 성공 !");
				isLogin = true;
				return true;
			} else {
				System.out.println("로그인 실패 ! 다시 시도해주세요");
				isLogin = false;
				return false;
			}
		}
		return true;
	}

	/**
	 * 메인메뉴
	 * 
	 * @return 선택한 메뉴번호
	 */
	public int menu() {
		System.out.println("\n1. 예약   2. 예약취소   3. 예약확인   4. 로그아웃   0. 종료 ");

		int num = -1;
		while (true) {// sy
			try {
				System.out.print("메뉴 선택 > ");
				num = ScanUtil.nextInt();
				if (num == 1 || num == 2 || num == 3 || num == 4 || num == 0) {
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요");
				}
			} catch (NumberFormatException ie) {
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
				break;
			}
		}
		return num;
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
		System.out.println();
		System.out.println();
		System.out.println("::::::::::::: 행선지선택 :::::::::::::");
		int country = 0;
		while (true) {
			try {
				System.out.println();
				System.out.println("1. 베이징");
				System.out.println("2. 동경");
				System.out.println("3. 하노이");
				System.out.print("도착지 선택 > ");
				System.out.println();
				country = ScanUtil.nextInt();
				if (country == 1 || country == 2 || country == 3) {
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요");
				}
			} catch (NumberFormatException ie) {// sy
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
				break;
			}
		}

		System.out.println();
		System.out.println("::::::::::::: 날짜선택 :::::::::::::");
		int date = 0;
		while (true) {
			try {
				System.out.println();
				System.out.println("1. 2022/09/14");
				System.out.println("2. 2022/09/15");
				System.out.println("3. 2022/09/16");
				System.out.println();
				System.out.print("날짜 선택 > ");
				date = ScanUtil.nextInt();
				if (date == 1 || date == 2 || date == 3) {
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요");
				}
			} catch (NumberFormatException ie) {// sy
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
				break;
			}
		}
		System.out.println("");
		System.out.println("::::::::::::: 탑승객 수 입력 :::::::::::::");
		System.out.println("");
		int num = 0;
		while (true) {
			try {
				System.out.print("인원수를 입력해주세요 > ");
				num = ScanUtil.nextInt();
				break;
			} catch (NumberFormatException ie) {// sy
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
				break;
			}
		}

		// 항공편 리스트 출력
		System.out.println("");
		System.out.println("::::::::::::: 항공편 선택 :::::::::::::");
		printCourseList(country, date, num);
		String courseID = "";
		while (true) {
			try {
				System.out.println("");
				System.out.print("항공편ID  입력 > ");
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
			System.out.println("");
			System.out.println("::::::::: 좌석 선택 :::::::::");
			printSeats(courseID, passengers);
			System.out.print("좌석번호 입력(예시:A1) > ");
			String seatNum = ScanUtil.nextLine();
			if (!seatNum.matches("^[A-D][1-6]$")) {
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
				i--;
				continue;
			}

			// 좌석 번호 중복확인
			if (duplicateSeat(seatNum, courseID, passengers)) {
				System.out.println("이미 선택된 좌석입니다. 다시 선택해주세요");
				i--;
				continue;
			}

			System.out.println("\n::::::::::::: 탑승자 정보입력 :::::::::::::\n");
			System.out.printf("탑승자%d 이름 입력 > ", i + 1);
			String name = ScanUtil.nextLine();
			String call = "";
			while (true) {
				try {
					System.out.printf("탑승자%d 전화번호(하이픈생략) 입력 > ", i + 1);
					call = ScanUtil.nextLine();
					if (call.matches("[0-9]{11}")) {
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
			String birth = "";
			while (true) {
				try {
					System.out.printf("탑승자%d 생년월일(yymmdd) 입력 > ", i + 1);
					birth = ScanUtil.nextLine();
					if (birth.matches("[0-9]{6}")) {
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

			passengers.add(new ReservInfoVO(id, courseID, seatNum, name, call, birth));
		}
		// db 에 저장
		if (reservController.insertReservInfo(passengers) > 0) {
			System.out.println("예약이 완료되었습니다.");
		} else {
			System.out.println("예약에 실패했습니다.");
		}

		// 예약 내역 출력
		printConfirmReserve(id, courseID);
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
		System.out.println();
		System.out.println("  항공편ID | 출발지 |  출발일자  | 출발시간 | 도착지 | 도착시간 | 잔여 좌석 |  거리   |   금액   |     항공사       ");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("  %s |  %s   | %s |   %s  |  %4s  |  %s   |     %d    |  %s  |  %d  | %10s \n",
					list.get(i).getCourseId(), list.get(i).getDeparture(), list.get(i).getDepartDate(),
					list.get(i).getDepartTime(), list.get(i).getAirportID(), list.get(i).getArrivalTime(),
					list.get(i).getRemainSeat(), list.get(i).getDistance(), list.get(i).getPrice(),
					list.get(i).getAirline());
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
	public void printSeats(String courseID, List<ReservInfoVO> passengers) throws Exception {
//		String[][] seats = new String[6][4];
//		List<SeatNumVO> vo = reservController.getSeatsIndex(courseID);
//		resetSeats(seats);
//
//		for (int k = 0; k < vo.size(); k++) {
//			seats[vo.get(k).getRow()][vo.get(k).getCol()] = "■";
//		}
		String[][] seats = new String[6][4];

		seats = showRemainSeats(courseID, passengers);
		System.out.printf("\n-----------------------------------------------\n");
		System.out.printf("\t    A B C D\n");
		for (int i = 0; i < seats.length; i++) {
			System.out.printf("\t%d : ", i + 1); // sy
			for (int j = 0; j < seats[i].length; j++) {
				System.out.print(seats[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.printf("\n-----------------------------------------------\n");
	}

	/**
	 * 실시간 잔여 좌석 출력 db에는 반영 안됨
	 * 
	 * @throws Exception
	 */
	public String[][] showRemainSeats(String courseId, List<ReservInfoVO> passengers) throws Exception {
		String[][] seats = new String[6][4];
		List<SeatNumVO> vo = reservController.getSeatsIndex(courseId);
		resetSeats(seats);

		// db에 저장된 비행기 좌석 색칠
		for (int k = 0; k < vo.size(); k++) {
			seats[vo.get(k).getRow()][vo.get(k).getCol()] = "■";
		}

		// 이전에 예약자가 선택한 좌석 색칠
		for (int p = 0; p < passengers.size(); p++) {
			SeatNumVO seat = new SeatNumVO(passengers.get(p).getSeatNO());
			seats[seat.getRow()][seat.getCol()] = "■";
		}

		return seats;
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
	public boolean duplicateSeat(String seat, String courseID, List<ReservInfoVO> passengers) throws Exception {
		List<String> seats = reservController.getSeatsNo(courseID);
		for (String seatNo : seats) {
			if (seat.equals(seatNo)) {
				return true;
			}
		}

		for (int p = 0; p < passengers.size(); p++) {
			if (passengers.get(p).getSeatNO().equals(seat)) {
				return true;
			}
		}
		return false;
	}

	public void printConfirmReserve(String memId, String courseID) throws Exception {
		List<CheckVO> list = reservController.printConfirmReserve(memId, courseID);
		System.out.println(
				"|     예약번호    | 탑승자 |   항공편  | 좌석번호 | 출발지  |    출발일   | 출발시간 |  도착지 | 도착시간 |    항공사   | 비행기 번호 |");
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("| %s | %s | %s |   %s   |  %s  | %s |  %s |  %s  |  %s | %s |   %s   |\n",
					list.get(i).getReservId(), list.get(i).getPassName(), list.get(i).getCourseId(),
					list.get(i).getSeatNo(), list.get(i).getDepLocation(), list.get(i).getDepDate(),
					list.get(i).getDepTime(), list.get(i).getAirportId(), list.get(i).getArrTime(),
					list.get(i).getAirline(), list.get(i).getAirplaneId());
		}
	}

	// -------------------------------------------------------------------------------------------------------------------------

	/**
	 * 취소 진행
	 * 
	 * @throws Exception
	 */
	public void cancel() throws Exception {
		printCancelList();
		getCancelNumber();
	}

	public void printCancelList() throws Exception {
		// 예약목록 확인
		System.out.println("\n::::::::::::: 예약목록 :::::::::::::");
		printReservList();
	}

	/**
	 * 예약번호로 취소하기
	 * 
	 * @throws Exception
	 */
	public void getCancelNumber() throws Exception {
		List<CheckVO> list = checkController.printReservList(id);
		if (list.size() == 0) {
			System.out.println("예약된 내역이 없습니다.");
			return;
		}
		// 취소할 예약번호 받기
		String reservID = "";
		while (true) {
			try {
				System.out.println("");
				System.out.print("취소할 예약번호를 입력해주세요 > ");
				reservID = ScanUtil.nextLine();
				if (reservID.matches("[0-9]{13}") && existReserveID(reservID)) {
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
		// 마일리지 차감
		cancelController.updateMileage(reservID);
		// 예약내역 취소
		if (cancelController.updateReserveList(reservID) > 0) {
			System.out.println("예약을 취소합니다");
		} else {
			System.out.println("예약취소를 실패했습니다");
		}
		// 비행기 T 잔여좌석 update
		reservController.updateSeatRemain(cancelController.getCourseID(reservID));
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
		if (printTotalList()) {
			switch (selectChoice1()) {
			case 1:
				printReservDetail();
				break;
			case 2:
				break;
			}
		}
	}

	/**
	 * 회원 아이디를 토대로 나오는 예약 목록(간이) 10개
	 * 
	 * @param controller
	 * @throws Exception
	 */
	public boolean printTotalList() throws Exception {
		List<CheckVO> list = checkController.printTotalList(id);
		System.out.println("");
		System.out.println(
				" 취소여부 |     예약번호    | 탑승자 |  항공편  | 좌석번호 | 출발지 |   출발일   | 출발시간 | 도착지 | 도착시간 | 비행기번호 |      항공사   ");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < list.size(); i++) {
			System.out.printf(
					"     %s    |  %s  | %s | %s |    %s    |   %s  | %s |   %s  |   %s  |   %s  |    %s    |   %10s  \n",
					list.get(i).getCancel(), list.get(i).getReservId(), list.get(i).getPassName(),
					list.get(i).getCourseId(), list.get(i).getSeatNo(), list.get(i).getDepLocation(),
					list.get(i).getDepDate(), list.get(i).getDepTime(), list.get(i).getAirportId(),
					list.get(i).getArrTime(), list.get(i).getAirplaneId(), list.get(i).getAirline());
		}
		if (list.size() == 0) {
			System.out.println("예약된 내역이 없습니다.");
			return false;
		}
		return true;
	}

	/**
	 * 회원 아이디를 토대로 나오는 예약 목록(간이) 10개
	 * 
	 * @param controller
	 * @throws Exception
	 */
	public void printReservList() throws Exception {
		List<CheckVO> list = checkController.printReservList(id);
		System.out.println("");
		System.out.println(
				" 취소여부 |     예약번호    | 탑승자 |  항공편  | 좌석번호 | 출발지 |   출발일   | 출발시간 | 도착지 | 도착시간 | 비행기번호 |      항공사   ");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < list.size(); i++) {
			System.out.printf(
					"     %s    |  %s  | %s | %s |    %s    |   %s  | %s |   %s  |   %s  |   %s  |    %s    |   %10s  \n",
					list.get(i).getCancel(), list.get(i).getReservId(), list.get(i).getPassName(),
					list.get(i).getCourseId(), list.get(i).getSeatNo(), list.get(i).getDepLocation(),
					list.get(i).getDepDate(), list.get(i).getDepTime(), list.get(i).getAirportId(),
					list.get(i).getArrTime(), list.get(i).getAirplaneId(), list.get(i).getAirline());
		}
	}

	/**
	 * 예약 목록에 들어가면 목록과 함께 어떤 행동을 할지 정하게 한다. 세부내역 보기, 메인 메뉴로 돌아가기, 종료하기
	 * 
	 * @return 1, 2, 3
	 */
	public int selectChoice1() {
		int menuChoice = -1;
		while (true) {// sy
			try {
				System.out.println("\\n1. 세부내역     2. 메인 메뉴");
				System.out.print("\\n선택할 메뉴의 번호를 입력해주세요 > ");
				menuChoice = ScanUtil.nextInt();
				if (menuChoice == 1 || menuChoice == 2) {
					break;
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요");
				}
			} catch (NumberFormatException ie) {
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
			} catch (Exception e) {
				System.out.println("오류 발생 : ");
				e.printStackTrace();
				break;
			}
		}
		return menuChoice;
	}

	/**
	 * 예약 목록을 통해 얻은 예약번호를 통해 세부 예약 정보 출력
	 * 
	 * @param controller
	 * @param scanner
	 * @throws Exception
	 */
	public void printReservDetail() throws Exception {
		String typeReservId = "";
		while (true) {
			try {
				System.out.print("\n보고 싶은 예약번호 입력 > ");
				typeReservId = ScanUtil.nextLine();
				if (typeReservId.matches("[0-9]{13}") && existReserveID(typeReservId)) {
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
		CheckVO vo = checkController.printReservDetail(typeReservId);
		System.out.println("---------------------------------------");
		System.out.printf("취소여부\t: %s\n---------------------------------------\n", vo.getCancel());
		System.out.printf("예약번호\t: %s\n회원번호\t: %s\n---------------------------------------\n", vo.getReservId(),
				vo.getMemId());
		System.out.printf("탑승자\t\t: %s\n전화번호\t: %s\n생년월일\t: %s\n---------------------------------------\n",
				vo.getPassName(), vo.getPassPhone(), vo.getPassReg());
		System.out.printf("탑승자\t\t: %s\n전화번호\t: %s\n생년월일\t: %s\n---------------------------------------\n",
				vo.getPassName(), vo.getPassPhone(), vo.getPassReg());
		System.out.printf(
				"항공편\t\t: %s\n좌석번호\t: %s\n출발지\t\t: %s\n출발일\t\t: %s\n출발시간\t: %s\n도착지\t\t: %s\n도착시간\t: %s\n항공사\t\t: %s\n비행기번호\t: %s\n---------------------------------------\n",
				vo.getCourseId(), vo.getSeatNo(), vo.getDepLocation(), vo.getDepDate(), vo.getDepTime(),
				vo.getAirportId(), vo.getArrTime(), vo.getAirline(), vo.getAirplaneId());
		System.out.printf("가격\t\t: %d\n계좌번호\t: %s\n은행\t\t: %s\n", vo.getPrice(), vo.getAccount(), vo.getBank());
		System.out.println("");
	}

	/**
	 * 예약 목록에 입력한 예약번호가 있는지
	 * 
	 * @param inputReserveId
	 * @return
	 * @throws Exception
	 */
	public boolean existReserveID(String inputReserveId) throws Exception {
		boolean isExist = false;
		List<CheckVO> list = checkController.getReserveIdList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getReservId().equals(inputReserveId)) {
				isExist = true;
			}
		}
		return isExist;
	}
//ASCIIArt 보기--------------------------------------------------------------------------------------------
	// sss
//	public void printAllArt() {
//		art.airplaneArt();
//		art.introduce();
//		art.loginArt();
//		art.menuArt();
//		art.cancelArt();
//		art.checkArt();
//		art.goodByeArt();
//	}
//	// sss
}