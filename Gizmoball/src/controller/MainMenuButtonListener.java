package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;
import view.BuildMode;
import view.MainMenu;
import view.RunMode;

public class MainMenuButtonListener implements ActionListener {

	private MainMenu mm;
	private RunMode rm;
	private BuildMode bm;
	private Model model;
	
	public MainMenuButtonListener() {
		model = new Model();
		mm = new MainMenu();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("New Game")) {
			bm = new BuildMode(model);
			//need to make the frame disappear here
		}else if(arg0.getActionCommand().equals("Load Game")) {
			rm = new RunMode(model);
			//need to make the frame disappear here
		}else if(arg0.getActionCommand().equals("Exit Game")) {
			mm.exitGameFrame();
		}
	}

}
