package c01a3.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.json.JSONUtil;
import c01a3.data.Data;

@SuppressWarnings("all")
public class TypeDBServlet extends HttpServlet {
	
	//Get method for type chart
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DBpath.DBpath);
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from Buildings;");
			Map map = new HashMap();
			while (rs.next()) {
				String type = rs.getString("type");
				type = type.replaceAll("\n","");
				if(map.containsKey(type)){
					map.put(type,(Integer)map.get(type)+1);
				}else{
					map.put(type,1);
				}
			}
			
			List<Data> list = new ArrayList<Data>();
			String[] colors = new String[]{"#b87333","#FF00FF","#FF0000","#00FF00","#FFFF00","#FF00FF"};
			int i = 0;
			for(Object key:map.keySet()){
				list.add(new Data((String) key,(Integer)map.get(key),colors[i]));
				i++;
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