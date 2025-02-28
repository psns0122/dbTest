package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ssgdb?serverTimezone=Asia/Seoul",
                    "ssg",
                    "ssg1234"
            );
        }
        catch (SQLException e) {
            System.out.println("연결 실패!");
            return null;
        }
    }

}
