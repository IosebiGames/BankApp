package other;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowListener;

import BankApp.FScreen;
import mechanics.SignupSystem;

public class Screen {
	public JFrame window;
	private static JFrame customWindow;
	private FScreen fScreen;
	private SignupSystem sys;

	public void init(String differentText, String differentButtonText) {
	  this.fScreen = new FScreen(differentText);
      this.sys = new SignupSystem(fScreen);
      this.fScreen.load();
      new Button(fScreen, this, sys, differentButtonText);
	}
	public void init() {
	  this.fScreen = new FScreen();
      this.sys = new SignupSystem(fScreen);
      this.fScreen.load();
      new Button(fScreen, this, sys);
  }
  public Screen() {
  }
  public Screen(String title, boolean res, final int w, final int h, Color c, boolean vis, String message) {
	     window = createWindow(title, JFrame.EXIT_ON_CLOSE, w, h, res, vis, new ImageIcon(getClass().getResource("/icons/logo.png")).getImage(), c, null);   
	  
		 init("Please Login:", "Login");
		    
	     for(int i = 0; i <= 4; i++) addComponent(fScreen.labels.get(i));
	     for(int i = 0; i <= 2; i++) addComponent(fScreen.tFields[i]);
  }
  public Screen(String title, boolean res, final int w, final int h, Color c, boolean vis) {
	    window = createWindow(title, JFrame.EXIT_ON_CLOSE, w, h, res, vis, new ImageIcon(getClass().getResource("/icons/logo.png")).getImage(), c, null);   
		    
	    init();
	    
	    for(int i = 0; i <= 4; i++) addComponent(fScreen.labels.get(i));
	    for(int i = 0; i <= 2; i++) addComponent(fScreen.tFields[i]);
   }
   public void addComponent(Component c) {
		window.getContentPane().add(c);
   }
   public static JFrame createWindow(String title, int exitOperation, int w, int h, boolean resizable, boolean visible, Image icon, Color bc, WindowListener wl) {
	   customWindow = new JFrame(title);
	   customWindow.setResizable(resizable);
	   customWindow.setDefaultCloseOperation(exitOperation);
	   customWindow.setLayout(null);
	   customWindow.setPreferredSize(new Dimension(w, h));
	   customWindow.pack();
	   customWindow.setLocationRelativeTo(null);
	   customWindow.setVisible(visible);
	   customWindow.getContentPane().setBackground(bc);
	   customWindow.setIconImage(icon);
	   customWindow.addWindowListener(wl);
	   return customWindow;
   }
}