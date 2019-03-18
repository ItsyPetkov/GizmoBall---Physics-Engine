package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.IModel;

public class AbsorberKeyListener implements KeyListener {
	
	private IModel model;
	
	public AbsorberKeyListener(IModel m) {
		model = m;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_F) {
			for(int i=0; i<model.getBalls().size(); i++){
				if(model.getBalls().get(i).isCaptured()){
					model.absorberShoot(model.getBalls().get(i));
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
