package mechanics;

import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;

import other.Screen;

import java.awt.Color;

public class CommonDataLoader {
	public static SignupSystem ss;
	private static boolean readPermission;
	
	public static void loadData() {
		try {
			CommonDataLoader.ss = new SignupSystem();
			BankApp.decorativeLabels[4].setText(ss.readData()[0]);
			
			if(new File("state.bap").exists()) {
				readPermission = false;
				BankApp.buttons[3].setEnabled(readPermission);
				BankApp.buttons[3].setToolTipText("Balance must increase reasonably before Starting account again.");					
			}else {
				readPermission = true;
				BankApp.buttons[3].setEnabled(readPermission);
		  }
		  return;
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void check() {
		  try {
			  UIManager.setLookAndFeel(new FlatDarkLaf());
			  if(new File("data.bap").exists() && new File("state.bap").exists()) {
				  new Screen("Banking App | Login", false, 644, 445, new Color(226, 76, 36), true, null);
			  }else {
				  new Screen("Banking App | Login", false, 644, 445, new Color(226, 76, 36), true, null);
			  }
			 }catch(UnsupportedLookAndFeelException e) {
			  	 e.printStackTrace();
		  }
	  }
}
