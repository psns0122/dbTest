package jdbc.boards;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class boardInsert {
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
                    .append("INSERT INTO boards")
                    .append(" (btitle, bcontent, bwriter, bdate, bfilename, bfiledata)")
                    .append(" VALUES")
                    .append(" (?, ?, ?, now(), ?, ?)")
                    .toString();

            // PreparedStatement : query를 담아서 connection을 타고 내가 지정한 db의 users 테이블로 insert를 날리겠다.
            // Statement.RETURN_GENERATED_KEYS : auto_increment 설정값을 받아옴
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, "봄이로구나");
            pstmt.setString(2, "봄이 오는데 구경은 못하고 강의실에 잡혀서 jdbc 프로그래밍을 하는 나!");
            pstmt.setString(3, "psns");
            // now() : ? 가 아니므로 넘어간다
            pstmt.setString(4, "spring.jpg");
            pstmt.setBlob(5, new FileInputStream("src/jdbc/images/spring.jpg"));

            // 4. SQL문 실행
            // DBMS로 출발시키는 명령어
            int rows = pstmt.executeUpdate();
            System.out.println("inserted rows : " + rows);

            // bno 값 가져오기
            if (rows == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int bno = rs.getInt(1);
                    System.out.println("now bno : " + bno);
                }
                rs.close();
            }

            // 5. PreparedStatement 객체 닫기 : pstmt 자동차 반납
            pstmt.close();
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (FileNotFoundException e) {
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
