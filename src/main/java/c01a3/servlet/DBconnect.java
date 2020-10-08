package c01a3.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import c01a3.servlet.DBpath;

public class DBconnect {
	
	private Connection conn;
	private Statement state;
	
	public void connection() {
		try{
			//Connect to a3kijiji database
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + DBpath.DBpath);
			state = conn.createStatement();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public String getTable() {
		try {
			String s = "select name from sqlite_master where type='table';";
			ResultSet rs = state.executeQuery(s);
			if (rs.next()) {
				return rs.getString(1);
			}
			else {
				return "no table exists";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "no table exists";
		}
	}
}
