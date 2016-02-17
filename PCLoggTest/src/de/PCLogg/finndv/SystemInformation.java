package de.PCLogg.finndv;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemInformation {

	public String getHost() {
		try {
			return InetAddress.getLocalHost().getHostName();
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.YYYY 'at' HH:mm:ss ");
		Date currentTime = new Date();
		String tme = formatter.format(currentTime);
		return tme;
	}
	
}