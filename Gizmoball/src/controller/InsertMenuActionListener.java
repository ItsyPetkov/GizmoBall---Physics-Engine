package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertMenuActionListener implements ActionListener {

	public InsertMenuActionListener() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("Square")) {
			System.out.println("Adding a square...");
		}else if(arg0.getActionCommand().equals("Triangle")) {
			System.out.println("Adding a triangle...");
		}else if(arg0.getActionCommand().equals("Circle")) {
			System.out.println("Adding a circle...");
		}else if(arg0.getActionCommand().equals("Left Flipper")) {
			System.out.println("Adding a left flipper...");
		}else if(arg0.getActionCommand().equals("Right Flipper")) {
			System.out.println("Adding a right flipper...");
		}else if(arg0.getActionCommand().equals("Absorber")) {
			System.out.println("Adding an absorber...");
		}else if(arg0.getActionCommand().equals("Ball")) {
			System.out.println("Adding a ball...");
		}
	}

}
