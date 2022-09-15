package teamProject.reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import oracle.jdbc.driver.OracleDriver;

public class reservDAO {
	
	/**
	 * 예약테이블에 예약 정보 저장
	 * @param reservinfo 예약정보
	 * @return 0이면 오류
	 * @throws Exception
	 */
	public int insertReservInfo(ReservInfo reservinfo) throws Exception {
		// 0. 드라이버 로딩
		DriverManager.registerDriver(new OracleDriver()); // 드라이버를 로딩
		// 1. 접속
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		// 2. 쿼리 작성		
		StringBuilder builder = new StringBuilder();
		builder.append(" ");		// insert
		String sql = builder.toString();

		// 3.
		PreparedStatement statement = connection.prepareStatement(sql);
		//statement.setInt(1,  ); 
		
		int result = statement.executeUpdate();
		
		// 4. 자원 반납
		statement.close();
		connection.close();
		return result;
	}
	
}
