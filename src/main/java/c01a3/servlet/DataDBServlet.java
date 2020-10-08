package c01a3.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.json.JSONUtil;
import c01a3.data.DataList;

@SuppressWarnings("all")
public class DataDBServlet extends HttpServlet {
	
	//Get method for rental list
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		try{
			//Connect to a3kijiji database
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DBpath.DBpath);
			Statement state = conn.createStatement();
			
			//Select all data from Buildings table
			ResultSet rs = state.executeQuery("select * from Buildings;");
			List<DataList> list = new ArrayList<DataList>();
			while (rs.next()) {
				int buildID = rs.getInt("buildID");
				long price = rs.getInt("price");
				String bedrooms = rs.getString("bedrooms");
				float bathrooms = rs.getFloat("bathrooms");
				int size = rs.getInt("size");
				String type = rs.getString("type");
				String address = rs.getString("address");
				DataList dataList = new DataList(buildID, type, address, price, bedrooms, bathrooms, size);
				list.add(dataList);
			}
			
			out.println(JSONUtil.toJsonStr(list));
			//Close connection
			rs.close();
			conn.close();
			out.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
