package teamProject.findid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import oracle.jdbc.OracleDriver;
import teamProject.check.CheckVO;
import teamProject.utils.DataFormatUtil;

public class FindIdDAO {
	public FindIdVO bringId(String typeName, int typeBir, String typeAccount) throws Exception {
		// 0. 드라이버 로딩
		DriverManager.registerDriver(new OracleDriver());

		// 1. 접속
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		// 2. 쿼리 작성
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("    mem_id ");
		builder.append("FROM ");
		builder.append("    member ");
		builder.append("WHERE ");
		builder.append("    mem_name = ? ");
		builder.append("    AND   mem_bir = ? ");
		builder.append("    AND   account = ? ");

		String sql = builder.toString();

		// 3. 준비된 쿼리에 데이터 입력
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, typeName);
		statement.setInt(2, typeBir);
		statement.setString(3, typeAccount);

		// 4. 쿼리 실행
		ResultSet resultSet = statement.executeQuery();

		// 5. 쿼리 결과 가져오기
		FindIdVO vo = null;
		if (resultSet.next()) {
			String memId = resultSet.getString("mem_id");
			vo = new FindIdVO(memId);

		}

		// 6. 자원 반납
		resultSet.close();
		statement.close();
		connection.close();

		return vo;
	}
}
