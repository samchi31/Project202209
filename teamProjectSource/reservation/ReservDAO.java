package teamProject.reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.OracleDriver;
import teamProject.utils.DataFormatUtil;

public class ReservDAO {
	/**
	 * 입력받은 도착지, 날짜, 탑승자수 를 항공편 정보 얻기 *
	 * 
	 * @param input 도착지, 날짜, 탑승자수
	 * @return list 항공편 리스트 List<CourseInfoVO>
	 * @throws Exception
	 */
	public List<CourseInfoVO> getCourseInfo(CourseInfoVO courseInfo) throws Exception {
		DriverManager.registerDriver(new OracleDriver()); // 드라이버를 로딩
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT                                                       ");
		builder.append("     a.course_id,                                             ");
		builder.append("     a.dep_location,                                          ");
		builder.append("     a.dep_date,                                              ");
		builder.append("     a.dep_time,                                              ");
		builder.append("     a.airport_id,                                            ");
		builder.append("     a.arr_time,                                              ");
		builder.append("     a.airplane_id,                                           ");
		builder.append("     a.price,                                                 ");
		builder.append("     a.distance,                                              ");
		builder.append("     b.airline,                                               ");
		builder.append("     b.seat_remain                                            ");
		builder.append(" FROM                                                         ");
		builder.append("     course a,                                                ");
		builder.append("     airplane b                                               ");
		builder.append(" WHERE                                                        ");
		builder.append("     a.airplane_id = b.airplane_id                            ");
		builder.append("     AND   a.airport_id = ?		                              ");
		builder.append("         AND   a.dep_date = to_date( ? ,'YYYY/MM/DD')		  ");
		builder.append("             AND   b.seat_remain >= ?                         ");
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
		builder.append("     and a.course_id = ?      ");
		String sql = builder.toString();

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, courseID);

		ResultSet resultSet = statement.executeQuery();
		List<String> list = new ArrayList<>();
		while(resultSet.next()) {
			list.add(resultSet.getString("seat_no"));
		}

		statement.close();
		connection.close();
		return list;
	}
	
	
	/**
	 * 예약 테이블 기반으로 airplane 테이블의 잔여 좌석 수 update
	 * @return
	 * @throws Exception
	 */
	public int updateSeatRemain() throws Exception {
		DriverManager.registerDriver(new OracleDriver()); 
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE airplane a                                  ");
		builder.append("     SET                                            ");
		builder.append("         ( a.seat_remain ) = (                      ");
		builder.append("             SELECT                                 ");
		builder.append("                 a.seat_total - b.bsum              ");
		builder.append("             FROM                                   ");
		builder.append("                 (                                  ");
		builder.append("                     SELECT                         ");
		builder.append("                         COUNT(c.reserv_id) AS bsum,");
		builder.append("                         d.airplane_id AS bid       ");
		builder.append("                     FROM                           ");
		builder.append("                         reservation c,             ");
		builder.append("                         course d                   ");
		builder.append("                     WHERE                          ");
		builder.append("                         c.course_id = d.course_id  ");
		builder.append("                     GROUP BY                       ");
		builder.append("                         d.airplane_id              ");
		builder.append("                 ) b                                ");
		builder.append("             WHERE                                  ");
		builder.append("                 b.bid = a.airplane_id              ");
		builder.append("         )                                          ");
		builder.append(" WHERE                                              ");
		builder.append("     a.airplane_id IN (                             ");
		builder.append("         SELECT                                     ");
		builder.append("             f.airplane_id                          ");
		builder.append("         FROM                                       ");
		builder.append("             reservation e,                         ");
		builder.append("             course f                               ");
		builder.append("         WHERE                                      ");
		builder.append("             e.course_id = f.course_id              ");
		builder.append("     )                                              ");
		String sql = builder.toString();
		
		PreparedStatement statement = connection.prepareStatement(sql);

		int result = statement.executeUpdate();

		statement.close();
		connection.close();
		return result;
	}
	
	/**
	 * 입력받은 예약정보 insert
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
		builder.append(" );                                                             ");	
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
}
