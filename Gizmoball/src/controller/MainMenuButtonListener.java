package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import view.BuildMode;
import view.MainMenu;
import view.RunMode;

import javax.swing.*;

public class MainMenuButtonListener implements ActionListener {

	private RunMode rm;
	private BuildMode bm;
	private Model model;
	private LoadFile loadfile;
	
	public MainMenuButtonListener() {
		model = new Model();
		loadfile = new LoadFile(model);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("New Game")) {
			bm = new BuildMode(model);
			MainMenu.getMainMenuFrame().dispose();
		}else if(arg0.getActionCommand().equals("Load Game")) {
//			model.addBall(new Ball(4,10,10,20));
//			model.addBall(new Ball(14,10,-10,5));
//			model.addGizmo(new Absorber("AB1",0, 18, 20, 20));
//			model.addGizmo(new SquareBumper("S1",10,1));
//			model.addGizmo(new TriangleBumper("T1",3,12));
//			model.addGizmo(new LeftFlipper("LF1",3,3));
//			model.addGizmo(new RightFlipper("RF1",6,6));
//			loadfile.load("test.txt");
			String filename = JOptionPane.showInputDialog("Load File Name?");
			if (filename != null) {
				loadfile.load(filename);
				System.out.println(filename + " has been loaded.");
				rm = new RunMode(model);
				MainMenu.getMainMenuFrame().dispose();
			}
		}else if(arg0.getActionCommand().equals("Exit Game")) {
			MainMenu.exitGameFrame();
		}
	}

}
