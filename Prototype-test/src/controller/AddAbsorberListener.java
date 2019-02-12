package controller;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import model.Model;
import view.Board;

public class AddAbsorberListener implements MouseInputListener {
	
	private Model model;
	private Board board;
	
	private int x1 = 0;
	private int y1 = 0;
	
	public AddAbsorberListener(Model m, Board b) {
		model = m;
		board = b;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
//		x1 = (int)(arg0.getX()/utilities.GBallGlobals.DEFAULT_L * 1.0);
//		y1 = (int)(arg0.getY()/utilities.GBallGlabals.DEFAULT_L * 1.0);
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
