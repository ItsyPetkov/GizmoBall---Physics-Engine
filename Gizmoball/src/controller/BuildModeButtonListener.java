package controller;

import model.Model;
import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class BuildModeButtonListener implements ActionListener {

	Model model;
	Board board;

	public BuildModeButtonListener(Model m, Board b) {
		this.model = m;
		this.board = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MouseListener[] mss = board.getMouseListeners();
		for(int i=0; i<mss.length; i++){
			board.removeMouseListener(mss[i]);
		}

		if(e.getActionCommand().equals("Move")) {
			System.out.println("Click an object to move, then click a position to move to");
			MouseListener ml = new BuildModeMoveGizmoListener(model, board.getLtoPx());
			board.addMouseListener(ml);
		} else if(e.getActionCommand().equals("Rotate")) {
			System.out.println("Click an object to rotate");
			MouseListener rl = new BuildModeRotateGizmoListener(model, board.getLtoPx());
			board.addMouseListener(rl);
		} else if(e.getActionCommand().equals("Delete")) {
			System.out.println("Click an object to delete");
			MouseListener dl = new BuildModeDeleteListener(model, board.getLtoPx());
			board.addMouseListener(dl);
		} else if(e.getActionCommand().equals("Connect")) {
			System.out.println("Connecting...");
		} else if(e.getActionCommand().equals("Disconnect")) {
			System.out.println("Disconneecting...");
		} else if(e.getActionCommand().equals("Key Connect")) {
			System.out.println("Connecting Key...");
		} else if(e.getActionCommand().equals("Key Disconnect")) {
			System.out.println("Disconnecting Key...");
		}
	}

}
