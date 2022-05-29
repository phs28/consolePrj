package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Program4 {
	public static void main(String[] args) throws Exception {
		Connection con;
		PreparedStatement pstmt;
		
		int id = 4;
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String userid = "ojsag";
		String userpw = "ojsag";
		String query = "delete from notice where id = ?"; 
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, userid, userpw);
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, id);
		
		int i = pstmt.executeUpdate();
		
		System.out.println(i);
		
		con.close();
		pstmt.close();
	}
}
