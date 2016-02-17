package de.PCLogg.finndv;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class GUI extends JFrame {
	private static final long serialVersionUID = -7071104934829402334L;	
	public JButton okBtn;
	public JButton cancelBtn;
	private Connector connector;
	
	public GUI() {		
		DatabaseGUI dbGUI = new DatabaseGUI();
		connector = new Connector();
		try {
			connector.establishConnection();
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}

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
			String pin = new String(passwordFld.getPassword());
			connector.check(pin);
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
		
		addWindowListener(new WindowListener() {			
			@Override
			public void windowOpened(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}
			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e) {
				connector.closeConnection();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
	}
	
	public static void main(String[]args) {
		GUI gui = new GUI();
		gui.setVisible(true);		
	}
}