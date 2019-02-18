package view;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Board extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	private boolean running;
	private model.Model model;
	private model.Ball ball;
	
	public Board(model.Model m) {
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
		
		if(!isRunning()) {
			//draw grid here
			g.setColor(Color.BLACK);
			for(int i = 0; i < 20; i++) {
				for(int j = 0; j < 20; j++) {
					g.drawRect(i*25, j*25, 25, 25); 
				}
			}
		}else {
			//drawing the objects and setting up the scene for Prototype 2
			//drawing the absorber

//			g.setColor(Color.MAGENTA);
//			Coordinate absTLPos = model.getAbsTLPos();
//			Coordinate absBRPos = model.getAbsBRPos();
//			g.fillRect((int) (absTLPos.getX()), (int) (absTLPos.getY()), (int) (absBRPos.getX()-absTLPos.getX()), (int) (absBRPos.getY()-absTLPos.getY()));
//			//drawing the ball
//			g.setColor(Color.BLUE);
//			Coordinate ballPos = model.getBallPos();
//			double bRad = model.getBallRadius();
//			g.fillOval(((int) ballPos.getX()), ((int) ballPos.getY()), ((int) bRad*2), ((int) bRad*2));
			//drawing the walls
//			Graphics2D g2 = (Graphics2D) g;
//			g2.setColor(Color.BLACK);
//			g2.setStroke(new BasicStroke(3));
//			g2.drawLine(0, 0, 500, 100);
//			g2.drawLine(0, 100, 400, 200);
			
		}
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		repaint();
	}

}
