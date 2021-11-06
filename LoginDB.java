import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;

public class LoginDB extends JFrame   {
	
	Connection connection=null;
	
	public static JFrame frame;
	public static String cashierName;
	public static JTextField textField;
	private JPasswordField passwordField;

	public LoginDB() {
		initialize();
		connection=sqliteConnection.dbConnector();
		
		
		
	}

	public void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cashier Name");
		lblNewLabel.setBounds(88, 77, 89, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(88, 120, 89, 14);
		frame.getContentPane().add(lblNewLabel_1);

		
		textField = new JTextField();
		textField.setBounds(220, 74, 105, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		frame.setLocationRelativeTo(null);
		//frame.pack();
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String query="select * from Login where username=? and password=?";
					PreparedStatement pst=connection.prepareStatement(query); 
					pst.setString(1, textField.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next()){
						count +=1;
					}
					if(count==1){
						JOptionPane.showMessageDialog(null, "Username and password is correct","Login", count);
						Menu menu = new Menu();
						menu.launchFrame();
					}
					else{
						JOptionPane.showMessageDialog(null, "Username or password not correct!");
					}
					rs.close();
					pst.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
					
				}

			
			}
		
		});
		btnNewButton.setBounds(162, 161, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(220, 117, 105, 20);
		frame.getContentPane().add(passwordField);
		
	
	}
	
}
