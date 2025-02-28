package boards.config;

// JDBC 드라이버를 로딩하고 Connection객체를 생성하는 메서드로 구성된 class 만들기
//( dbinfo.properties파일의 내용으로 설정하는 방법)

// 방법2 : ResourceBundle객체 이용하기

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {
    private static ResourceBundle bundle; // ResourceBundle객체 변수 선언

    // static 초기화 블럭 - 왜 초기화블럭에서 실행할까?
    static {
        bundle = ResourceBundle.getBundle("src/jdbc/advanced/boards/config/dbinfo");

        try {
            Class.forName(bundle.getString("driver"));
            //Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패~~~");
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        try {
            //return DriverManager.getConnection("jdbc:mysql://localhost:3306/ssg?serverTimezone=Asia/Seoul","root", "mysql1234");
            return DriverManager.getConnection(
                    bundle.getString("url"),
                    bundle.getString("user"),
                    bundle.getString("password"));
        } catch (SQLException e) {
            System.out.println("연결 실패!!!");
            return null;
        }
    }

}