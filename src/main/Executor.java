package main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Color;
import com.formdev.flatlaf.FlatDarkLaf;
import mechanics.*;
import java.io.File;
import other.Screen;

public class Executor {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			  if(!new File("data.bap").exists()) {
				  try {
					UIManager.setLookAndFeel(new FlatDarkLaf());
				  } catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				  }
				  new Screen("Banking App | Register", false, 644, 445, new Color(226, 76, 36), true);			 
			  }else {
				  CommonDataLoader.check();
				  CommonDataLoader.loadData();       
			  }
		 });
	}
}         