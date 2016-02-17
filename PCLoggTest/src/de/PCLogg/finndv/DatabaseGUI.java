package de.PCLogg.finndv;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DatabaseGUI extends JFrame {
	private static final long serialVersionUID = -3356267632702030156L;
	

	public JButton btn_cncl;
	public JPanel content;
	
	public DatabaseGUI() {
		this.setTitle("PCLog by Finn v1.2");
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Erstelle Componente
		JButton btn_cncl = new JButton("Close");
		JPanel content = new JPanel();
		
		//Lege Layout fest:
		getContentPane().add(content);
		content.setLayout(null);
		
		//Füge content hinzu
		content.add(btn_cncl);
		
		//setze position
		btn_cncl.setBounds(290, 420, 100, 30);
	}
}
