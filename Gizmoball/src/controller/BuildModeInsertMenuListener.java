package controller;

import model.Absorber;
import model.Gizmo;
import model.IModel;
import physics.Vect;
import view.Board;

import java.awt.event.*;

public class BuildModeInsertMenuListener implements ActionListener {

	IModel model;
	Board board;
	String idGen = "";
	Gizmo newAbs = null;
	boolean avail = false;
	Vect last;

	public BuildModeInsertMenuListener(IModel m, Board b) {
		this.model = m;
		this.board = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Remove listeners
		MouseListener[] mss = board.getMouseListeners();
		for(int i=0; i<mss.length; i++){
			board.removeMouseListener(mss[i]);
		}

		MouseMotionListener[] mml = board.getMouseMotionListeners();
		for(int i=0; i<mml.length; i++){
			board.removeMouseMotionListener(mml[i]);
		}

		//Creating id's for gizmos
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

		//Dynamically moving absorber
		if(e.getActionCommand().equals("Absorber")){
			board.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent me) {
					avail = model.addAbsorber(idGen, me.getX()/board.getLtoPx(), me.getY()/board.getLtoPx(), (me.getX()/board.getLtoPx()) +1, (me.getY()/board.getLtoPx()) +1);
				}
			});

			board.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent me) {
					if(!(newAbs == null) && avail){
						Absorber abs = (Absorber) newAbs;
						int x = me.getX()/board.getLtoPx();
						int y = me.getY()/board.getLtoPx();
						if(last == null){
							last = new Vect(x,y);
						} else if(!(last.x() == x && last.y() == y)){
							boolean check = model.dragAbs(abs, last, x, y);
							if(check){
								last = new Vect(x,y);
							}
						}

					}
				}
			});
		} else {
			board.addMouseListener(new BuildModeInsertMouseListener(model, board.getLtoPx(), e.getActionCommand().replaceAll("\\s+",""), idGen));
	}
	}

}
