package mechanics;

import javax.swing.JOptionPane;
import java.io.IOException;
import BankApp.FScreen;

public class SettingSystem {
	private SignupSystem ss;
	public String password;
	private int yesOpt, noOpt;
	
	public SettingSystem(FScreen fs) throws IOException {
		try {
			ss = new SignupSystem(fs);
			password = JOptionPane.showInputDialog(null, "Please choose new password:", "Banking App", JOptionPane.DEFAULT_OPTION);
			
			yesOpt = JOptionPane.YES_OPTION;
			noOpt = JOptionPane.NO_OPTION;
			
			if(yesOpt == JOptionPane.YES_OPTION && noOpt == JOptionPane.NO_OPTION) {
				if(password != null) {
					ss.saveUserData(ss.Data[0], ss.Data[1], ss.Data[2]);
				}else {
					throw new PasswordOperationException();
				}
			}
		}catch(Exception e) {
			// Yes, some exceptions can go unnoticed but I don't like JOptionPane throwing exception.
		}
	}
	private class PasswordOperationException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public PasswordOperationException() {
			if(!password.isEmpty() || !password.isBlank()) {
				return;
		   }else {
			  throw new PasswordOperationException("Failed to change password, please pick a correct password.");
		   }
		}
		public PasswordOperationException(String errorMessage) {
			 super(errorMessage);
		}
	}
}
