package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerTickListener implements ActionListener {

	private model.Model model;
	
	public TimerTickListener(model.Model m) {
		model = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		model.moveBall();
	}

}
