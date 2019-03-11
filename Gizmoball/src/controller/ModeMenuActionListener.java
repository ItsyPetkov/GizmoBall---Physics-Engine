package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;
import view.BuildMode;
import view.RunMode;

public class ModeMenuActionListener implements ActionListener {
	
	Model model;
	
	public ModeMenuActionListener(Model m) {
		this.model = m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("Enter Run Mode")) {
			new RunMode(model);
		}else if(arg0.getActionCommand().equals("Enter Build Mode")) {
			new BuildMode(model);
		}
	}

}
