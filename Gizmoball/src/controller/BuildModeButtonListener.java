package controller;

import model.Model;
import view.Board;

import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class BuildModeButtonListener implements ActionListener {

	Model model;
	Board board;
	MouseListener lastUsed;

	public BuildModeButtonListener(Model m, Board b) {
		this.model = m;
		this.board = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!(lastUsed == null)){
			board.removeMouseListener(lastUsed);
		}

		if(e.getActionCommand().equals("Move")) {
			MouseListener ml = new BuildModeMoveGizmoListener(model, board.getLtoPx());
			lastUsed = ml;
			board.addMouseListener(ml);
			System.out.println("Click an object to move, then click a position to move to");
		} else if(e.getActionCommand().equals("Rotate")) {
			MouseListener rl = new BuildModeRotateGizmoListener(model, board.getLtoPx());
			lastUsed = rl;
			board.addMouseListener(rl);
			System.out.println("Click an object to rotate");
		} else if(e.getActionCommand().equals("Delete")) {
			System.out.println("Deleting object...");
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
