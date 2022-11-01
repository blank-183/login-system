package loginsystem;

import java.awt.EventQueue;

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

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login frame = new Login();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
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
		
		JLabel usernameLabel = new JLabel("username");
		usernameLabel.setBounds(48, 87, 97, 44);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(usernameLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setBackground(new Color(249, 249, 249));
		usernameTextField.setBounds(155, 87, 308, 44);
		usernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("password");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordLabel.setBounds(48, 142, 97, 44);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBackground(new Color(249, 249, 249));
		passwordField.setBounds(155, 145, 308, 44);
		contentPane.add(passwordField);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO button action
			}
		});
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		loginBtn.setBounds(102, 225, 144, 44);
		loginBtn.setBackground(new Color(0, 128, 64));
		loginBtn.setOpaque(true);
		loginBtn.setBorderPainted(false);
		contentPane.add(loginBtn);
		
		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameTextField.setText("");
				passwordField.setText("");
			}
		});
		clearBtn.setForeground(new Color(255, 255, 255));
		clearBtn.setBackground(new Color(128, 0, 0));
		clearBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		clearBtn.setBounds(269, 225, 144, 44);
		clearBtn.setOpaque(true);
		clearBtn.setBorderPainted(false);
		contentPane.add(clearBtn);
	}
}
