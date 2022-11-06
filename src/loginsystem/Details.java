package loginsystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Details extends Message {
	
	private JFrame frame = new JFrame();
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JTextField addressTextField;

	public Details(int userId) {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 580, 425);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGreeting = new JLabel("Welcome back, User!");
		lblGreeting.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGreeting.setBounds(28, 21, 368, 44);
		contentPane.add(lblGreeting);
		
		JLabel lblName = new JLabel("Full name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(28, 93, 88, 44);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(28, 159, 88, 44);
		contentPane.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddress.setBounds(28, 225, 88, 44);
		contentPane.add(lblAddress);
		
		JButton logOutBtn = new JButton("Log out");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(confirmMessage("Do you want to log out?", contentPane) == JOptionPane.YES_OPTION) {
					new Login();
					frame.dispose();
				}
			}
		});
		logOutBtn.setForeground(new Color(255, 255, 255));
		logOutBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		logOutBtn.setBounds(208, 302, 148, 44);
		logOutBtn.setBackground(new Color(140, 0, 0));
		logOutBtn.setOpaque(true);
		logOutBtn.setBorderPainted(false);
		contentPane.add(logOutBtn);
		
		nameTextField = new JTextField();
		nameTextField.setEditable(false);
		nameTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nameTextField.setBackground(new Color(255, 255, 255));
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameTextField.setBounds(126, 94, 400, 44);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setEditable(false);
		emailTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		emailTextField.setBackground(new Color(255, 255, 255));
		emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailTextField.setColumns(10);
		emailTextField.setBounds(126, 159, 400, 44);
		contentPane.add(emailTextField);
		
		addressTextField = new JTextField();
		addressTextField.setEditable(false);
		addressTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		addressTextField.setBackground(new Color(255, 255, 255));
		addressTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addressTextField.setColumns(10);
		addressTextField.setBounds(126, 225, 400, 44);
		contentPane.add(addressTextField);

		getUserDetails(userId);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void getUserDetails(int userId) {
		Connection conn = null;
		ResultSet rs = null;
	    conn = ConnectionManager.getConnection();
	    String query = "SELECT first_name, last_name, email, address FROM users WHERE user_id = ?;";
	    
    	try(PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setLong(1, userId);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String fullName = firstName + " " + lastName;
				String email = rs.getString("email");
				String address = rs.getString("address");
				
				nameTextField.setText(fullName);
				emailTextField.setText(email);
				addressTextField.setText(address);
			}
			conn.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
