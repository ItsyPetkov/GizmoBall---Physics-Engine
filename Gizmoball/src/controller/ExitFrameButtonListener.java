package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainMenu;

public class ExitFrameButtonListener implements ActionListener {

	private MainMenu mm;
	
	public ExitFrameButtonListener() {
		mm = new MainMenu();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("Leave")) {
			System.exit(0);
		}else if(arg0.getActionCommand().equals("Stay")) {
			//Leave this for later
			// need to make frame disappear here
		}
	}

}