package controler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.InGameMenuBuildMode;
import view.InGameMenuRunMode;

public class EscapeKeyListener implements KeyListener {
	
	private InGameMenuBuildMode igmbm;
	private InGameMenuRunMode igmrm;
	private String gameState;
	
	public EscapeKeyListener(String state) {
		gameState = state;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE && gameState.equals("running")) {
			igmrm = new InGameMenuRunMode();
		}else if(key == KeyEvent.VK_ESCAPE && gameState.equals("not running")){
			igmbm = new InGameMenuBuildMode();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
