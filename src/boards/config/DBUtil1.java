package boards.config;

// JDBC 드라이버를 로딩하고 Connection객체를 생성하는 메서드로 구성된 class 만들기
// (dbinfo.properties파일의 내용으로 설정하는 방법)

// 방법1 : properties객체 이용하기

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil1 {
    private static Properties prop; // Properties객체 변수 선언 - static으로 선언     (static 메서드 안에서 사용하기 위해)
    // static 초기화 블럭


    static {
        prop = new Properties(); //Properties객체 생성
        File f = new File("src/jdbc/advanced/boards/config/dbinfo.properties");
        FileInputStream fin = null;

        try {
            fin = new FileInputStream(f); // 입력 스트림 객체 생
            prop.load(fin);


            Class.forName(prop.getProperty("driver"));

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("설정 파일이 없습니다.");
            System.out.println("드라이버 로딩 실패");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("입출력 오류");
            System.out.println("드라이버 로딩 실패");
            e.printStackTrace();

        } finally {
            if(fin!=null)try {fin.close();}catch(IOException e) {}
        }

    }

    public static Connection getConnection() {
        try {

            return DriverManager.getConnection(
                    prop.getProperty("url"),
                    prop.getProperty("user"),
                    prop.getProperty("password"));

        } catch (SQLException e) {
            System.out.println("DB 연결 실패!!!");
            return null;
        }
    }

}