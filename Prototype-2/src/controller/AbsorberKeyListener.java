package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Model;
import view.Board;

public class AbsorberKeyListener implements KeyListener {
	
	private Model model;
	
	private int x1 = 0;
	private int y1 = 0;
	
	public AbsorberKeyListener(Model m) {
		model = m;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("start");
		model.absorberShoot(model.getBall());
		model.moveBall();
		System.out.println("stop");
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
