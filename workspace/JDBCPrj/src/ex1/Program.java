package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@192.168.137.1:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con= DriverManager.getConnection(url,"newlec","1234");
		Statement st = con.createStatement();
		ResultSet rs= st.executeQuery(sql);
		
		if(rs.next()) {
			String title = rs.getString("title");
			System.out.println();
		}
		rs.close();
		st.close();
		con.close();
		
	}

}
