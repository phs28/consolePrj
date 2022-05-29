package com.newlecture.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.app.entity.Notice;

public class NoticeService  {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	String query;
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String userid = "ojsag";
	String userpw = "ojsag";
	
	public List<Notice> getList(int page, String field, String text) throws Exception {
		
		int start = 1 + (page-1)*10; 
		int end = 10*page;
		
		query = "select * from notice_view where " + field + " like ? and NUM between ? and ?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, userid, userpw);
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, "%"+text+"%");
		pstmt.setInt(2, start);
		pstmt.setInt(3, end);
		rs = pstmt.executeQuery();
		
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String wirterid = rs.getString("writer_id");
			Date regDate = rs.getDate("regdate");
			String content = rs.getString("content");
			int hit = rs.getInt("hit");
			String files = rs.getString("files");
			
			Notice notice = new Notice(id, title, wirterid, regDate, content, hit, files);
			list.add(notice);
		}
		
		rs.close();
		con.close();
		pstmt.close();
		
		return list;
	}
	
	//scalar : 단위 값
	public int getCount() throws Exception {
		int count = 0;
		
		query = "select count(id) count from notice";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, userid, userpw);
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		
		if(rs.next()) {
			count = rs.getInt("count");
		}
		
		rs.close();
		con.close();
		pstmt.close();
		
		return count;
	}

	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		
		String title = notice.getTitle();
		String writerid = notice.getWirterid();
		String content = notice.getContent();
		String files = notice.getFiles();
		
		query = "INSERT INTO notice ("
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
		
		return i;
	}
	
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		
		String title = notice.getTitle();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		
		query = "update notice "
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
		
		return i;
	}
	
	public int delete(int id) throws SQLException, ClassNotFoundException {
		
		query = "delete from notice where id = ?"; 
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, userid, userpw);
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, id);
		
		int i = pstmt.executeUpdate();
		
		System.out.println(i);
		
		con.close();
		pstmt.close();
		
		return i;
	}
	
	
}
