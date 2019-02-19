package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Board extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	private boolean running;
	private Model model;
	private Ball ball;
	private int LtoPx = 25;
	private Flipper flipper;
	
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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (!isRunning()) {
			//draw grid here
			g.setColor(Color.BLACK);
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					g.drawRect(i * 25, j * 25, 25, 25);
				}
			}
		} else {
			//drawing the objects and setting up the scene for Prototype 3
			//drawing the ball
			g.setColor(model.getBall().getColour());
			Coordinate ballPos = model.getBall().getPos();
			double bRad = model.getBall().getRadius();
			g.fillOval(((int) ballPos.getX() * LtoPx), ((int) ballPos.getY() * LtoPx), ((int) (bRad * 2) * LtoPx), ((int) (bRad * 2) * LtoPx));

			//drawing gizmos
			List<Gizmo> gizmoList = model.getGizmos();
			for (int i = 0; i < gizmoList.size(); i++) {
				g.setColor(gizmoList.get(i).getColour());
				Coordinate gPos = gizmoList.get(i).getPos();

				if (gizmoList.get(i).getSides().size() == 4) {
					g.fillRect((int) (gPos.getX() * LtoPx), (int) (gPos.getY() * LtoPx), LtoPx, LtoPx);
				} else if (gizmoList.get(i).getSides().size() == 3) {
					int[] xP = {(int) (gPos.getX() * LtoPx), (int) ((gPos.getX() + 1) * LtoPx), (int) (gPos.getX() * LtoPx)};
					int[] yP = {(int) (gPos.getY() * LtoPx), (int) (gPos.getY() * LtoPx), (int) ((gPos.getY() + 1) * LtoPx)};
					g.fillPolygon(xP, yP, 3);
				} else {
					g.fillOval((int) (gPos.getX() * LtoPx), (int) (gPos.getY() * LtoPx), LtoPx, LtoPx);
				}

			}
		}
	}

	private void drawFlipper(Graphics g){
		g.setColor(flipper.getColor());
		g.fillOval((int)((flipper.getTopCircle().getCenter().x()-0.25)*20), (int)((flipper.getTopCircle().getCenter().y()-0.25)*20), 10, 10);
		g.fillOval((int)((flipper.getBottomCircle().getCenter().x()-0.25)*20), (int)((flipper.getBottomCircle().getCenter().y()-0.25)*20), 10, 10);

		//Make middle of flipper with a polynomial, rectangle doesn't work
		int[] X = new int[4];
		int[] Y = new int[4];
		int[] X1 = new int[4];
		int[] Y1 = new int[4];

       /* int offset = 4;
        X[0]=(int)(flipper.getTopCircle().getCenter().x()*20+offset);
        X[1]=(int)(flipper.getBottomCircle().getCenter().x()*20+offset);
        X[2]=(int)(flipper.getBottomCircle().getCenter().x()*20-offset);
        X[3]=(int)(flipper.getTopCircle().getCenter().x()*20-offset);

        Y[0]=(int)(flipper.getTopCircle().getCenter().y()*20+offset);
        Y[1]=(int)(flipper.getBottomCircle().getCenter().y()*20+offset);
        Y[2]=(int)(flipper.getBottomCircle().getCenter().y()*20-offset);
        Y[3]=(int)(flipper.getTopCircle().getCenter().y()*20-offset);*/

		X[0]=(int)(flipper.getTopSideLine().p1().x()*20);
		X[1]=(int)(flipper.getTopSideLine().p2().x()*20);
		X[2]=(int)(flipper.getBottomSideLine().p1().x()*20);
		X[3]=(int)(flipper.getBottomSideLine().p2().x()*20);

		Y[0]=(int)(flipper.getTopSideLine().p1().y()*20);
		Y[1]=(int)(flipper.getTopSideLine().p2().y()*20);
		Y[2]=(int)(flipper.getBottomSideLine().p1().y()*20);
		Y[3]=(int)(flipper.getBottomSideLine().p2().y()*20);

		X1[0]=(int)(flipper.getRightSideLine().p1().x()*20);
		X1[1]=(int)(flipper.getRightSideLine().p2().x()*20);
		X1[2]=(int)(flipper.getLeftSideLine().p1().x()*20);
		X1[3]=(int)(flipper.getLeftSideLine().p2().x()*20);

		Y1[0]=(int)(flipper.getRightSideLine().p1().y()*20);
		Y1[1]=(int)(flipper.getRightSideLine().p2().y()*20);
		Y1[2]=(int)(flipper.getLeftSideLine().p1().y()*20);
		Y1[3]=(int)(flipper.getLeftSideLine().p2().y()*20);



		g.fillPolygon(X,Y,4);
		g.fillPolygon(X1,Y1,4);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		repaint();
	}

}
