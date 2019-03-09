package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import view.BuildMode;
import view.MainMenu;
import view.RunMode;

public class MainMenuButtonListener implements ActionListener {

	private RunMode rm;
	private BuildMode bm;
	private Model model;
	
	public MainMenuButtonListener() {
		model = new Model();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("New Game")) {
			bm = new BuildMode(model);
			MainMenu.getMainMenuFrame().dispose();
		}else if(arg0.getActionCommand().equals("Load Game")) {
			model.addBall(new Ball(4,10,10,20));
			model.addBall(new Ball(14,10,-10,5));
			model.addGizmo(new Absorber(5, 5, 8, 8));
			model.addGizmo(new SquareBumper(10,1));
			model.addGizmo(new TriangleBumper(3,12));
			model.addGizmo(new LeftFlipper(3,3));
			model.addGizmo(new RightFlipper(6,6));
			rm = new RunMode(model);
			MainMenu.getMainMenuFrame().dispose();
		}else if(arg0.getActionCommand().equals("Exit Game")) {
			MainMenu.exitGameFrame();
		}
	}

}
