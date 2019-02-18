package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AbsorberKeyListener implements KeyListener {
	
	private model.Model model;
	private view.GUI gui;
	
	public AbsorberKeyListener(model.Model m, view.GUI g) {
		model = m;
		gui = g;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_F) {
//			model.absorberShoot(model.getBall());
			gui.getTimer().start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
