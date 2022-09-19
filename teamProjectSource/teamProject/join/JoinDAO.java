package teamProject.join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

public class JoinDAO {
	public static int insertJoin(JoinVO vo) throws Exception { // int?
		// 0. 드라이버 로딩
		// Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver(new OracleDriver());
		// 위에 거랑 똑같음. 에러확률 좀 더 줄어듬. 드라이버 로딩

		// 1. 접속
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		// 2. 쿼리 작성
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO member VALUES ( ");
		builder.append("    ?, ");
		builder.append("    'java', ");
		builder.append("    ?, ");
		builder.append("    ?, ");
		builder.append("    ?, ");
		builder.append("    ?, ");
		builder.append("    '0' ");
		builder.append(") ");

		String sql = builder.toString();

		// 3. 준비된 쿼리에 데이터 입력
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, vo.getMemId());// 1부터 시작함
		statement.setString(2, vo.getMemName());
		statement.setInt(3, vo.getMemBir());
		statement.setString(4, vo.getAccount());
		statement.setString(5, vo.getBank());

		// 4. 쿼리 실행
		int executeUpdate = statement.executeUpdate();
		
		// 5. 자원 반납
		statement.close();
		connection.close();
		
		return executeUpdate;
	}
}