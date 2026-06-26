
package BankApp;

import javax.swing.*;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import other.Bounds;
import other.Fonts;

public class FScreen {
	public JTextField[] tFields = {new JTextField(), new JTextField(), new JTextField()};
    public ArrayList<JLabel> labels = new ArrayList<>();
    public ArrayList<Bounds> bounds = new ArrayList<>();
    private final int[] entireComponentBound = {260, 32, 283, 71};
    private final int[] oneComponentBound = {260, 32, 283, 71};
    
    public FScreen(String differentText) {
    	labels.add(DefaultComponentFactory.getInstance().createLabel("Banking App"));
    	labels.add(new JLabel(differentText));
    	labels.add(new JLabel("Name:"));
    	labels.add(new JLabel("Last Name:"));
    	labels.add(new JLabel("Password:"));

    	bounds.add(new Bounds(entireComponentBound[0], entireComponentBound[1], entireComponentBound[2], entireComponentBound[3]));
		bounds.add(new Bounds(entireComponentBound[0]-46, entireComponentBound[1]+18, entireComponentBound[2]+119, oneComponentBound[3]+57));
		bounds.add(new Bounds(entireComponentBound[0]-90, entireComponentBound[1]+175, entireComponentBound[2]-83, entireComponentBound[3]-57));
		bounds.add(new Bounds(entireComponentBound[0]-105, entireComponentBound[1]+210, entireComponentBound[2]-83, entireComponentBound[3]-57));
		bounds.add(new Bounds(entireComponentBound[0]-124, oneComponentBound[1]+237, oneComponentBound[2]-70, oneComponentBound[3]-48));
		bounds.add(new Bounds(entireComponentBound[0]+18, entireComponentBound[1]+317, oneComponentBound[2]-70, entireComponentBound[3]-48));
	
		tFields[0].setBounds(bounds.get(5).getBounds(-180, 4));
		tFields[1].setBounds(tFields[0].getX(), tFields[0].getY()+31, tFields[0].getWidth(), tFields[0].getHeight());
		tFields[2].setBounds(tFields[1].getX(), tFields[1].getY()+31, tFields[1].getWidth(), tFields[1].getHeight());

		for(int i = 0; i <= 2; i++) { 
			tFields[i].setColumns(i+8);       
			tFields[i].setFont(Fonts.fonts[1]);
			tFields[i].setForeground(Color.white);
		}
    }
    public FScreen() {
    	labels.add(DefaultComponentFactory.getInstance().createLabel("Banking App"));
    	labels.add(new JLabel("Please register:"));
    	labels.add(new JLabel("Name:"));
    	labels.add(new JLabel("Last Name:"));
    	labels.add(new JLabel("Password:"));

    	bounds.add(new Bounds(entireComponentBound[0], entireComponentBound[1], entireComponentBound[2], entireComponentBound[3]));
		bounds.add(new Bounds(oneComponentBound[0]-46, oneComponentBound[1]+18, oneComponentBound[2]+119, oneComponentBound[3]+57));
		bounds.add(new Bounds(entireComponentBound[0]-90, entireComponentBound[1]+175, entireComponentBound[2]-83, entireComponentBound[3]-57));
		bounds.add(new Bounds(entireComponentBound[0]-105, entireComponentBound[1]+210, entireComponentBound[2]-83, entireComponentBound[3]-57));
		bounds.add(new Bounds(entireComponentBound[0]-124, entireComponentBound[1]+237, entireComponentBound[2]-70, entireComponentBound[3]-48));
		bounds.add(new Bounds(entireComponentBound[0]+18, entireComponentBound[1]+317, entireComponentBound[2]-70, entireComponentBound[3]-48));
	
		tFields[0].setBounds(bounds.get(5).getBounds(-180, 4));
		tFields[1].setBounds(tFields[0].getX(), tFields[0].getY()+31, tFields[0].getWidth(), tFields[0].getHeight());
		tFields[2].setBounds(tFields[1].getX(), tFields[1].getY()+31, tFields[1].getWidth(), tFields[1].getHeight());

		for(int i = 0; i <= 2; i++) {
			tFields[i].setColumns(i+8);       
			tFields[i].setFont(Fonts.fonts[1]);
			tFields[i].setForeground(Color.white);
		}
    }
    public void load() {
    	for(int i = 0; i <= 4; i++) {
    		labels.get(i).setBounds(bounds.get(i).getBounds(-40, 0));
    		labels.getLast().setBounds(bounds.get(i).getBounds(2, 32));
    		labels.get(i).setFont(Fonts.fonts[2]);
    	}
    }
    public void addComponent(Component c) {}
}