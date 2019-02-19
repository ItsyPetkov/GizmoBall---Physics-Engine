package controller;

import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerTickListener implements ActionListener {

	private Model model;
	
	public TimerTickListener(Model m) {
		model = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		model.moveBall();
	}

}
