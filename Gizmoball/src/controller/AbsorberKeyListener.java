package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import model.IGizmo;
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
			List<IGizmo> gizmoList = model.getGizmos();
			for(int i=0; i<gizmoList.size(); i++){
				if(gizmoList.get(i).type().equals("Absorber")){
					gizmoList.get(i).trigger();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
