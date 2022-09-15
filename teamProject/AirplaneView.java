package teamProject;

import java.util.Scanner;

public class AirplaneView {
	Scanner scanner = new Scanner(System.in);
	private String loginID;
	private String passwd;

	/**
	 * 로그인 화면 scanner로 로그인 정보 받음
	 */
	public void init() {
		System.out.println(">>>>>>>>>로그인<<<<<<<<<");
		System.out.print("아이디 > ");
		loginID = scanner.nextLine();
		System.out.print("비밀번호 > ");
		passwd = scanner.nextLine();
	}

	/**
	 * db에 저장된 로그인 정보랑 비교해서 로그인 성공 여부 리턴 
	 * @return 로그인 성공 여부
	 */
	public boolean login() {
		return false;
	}

	/**
	 * 메인메뉴
	 * @return 선택한 메뉴번호
	 */
	public int menu() {
		System.out.println(">>>>>>>>>메뉴<<<<<<<<<");
		System.out.println("| 1. 예약 | 2. 예약취소 | 3. 예약확인 | 0. 종료 |");

		return Integer.parseInt(scanner.nextLine());
	}

	public void reservation() {
		System.out.println(">>>>>>>>>예약<<<<<<<<<");

		System.out.println(">>>>>>>>>행선지선택<<<<<<<<<");
		System.out.println("> 베이징");
		System.out.println("> 동경");
		System.out.println("> 하노이");
		System.out.print(">> 나라 선택 : ");
		String country = scanner.nextLine();

		System.out.println(">>>>>>>>>날짜선택<<<<<<<<<");
		System.out.println("1. 2022/09/14");
		System.out.println("2. 2022/09/15");
		System.out.println("3. 2022/09/16");
		System.out.print(">> 날짜 선택 : ");
		int date = Integer.parseInt(scanner.nextLine());
		
		// 나라와 날짜로 시간 리스트 반환
		// 시간 리스트 출력
		
		// 항공편의 비행기의 좌석 리스트 반환
		// 좌석 리스트 출력
		
		//db 에 저장
	}
}
