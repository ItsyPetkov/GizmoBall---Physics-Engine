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
					g.drawRect(i*25, j*25, 25, 25); 
				}
			}
		}else {
			//drawing the objects and setting up the scene for the game
			
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		repaint();
	}

}
