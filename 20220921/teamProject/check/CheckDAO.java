package teamProject.check;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleDriver;
import teamProject.utils.DataFormatUtil;

public class CheckDAO {

	/**
	 * 예약 목록 갖고 오기
	 * 
	 * @param () - 회원 번호로 예약된 것 다 갖고 오기
	 * @return list - 예약 목록
	 * @throws Exception
	 */
	public List<CheckVO> printTotalList(String memId) throws Exception {
		// 0. 드라이버 로딩
		DriverManager.registerDriver(new OracleDriver());

		// 1. 접속
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		// 2. 쿼리 작성
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("    a.cancel, ");
		builder.append("    a.reserv_id, ");
		builder.append("    a.pass_name, ");
		builder.append("    a.course_id, ");
		builder.append("    a.seat_no, ");
		builder.append("    b.dep_location, ");
		builder.append("    b.dep_date, ");
		builder.append("    b.dep_time, ");
		builder.append("    b.airport_id, ");
		builder.append("    b.arr_time,  ");
		builder.append("    c.airline, ");
		builder.append("    b.airplane_id ");
		builder.append("FROM ");
		builder.append("    reservation a, ");
		builder.append("    course b, ");
		builder.append("    airplane c ");
		builder.append("WHERE ");
		builder.append("    a.mem_id = ? ");
		builder.append("    AND   a.course_id = b.course_id ");
		builder.append("    AND   b.airplane_id = c.airplane_id ");
		builder.append("ORDER BY ");
		builder.append("    1 ");

		String sql = builder.toString();

		// 3. 준비된 쿼리에 데이터 입력
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, memId);

		// 4. 쿼리 실행
		ResultSet resultSet = statement.executeQuery();

		// 5. 쿼리 결과 가져오기
		List<CheckVO> list = new ArrayList<>();
		while (resultSet.next()) {
			String cancel = resultSet.getString("cancel");
			String reservId = resultSet.getString("reserv_id");
			String passName = resultSet.getString("pass_name");
			String courseId = resultSet.getString("course_id");
			String seatNo = resultSet.getString("seat_no");
			String depLocation = resultSet.getString("dep_location");
			String depDate = DataFormatUtil.dateFormat(resultSet.getString("dep_date"));
			String depTime = resultSet.getString("dep_time");
			String airportId = resultSet.getString("airport_id");
			String arrTime = resultSet.getString("arr_time");
			String airline = resultSet.getString("airline");
			String airplaneId = resultSet.getString("airplane_id");
			list.add(new CheckVO(cancel, reservId, passName, courseId, seatNo, depLocation, depDate, depTime, airportId,
					arrTime, airline, airplaneId));
		}

