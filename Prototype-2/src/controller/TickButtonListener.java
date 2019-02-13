package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;

public class TickButtonListener implements ActionListener {

	private Model model;
	
	public TickButtonListener(Model m) {
		model = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		model.moveBall();
	}

}
