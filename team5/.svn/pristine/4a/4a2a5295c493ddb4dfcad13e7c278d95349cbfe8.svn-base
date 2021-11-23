package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "TEST";
		String pass = "1234";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, pass);
			return conn;
		} catch (Exception e) {
			System.out.println("OracleService.getConnection() : " + e.toString());
		}
		return null;

	}
//
//	public static void main(String[] args) {
//		System.out.println(getConnection());
//	}

}
