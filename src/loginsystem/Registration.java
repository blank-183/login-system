package loginsystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField addressTextField;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JPasswordField rePasswordField;

	public Registration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 660);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel signUpLabel = new JLabel("CREATE AN ACCOUNT");
		signUpLabel.setBounds(108, 26, 342, 44);
		signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signUpLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		contentPane.add(signUpLabel);
		
		JLabel lblFirstName = new JLabel("First name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFirstName.setBounds(58, 96, 109, 44);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLastName.setBounds(58, 166, 109, 44);
		contentPane.add(lblLastName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(58, 376, 109, 44);
		contentPane.add(lblPassword);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(58, 306, 109, 44);
		contentPane.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddress.setBounds(58, 236, 109, 44);
		contentPane.add(lblAddress);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.setForeground(new Color(255, 255, 255));
		registerBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		registerBtn.setBounds(300, 522, 150, 44);
		registerBtn.setBackground(new Color(0, 149, 0));
		registerBtn.setOpaque(true);
		registerBtn.setBorderPainted(false);
		contentPane.add(registerBtn);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		firstNameTextField.setBackground(new Color(245, 245, 245));
		firstNameTextField.setBounds(188, 99, 318, 44);
		contentPane.add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lastNameTextField.setBackground(new Color(245, 245, 245));
		lastNameTextField.setColumns(10);
		lastNameTextField.setBounds(188, 169, 318, 44);
		contentPane.add(lastNameTextField);
		
		addressTextField = new JTextField();
		addressTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addressTextField.setBackground(new Color(245, 245, 245));
		addressTextField.setColumns(10);
		addressTextField.setBounds(188, 239, 318, 44);
		contentPane.add(addressTextField);
		
		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		emailTextField.setBackground(new Color(245, 245, 245));
		emailTextField.setColumns(10);
		emailTextField.setBounds(188, 309, 318, 44);
		contentPane.add(emailTextField);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBackground(new Color(245, 245, 245));
		passwordField.setBounds(188, 379, 318, 44);
		contentPane.add(passwordField);
		
		JLabel lblRePassword = new JLabel("Re Password:");
		lblRePassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRePassword.setBounds(58, 445, 120, 44);
		contentPane.add(lblRePassword);
		
		rePasswordField = new JPasswordField();
		rePasswordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rePasswordField.setBackground(new Color(245, 245, 245));
		rePasswordField.setBounds(188, 448, 318, 44);
		contentPane.add(rePasswordField);
		
		JButton signInBtn = new JButton("Sign in");
		signInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				dispose();
			}
		});
		signInBtn.setOpaque(true);
		signInBtn.setForeground(Color.WHITE);
		signInBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		signInBtn.setBorderPainted(false);
		signInBtn.setBackground(new Color(0, 116, 232));
		signInBtn.setBounds(108, 522, 150, 44);
		contentPane.add(signInBtn);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
