package teamProject.reservation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.OracleDriver;
import teamProject.check.CheckVO;
import teamProject.utils.DataFormatUtil;

//sy 전체적인 쿼리 수정
public class ReservDAO {
	/**
	 * 입력받은 도착지, 날짜, 탑승자수로 항공편 정보 얻기 *
	 * 
	 * @param input 도착지, 날짜, 탑승자수
	 * @return list 항공편 리스트 List<CourseInfoVO>
	 * @throws Exception
	 */
	public List<CourseInfoVO> getCourseInfo(CourseInfoVO courseInfo) throws Exception {
		DriverManager.registerDriver(new OracleDriver()); // 드라이버를 로딩
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT                                        ");
		builder.append("     a.course_id,                              ");
		builder.append("     a.dep_location,                           ");
		builder.append("     a.dep_date,                               ");
		builder.append("     a.dep_time,                               ");
		builder.append("     a.airport_id,                             ");
		builder.append("     a.arr_time,                               ");
		builder.append("     a.airplane_id,                            ");
		builder.append("     a.price,                                  ");
		builder.append("     a.distance,                               ");
		builder.append("     b.airline,                                ");
		builder.append("     a.seat_remain                             ");
		builder.append(" FROM                                          ");
		builder.append("     course a,                                 ");
		builder.append("     airplane b                                ");
		builder.append(" WHERE                                         ");
		builder.append("     a.airplane_id = b.airplane_id             ");
		builder.append("     AND   a.airport_id = ?                    ");
		builder.append("     AND   a.dep_date = TO_DATE(?,'YYYY/MM/DD')");
		builder.append("     AND   a.seat_remain >=?                   ");
		builder.append(" ORDER BY 1                   ");
		String sql = builder.toString();

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, courseInfo.getAirportID());
		statement.setString(2, courseInfo.getDepartDate());
		statement.setInt(3, courseInfo.getNumPassengers());

