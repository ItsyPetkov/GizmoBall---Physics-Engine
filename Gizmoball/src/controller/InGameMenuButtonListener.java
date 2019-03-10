package controller;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.BuildMode;
import view.InGameMenuBuildMode;
import view.InGameMenuRunMode;
import view.RunMode;

public class InGameMenuButtonListener implements ActionListener {


	
	public InGameMenuButtonListener() {
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("Resume Game ")) {
			InGameMenuBuildMode.getInGameMenuFrame().dispose();
		}else if(arg0.getActionCommand().equals("Resume Game")) {
			InGameMenuRunMode.getInGameMenuFrame().dispose();
		}else if(arg0.getActionCommand().equals("Enter Run Mode")) {
			for(Frame f: BuildMode.getFrames()) {
				f.dispose();
			}
			//Need to enter RunMode here
		}else if(arg0.getActionCommand().equals("Restart Game")) {
			System.out.println("Restarting game...");
		}else if(arg0.getActionCommand().equals("Enter Build Mode")) {
			for(Frame f: RunMode.getFrames()) {
				f.dispose();
			}
			//Need to enter BuildMode here
		}else if(arg0.getActionCommand().equals("Exit Game ")) {
			InGameMenuBuildMode.inGameMenuExitGameFrame();
		}else if(arg0.getActionCommand().equals("Exit Game")) {
			InGameMenuRunMode.inGameMenuExitGameFrame();
		}
	}

}
