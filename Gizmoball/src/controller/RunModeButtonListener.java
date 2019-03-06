package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;
import view.RunMode;

public class RunModeButtonListener implements ActionListener {

	Model model;
	RunMode gui;

	public RunModeButtonListener(Model model, RunMode gui) {
		this.model = model;
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Tick")) {
			model.moveBall();
		}else if(e.getActionCommand().equals("Pause")) {
			gui.getTimer().stop();
		}else if(e.getActionCommand().equals("Run")) {
			gui.getTimer().start();
		}
	}

}
