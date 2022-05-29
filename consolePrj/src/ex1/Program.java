package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class Program {
	public static void main(String[] args) throws Exception {
		Connection con;
		Statement stmt;
		ResultSet rs;
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String userid = "ojsag";
		String userpw = "ojsag";
		String query = "select * from notice";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, userid, userpw);
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);

		while(rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String wirterid = rs.getString("writer_id");
			Date regDate = rs.getDate("regdate");
			String content = rs.getString("content");
			int hit = rs.getInt("hit");
			
			System.out.println(id + " " + title + " " + wirterid + " " + regDate + " " + content + " " + hit );

		}
		
		rs.close();
		con.close();
		stmt.close();
	}
}
