package de.PCLogg.finndv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class connector {
	private Connection DBConnection = null;
	SystemInformation sysinfo = new SystemInformation();
	
	public Connection connect() {
		System.out.println("Connect");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connection Success");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Connection Fail" + cnfe);
		}
		String url = "jdbc:mysql://192.168.2.111:3306/LoggDB";
		try {
			DBConnection = DriverManager.getConnection(url, "root", "qwertz00");
			System.out.println("Database Connected!");
		} catch (SQLException se) {
			System.out.println("No database " + se);
		}
		return DBConnection;
	}
	
	public void check(String passwd) {
		String selectPassQuery =  "SELECT * FROM Data WHERE Passcode=?";
		PreparedStatement ps;
		ResultSet rs = null;
		Connection connect = null;
		try {
			connect = connect();
			ps = connect.prepareStatement(selectPassQuery);
			ps.setString(1, passwd);
			rs = ps.executeQuery();
			if(rs.next()) {
				update("OK", passwd);
				connect.close();
			}else {
				update("FAIL", passwd);
				connect.close();
			}
		}catch(Exception ex) {
			System.out.println("Error");
			ex.printStackTrace();
		
		}
	}
	
	public void update(String status, String passwd) {
		String addNewLogQuery = "INSERT INTO `Logs` (`Zeit`,`Status`,`Code`,`Host`,`IP`)VALUES(?,?,?,?,?)";
		PreparedStatement ps;
		Connection connect = null;
		try {
			connect = connect();
			ps = connect.prepareStatement(addNewLogQuery);
			System.out.println("Fail!");
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