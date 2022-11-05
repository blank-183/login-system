package loginsystem;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HelperMethods {
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
    
    public void successMessage(String message, JPanel contentPane) {
    	JOptionPane.showMessageDialog(contentPane, 
				  message, 
				  "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void errorMessage(String message, JPanel contentPane) {
		JOptionPane.showMessageDialog(contentPane, 
				  message, 
				  "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public int confirmMessage(JPanel contentPane) {
    	return JOptionPane.showConfirmDialog(contentPane, "Do you want to log out?", 
    										 "Please confirm", JOptionPane.YES_NO_OPTION);
    }
}
