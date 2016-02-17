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
	
	@SuppressWarnings("resource")
	public void check(String tme,String stmt, String cde, String hst, String ip) {
		String selectPassQuery =  "SELECT * FROM Data WHERE Passcode=?";
		PreparedStatement ps;
		ResultSet rs = null;
		Connection connect = null;
		try {
			String addNewLogQuery = "INSERT INTO `Logs` (`Zeit`,`Status`,`Code`,`Host`,`IP`)VALUES(?,?,?,?,?)";
			connect = connect();
			ps = connect.prepareStatement(selectPassQuery);
			ps.setString(1, sysinfo.getCode(cde));
			rs = ps.executeQuery();
			if(rs.next()) {
				ps = connect.prepareStatement(addNewLogQuery);
				System.out.println("Success!");
				stmt = "OK";
				ps.setString(1, sysinfo.getTime(tme));
				ps.setString(2, stmt);
				ps.setString(3, sysinfo.getCode(cde));
				ps.setString(4, sysinfo.getHost(hst));
				ps.setString(5, sysinfo.getIP(ip));
				ps.executeUpdate();
			}else {
				ps = connect.prepareStatement(addNewLogQuery);
				System.out.println("Fail!");
				stmt = "Fail";
				ps.setString(1, sysinfo.getTime(tme));
				ps.setString(2, stmt);
				ps.setString(3, sysinfo.getCode(cde));
				ps.setString(4, sysinfo.getHost(hst));
				ps.setString(5, sysinfo.getIP(ip));
				ps.executeUpdate();
			}
		}catch(Exception ex) {
			System.out.println("Error");
			ex.printStackTrace();
			
		}finally{
			if(connect != null) {
				try {
					if(rs != null) {
						rs.close();
					}
					connect.setAutoCommit(true);
					connect.close();
				}catch(SQLException sx) {
					sx.printStackTrace();
				}
			}
		}
	}
}