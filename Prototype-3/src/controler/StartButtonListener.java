package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;
import view.GUI;

public class StartButtonListener implements ActionListener {

	private Model model;
	private GUI gui;
	
	public StartButtonListener(Model m, GUI g) {
		model = m;
		gui = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Starting...");
		gui.getTimer().start();
	}

}
