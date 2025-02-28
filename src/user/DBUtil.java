package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static Connection getConnection() {
        try{
            // 1. JDBC 드라이버 등록 : MYSQL DB 접근하기 위한 드라이버 등록
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Mysql DB에 연결객체를 얻어와서 연결하기
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ssgdb?serverTimezone=Asia/Seoul",
                    "ssg",
                    "ssg1234"
            );
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (SQLException e) {
            System.out.println("연결 실패!");
            return null;
        }
    }

}
