package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IModel;
import view.BuildMode;
import view.RunMode;

public class ModeMenuActionListener implements ActionListener {

	IModel model;

	public ModeMenuActionListener(IModel m) {
		this.model = m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("Enter Run Mode")) {
			for(Frame f: BuildMode.getFrames()) {
				f.dispose();
			}
			new RunMode(model);
		}else if(arg0.getActionCommand().equals("Enter Build Mode")) {
			RunMode.getTimer().stop();
			for(Frame f: RunMode.getFrames()) {
				f.dispose();
			}
			new BuildMode(model);
		}
	}

}
