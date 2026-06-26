
package mechanics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import BankApp.FScreen;
import other.Button;

public class SignupSystem {
	private FScreen fs;
	private ArrayList<String> dataList = new ArrayList<>();
    public BufferedReader br;
    public BufferedWriter bw;
    public BufferedWriter dbw;
    public String[] Data;
    private String DataName, DataLastName, DataPassword;
    public int newUserID, counter, newStats;
    public ServiceWindow sw;
    private Timer timer;
    
    public SignupSystem() {}
	public SignupSystem(FScreen fs) {
	     this.fs = fs;	
	     this.sw = new ServiceWindow();
	}
	public boolean signUp(String name, String lastName, String password) {
			name = fs.tFields[0].getText();
			lastName = fs.tFields[1].getText();
		   	password = fs.tFields[2].getText();
		 	dataList.add(name);
		 	dataList.add(lastName);
		 	dataList.add(password);

		 	saveUserData(dataList.get(0), dataList.get(1), dataList.get(2));
		 	
		 	try {
				Data = readData();
			} catch (IOException e) {
    			e.printStackTrace();
			}
		 	return false;
	}
	public void SignIn(String name, String lastName, String password, JLabel l, JFrame w) {
		 try {
			 DataName = readData()[0];
			 DataLastName = readData()[1];
			 DataPassword = readData()[2];
			
			if(name.equals(DataName) && lastName.equals(DataLastName) && password.equals(DataPassword)) {
					w.dispose();
					new BankApp().load();
					CommonDataLoader.loadData();
			}else {
				Button.b.setEnabled(false);
				l.setText("Please check Login Details again");
				
				timer = new Timer(1000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						counter++;
						if(counter == 6) {
							l.setText("Banking App");
							Button.b.setEnabled(true);
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
		 } catch (IOException e) {
			e.printStackTrace();
		 }
	}
	public void saveUserData(String name, String lastName, String password) {
		try {
			 bw = new BufferedWriter(new FileWriter("data.bap"));
			 bw.write(name + "\n" + lastName + "\n" + password + "\n" + BankApp.buttons[3].isEnabled());
			 bw.close();
		}catch(IOException e) {
		   System.out.println("Failed to save the user data: " + e.getMessage());
		}
	}
	public String[] readData() throws IOException {
	    try (BufferedReader reader = new BufferedReader(new FileReader("data.bap"))) {
	        return new String[] {
	            reader.readLine(),
	            reader.readLine(),
	            reader.readLine()
	        };
	    }
	}
	public void saveState(boolean state) {
		try {
		    dbw = new BufferedWriter(new FileWriter("state.bap"));
		    dbw.write(String.valueOf(state));
		    dbw.close();
		}catch(IOException e) {
			System.out.println("Failed to save the state: " + e.getMessage());
		}
	}
	public boolean loadState() throws IOException {
	    try (BufferedReader reader = new BufferedReader(new FileReader("state.bap"))) {
	        return Boolean.parseBoolean(reader.readLine());
	    }
	}
	public void clearAll() {
		Arrays.fill(BankApp.stats, 0);
		
		for(int i = 6; i <= 10; i++) {
			if(BankApp.decorativeLabels[i].getText().lastIndexOf(':') != -1) {
				 BankApp.decorativeLabels[0].setText(BankApp.decorativeLabels[0].getText().substring(0, BankApp.decorativeLabels[0].getText().lastIndexOf(':') + 1) + " 0");
				 BankApp.decorativeLabels[i].setText(BankApp.decorativeLabels[i].getText().substring(0, BankApp.decorativeLabels[i].getText().lastIndexOf(':') + 1) + " 0");
			}
		}
	}
	public void connectService() {
		sw.open();
	}
    public void startFromZero() {
        try {
            Files.deleteIfExists(Paths.get("data.bap"));
            clearAll();
            newUserID = (new Random().nextInt(10000) + 1);
            BankApp.decorativeLabels[4].setText("User " + newUserID);
            BankApp.buttons[3].setEnabled(false);
            saveState(BankApp.buttons[3].isEnabled());
            saveUserData(BankApp.decorativeLabels[4].getText(), String.valueOf(newUserID), null);
        } catch(IOException e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }
}