		// 6. 자원 반납
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}

	public List<CheckVO> printReservList(String memId) throws Exception {
		// 0. 드라이버 로딩
		DriverManager.registerDriver(new OracleDriver());

		// 1. 접속
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		// 2. 쿼리 작성
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("    a.cancel, ");
		builder.append("    a.reserv_id, ");
		builder.append("    a.pass_name, ");
		builder.append("    a.course_id, ");
		builder.append("    a.seat_no, ");
		builder.append("    b.dep_location, ");
		builder.append("    b.dep_date, ");
		builder.append("    b.dep_time, ");
		builder.append("    b.airport_id, ");
		builder.append("    b.arr_time,  ");
		builder.append("    c.airline, ");
		builder.append("    b.airplane_id ");
		builder.append("FROM ");
		builder.append("    reservation a, ");
		builder.append("    course b, ");
		builder.append("    airplane c ");
		builder.append("WHERE ");
		builder.append("    a.mem_id = ? ");
		builder.append("    AND	  a.cancel = 'N' ");
		builder.append("    AND   a.course_id = b.course_id ");
		builder.append("    AND   b.airplane_id = c.airplane_id ");
		builder.append("ORDER BY ");
		builder.append("    1 ");

		String sql = builder.toString();

		// 3. 준비된 쿼리에 데이터 입력
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, memId);

		// 4. 쿼리 실행
		ResultSet resultSet = statement.executeQuery();

		// 5. 쿼리 결과 가져오기
		List<CheckVO> list = new ArrayList<>();
		while (resultSet.next()) {
			String cancel = resultSet.getString("cancel");
			String reservId = resultSet.getString("reserv_id");
			String passName = resultSet.getString("pass_name");
			String courseId = resultSet.getString("course_id");
			String seatNo = resultSet.getString("seat_no");
			String depLocation = resultSet.getString("dep_location");
			String depDate = DataFormatUtil.dateFormat(resultSet.getString("dep_date"));
			String depTime = resultSet.getString("dep_time");
			String airportId = resultSet.getString("airport_id");
			String arrTime = resultSet.getString("arr_time");
			String airline = resultSet.getString("airline");
			String airplaneId = resultSet.getString("airplane_id");
			list.add(new CheckVO(cancel, reservId, passName, courseId, seatNo, depLocation, depDate, depTime, airportId,
					arrTime, airline, airplaneId));
		}

		// 6. 자원 반납
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}

	/**
	 * 예약 세부 내역 보여주기
	 * 
	 * @param 입력한 예약번호(typeReservId)로 찾고자 하는 내역
	 * @return vo - 예약 세부 내역
	 * @throws Exception
	 */
	public CheckVO printReservDetail(String typeReservId) throws Exception {
		// 0. 드라이버 로딩
		DriverManager.registerDriver(new OracleDriver());

		// 1. 접속
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		// 2. 쿼리 작성
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		builder.append("    a.cancel, ");
		builder.append("    a.reserv_id, ");
		builder.append("    a.mem_id, ");
		builder.append("    a.pass_name, ");
		builder.append("    a.pass_phone, ");
		builder.append("    a.pass_reg, ");
		builder.append("    a.course_id, ");
		builder.append("    a.seat_no, ");
		builder.append("    b.dep_location, ");
		builder.append("    b.dep_date, ");
		builder.append("    b.dep_time, ");
		builder.append("    b.airport_id, ");
		builder.append("    b.arr_time, ");
		builder.append("    c.airline, ");
		builder.append("    b.airplane_id, ");
		builder.append("    b.price, ");
		builder.append("    d.account, ");
		builder.append("    d.bank, ");
		builder.append("    d.mileage ");
		builder.append("FROM ");
		builder.append("    reservation a, ");
		builder.append("    course b, ");
		builder.append("    airplane c, ");
		builder.append("    member d ");
		builder.append("WHERE ");
		builder.append("    a.reserv_id = ? ");
		builder.append("    AND   a.course_id = b.course_id ");
		builder.append("    AND   b.airplane_id = c.airplane_id ");
		builder.append("    AND   a.mem_id = d.mem_id ");
		builder.append("ORDER BY ");
		builder.append("    1 ");

		String sql = builder.toString();

		// 3. 준비된 쿼리에 데이터 입력
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, typeReservId);

		// 4. 쿼리 실행
		ResultSet resultSet = statement.executeQuery();

		// 5. 쿼리 결과 가져오기
		CheckVO vo = null;
		if (resultSet.next()) {
			String cancel = resultSet.getString("cancel");
			String reservId = resultSet.getString("reserv_id");
			String memId = resultSet.getString("mem_id");
			String passName = resultSet.getString("pass_name");
			String passPhone = resultSet.getString("pass_phone");
			String passReg = resultSet.getString("pass_reg");
			String courseId = resultSet.getString("course_id");
			String seatNo = resultSet.getString("seat_no");
			String depLocation = resultSet.getString("dep_location");
			String depDate = DataFormatUtil.dateFormat(resultSet.getString("dep_date"));
			String depTime = resultSet.getString("dep_time");
			String airportId = resultSet.getString("airport_id");
			String arrTime = resultSet.getString("arr_time");
			String airline = resultSet.getString("airline");
			String airplaneId = resultSet.getString("airplane_id");
			int price = resultSet.getInt("price");
			String account = resultSet.getString("account");
			String bank = resultSet.getString("bank");
			int mileage = resultSet.getInt("mileage");
			vo = new CheckVO(cancel, reservId, memId, passName, passPhone, passReg, courseId, seatNo, depLocation,
					depDate, depTime, airline, airportId, arrTime, airplaneId, price, account, bank, mileage);
		}

		// 6. 자원 반납
		resultSet.close();
		statement.close();
		connection.close();

		return vo;
	}

	public List<CheckVO> getReserveIdList() throws Exception {
		// 0. 드라이버 로딩
		DriverManager.registerDriver(new OracleDriver());

		// 1. 접속
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		// 2. 쿼리 작성
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT  reserv_id   ");
		builder.append(" FROM    reservation");
		String sql = builder.toString();

		// 3. 준비된 쿼리에 데이터 입력
		PreparedStatement statement = connection.prepareStatement(sql);

		// 4. 쿼리 실행
		ResultSet resultSet = statement.executeQuery();

		// 5. 쿼리 결과 가져오기
		List<CheckVO> list = new ArrayList<>();
		while (resultSet.next()) {
			String reservId = resultSet.getString("reserv_id");
			list.add(new CheckVO(reservId));
		}

		// 6. 자원 반납
		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}

}
