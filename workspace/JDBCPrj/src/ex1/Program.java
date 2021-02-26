package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@192.168.77.1:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con= DriverManager.getConnection(url,"NEWLEC","1234");
		Statement st = con.createStatement();
		ResultSet rs= st.executeQuery(sql);
		

		
		while(rs.next()) {
		int id =rs.getInt("ID");
		String title = rs.getString("TITLE");
		String writer = rs.getString("WRITER_ID");
		Date regDate = rs.getDate("REGDATE");
		String content = rs.getString("CONTENT");
		int hit =rs.getInt("hit");
		System.out.printf("id:%d, title:%s, writerId:%s, regDate:%s, content:%s, hit:%d\n",id,title,writer,regDate,content,hit);
	}
		
		
//		if(rs.next()) {
//			int id =rs.getInt("ID");
//			String title = rs.getString("TITLE");
//			String writer = rs.getString("WRITER_ID");
//			Date regDate = rs.getDate("REGDATE");
//			String content = rs.getString("CONTENT");
//			int hit =rs.getInt("hit");
//			System.out.printf("id:%d, title:%s, writerId:%s, regDate:%s, content:%s, hit:%d\n",id,title,writer,regDate,content,hit);
//		}
//		
		rs.close();
		st.close();
		con.close();
		
	}

}
