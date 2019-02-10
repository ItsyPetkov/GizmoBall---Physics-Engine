package view;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Ball;
import model.Model;

public class Board extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	private boolean running;
	private Model model;
	private Ball ball;
	
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
			g.setColor(Color.MAGENTA);
			g.fillRect(0, 450, 500, 50);
			g.setColor(Color.BLUE);
			g.fillOval(400, 470, 20, 20);
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(0, 0, 500, 100);
			g2.drawLine(0, 100, 400, 200);
			
		}
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		repaint();
	}

}
