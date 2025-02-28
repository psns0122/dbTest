package jdbc.boards;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class boardDelete {
    public static void main(String[] args) throws ClassNotFoundException {
        Connection connection = null;

        try
        {
            // 1. JDBC 드라이버 등록 : MYSQL DB 접근하기 위한 드라이버 등록
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded OK! " + connection);

            // 2. Mysql DB에 연결객체를 얻어와서 연결하기
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssgdb?serverTimezone=Asia/Seoul","ssg", "ssg1234");
            System.out.println("Connection OK! " + connection);

            // 3. 깔끔하게 매개변수화된 SQL 문 작성하기
            String query = new StringBuilder()
                    .append("DELETE FROM boards")
                    .append(" WHERE bno = ?")
                    .toString();

            // PreparedStatement : query를 담아서 connection을 타고 내가 지정한 db의 users 테이블로 insert를 날리겠다.
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, 4);

            // 4. SQL문 실행
            // DBMS로 출발시키는 명령어
            int rows = pstmt.executeUpdate();
            System.out.println("deleted rows : " + rows);

            // 5. PreparedStatement 객체 닫기 : pstmt 자동차 반납
            pstmt.close();
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("connection closed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
