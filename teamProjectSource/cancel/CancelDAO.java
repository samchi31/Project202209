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
       * 예약 목록 갖고 오기
       * @param typeReservId 
       * 
       * @param () - 회원 번호로 예약된 것 다 갖고 오기
       * @return list - 예약 목록
       * @throws Exception
       */
      public List<CancelVO> cancelRes() throws Exception {
         // 0. 드라이버 로딩
         DriverManager.registerDriver(new OracleDriver());
   
         // 1. 접속
         Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:1521:xe", "ks95", "java");
         
         System.out.println("왔다");
         
         String sql = "SELECT"
               + "    a.reserv_id,"
               + "    a.pass_name,"
               + "    a.seat_no,"
               + "    b.dep_location,"
               + "    b.dep_date,"
               + "    b.dep_time,"
               + "    b.airport_id,"
               + "    b.arr_time,"
               + "    c.airline,"
               + "    b.airplane_id"
               + " FROM"
               + "    reservation a,"
               + "    course b,"
               + "    airplane c"
               + " WHERE"
               + "    a.mem_id = 'A001'"
               + "    AND   a.course_id = b.course_id"
               + "    AND   b.airplane_id = c.airplane_id"
               + " ORDER BY"
               + "    1";
         PreparedStatement statement = connection.prepareStatement(sql);
//         statement.setString(1, "A001");
   
         // 3. 쿼리 전송
         ResultSet resultSet = statement.executeQuery();
   
         // 4. 결과 정리
         List<CancelVO> list = new ArrayList<>();
         while (resultSet.next()) {
            String reservId = resultSet.getString("reserv_id");
            System.out.println("reservId : " + reservId);
            String passName = resultSet.getString("pass_name");
            String seatNo = resultSet.getString("seat_no");
            String depLocation = resultSet.getString("dep_location");
            String depDate = resultSet.getString("dep_date");
            String depTime = resultSet.getString("dep_time");
            String airportId = resultSet.getString("airport_id");
            String arrTime = resultSet.getString("arr_time");
            String airline = resultSet.getString("airline");
            String airplaneId = resultSet.getString("airplane_id");
            list.add(new CancelVO(reservId, passName, seatNo, depLocation, depDate, depTime, airportId, arrTime, airline, airplaneId));
         }
         
         
   
         // 5. 자원 반납
         resultSet.close();
         statement.close();
         connection.close();
         return list;
      }


      /**
       * 예약취소를 선택하면 마일리지를 빼준다 
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
       * 예약번호를 받아서 해당하는 예약내역을 삭제해줌
       * @param deleteRe 예약번호
       * @return
       * @throws Exception
       */
      //예약을 취소할 시 예약을 삭제하는 기능
      public int deleteReserve(String deleteRe) throws Exception {
      // 0. 드라이버 로딩
      //Class.forName("oracle.jdbc.driver.OracleDriver");
      DriverManager.registerDriver(new OracleDriver());
      // 1. 접속
      Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");
      // 2. 쿼리 작성
      StringBuilder builder = new StringBuilder();
      builder.append("DELETE FROM ");
      builder.append("   RESERVATION ");
      builder.append("WHERE ");
      builder.append("    RESERV_ID =? ");
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