package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class Connect {
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "stellarfest";
	private final String HOST = "localhost:3306";
	private final String URL = String.format("jdbc:mysql://%s/%s",HOST,DATABASE);
	public ResultSet rs;
	public ResultSetMetaData rsm;
	
	private Connection con;
	private Statement st;
	private static Connect connect;
	public static Connect getInstance() {
		if(connect==null)return new Connect();
		return connect;
	}
	private Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ResultSet execQuery(String query) { // Lagi SELECT dan akan mengembalikan data. (CONTOH)
		try {
			rs = st.executeQuery(query); //masukan data dari exec query ke dalam resultset berupa row
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void execUpdate(String query) { // Ketika menjalankan statement yang ngga return apa2.
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PreparedStatement prepareStatement(String query) {
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ps;
	}

}
