package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;
import view.GUI;

public class TimerTickListener implements ActionListener {

	private Model model;
	private GUI gui;
	
	public TimerTickListener(Model m, GUI g) {
		model = m;
		gui = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		model.moveBall();
		gui.getTextField().setText(gui.result(model.getBall().getVelo().y()/25));
	}

}
