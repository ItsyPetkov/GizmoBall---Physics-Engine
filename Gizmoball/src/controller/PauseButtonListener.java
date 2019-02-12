package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;
import view.GUI;

public class PauseButtonListener implements ActionListener {

	private Model model;
	private GUI gui;
	
	public PauseButtonListener(Model m, GUI g) {
		model = m; 
		gui = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

}
