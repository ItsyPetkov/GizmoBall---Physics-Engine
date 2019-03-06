package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunModeButtonListener implements ActionListener {

	public RunModeButtonListener() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Tick")) {
			System.out.println("Ticking...");
		}else if(e.getActionCommand().equals("Pause")) {
			System.out.println("Paused...");
		}else if(e.getActionCommand().equals("Run")) {
			System.out.println("Running...");
		}
	}

}
