package teamProject.mypage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleDriver;

public class MypageDAO {

	public List<MypageVO> printMypage(String id) throws Exception{
		DriverManager.registerDriver(new OracleDriver());
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");
		
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("    mem_id, ");
		builder.append("    mem_name, ");
		builder.append("    mem_bir, ");
		builder.append("    account, ");
		builder.append("    bank, ");
		builder.append("    mileage ");
		builder.append("FROM ");
		builder.append("    member ");
		builder.append("WHERE ");
		builder.append("    mem_id = ? ");
		String sql = builder.toString();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		List<MypageVO> list = new ArrayList<>();
		while (resultSet.next()) {
			String memId = resultSet.getString("mem_id");
			String memName = resultSet.getString("mem_name");
			int memBir = resultSet.getInt("mem_bir");
			String account = resultSet.getString("account");
			String bank = resultSet.getString("bank");
			int mileage = resultSet.getInt("mileage");
			list.add(new MypageVO(memId, memName, memBir, account, bank, mileage));
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}
}
