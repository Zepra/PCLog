package de.PCLogg.finndv;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemInformation {

	public String getHost(String hst) {
		try {
			hst = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hst;
	}
	
	
	public String getIP(String ip) {
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		return ip;
	}
	
	
	public String getTime(String tme) {
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd 'at' HH:mm:ss ");
		Date currentTime = new Date();
		tme = formatter.format(currentTime);
		return tme;
	}
	
	public String getCode(String cde) {
		return cde;
	}
}
