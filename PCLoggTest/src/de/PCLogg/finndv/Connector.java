package de.PCLogg.finndv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connector {
	private Connection dbConnection;
	SystemInformation sysinfo = new SystemInformation();
	
	public void establishConnection() throws ClassNotFoundException, SQLException {
		System.out.println("Connect");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connection Success");
		String url = "jdbc:mysql://192.168.2.111:3306/LoggDB";
			dbConnection = DriverManager.getConnection(url, "root", "qwertz00");
			System.out.println("Database Connected!");
	}
	
	public void closeConnection(){
		try {
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void check(String passwd) {
		String selectPassQuery =  "SELECT * FROM Data WHERE Passcode=?";
		PreparedStatement ps;
		ResultSet rs = null;
		try {
			ps = dbConnection.prepareStatement(selectPassQuery);
			ps.setString(1, passwd);
			rs = ps.executeQuery();
			if(rs.next()) {
				update("OK", passwd);
			}else {
				update("FAIL", passwd);
			}
		}catch(Exception ex) {
			System.out.println("Error");
			ex.printStackTrace();
		}
	}
	
	public void update(String status, String passwd) {
		String addNewLogQuery = "INSERT INTO `Logs` (`Zeit`,`Status`,`Code`,`Host`,`IP`)VALUES(?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = dbConnection.prepareStatement(addNewLogQuery);
			ps.setString(1, sysinfo.getTime());
			ps.setString(2, status);
			ps.setString(3, passwd);
			ps.setString(4, sysinfo.getHost());
			ps.setString(5, sysinfo.getIP());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}