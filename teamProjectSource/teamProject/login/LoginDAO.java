package teamProject.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import oracle.jdbc.driver.OracleDriver;

public class LoginDAO {
	public LoginVO getLoginInfo(String id, String passwd) throws Exception {
		DriverManager.registerDriver(new OracleDriver());
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		StringBuilder builder = new StringBuilder();
		builder.append(" select  mem_id,");
		builder.append("         password");
		builder.append(" from    member");
		builder.append(" where   mem_id = ?");
		builder.append(" and     password = ?");
		String sql = builder.toString();

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, id);
		statement.setString(2, passwd);

		// int result = statement.executeUpdate();
		ResultSet resultSet = statement.executeQuery();
		LoginVO loginVO = null;
		if (resultSet.next()) {
			loginVO = new LoginVO(resultSet.getString("mem_id"), resultSet.getString("password"));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return loginVO;
	}
}
