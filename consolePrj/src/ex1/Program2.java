package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Program2 {
	public static void main(String[] args) throws Exception {
		Connection con;
		PreparedStatement pstmt;
		
		String title = "test2";
		String writerid = "newlec";
		String content = "haha";
		String files = "";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String userid = "ojsag";
		String userpw = "ojsag";
		String query = "INSERT INTO notice ("
				+ "    title,"
				+ "    writer_id,"
				+ "    content,"
				+ "    files"
				+ ") VALUES ( ?, ?, ?, ?)";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, userid, userpw);
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, title);
		pstmt.setString(2, writerid);
		pstmt.setString(3, content);
		pstmt.setString(4, files);
		int i = pstmt.executeUpdate();
		
		System.out.println(i);
		
		con.close();
		pstmt.close();
	}
}
