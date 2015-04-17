package org.kd.singh.db;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import org.kd.singh.classes.Crawler;
import org.kd.singh.classes.KDCrawlingApp;

public class Database {
	final private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DATABASE_NAME;
	private final String DATABASE_URL;
	Connection conn = null;
	
	public Database(String db_name, String user_name, String password) throws ClassNotFoundException, SQLException {
		this.DATABASE_NAME = db_name;
		this.DATABASE_URL = "jdbc:mysql://localhost/"+this.DATABASE_NAME;
		
		Class.forName(JDBC_DRIVER);
		
		//open a connection
		conn = DriverManager.getConnection(DATABASE_URL, user_name, password);
//		System.out.println(conn);
	}
	
	private boolean hasUrlInDatabase(String url) throws SQLException{
		PreparedStatement stmt_check_sql_query = conn.prepareStatement("Select 1 from websites where url=?");
		stmt_check_sql_query.setString(1, url);
		boolean hasRows = false;

		if(stmt_check_sql_query.execute()){
			ResultSet rs_select_rows = stmt_check_sql_query.getResultSet();
			while(rs_select_rows.next()){
				hasRows = true;
				break;
			}
		}
		return hasRows;
	}
	
	private int mainUrl(Crawler main) throws SQLException, IOException{
		int inserted_id = 0;
		 /* Insert main website url */

		if(!hasUrlInDatabase(main.getUrl())){ //main url is not in the database
			PreparedStatement stmt = conn.prepareStatement("insert into websites(title, url, author, description, total_hits, page_rank) values(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, main.getTitle());
			stmt.setString(2, main.getUrl());
			stmt.setString(3, main.getAuthor());
			stmt.setString(4, main.getDescription());
			stmt.setInt(5, 0);
			stmt.setInt(6, 1);
			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				inserted_id = rs.getInt(1);
			}
		}else{
			String return_sql = "Select website_id as id from websites where url=?";
			PreparedStatement stmt = conn.prepareStatement(return_sql);
			stmt.setString(1, main.getUrl());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				inserted_id = rs.getInt("id");
			}
		}
		/* Insert Keywords */
		
		//fetching all keywords in string array
		String[] strKeywords_array = main.getKeyWords().split(",");
		String insert_query = "insert ignore into keywords(keywords, website_id)values(?,?)"; 
		PreparedStatement stmt = conn.prepareStatement(insert_query);

		for (String keyword : strKeywords_array) {
			stmt.setString(1, keyword);
			stmt.setInt(2, inserted_id);
			stmt.addBatch();
		}
		int[] rs = stmt.executeBatch();
		
		return inserted_id;
	}
	
	private boolean insertSubLinks(Crawler sub_page, int id) throws SQLException, IOException{
		PreparedStatement stmt = conn.prepareStatement("insert ignore into websites(title, url, parent_id, author, description, total_hits, page_rank) values(?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setString(1, sub_page.getTitle());
		stmt.setString(2, sub_page.getUrl());
		stmt.setInt(3, id);
		stmt.setString(4, sub_page.getAuthor());
		stmt.setString(5, sub_page.getDescription());
		stmt.setInt(6, 0);
		stmt.setInt(7, 2);
		return stmt.execute();
		
	}
	
	public void saveInfo(ArrayList<Crawler> list, KDCrawlingApp base) throws SQLException, IOException{
		Crawler main = base.getBasePage();
		int inserted_id = 0;
		/*
		 * WORKING WITH MAIN WEBSITE
		 */
		inserted_id = this.mainUrl(main);
		/*
		 * WORKING WITH SUB LINKS OF MAIN WEBSITE
		 */
		for (Crawler crawler : list) {
			insertSubLinks(crawler, inserted_id);
		}		
		
		
	}
	
}
