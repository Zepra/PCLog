package de.PCLogg.finndv;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class connector {
	private Connection DBConnection = null;
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
	
	
	public String getHost(String hst) {
		try {
			hst = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hst;
	}
	@SuppressWarnings("resource")
	public void check(String tme, String stmt, String cde, String hst) {
		String sql =  "SELECT * FROM Data WHERE Passcode=?";
		PreparedStatement ps;
		ResultSet rs = null;
		Connection connect = null;
		try {
			connect = connect();
			ps = connect.prepareStatement(sql);
			ps.setString(1, cde);
			rs = ps.executeQuery();
			if(rs.next()) {
				stmt = "OK";
				hst = getHost("");
				System.out.println("Success! ");
				String sql2 = "INSERT INTO `Loggs` (`Zeit`,`Status`,`Code`,`Host`)VALUES(?,?,?,?)";
				System.out.println(System.getProperties());
				ps = connect.prepareStatement(sql2);
				ps.setString(1, tme);
				ps.setString(2, stmt);
				ps.setString(3, cde);
				ps.setString(4, hst);
				ps.executeUpdate();
				System.out.println("Test");
			}else {
				stmt = "Fail";
				hst = getHost("");
				System.out.println("Fail!");
				String sql2 = "INSERT INTO `Loggs` (`Zeit`,`Status`,`Code`,`Host`)VALUES(?,?,?,?)";
				ps = connect.prepareStatement(sql2);
				ps.setString(1, tme);
				ps.setString(2, stmt);
				ps.setString(3, cde);
				ps.setString(4, hst);
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
