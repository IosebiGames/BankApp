package mechanics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import other.Screen;
import sound.Sound;

public class ServiceWindow implements java.awt.event.ActionListener {
	private int w = 630;
	private int h = 420;
	private int counter = 0;
	private JFrame window;
	private JLabel titleL;
	private JTextArea serviceTextArea;
	private JPanel panel, serviceTextPanel;
	public JButton refreshButton, connectButton, qButton;
    private ImageIcon RecipeManagerIcon;
	public JToggleButton serviceDetail;
    private Timer refreshTimer;
    private String serviceText;
    private ActionListener defaultListener, resetListener, connectListener, disconnectListener;
    private Sound sound;
    private boolean connected = false;
    private String content;
    
	public ServiceWindow() {
       this.titleL = new JLabel("Services:");
       this.panel = new JPanel();
       this.serviceTextPanel = new JPanel();
       this.refreshButton = new JButton("Refresh");
       this.connectButton = new JButton("Connect");
       this.qButton = new JButton("?");
       this.RecipeManagerIcon = new ImageIcon(getClass().getResource("/icons/RecipeManager_icon.png"));
       this.serviceDetail = new JToggleButton("RecipeManager", RecipeManagerIcon);       
       this.serviceText =
            "Service Connection is one of the features implemented in the Banking App.\n"
    		    		  + "It is made to connect with other Applications to review payment details.\n\n"
    		    		  + "Why can connection fail?\n\n"
    		    		  + "It can fail if Service data is File-dependent,\n"
    		    		  + "meaning crucial files may be required\n"
    		    		  + "in order to establish connection.";
       this.serviceTextArea = new JTextArea(serviceText);
       this.sound = new Sound();
       this.resetListener = e -> {
    	   if(e.getSource()==qButton) {  
    		   sound.playClickSound();
    		   resetComponents();
    	   }
       };
       this.connectListener = e -> {
			if(e.getSource() == connectButton) {
				sound.playClickSound();
				if(!new File("payment.txt").exists()) {
		               serviceDetail.setText("RecipeManager (Connection Failed)");	 
			           qButton.setVisible(true);
				 }else {
				       serviceDetail.setText("Connected!");
				       connected = true;
				       if(connected) {
				    	  try (BufferedWriter bw = new BufferedWriter(new FileWriter("connection.txt"))) {
				    		     bw.write(String.valueOf(connected));
				    		     bw.close();
				    		     connectButton.setText("Disconnect");
                                 connectButton.removeActionListener(connectListener);
                                 connectButton.addActionListener(disconnectListener);
				    	  }catch(IOException ex) {
				    		  System.out.println("Can't save connection boolean: " + ex.getMessage());
				    	  }
				       }else {
				    	   return;
				       }
				 }
			 }  
       };
       this.disconnectListener = e -> {
    	 if(e.getSource() == connectButton) {
    		 DisconnectService();
    		 connectButton.removeActionListener(disconnectListener);
    		 connectButton.addActionListener(connectListener);
    		 connectButton.setText("Connect");
    	  }
       };
       this.defaultListener = e -> {
   		 if(e.getSource()==qButton) {
			serviceDetail.setVisible(false);
			refreshButton.setVisible(false);
			connectButton.setVisible(false);
			panel.setVisible(false);
			qButton.setText("Back");
			qButton.removeActionListener(defaultListener);
			qButton.addActionListener(resetListener);
			qButton.setBounds(515, 340, 60, 40);
			qButton.setToolTipText("Go Back");
			qButton.setVisible(true);
			titleL.setBounds(113, 10, 326, 40);
			titleL.setText("What is a Service Connection?");
			serviceTextArea.setVisible(true);
			serviceTextPanel.setVisible(true);
			sound.playClickSound();
	  	  }				
       };
	}
	public void open() {
		window = Screen.createWindow("Select Service: ", JFrame.DISPOSE_ON_CLOSE, w, h, false, true, new ImageIcon(getClass().getResource("/icons/logo.png")).getImage(), new Color(255, 165, 0), new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				sound.playClickSound();
				resetComponents();
			}
		});
		titleL.setForeground(Color.black);
		titleL.setFocusable(false);
		titleL.setFont(new Font("Times New Roman", Font.BOLD, 25));
		titleL.setBounds(240, 50, 135, 40);
		
		serviceDetail.setFocusable(false);
		serviceDetail.setBorder(null);
		serviceDetail.setBackground(Color.white);
		serviceDetail.setForeground(Color.black);
		serviceDetail.setBounds(19, 10, 225, 25);	
		serviceDetail.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		panel.setBackground(Color.white);
		panel.setLayout(null);
		panel.setBounds(160, 120, 260, 90);
		panel.setVisible(true);
		
		refreshButton.setBackground(Color.white);
		refreshButton.setForeground(Color.black);
		refreshButton.setFocusable(false);
		refreshButton.setBounds(185, 230, 210, 40);
		refreshButton.addActionListener(this);
		
		serviceTextArea.setBackground(new Color(255, 165, 0));
		serviceTextArea.setOpaque(true);
		serviceTextArea.setForeground(Color.black);
		serviceTextArea.setCaretColor(Color.black);
		serviceTextArea.setFont(new Font("Times New Roman", Font.BOLD, 16));
		serviceTextArea.setBounds(15, 10, 576, 210);
		serviceTextArea.setEditable(false);
		serviceTextArea.setFocusable(false);
		serviceTextArea.setHighlighter(null);
		serviceTextArea.setWrapStyleWord(true);
		serviceTextArea.setVisible(false);

		serviceTextPanel.setBackground(new Color(255, 165, 0));
		serviceTextPanel.setLayout(null);
		serviceTextPanel.setBounds(50, 65, 526, 965);
		serviceTextPanel.setVisible(false);

		qButton.setBackground(Color.white);
		qButton.setForeground(Color.black);
		qButton.setFocusable(false);
		qButton.setBounds(525, 320, 60, 40);
		qButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		qButton.setToolTipText("Why would Service Connection fail? \nFind out more about Service Connection");
		qButton.addActionListener(defaultListener);
        qButton.setVisible(false);
        
		if(serviceDetail.isSelected()) {
			serviceDetail.setText("RecipeManager");
			serviceDetail.setSelected(false);
			refreshButton.setVisible(true);
			serviceDetail.setVisible(true);
			refreshButton.setVisible(true);
			connectButton.setVisible(true);
			panel.setVisible(true);
			qButton.setVisible(false);
			titleL.setBounds(140, 50, 210, 40);
			titleL.setText("Services:");
			sound.playClickSound();
		}
		connectButton.setBackground(Color.white);
		connectButton.setForeground(Color.black);
		connectButton.setFocusable(false);
		connectButton.setBounds(185, 230, 210, 40);
		connectButton.setVisible(false);	
		if(new File("connection.txt").exists()) {
	    	 try (BufferedReader br = new BufferedReader(new FileReader("connection.txt"))) {
	    		  content = br.readLine();
	    		  if(content.equals("true")) {
	    			   connectButton.setText("Disconnect");
	    			   connectButton.removeActionListener(connectListener);
	    			   connectButton.addActionListener(disconnectListener);
	    		   }
	    		   br.close();
	    	   }catch(IOException e) {
	    		   System.out.println("Can't read connection boolean: " + e.getMessage());
	    	   }
		}else {
			connectButton.addActionListener(connectListener);
		}
		serviceDetail.addActionListener(_ -> {
            	  if(serviceDetail.isSelected()) {
            		  refreshButton.setVisible(false);
            		  connectButton.setVisible(true);
            		  sound.playClickSound();
            	  }else {
            		  serviceDetail.setSelected(false);
            		  serviceDetail.setText("RecipeManager");
            		  refreshButton.setVisible(true);
            		  connectButton.setVisible(false);
            		  qButton.setVisible(false);
                  }
	   });
	   window.getContentPane().add(titleL);
	   window.getContentPane().add(panel);
	   window.getContentPane().add(refreshButton);
	   window.getContentPane().add(connectButton);
	   window.getContentPane().add(qButton);
	   window.getContentPane().add(serviceTextPanel);
	   panel.add(serviceDetail);
	   serviceTextPanel.add(serviceTextArea);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		sound.playClickSound();
		if(e.getSource() == refreshButton) {
			sound.playClickSound();
			serviceDetail.setVisible(false);		 
			refreshButton.setEnabled(false);
			refreshTimer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					counter++;
					if(counter == 3) {
						serviceDetail.setVisible(true);
						refreshButton.setEnabled(true);
						counter = 0;
						refreshTimer.stop();
					}
				}
			});
			refreshTimer.start();  
		}
	}
	private void resetComponents() {
	   serviceDetail.setText("RecipeManager");
	   serviceDetail.setSelected(false);
	   refreshButton.setVisible(true);
	   serviceDetail.setVisible(true);
	   refreshButton.setVisible(true);
	   connectButton.setVisible(true);
	   panel.setVisible(true);
	   serviceTextPanel.setVisible(false);
	   qButton.setText("?");
	   qButton.setToolTipText("Why would Service Connection fail? \nFind out more about Service Connection");
	   qButton.setVisible(false);
	   qButton.removeActionListener(resetListener);
       qButton.addActionListener(defaultListener);
	   titleL.setBounds(240, 50, 210, 40);
	   titleL.setText("Services:");
	}
	public void DisconnectService() {
    	try {
			Files.deleteIfExists(Paths.get(BankApp.RMServiceFile));
			connectButton.setText("Connect");
		} catch (IOException e) {
			System.out.println("Failed to disconnect: " + e.getMessage());
		}
    }
}  