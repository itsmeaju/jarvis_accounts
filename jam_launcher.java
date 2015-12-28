package pkg_main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class jam_launcher {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jam_launcher window = new jam_launcher();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public jam_launcher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("WELCOME");
		frame.getContentPane().setBackground(Color.RED);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" ACCOUNTS");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("DialogInput", Font.PLAIN, 22));
		lblNewLabel.setBounds(248, 98, 136, 102);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnContinue = new JButton("CONTINUE >>");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				jam_login jl= new jam_login();
				jl.setVisible(true);
				
			}
		});
		
		JLabel lblJarvis = new JLabel("J.A.R.V.I.S.");
		lblJarvis.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblJarvis.setBounds(69, 45, 161, 101);
		frame.getContentPane().add(lblJarvis);
		btnContinue.setFont(new Font("Mistral", Font.PLAIN, 22));
		btnContinue.setBackground(Color.WHITE);
		btnContinue.setBounds(253, 200, 148, 31);
		frame.getContentPane().add(btnContinue);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setEnabled(false);
		btnNewButton.setBackground(new Color(255, 255, 0));
		btnNewButton.setBounds(30, 34, 184, 126);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(new Color(128, 0, 0));
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(59, 74, 184, 157);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBackground(new Color(0, 0, 139));
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setBounds(201, 118, 223, 57);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setBackground(new Color(124, 252, 0));
		btnNewButton_3.setBounds(130, 11, 209, 157);
		frame.getContentPane().add(btnNewButton_3);
	}
}
