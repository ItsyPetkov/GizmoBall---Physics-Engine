package controller;

import model.LoadFile;
import model.Model;
import model.SaveFile;
import view.BuildMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileMenuActionListener implements ActionListener {

	private LoadFile loadfile;
	private SaveFile savefile;
	private Model model;
	private BuildMode gui;

	public FileMenuActionListener(Model m, BuildMode g) {
		model = m;
		gui = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Clear Board")) {
			System.out.println("Clearing board..");
			int gsize = model.getGizmos().size();
			for(int i=0; i<gsize; i++){
				model.deleteGizmo(model.getGizmos().get(0));
			}

			int bsize = model.getBalls().size();
			for(int i=0; i<bsize; i++){
				model.deleteBall(model.getBalls().get(0));
			}

		}else if(e.getActionCommand().equals("Save Board")) {
			savefile = new SaveFile(model);
			System.out.println("Saving file..");
			String filename = JOptionPane.showInputDialog("What do you wanna save it as? Don't forget to add .txt at the end!");
			if (filename != null) {
				savefile.save(filename);
				System.out.println(filename + " has been saved.");
				gui.repaint();
			}
		}else if(e.getActionCommand().equals("Load Board")) {
			loadfile = new LoadFile(model);
			System.out.println("Loading file..");
			String filename = JOptionPane.showInputDialog("Load File Name?");
			if (filename != null) {
				loadfile.load(filename);
				System.out.println(filename + " has been loaded.");
//            gui.repaint();
				for(Frame f: BuildMode.getFrames()) {
					f.dispose();
				}
				gui = new BuildMode(model);
			}
		}
	}

}
