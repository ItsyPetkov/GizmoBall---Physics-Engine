package view;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Absorber;
import model.Ball;
import model.Gizmo;
import model.Model;
import physics.Vect;

public class Board extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private boolean running;
	private Model model;
	private int LtoPx = 25;
	
	public Board(Model m) {
		m.addObserver(this);
		model = m;
		running = true;
		this.setSize(500, 500);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.white);
	}
	
	private boolean isRunning() {
		if(running) {
			return true;
		}
		return false;
	}
	
	public void setRunningState(boolean b) {
		running = b;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(!isRunning()) {
			//draw grid here
			g.setColor(Color.BLACK);
			for(int i = 0; i < 20; i++) {
				for(int j = 0; j < 20; j++) {
					g.drawRect(i*LtoPx, j*LtoPx, LtoPx, LtoPx);
				}
			}
		}else {
			//drawing the objects and setting up the scene for the game

			//drawing the balls
			List<Ball> ballList = model.getBalls();
			for(int i=0; i<ballList.size(); i++){
				g.setColor(ballList.get(i).getColour());
				Vect ballPos = ballList.get(i).getPos();
				double bRad = ballList.get(i).getRadius();
				g.fillOval((int) ((ballPos.x()-ballList.get(i).getRadius())*LtoPx), (int) ((ballPos.y()-ballList.get(i).getRadius())*LtoPx), (int) ((bRad*2)*LtoPx), (int) ((bRad*2)*LtoPx));
			}

			//drawing gizmos
			List<Gizmo> gizmoList = model.getGizmos();
			for(int i=0; i<gizmoList.size(); i++) {
				switch (gizmoList.get(i).type()) {
					case "absorber":
						drawAbsorber(g, (Absorber) gizmoList.get(i));
					case "square":
						drawSquare(g, gizmoList.get(i));
					case "triangle":
						drawTriangle(g, gizmoList.get(i));
					case "circle":
						drawCircle(g, gizmoList.get(i));
				}
			}

			//drawing the walls
			g.setColor(Color.BLACK);
			g.drawLine((int) (model.getWallTL().x()*LtoPx),(int) (model.getWallTL().y()*LtoPx),(int) (model.getWallBR().x()*LtoPx),(int) (model.getWallTL().y()*LtoPx));
			g.drawLine((int) (model.getWallTL().x()*LtoPx),(int) (model.getWallTL().y()*LtoPx),(int) (model.getWallTL().x()*LtoPx),(int) (model.getWallBR().y()*LtoPx));
			g.drawLine((int) (model.getWallBR().x()*LtoPx),(int) (model.getWallBR().y()*LtoPx),(int) (model.getWallBR().x()*LtoPx),(int) (model.getWallTL().y()*LtoPx));
			g.drawLine((int) (model.getWallBR().x()*LtoPx),(int) (model.getWallBR().y()*LtoPx),(int) (model.getWallTL().x()*LtoPx),(int) (model.getWallBR().y()*LtoPx));
		}
	}

	private void drawAbsorber(Graphics g, Absorber abs){
		Vect gPos1 = abs.getPos();
		Vect gPos2 = abs.getPos2();
		g.setColor(abs.getColour());
		g.fillRect((int) (gPos1.x()*LtoPx), (int) (gPos1.y()*LtoPx), (int) ((gPos2.x()-gPos1.x())*LtoPx), (int) ((gPos2.y()-gPos1.y())*LtoPx));
	}

	private void drawSquare(Graphics g, Gizmo gizmo){
		Vect gPos = gizmo.getPos();
		g.setColor(gizmo.getColour());
		g.fillRect((int) (gPos.x()*LtoPx), (int) (gPos.y()*LtoPx), LtoPx, LtoPx);
	}

	private void drawCircle(Graphics g, Gizmo gizmo){
		Vect gPos = gizmo.getPos();
		g.setColor(gizmo.getColour());
		g.fillOval((int) (gPos.x()*LtoPx), (int) (gPos.y()*LtoPx), LtoPx, LtoPx);
	}

	private void drawTriangle(Graphics g, Gizmo gizmo){
		Vect gPos = gizmo.getPos();
		g.setColor(gizmo.getColour());
		int[] xP = {(int) (gPos.x()*LtoPx), (int) ((gPos.x()+1)*LtoPx), (int) (gPos.x()*LtoPx)};
		int[] yP = {(int) (gPos.y()*LtoPx), (int) (gPos.y()*LtoPx), (int) ((gPos.y()+1)*LtoPx)};
		g.fillPolygon(xP, yP, 3);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		repaint();
	}

}
