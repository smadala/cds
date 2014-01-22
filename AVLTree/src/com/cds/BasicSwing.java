package com.cds;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BasicSwing  extends JFrame{
	
	JPanel panel=new JPanel();
	JButton button = new JButton("Hello");
	JTextField textField= new JTextField("Hi");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 
		new BasicSwing();

	}
	public BasicSwing(){
		super("Basic Swing app");
		
		setSize(400, 400);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panel.add(button);
		panel.add(textField);
		add(panel);
		setVisible(true);
	}

}
