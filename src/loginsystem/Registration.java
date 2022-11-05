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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Registration extends HelperMethods {

	private JFrame frame = new JFrame();
	private JPanel contentPane;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField addressTextField;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JPasswordField rePasswordField;

	public Registration() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 575, 660);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
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
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isValidLength()) {
					return;
				}
				if(isComplete() && isEmailValid() && !isEmailTaken() && isSamePassword() && isPasswordValid()) {
					Connection conn = null;
					conn = ConnectionManager.getConnection();
				    String query = "INSERT INTO USERS (first_name, last_name, address, email, password)" + " values (?, ?, ?, ?, ?)";
				    
				    if(conn != null) {
				    	runQuery(conn, query);
						successMessage("Your registration is successful! You can now log in.", contentPane);
						new Login();
						frame.dispose();
				    } else {
						errorMessage("There is something wrong with the server.", contentPane);
					}
				} else if (!isComplete()){
					errorMessage("Fields cannot be empty!", contentPane);
				} else if(!isEmailValid()) {
					errorMessage("Please enter a valid email!", contentPane);
				} else if (isEmailTaken()) {
					errorMessage("Email is already taken! Please try another email.", contentPane);
				} else if(!isSamePassword()) {
					errorMessage("Passwords should be the same!", contentPane);
				} else if (!isPasswordValid()) {
					errorMessage("Password length should be atleast 8 characters!", contentPane);
				} else {
					errorMessage("Cannot resolve error! Please try again.", contentPane);
				}
			}
		});
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
				frame.dispose();
			}
		});
		signInBtn.setOpaque(true);
		signInBtn.setForeground(Color.WHITE);
		signInBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		signInBtn.setBorderPainted(false);
		signInBtn.setBackground(new Color(0, 116, 232));
		signInBtn.setBounds(108, 522, 150, 44);
		contentPane.add(signInBtn);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
    
    private boolean isComplete() {
    	return !(firstNameTextField.getText().equals("") || 
    			 lastNameTextField.getText().equals("") ||
    			 addressTextField.getText().equals("") ||
    			 emailTextField.getText().equals("") ||
    			 new String(passwordField.getPassword()).equals(""));
    }
    
    private boolean isSamePassword() {
    	String password = new String(passwordField.getPassword());
    	String rePassword = new String(rePasswordField.getPassword());
    	
    	return (password.equals(rePassword));
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
    
    private boolean isEmailTaken() {
    	Connection conn = null;
		ResultSet rs = null;
	    conn = ConnectionManager.getConnection();
	    String query = "SELECT * FROM users WHERE email = ?;";
	    
    	try(PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, emailTextField.getText());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			conn.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
    	
    	return false;
	}
    
    private void runQuery(Connection con, String qry) {
    	try(PreparedStatement stmt = con.prepareStatement(qry)) {
	        stmt.setString(1, firstNameTextField.getText());
	        stmt.setString(2, lastNameTextField.getText());
		    stmt.setString(3, addressTextField.getText());
		    stmt.setString(4, emailTextField.getText());
		    stmt.setString(5, toHexString(getSHA(new String(passwordField.getPassword()))));
		    
		 // execute the prepared statement
		    stmt.execute();
		    con.close();
    	} catch (Exception err) {
    		System.err.println(err.getMessage());
    	}
    }
    
    private boolean isValidLength() {
    	if(firstNameTextField.getText().length() >= 45) {
    		errorMessage("First name is too long!", contentPane);
    		return false;
    	} else if(lastNameTextField.getText().length() >= 45) {
    		errorMessage("Last name is too long!", contentPane);
    		return false;
    	} else if(addressTextField.getText().length() >= 120) {
    		errorMessage("Address is too long!", contentPane);
    		return false;
    	} else if(emailTextField.getText().length() >= 75) {
    		errorMessage("Email is too long!", contentPane);
    		return false;
    	} else if(passwordField.getPassword().length >= 50) {
    		errorMessage("Password is too long!", contentPane);
    		return false;
    	}
    	
    	return true;
    }
}
