package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Program3 {
	public static void main(String[] args) throws Exception {
		Connection con;
		PreparedStatement pstmt;
		
		String title = "test3";
		String content = "hahaha3";
		String files = "";
		int id = 3;
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String userid = "ojsag";
		String userpw = "ojsag";
		String query = "update notice "
				+ "set"
				+ "    title=?,"
				+ "   content=?,"
				+ "    files=? "
				+ "where id = ?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, userid, userpw);
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, files);
		pstmt.setInt(4, id);
		
		int i = pstmt.executeUpdate();
		
		System.out.println(i);
		
		con.close();
		pstmt.close();
	}
}
