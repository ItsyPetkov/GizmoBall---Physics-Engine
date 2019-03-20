package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import view.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainMenuButtonListener implements ActionListener {

	private IRunMode rm;
	private IBuildMode bm;
	private IModel model;
	private ILoadFile loadfile;
	private JFileChooser chooser;

	public MainMenuButtonListener() {
		model = new Model();
		loadfile = new LoadFile(model);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Starting a New Game
		if(arg0.getActionCommand().equals("New Game")) {
			bm = new BuildMode(model);
			MainMenu.getMainMenuFrame().dispose();
			//Loading a saved Game txt file
		}else if(arg0.getActionCommand().equals("Load Game")) {
			chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files","txt");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				if (loadfile.load(chooser.getSelectedFile().getPath())) {
					System.out.println(chooser.getSelectedFile().getName()+" loaded successfully");
					rm = new RunMode(model);
					MainMenu.getMainMenuFrame().dispose();
				}
			}

			//Terminating the program
		}else if(arg0.getActionCommand().equals("Exit Game")) {
			MainMenu.exitGameFrame();
		}
	}

}
