package mechanics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import BankApp.FScreen;
import other.Bounds;
import other.Button;
import other.Screen;
import sound.Sound;

public class BankApp {
	private JFrame window;
	private SignupSystem ss;
	public static String serviceFileAddress;
	public static String RMServiceFile = "connection.txt";
	public ServiceWindow sw;
	private Sound sound = new Sound();
	private File paymentFile;
	
	//Nothing will be left for 'Restart Account' since everything is zero.
	//This should be modified internally, to get some hypothetical account statistics.
	//Indexes for different statistics to change values:
	// 0 - Money paid last month
	// 1 - Most recent payment
	// 2 - Most expensive payment
	// 3 - Total amount left
	// 4 - Total payment cases
	public static int[] stats = new int[] {0, 0, 0, 0, 0, 0};
	private ActionListener features = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==buttons[0]) {
				try {
					new SettingSystem(new FScreen());
					sound.playClickSound();
				} catch (IOException ex) {
					ex.printStackTrace();
				} return;
			}
			if(e.getSource()==buttons[1]) {
				ss.clearAll();
		   		sound.playClickSound();
			}
			if(e.getSource()==buttons[2]) {
				ss.connectService();
		        sound.playClickSound();
			}
			if(e.getSource()==buttons[3]) {
				ss.startFromZero();
				sound.playClickSound();
			}
		}
	};
	public static JLabel[] decorativeLabels = {
			 new JLabel("          Total Payments: " + stats[0]),
			 new JLabel(""),
			 new JLabel("STATISTICS:"),
			 new JLabel("Welcome!"),
			 new JLabel(""),
			 new JLabel("Dashboard:"),
			 new JLabel("Money paid last month: " + stats[1]),
			 new JLabel("Most recent payment: " + stats[2]),
			 new JLabel("Most expensive payment: " + stats[3]),
			 new JLabel("Total amount left: " + stats[4]),
			 new JLabel("Total payment cases: " + stats[5])
	};
	private JPanel[] panels = {
		new JPanel()
	};
	private Bounds[] bounds = {
		 new Bounds((int) 0.6, 414, 399, 86),
		 new Bounds(397, 3, 325, 495),
		 new Bounds(502, 11, 139, 65),
		 new Bounds(138, 32, 124, 26),
         new Bounds(138, 69, 204, 26),
         new Bounds(0, 138, 406, 274),
         new Bounds(145, 11, 87, 25),
         new Bounds(123, 48, 144, 23),
         new Bounds(123, 82, 144, 23),
         new Bounds(123, 117, 144, 23),
         new Bounds(123, 153, 144, 23),
         new Bounds(123, 154, 144, 23),
         new Bounds(409, 87, 342, 26),
         new Bounds(409, 105, 342, 26),
         new Bounds(409, 124, 342, 26),
         new Bounds(409, 142, 342, 26),
         new Bounds(409, 161, 342, 26),
    };
	public static Button[] buttons = {
			new Button("Change Password", false, new Rectangle(), Color.white, Color.black),
			new Button("Clear/Invest All", false, new Rectangle(), Color.white, Color.black),
			new Button("Connect to service", false, new Rectangle(), Color.white, Color.black),
			new Button("Restart Account", false, new Rectangle(), Color.white, Color.black),
	};
	private Font[] fonts = {
		   new Font("Tahoma", Font.BOLD, 23),
		   new Font("Wingdings 2", Font.BOLD, 15),
		   new Font("Yu Gothic UI", Font.BOLD, 23),
		   new Font("Verdana", Font.BOLD, 20),
		   new Font("Times New Roman", Font.BOLD, 21),
		   new Font("Palatino Linotype", Font.BOLD, 15),
	};
	public BankApp(String s) {}
	public BankApp() {
		window = Screen.createWindow("Bank App", JFrame.DO_NOTHING_ON_CLOSE, 742, 537, false, true, new ImageIcon(getClass().getResource("/icons/logo.png")).getImage(), new Color(255, 165, 0), new WindowAdapter() {
		     @Override
		     public void windowClosing(WindowEvent e) {
		    	 sound.playClickSound();
		    	 window.dispose();
		     }
		});
		ss = new SignupSystem(new FScreen());
		sw = new ServiceWindow();
	}
	public void load() {
		decorativeLabels[0].setForeground(new Color(0, 0, 0));
		decorativeLabels[5].setFont(new Font("Verdana", Font.BOLD, 13));
		decorativeLabels[0].setFont(fonts[3]);
		decorativeLabels[0].setBorder(BorderFactory.createEtchedBorder(new Color(255,165,0), Color.black));
		decorativeLabels[1].setBorder(BorderFactory.createEtchedBorder(new Color(255,165,0), Color.black));
		
		for(int i = 0; i <= 4; i++) {
			decorativeLabels[i].setForeground(new Color(0, 51, 102));
		}
		for(int i = 0; i <= 2; i++) {
			decorativeLabels[i+2].setFont(fonts[i+2]);
			decorativeLabels[i].setFont(fonts[i]);
		}
		for(int i = 0; i <= 5; i++) {
			decorativeLabels[i].setBounds(bounds[i+1].getBounds(0, 0));
		}
		for(int i = 5; i <= 10; i++) {
			decorativeLabels[i].setForeground(Color.black);
		}
		for(int i = 0; i <= 3; i++) {
			buttons[i].setBounds(bounds[i+7].getBounds(0, 0));
		}
		for(int i = 0; i <= 4; i++) {
			decorativeLabels[i].setBounds(bounds[i].getBounds(0, 0));
		}
		for(int i = 6; i <= 10; i++) {
			decorativeLabels[i].setBounds(bounds[i + 6].getBounds(0, 0));
			decorativeLabels[i].setFont(fonts[5]);
		}
		for(JLabel l : decorativeLabels) {
			window.getContentPane().add(l);
		}
		for(JButton b : buttons) panels[0].add(b);
		for(JButton b : buttons) b.addActionListener(features);
		
		panels[0].setBounds(bounds[5].getBounds(0, 0));
		panels[0].setBackground(new Color(255, 165, 0));
		panels[0].setBorder(decorativeLabels[3].getBorder());
		panels[0].setLayout(null);
		panels[0].add(decorativeLabels[5]);
		window.getContentPane().add(panels[0]);	

        setStats(paymentFile, "payment.txt");   
	 }
     public void setStats(File f, String path) {
        	f = new File(path);
        	serviceFileAddress = f.getAbsolutePath();
            if(f.exists() && !f.isDirectory()) {
            	try (BufferedReader br = new BufferedReader(new FileReader(serviceFileAddress))) {
            		BankApp.stats[2] = Integer.parseInt(br.readLine());
            		BankApp.decorativeLabels[7].setText("Most recent payment: " + BankApp.stats[2]);
            		br.close();
            } catch(IOException e) {
            	System.out.println("Failed to get stat: " + e.getMessage());
            }
         }else {
        	 return;
         }
     }
}