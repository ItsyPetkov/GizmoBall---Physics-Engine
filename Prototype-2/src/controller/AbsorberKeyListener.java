package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Model;
import view.Board;

public class AbsorberKeyListener implements KeyListener {
	
	private Model model;
	
	public AbsorberKeyListener(Model m) {
		model = m;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_F) {
			model.absorberShoot(model.getBall());
			System.out.println("F");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
