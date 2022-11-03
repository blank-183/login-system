package loginsystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 354);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel loginLabel = new JLabel("SIGN IN");
		loginLabel.setBounds(166, 21, 176, 44);
		loginLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(loginLabel);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(48, 89, 97, 44);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblEmail);
		
		usernameTextField = new JTextField();
		usernameTextField.setBackground(new Color(245, 245, 245));
		usernameTextField.setBounds(155, 89, 308, 44);
		usernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(48, 157, 97, 44);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBackground(new Color(245, 245, 245));
		passwordField.setBounds(155, 157, 308, 44);
		contentPane.add(passwordField);
		
		JButton signInBtn = new JButton("Sign in");
		signInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameTextField.setText("Login");
			}
		});
		signInBtn.setForeground(new Color(255, 255, 255));
		signInBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		signInBtn.setBounds(269, 225, 144, 44);
		signInBtn.setBackground(new Color(0, 149, 0));
		signInBtn.setOpaque(true);
		signInBtn.setBorderPainted(false);
		contentPane.add(signInBtn);
		
		JButton signUpBtn = new JButton("Sign up");
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Registration();
				dispose();
			}
		});
		signUpBtn.setForeground(new Color(255, 255, 255));
		signUpBtn.setBackground(new Color(0, 116, 232));
		signUpBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		signUpBtn.setBounds(103, 225, 144, 44);
		signUpBtn.setOpaque(true);
		signUpBtn.setBorderPainted(false);
		contentPane.add(signUpBtn);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
