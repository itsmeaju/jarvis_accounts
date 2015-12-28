package pkg_main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class jam_login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	Connection connection = null;
	 String newLine = System.getProperty("line.separator");//This will retrieve line separator dependent on OS.
    
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jam_login frame2 = new jam_login();
					frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public jam_login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		connection= db_helper.dbConnector();
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseLoginTo = new JLabel("Please Login to Continue!");
		lblPleaseLoginTo.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 20));
		lblPleaseLoginTo.setBounds(26, 29, 353, 30);
		contentPane.add(lblPleaseLoginTo);
		
		username = new JTextField();
		username.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		username.setBounds(238, 98, 99, 28);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		lblName.setBounds(151, 103, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		lblPassword.setBounds(132, 147, 96, 14);
		contentPane.add(lblPassword);
		
		password = new JPasswordField();
		password.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		password.setBounds(238, 142, 99, 28);
		contentPane.add(password);
		
		JButton btnLogin = new JButton("LOG IN");
		btnLogin.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 11));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query="select * from JAM_USER where user_name=? and user_pwd=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,username.getText());
					pst.setString(2,password.getText());
					ResultSet rs = pst.executeQuery();
					int count=0;
					while(rs.next()){
						
						count++;
					}
					if(count==1){
				//	JOptionPane.showMessageDialog(null,"Login Successful");
					
					
					jam_review jr= new jam_review();
					setVisible(false);
					jr.setVisible(true);
				//	sf1.setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(null,"Enter Correct Password Or Create the User "+newLine+" For Support Contact Ajay +919952740077 ","Jarvis Accounts Manager", JOptionPane.ERROR_MESSAGE);
						}
					rs.close();
					pst.close();
				}catch(Exception se){
					JOptionPane.showMessageDialog(null,se);
				}
			}
			});
		btnLogin.setBounds(238, 205, 99, 27);
		contentPane.add(btnLogin);
		
		JButton btnNewToJam = new JButton("Create User");
		btnNewToJam.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 11));
		btnNewToJam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 //CREATE THE USER AND INSERT INTO JAM_USER TABLE
				try{
					
					String create_query="CREATE TABLE '" + username.getText() + "' ('ID' TEXT PRIMARY KEY  NOT NULL , 'DATE' TEXT, 'CODE' TEXT, 'NumOfPieces' INTEGER DEFAULT 0,'WEIGHT' DOUBLE DEFAULT 0.000,'PARTICULAR' TEXT, 'DEBIT' DOUBLE DEFAULT 0.00, 'CREDIT' DOUBLE DEFAULT 0.00)";
					PreparedStatement pst2 = connection.prepareStatement(create_query);
					pst2.executeUpdate();
					String insert_query="INSERT INTO JAM_USER(user_name,user_pwd,user_cr,user_db,user_bal) VALUES(?,?,?,?,?)";
					PreparedStatement pst3 = connection.prepareStatement(insert_query);
					pst3.setString(1,username.getText());
					pst3.setString(2,password.getText());
					//pst2.setString(3,);
					//pst2.setString(4,password.getText());
					//pst2.setString(5,username.getText());
					
					
					pst3.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"User Created Successfully, Please Login!","Jarvis Account Manager", JOptionPane.INFORMATION_MESSAGE);
				//	frame.dispose();
				//	Second_frame sf1= new Second_frame();
				//	sf1.setVisible(true);
					
						
						
					
					pst2.close();
					pst3.close();
				}catch(Exception se){
					//JOptionPane.showMessageDialog(null,se);
					JOptionPane.showMessageDialog(null,se.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		});
		btnNewToJam.setBounds(113, 205, 115, 27);
		contentPane.add(btnNewToJam);
	}
}
