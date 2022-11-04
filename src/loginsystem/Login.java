package loginsystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailTextField;
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
				Connection conn = null;
			    ResultSet rs = null;
			    
			    conn = ConnectionManager.getConnection();
			    String query = "SELECT * FROM users WHERE email = ? LIMIT 1";
			    
			    if((conn != null) && isComplete() && isEmailValid() && isPasswordValid()) {
					try(PreparedStatement stmt = conn.prepareStatement(query)) {
						stmt.setString(1, emailTextField.getText());
						rs = stmt.executeQuery();
						
						if (rs.isBeforeFirst()) {
							readResultSet(rs);
						} else {
							JOptionPane.showMessageDialog(contentPane, 
									  "Wrong email or password!", 
									  "Error", JOptionPane.ERROR_MESSAGE);
						}
							
						conn.close();
					} catch(Exception ex) {
						ex.printStackTrace();
					}
			    } else if (!isComplete()){
					JOptionPane.showMessageDialog(contentPane, 
							  "Fields cannot be empty!", 
							  "Error", JOptionPane.ERROR_MESSAGE);
				} else if(!isEmailValid()) {
					JOptionPane.showMessageDialog(contentPane, 
							  "Please enter a valid email!", 
							  "Error", JOptionPane.ERROR_MESSAGE);
				} else if (!isPasswordValid()) {
					JOptionPane.showMessageDialog(contentPane, 
							  "Password length should be atleast 8 characters!", 
							  "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(contentPane, 
							  "There is something wrong with the server.", 
							  "Error", JOptionPane.ERROR_MESSAGE);
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
	
	public byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");
 
        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
     
    public String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);
 
        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));
 
        // Pad with leading zeros
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
 
        return hexString.toString();
    }
    
    public boolean isComplete() {
    	return !(emailTextField.getText().equals("") ||
    			 new String(passwordField.getPassword()).equals(""));
    }
    
    public boolean isPasswordValid() {
    	return (passwordField.getPassword().length >= 8);
    }
    
    public boolean isEmailValid()
    {
        String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"; 

        Pattern pat = Pattern.compile(emailRegex);
        if (emailTextField.getText() == null)
            return false;
        return pat.matcher(emailTextField.getText()).matches();
    }
    
    public void readResultSet(ResultSet rst) {
    	Integer userId = null;
	    String email = emailTextField.getText();
	    String password = new String(passwordField.getPassword());
	    
	    try {
			while(rst.next()) {
				userId = rst.getInt("user_id");
				System.out.println(rst.getInt("user_id"));

				if(email.equals(rst.getString("email")) && toHexString(getSHA(password)).equals(rst.getString("password"))) {
					JOptionPane.showMessageDialog(contentPane, 
							  "User found! You are now logged in.", 
							  "Success", JOptionPane.INFORMATION_MESSAGE);
					new Details(userId);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, 
							  "Wrong email or password!", 
							  "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
    }

}
