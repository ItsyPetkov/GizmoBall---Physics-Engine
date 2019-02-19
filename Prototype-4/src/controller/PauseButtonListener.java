package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GUI;

public class PauseButtonListener implements ActionListener {

	private GUI gui;
	
	public PauseButtonListener(GUI g) {
		gui = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Stopping...");
		gui.getTimer().stop();
	}

}
