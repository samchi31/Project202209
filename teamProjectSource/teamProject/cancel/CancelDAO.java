package teamProject.cancel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import teamProject.cancel.CancelVO;
import teamProject.check.CheckVO;
import oracle.jdbc.driver.OracleDriver;

public class CancelDAO {

	/**
	 * 예약취소를 선택하면 마일리지를 빼준다
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateMileage(String deleteRe) throws Exception {
		// 0. 드라이버 로딩
//         Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver(new OracleDriver());
		// 1. 접속
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");
		// 2. 쿼리 작성
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE member");
		builder.append("     SET");
		builder.append("         MILEAGE = MILEAGE + (");
		builder.append("             SELECT");
		builder.append("                 SUM(PRICE * 0.01)");
		builder.append("             FROM");
		builder.append("                 course a,");
		builder.append("                 reservation b");
		builder.append("             WHERE");
		builder.append("                 a.COURSE_ID = b.COURSE_ID");
		builder.append("                 AND   b.MEM_ID = ?");
		builder.append("             GROUP BY");
		builder.append("                 a.PRICE");
		builder.append("         )");
		builder.append(" WHERE");
		builder.append("     mem_id = ?");

		String sql = builder.toString();

		// 3. 준비된 쿼리에 데이터 입력
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, deleteRe);
		statement.setString(2, deleteRe);

		// 4. 쿼리 실행
		int executeUpdate = statement.executeUpdate();

		// 5. 자원 반납
		statement.close();
		connection.close();

		return executeUpdate;
	}

	/**
	 * 예약번호를 받아서 해당하는 예약내역의 cancel 값을 y로 바꿈
	 * 
	 * @param deleteRe 예약번호
	 * @return
	 * @throws Exception
	 */
	// 예약을 취소할 시 예약을 삭제하는 기능
	public int updateReserveList(String deleteRe) throws Exception {
		// 0. 드라이버 로딩
		// Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver(new OracleDriver());
		// 1. 접속
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");
		// 2. 쿼리 작성
		StringBuilder builder = new StringBuilder();
		builder.append(" update reservation   ");
		builder.append("   set cancel = 'Y'   ");
		builder.append("  where reserv_id = ? ");
		String sql = builder.toString();

		// 3. 준비된 쿼리에 데이터 입력
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, deleteRe);

		// 4. 쿼리 실행
		int executeUpdate = statement.executeUpdate();

		// 5. 자원 반납
		statement.close();
		connection.close();
		return executeUpdate;
	}

}