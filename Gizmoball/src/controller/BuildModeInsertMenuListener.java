package controller;

import model.Model;
import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BuildModeInsertMenuListener implements ActionListener {

	Model model;
	Board board;

	public BuildModeInsertMenuListener(Model m, Board b) {
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

		MouseMotionListener[] mml = board.getMouseMotionListeners();
		for(int i=0; i<mml.length; i++){
			board.removeMouseMotionListener(mml[i]);
		}

		String idGen = "";


		if(e.getActionCommand().equals("Square")) {
			int i = 1;
			idGen = "S" + i;
			while(!model.checkGizmoId(idGen)){
				i++;
				idGen = "S" + i;
			}
		}else if(e.getActionCommand().equals("Triangle")) {
			int i = 1;
			idGen = "T" + i;
			while(!model.checkGizmoId(idGen)){
				i++;
				idGen = "T" + i;
			}
		}else if(e.getActionCommand().equals("Circle")) {
			int i = 1;
			idGen = "C" + i;
			while(!model.checkGizmoId(idGen)){
				i++;
				idGen = "C" + i;
			}
		}else if(e.getActionCommand().equals("Left Flipper")) {
			int i = 1;
			idGen = "LF" + i;
			while(!model.checkGizmoId(idGen)){
				i++;
				idGen = "LF" + i;
			}
		}else if(e.getActionCommand().equals("Right Flipper")) {
			int i = 1;
			idGen = "RF" + i;
			while(!model.checkGizmoId(idGen)){
				i++;
				idGen = "RF" + i;
			}
		}else if(e.getActionCommand().equals("Absorber")) {
			int i = 1;
			idGen = "A" + i;
			while(!model.checkGizmoId(idGen)){
				i++;
				idGen = "A" + i;
			}
			System.out.println("Adding an absorber...");
		}else if(e.getActionCommand().equals("Ball")) {
			int i = 1;
			idGen = "B" + i;
			while(!model.checkBallId(idGen)){
				i++;
				idGen = "B" + i;
			}
		}
		board.addMouseListener(new BuildModeInsertMouseListener(model, board.getLtoPx(), e.getActionCommand().replaceAll("\\s+",""), idGen));
	}

}
