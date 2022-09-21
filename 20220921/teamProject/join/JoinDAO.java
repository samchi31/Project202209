package teamProject.join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

public class JoinDAO {
	public int insertJoin(JoinVO vo) throws Exception { // int?
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
		builder.append("    ?, ");
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
		statement.setString(2, vo.getPassword());
		statement.setString(3, vo.getMemName());
		statement.setInt(4, vo.getMemBir());
		statement.setString(5, vo.getAccount());
		statement.setString(6, vo.getBank());

		// 4. 쿼리 실행
		int executeUpdate = statement.executeUpdate();
		
		// 5. 자원 반납
		statement.close();
		connection.close();
		
		return executeUpdate;
	}
	
	public String getMemID(String memId) throws Exception {
		DriverManager.registerDriver(new OracleDriver());

		// 1. 접속
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		// 2. 쿼리 작성
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT  mem_id   ");
		builder.append(" FROM    member   ");
		builder.append(" WHERE   mem_id =?");

		String sql = builder.toString();

		// 3. 준비된 쿼리에 데이터 입력
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, memId);// 1부터 시작함

		// 4. 쿼리 실행
		ResultSet resultSet = statement.executeQuery();
		String id = null;
		if(resultSet.next()) {
			id = resultSet.getString("mem_id");
		}
		
		// 5. 자원 반납
		resultSet.close();
		statement.close();
		connection.close();
		
		return id;
	}
}