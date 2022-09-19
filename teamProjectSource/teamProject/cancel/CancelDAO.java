package teamProject.cancel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
//		StringBuilder builder = new StringBuilder();
//		
//		builder.append("UPDATE member  ");
//		builder.append("    SET ");
//		builder.append("        mileage = mileage - ( ");
//		builder.append("            SELECT ");
//		builder.append("                a.price * 0.01 ");
//		builder.append("            FROM ");
//		builder.append("                course a, ");
//		builder.append("                reservation b ");
//		builder.append("            WHERE ");
//		builder.append("                a.course_id = b.course_id ");
//		builder.append("                AND   b.reserv_id = ? ");
//		builder.append("                AND   b.cancel = 'Y' ");
//		builder.append("        ) ");
//		builder.append("WHERE ");
//		builder.append("    mem_id = ( SELECT ");
//		builder.append("            c.mem_id ");
//		builder.append("        FROM  member c, ");
//		builder.append("            reservation d ");
//		builder.append("        WHERE  c.mem_id = d.mem_id ");
//		builder.append("            AND   d.reserv_id = ? ");
//		builder.append("    ) ");

//		System.out.println(deleteRe);

//		String sql = deleteRe.toString();
		String sql = " UPDATE member e" + "    SET" + "        e.mileage = e.mileage - (" + "            SELECT"
				+ "                a.price * 0.01" + "            FROM" + "                course a,"
				+ "                reservation b" + "            WHERE" + "                a.course_id = b.course_id"
				+ "                AND   b.reserv_id = ?" + "                AND   b.cancel = 'Y'" + "        )"
				+ " WHERE" + "    e.mem_id = (" + "        SELECT" + "            c.mem_id" + "        FROM"
				+ "            member c," + "            reservation d" + "        WHERE"
				+ "            c.mem_id = d.mem_id" + "            AND   d.reserv_id = ?" + "    )";

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

	public String getCourseID(String reservID) throws Exception {
		// 0. 드라이버 로딩
		// Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver(new OracleDriver());
		// 1. 접속
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");
		// 2. 쿼리 작성
		StringBuilder builder = new StringBuilder();
		builder.append(" select  course_id");
		builder.append(" from    reservation");
		builder.append(" where   reserv_id = ?");
		String sql = builder.toString();

		// 3. 준비된 쿼리에 데이터 입력
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, reservID);

		ResultSet resultSet = statement.executeQuery();
		// 4. 쿼리 실행
		String courseId = "";
		if(resultSet.next()) {
			courseId = resultSet.getString("course_id");
		}

		// 5. 자원 반납
		resultSet.close();
		statement.close();
		connection.close();
		return courseId;
	}
}