package de.PCLogg.finndv;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class GUI extends JFrame {
	private static final long serialVersionUID = -7071104934829402334L;
	SystemInformation sysinfo = new SystemInformation();
	
	public JButton Ok_btn;
	public JButton Cancel_btn;
	private connector connection;
	
	public String time =  "";
	private String pin = "";
	public String hostname = "";
	public String ipaddress = "";
	public String status = "";
	
	public GUI() {
		
		connection = new connector();

		//Größe und Titel vom Frame wird festgelegt;
		this.setTitle("PCLogg by Finn v1.2");
		this.setSize(400,250);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//Erstelle Komponenten;
		JPasswordField password_fld = new JPasswordField("Passwort");
		JPanel content = new JPanel();
		JLabel message = new JLabel("Password:");
		JButton Ok_btn = new JButton("Ok!");
		JButton Cancel_btn = new JButton("Cancel!");
		
		//Lege Layout fest;
		getContentPane().add(content);
		content.setLayout(null);
		
		//Füge Komponenten hinzu;
		content.add(password_fld);
		content.add(message);
		content.add(Ok_btn);
		content.add(Cancel_btn);

		//Position und Größe der Komponenten wird gesetzt;
		password_fld.setBounds(101, 96, 167, 20);
		message.setBounds(101, 45, 167, 43);
		Ok_btn.setBounds(100, 180, 68, 20);
		Cancel_btn.setBounds(200, 180, 68, 20);
		
		//ActionListener	
		Ok_btn.addActionListener(e -> {
			char[] kennung = password_fld.getPassword();
			pin = (new String(kennung));
			System.out.println("Pin: " + pin);
			sysinfo.getCode(pin);
			connection.check(time,status,pin,hostname,ipaddress);
			this.setVisible(false);
		});
		
		
		Cancel_btn.addActionListener(e -> {
			try {
				//Process p = Runtime.getRuntime().exec("shutdown -s");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
	}
	
	public static void main(String[]args) {
		GUI gui = new GUI();
		gui.setVisible(true);
		
	}

}