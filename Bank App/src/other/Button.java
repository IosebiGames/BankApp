package other;

import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import BankApp.FScreen;
import mechanics.*;
import sound.Sound;

public class Button extends JButton {
	private FScreen fs;
	private Screen s;
	public static JButton b;
	private SignupSystem sys;
	private Timer timer;
	private int counter = 0;
	private String[] data;
    private boolean field0B, field1B, field2B;
    private Sound sound;
    
	public Button(FScreen fs, Screen s, SignupSystem sys, String differentText) {
		this.fs = fs;
		this.s = s;
		this.sys = sys;
		this.data = new String[] {null, null, null};
		this.sound = new Sound();
		
		b = new Button(differentText, false, new Rectangle(fs.bounds.getLast().getBounds(110, 0)), Color.white, Color.black);
		b.putClientProperty("JButton.buttonType", "roundRect");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 sound.playClickSound();
				 field0B = fs.tFields[0].getText().isEmpty() || fs.tFields[0].getText().isBlank();
				 field1B = fs.tFields[1].getText().isEmpty() || fs.tFields[1].getText().isBlank();
				 field2B = fs.tFields[2].getText().isEmpty() || fs.tFields[2].getText().isBlank();
				 
				 if(field0B || field1B || field2B) {
					  setTexts();
				 }else {
					 sound.playClickSound();
					 data[0] = fs.tFields[0].getText();
					 data[1] = fs.tFields[1].getText();
					 data[2] = fs.tFields[2].getText();
					 sys.SignIn(data[0], data[1], data[2], fs.labels.get(0), s.window);
				  }
			    }
	        });
		s.addComponent(b);
	}
	public Button(FScreen fs, Screen s, SignupSystem sys) {
		this.fs = fs;
		this.s = s;
		this.sys = sys;
		this.data = new String[] {null, null, null};
		this.sound = new Sound();
		
		b = new Button("create account", false, new Rectangle(fs.bounds.getLast().getBounds(110, 0)), Color.white, Color.black);
		b.putClientProperty("JButton.buttonType", "roundRect");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		     	 sound.playClickSound(); 
				 field0B = fs.tFields[0].getText().isEmpty() || fs.tFields[0].getText().isBlank();
				 field1B = fs.tFields[1].getText().isEmpty() || fs.tFields[1].getText().isBlank();
				 field2B = fs.tFields[2].getText().isEmpty() || fs.tFields[2].getText().isBlank();
				 
				 if(field0B || field1B || field2B) {
					  setTexts();
				 }else {
					 sound.playClickSound();
					 data[0] = fs.tFields[0].getText();
					 data[1] = fs.tFields[1].getText();
					 data[2] = fs.tFields[2].getText();
					 sys.signUp(data[0], data[1], data[2]);
					 s.window.dispose();
					 new BankApp().load();
					 CommonDataLoader.loadData();

				  }
			    }
	        });
		s.addComponent(b);
	}
	public void setTexts() {
		for(int i = 0; i <= 2; i++) fs.tFields[i].setText("please fill in boxes");
		Button.b.setEnabled(false);
		  
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				counter++;
				if(counter == 4) {
					fs.tFields[0].setText("");
					fs.tFields[1].setText("");
					fs.tFields[2].setText("");
				    Button.b.setEnabled(true);
					counter = 0;
					timer.stop();
				}
			}
		});
		timer.start();
	}
	public Button(String text, boolean focus, Rectangle r, Color bc, Color fc) {
		super(text);
		setFocusable(focus);
		setBounds(r.getBounds());
		setBackground(bc);
		setForeground(fc);
	}
}