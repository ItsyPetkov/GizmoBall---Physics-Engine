package view;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Ball;
import model.Gizmo;
import model.Model;
import physics.Vect;

public class Board extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private boolean running;
	private Model model;
	private Ball ball;
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
			//drawing the ball
			g.setColor(model.getBall().getColour());
			Vect ballPos = model.getBall().getPos();
			double bRad = model.getBall().getRadius();
			g.fillOval((int) ((ballPos.x()-model.getBall().getRadius())*LtoPx), (int) ((ballPos.y()-model.getBall().getRadius())*LtoPx), (int) ((bRad*2)*LtoPx), (int) ((bRad*2)*LtoPx));

			//drawing gizmos
			List<Gizmo> gizmoList = model.getGizmos();
			for(int i=0; i<gizmoList.size(); i++){
				g.setColor(gizmoList.get(i).getColour());
				Vect gPos = gizmoList.get(i).getPos();

				if(gizmoList.get(i).getSides().size() == 4){
					g.fillRect((int) (gPos.x()*LtoPx), (int) (gPos.y()*LtoPx), LtoPx, LtoPx);
				} else if(gizmoList.get(i).getSides().size() == 3){
					int[] xP = {(int) (gPos.x()*LtoPx), (int) ((gPos.x()+1)*LtoPx), (int) (gPos.x()*LtoPx)};
					int[] yP = {(int) (gPos.y()*LtoPx), (int) (gPos.y()*LtoPx), (int) ((gPos.y()+1)*LtoPx)};
					g.fillPolygon(xP, yP, 3);
				} else {
					g.fillOval((int) (gPos.x()*LtoPx), (int) (gPos.y()*LtoPx), LtoPx, LtoPx);
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

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		repaint();
	}

}
