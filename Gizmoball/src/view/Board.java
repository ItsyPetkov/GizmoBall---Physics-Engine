package view;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.*;
import physics.Circle;
import physics.LineSegment;
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
		}
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
					case "Absorber":
						drawAbsorber(g, (Absorber) gizmoList.get(i));
						break;
					case "Square":
						drawSquare(g, (SquareBumper) gizmoList.get(i));
						break;
					case "Triangle":
						drawTriangle(g, (TriangleBumper) gizmoList.get(i));
						break;
					case "Circle":
						drawCircle(g, gizmoList.get(i));
						break;
					case "LeftFlipper":
						drawFlipper(g, gizmoList.get(i));
						break;
					case "RightFlipper":
						drawFlipper(g, gizmoList.get(i));
						break;
				}
			}

			//Drawing flippers
//			List<LeftFlipper> leftFlipperList = model.getLeftFlippers();
//			List<RightFlipper> rightFlipperList = model.getRightFlippers();
//			for (LeftFlipper lf : leftFlipperList) {
//				drawFlipper(g, lf);
//			}
//			for (RightFlipper rf : rightFlipperList) {
//				drawFlipper(g, rf);
//			}

			//drawing the walls
			g.setColor(Color.BLACK);
			g.drawLine((int) (model.getWallTL().x()*LtoPx),(int) (model.getWallTL().y()*LtoPx),(int) (model.getWallBR().x()*LtoPx),(int) (model.getWallTL().y()*LtoPx));
			g.drawLine((int) (model.getWallTL().x()*LtoPx),(int) (model.getWallTL().y()*LtoPx),(int) (model.getWallTL().x()*LtoPx),(int) (model.getWallBR().y()*LtoPx));
			g.drawLine((int) (model.getWallBR().x()*LtoPx),(int) (model.getWallBR().y()*LtoPx),(int) (model.getWallBR().x()*LtoPx),(int) (model.getWallTL().y()*LtoPx));
			g.drawLine((int) (model.getWallBR().x()*LtoPx),(int) (model.getWallBR().y()*LtoPx),(int) (model.getWallTL().x()*LtoPx),(int) (model.getWallBR().y()*LtoPx));
		}

	private void drawAbsorber(Graphics g, Absorber abs){
		Vect gPos1 = abs.getPos();
		Vect gPos2 = abs.getPos2();
		g.setColor(abs.getColour());
		g.fillRect((int) (gPos1.x()*LtoPx), (int) (gPos1.y()*LtoPx), (int) ((gPos2.x()-gPos1.x())*LtoPx), (int) ((gPos2.y()-gPos1.y())*LtoPx));
	}

	private void drawSquare(Graphics g, SquareBumper sq){
		Vect gPos = sq.getPos();
		g.setColor(sq.getColour());
		g.fillRect((int) (gPos.x()*LtoPx), (int) (gPos.y()*LtoPx), LtoPx, LtoPx);
	}

	private void drawCircle(Graphics g, Gizmo gizmo){
		Vect gPos = gizmo.getPos();
		g.setColor(gizmo.getColour());
		g.fillOval((int) (gPos.x()*LtoPx), (int) (gPos.y()*LtoPx), LtoPx, LtoPx);
	}

	private void drawTriangle(Graphics g, TriangleBumper tr){
		Vect gPos = tr.getPos();
		g.setColor(tr.getColour());
		int rotation = tr.getRotation();
		switch(rotation){
			case 0:
				int[] x0 = {(int) (gPos.x()*LtoPx), (int) ((gPos.x()+1)*LtoPx), (int) (gPos.x()*LtoPx)};
				int[] y0 = {(int) (gPos.y()*LtoPx), (int) (gPos.y()*LtoPx), (int) ((gPos.y()+1)*LtoPx)};
				g.fillPolygon(x0, y0, 3);
				break;
			case 1:
				int[] x1 = {(int) (gPos.x()*LtoPx), (int) ((gPos.x()+1)*LtoPx), (int) ((gPos.x()+1)*LtoPx)};
				int[] y1 = {(int) (gPos.y()*LtoPx), (int) (gPos.y()*LtoPx), (int) ((gPos.y()+1)*LtoPx)};
				g.fillPolygon(x1, y1, 3);
				break;
			case 2:
				int[] x2 = {(int) ((gPos.x()+1)*LtoPx), (int) ((gPos.x()+1)*LtoPx), (int) (gPos.x()*LtoPx)};
				int[] y2 = {(int) ((gPos.y()+1)*LtoPx), (int) (gPos.y()*LtoPx), (int) ((gPos.y()+1)*LtoPx)};
				g.fillPolygon(x2, y2, 3);
				break;
			case 3:
				int[] x3 = {(int) (gPos.x()*LtoPx), (int) ((gPos.x()+1)*LtoPx), (int) (gPos.x()*LtoPx)};
				int[] y3 = {(int) (gPos.y()*LtoPx), (int) ((gPos.y()+1)*LtoPx), (int) ((gPos.y()+1)*LtoPx)};
				g.fillPolygon(x3, y3, 3);
				break;
		}
	}

	private void drawFlipper(Graphics g, Gizmo gizmo){    //drawing the flippers
	    List<LineSegment> ls = gizmo.getSides();
	    List<Circle> cs = gizmo.getCorners();
	    Circle topCircle = cs.get(0);
	    Circle bottomCircle = cs.get(1);

	    LineSegment topSideLine = ls.get(0);
	    LineSegment bottomSideLine = ls.get(1);
	    LineSegment rightSideLine = ls.get(2);
	    LineSegment leftSideLine = ls.get(3);

	    g.setColor(gizmo.getColour());
	    g.fillOval((int) ((topCircle.getCenter().x() - 0.25) * LtoPx), (int) ((topCircle.getCenter().y() - 0.25) * LtoPx), 10, 10);
	    g.fillOval((int) ((bottomCircle.getCenter().x() - 0.25) * LtoPx), (int) ((bottomCircle.getCenter().y() - 0.25) * LtoPx), 10, 10);

	    //Make middle of flipper with a polynomial, rectangle doesn't work
        int[] X = new int[4];
        int[] Y = new int[4];
        int[] X1 = new int[4];
        int[] Y1 = new int[4];

        X[0] = (int) (topSideLine.p1().x() * LtoPx);
        X[1] = (int) (topSideLine.p2().x() * LtoPx);
        X[2] = (int) (bottomSideLine.p1().x() * LtoPx);
        X[3] = (int) (bottomSideLine.p2().x() * LtoPx);

        Y[0] = (int) (topSideLine.p1().y() * LtoPx);
        Y[1] = (int) (topSideLine.p2().y() * LtoPx);
        Y[2] = (int) (bottomSideLine.p1().y() * LtoPx);
        Y[3] = (int) (bottomSideLine.p2().y() * LtoPx);

        X1[0] = (int) (rightSideLine.p1().x() * LtoPx);
        X1[1] = (int) (rightSideLine.p2().x() * LtoPx);
        X1[2] = (int) (leftSideLine.p1().x() * LtoPx);
        X1[3] = (int) (leftSideLine.p2().x() * LtoPx);

        Y1[0] = (int) (rightSideLine.p1().y() * LtoPx);
        Y1[1] = (int) (rightSideLine.p2().y() * LtoPx);
        Y1[2] = (int) (leftSideLine.p1().y() * LtoPx);
        Y1[3] = (int) (leftSideLine.p2().y() * LtoPx);

        g.fillPolygon(X, Y, 4);
        g.fillPolygon(X1, Y1, 4);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		repaint();
	}

}
