package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildModeButtonListener implements ActionListener {

	public BuildModeButtonListener() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Move")) {
			System.out.println("Moving object...");
		}else if(e.getActionCommand().equals("Rotate")) {
			System.out.println("Rotating object...");
		}else if(e.getActionCommand().equals("Delete")) {
			System.out.println("Deleting object...");
		}else if(e.getActionCommand().equals("Connect")) {
			System.out.println("Connecting...");
		}else if(e.getActionCommand().equals("Disconnect")) {
			System.out.println("Disconneecting...");
		}else if(e.getActionCommand().equals("Key Connect")) {
			System.out.println("Connecting Key...");
		}else if(e.getActionCommand().equals("Key Disconnect")) {
			System.out.println("Disconnecting Key...");
		}
	}

}