		ResultSet resultSet = statement.executeQuery();
		List<CourseInfoVO> list = new ArrayList<>();
		while (resultSet.next()) {
			list.add(new CourseInfoVO(resultSet.getString("course_id"), resultSet.getString("dep_location"),
					DataFormatUtil.dateFormat(resultSet.getString("dep_date")), resultSet.getString("dep_time"),
					resultSet.getString("airport_id"), resultSet.getString("arr_time"),
					resultSet.getString("airplane_id"), resultSet.getInt("price"), resultSet.getString("distance"),
					resultSet.getString("airline"), resultSet.getInt("seat_remain")));
		}

		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}

	/**
	 * 비행기의 예약된 자석 번호 리스트 얻기
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> getSeatsNo(String courseID) throws Exception {
		DriverManager.registerDriver(new OracleDriver());
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT                       ");
		builder.append("     seat_no                  ");
		builder.append(" FROM                         ");
		builder.append("     reservation a,           ");
		builder.append("     course b                 ");
		builder.append(" WHERE                        ");
		builder.append("     a.course_id = b.course_id");
		builder.append("     AND   a.course_id =?     ");
		builder.append("     AND   a.cancel ='N'     ");
		String sql = builder.toString();

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, courseID);

		ResultSet resultSet = statement.executeQuery();
		List<String> list = new ArrayList<>();
		while (resultSet.next()) {
			list.add(resultSet.getString("seat_no"));
		}
		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}
	
	public int updateSeatRemain(String courseId) throws Exception {
	      DriverManager.registerDriver(new OracleDriver()); 
	      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

	      StringBuilder builder = new StringBuilder();
	      builder.append(" UPDATE COURSE A                                     ");
	      builder.append(" SET    A.SEAT_REMAIN = (                            ");
	      builder.append("             SELECT B.SEAT_REMAIN FROM VW_GETREMAIN B");
	      builder.append("             WHERE  B.COURSE_ID = A.COURSE_ID        ");
	      builder.append("         )                                           ");
	      builder.append(" WHERE  A.COURSE_ID = ?                    ");
	      String sql = builder.toString();
	      
	      PreparedStatement statement = connection.prepareStatement(sql);
	      statement.setString(1, courseId);
	      
	      int result = statement.executeUpdate();

	      statement.close();
	      connection.close();
	      return result;
	   }
	

	/**
	 * 입력받은 예약정보 insert
	 * 
	 * @return
	 * @throws Exception
	 */
	public int insertReservInfo(ReservInfoVO reservInfo) throws Exception {
		DriverManager.registerDriver(new OracleDriver());
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		StringBuilder builder = new StringBuilder();
		builder.append(" INSERT INTO reservation (                                      ");
		builder.append("     reserv_id,                                                 ");
		builder.append("     mem_id,                                                    ");
		builder.append("     course_id,                                                 ");
		builder.append("     seat_no,                                                   ");
		builder.append("     pass_name,                                                 ");
		builder.append("     pass_phone,                                                ");
		builder.append("     pass_reg,                                                  ");
		builder.append("     cancel                                                     ");
		builder.append(" ) VALUES (                                                     ");
		builder.append("     (                                                          ");
		builder.append("         SELECT                                                 ");
		builder.append("             nvl(MAX(reserv_id) + 1,TO_CHAR(SYSDATE,'YYYYMMDD') ");
		builder.append("             || '00001')                                        ");
		builder.append("         FROM                                                   ");
		builder.append("             reservation                                        ");
		builder.append("         WHERE                                                  ");
		builder.append("             substr(reserv_id,1,8) = TO_CHAR(SYSDATE,'YYYYMMDD')");
		builder.append("     ),                                                         ");
		builder.append("     ?,                                                         ");
		builder.append("     ?,                                                         ");
		builder.append("     ?,                                                         ");
		builder.append("     ?,                                                         ");
		builder.append("     ?,                                                         ");
		builder.append("     ?,                                                         ");
		builder.append("     ?                                                          ");
		builder.append(" )                                                             ");
		String sql = builder.toString();

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, reservInfo.getMemID());
		statement.setString(2, reservInfo.getCourseID());
		statement.setString(3, reservInfo.getSeatNO());
		statement.setString(4, reservInfo.getName());
		statement.setString(5, reservInfo.getPhone());
		statement.setString(6, reservInfo.getReg());
		statement.setString(7, reservInfo.getCancel());

		int result = statement.executeUpdate();

		statement.close();
		connection.close();
		return result;
	}

	/**
	 * 예약 완료시 마일리지 추가
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updatePlusMileage(int numPassengers, String courseId, String memId) throws Exception {
		// 0. 드라이버 로딩
//         Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.registerDriver(new OracleDriver());
		// 1. 접속
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");
		// 2. 쿼리 작성
		StringBuilder builder = new StringBuilder();
//		builder.append(" UPDATE member");
//		builder.append("     SET");
//		builder.append("         MILEAGE = MILEAGE + (");
//		builder.append("             SELECT");
//		builder.append("                 SUM(PRICE * 0.01)");
//		builder.append("             FROM");
//		builder.append("                 course a,");
//		builder.append("                 reservation b");
//		builder.append("             WHERE");
//		builder.append("                 a.COURSE_ID = b.COURSE_ID");
//		builder.append("                 AND   b.MEM_ID = ?");
//		builder.append("             GROUP BY");
//		builder.append("                 a.PRICE");
//		builder.append("         )");
//		builder.append(" WHERE");
//		builder.append("     mem_id = ?");
		
		builder.append(" UPDATE  member                         ");
		builder.append(" SET     mileage = mileage + (                    ");
		builder.append("             SELECT  SUM(price * ? * 0.01)");
		builder.append("             FROM    course             ");
		builder.append("             WHERE   course_id =?       ");
		builder.append("             )                          ");
		builder.append(" WHERE   mem_id =?                      ");
		String sql = builder.toString();

		// 3. 준비된 쿼리에 데이터 입력
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, numPassengers);
		statement.setString(2, courseId);
		statement.setString(3, memId);

		// 4. 쿼리 실행
		int executeUpdate = statement.executeUpdate();

		// 5. 자원 반납
		statement.close();
		connection.close();

		return executeUpdate;
	}

	/**
	 * 예약완료 후 예약한 확인 정보 출력
	 * 
	 * @param memId
	 * @param courseID
	 * @return
	 * @throws Exception
	 */
	public List<CheckVO> printConfirmReserve(String memId, String courseID) throws Exception {
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
		builder.append("    AND	  a.course_id = ? ");
		builder.append("    AND	  a.cancel = 'N' ");
		builder.append("    AND   a.course_id = b.course_id ");
		builder.append("    AND   b.airplane_id = c.airplane_id ");
		builder.append("ORDER BY ");
		builder.append("    1 ");

		String sql = builder.toString();

		// 3. 준비된 쿼리에 데이터 입력
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, memId);
		statement.setString(2, courseID);

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

}
