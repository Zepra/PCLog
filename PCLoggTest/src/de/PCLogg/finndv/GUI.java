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
	
	private String pin = "";
	
	public GUI() {
		
		DatabaseGUI dbGUI = new DatabaseGUI();
		connection = new connector();

		//Größe und Titel vom Frame wird festgelegt;
		this.setTitle("PCLog by Finn v1.2");
		this.setSize(400,250);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//Erstelle Komponenten;
		JPasswordField passwordFld = new JPasswordField("Passwort");
		JPanel content = new JPanel();
		JLabel message = new JLabel("Password:");
		JButton okBtn = new JButton("Ok!");
		JButton cancelBtn = new JButton("Cancel!");
		
		//Lege Layout fest;
		getContentPane().add(content);
		content.setLayout(null);
		
		//Füge Komponenten hinzu;
		content.add(passwordFld);
		content.add(message);
		content.add(okBtn);
		content.add(cancelBtn);

		//Position und Größe der Komponenten wird gesetzt;
		passwordFld.setBounds(101, 96, 167, 20);
		message.setBounds(101, 45, 167, 43);
		okBtn.setBounds(100, 180, 68, 20);
		cancelBtn.setBounds(200, 180, 68, 20);
		
		//ActionListener	
		okBtn.addActionListener(e -> {
			char[] kennung = passwordFld.getPassword();
			pin = (new String(kennung));
			connection.check(pin);
			this.setVisible(false);
			dbGUI.setVisible(true);
		});
		
		
		cancelBtn.addActionListener(e -> {
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