package dbtest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import c01a3.servlet.DBconnect;

public class DBtest {
	
	private DBconnect db;
	
	@Test
	public void TestConnection() {
		db = new DBconnect();
	}
	
	@Test
	public void TestTable_null() {
		db = new DBconnect();
		assertThat(db.getTable(), not(equalTo(null)));
	}
	
	@Test
	public void TestTable_empty() {
		db = new DBconnect();
		assertThat(db.getTable(), not(equalTo("")));
	}
	
	@Test
	public void TestTable_value() {
		db = new DBconnect();
		assertThat(db.getTable(), equalTo("Buildings"));
	}
	
	@Test
	public void TestInsert() {
		
	}
}
