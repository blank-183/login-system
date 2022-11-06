package loginsystem;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Message extends HashPass {
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
    
    public int confirmMessage(String message, JPanel contentPane) {
    	return JOptionPane.showConfirmDialog(contentPane, message, 
    										 "Please confirm", JOptionPane.YES_NO_OPTION);
    }
}
