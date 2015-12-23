package de.PCLogg.finndv;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class GUI extends JFrame {
	private static final long serialVersionUID = -7071104934829402334L;
	public JButton Ok_btn;
	public JButton Cancel_btn;
	
	
	
	private connector connection;
	
	public String status = "";
	public String time =  "";
	public String pin = "";
	
	public GUI() {
		connection = new connector();
		
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd 'at' HH:mm:ss ");
		Date currentTime = new Date();
		time = formatter.format(currentTime);
		
		//Größe und Titel vom Frame wird festgelegt;
		this.setTitle("PCLogg by Finn v1.2");
		this.setSize(400,250);
		
		//Erstelle Komponenten;
		JPasswordField password_fld = new JPasswordField();
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
			pin = new String(kennung);
			System.out.println("Pin: " + pin);
			connection.check(time,status,pin);
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
