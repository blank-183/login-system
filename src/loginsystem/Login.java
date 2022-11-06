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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Login extends Message {

	private JFrame frame = new JFrame();
	private JPanel contentPane;
	private JTextField emailTextField;
	private JPasswordField passwordField;

	public Login() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 525, 354);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
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
		
		emailTextField = new JTextField();
		emailTextField.setBackground(new Color(245, 245, 245));
		emailTextField.setBounds(155, 89, 308, 44);
		emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
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
			    if(isComplete() && isEmailValid() && isPasswordValid()) {
			    	Connection conn = null;
				    conn = ConnectionManager.getConnection();
				    String query = "SELECT * FROM users WHERE email = ? LIMIT 1";
				    
				    if(conn != null) {
				    	getResultSet(conn, query);
				    } else {
						errorMessage("There is something wrong with the server.", contentPane);
					}
			    } else if (!isComplete()){
					errorMessage("Fields cannot be empty!", contentPane);
				} else if(!isEmailValid()) {
					errorMessage("Please enter a valid email!", contentPane);
				} else if (!isPasswordValid()) {
					errorMessage("Password length should be atleast 8 characters!", contentPane);
				} else {
					errorMessage("There is something wrong with the server.", contentPane);
				}
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
				frame.dispose();
			}
		});
		signUpBtn.setForeground(new Color(255, 255, 255));
		signUpBtn.setBackground(new Color(0, 116, 232));
		signUpBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		signUpBtn.setBounds(103, 225, 144, 44);
		signUpBtn.setOpaque(true);
		signUpBtn.setBorderPainted(false);
		contentPane.add(signUpBtn);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
    private boolean isComplete() {
    	return !(emailTextField.getText().equals("") ||
    			 new String(passwordField.getPassword()).equals(""));
    }
    
    private boolean isPasswordValid() {
    	return (passwordField.getPassword().length >= 8);
    }
    
    private boolean isEmailValid() {
    	String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[\\a-zA-Z]{2,6}"; 

        Pattern pat = Pattern.compile(emailRegex);
        if (emailTextField.getText() == null)
            return false;
        return pat.matcher(emailTextField.getText()).matches();
    }
    
    private void readResultSet(ResultSet rst) {
    	Integer userId = null;
	    String email = emailTextField.getText();
	    String password = new String(passwordField.getPassword());
	    
	    try {
			while(rst.next()) {
				userId = rst.getInt("user_id");
				System.out.println(rst.getInt("user_id"));

				if(email.equals(rst.getString("email")) && toHexString(getSHA(password)).equals(rst.getString("password"))) {
					successMessage("User found! You are now logged in.", contentPane);
					new Details(userId);
					frame.dispose();
				} else {
					errorMessage("Wrong email or password", contentPane);
				}
			}
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
    }
    
    private void getResultSet(Connection con, String qry) {
    	ResultSet rst = null;
    	try(PreparedStatement stmt = con.prepareStatement(qry)) {
			stmt.setString(1, emailTextField.getText());
			rst = stmt.executeQuery();
			
			if (rst.isBeforeFirst()) {
				readResultSet(rst);
			} else {
				errorMessage("Wrong email or password!", contentPane);
			}
			con.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
    }
}
