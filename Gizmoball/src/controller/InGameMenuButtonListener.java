package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InGameMenuButtonListener implements ActionListener {

	public InGameMenuButtonListener() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("Resume Game")) {
			System.out.println("Resuming game...");
		}else if(arg0.getActionCommand().equals("Enter Run Mode")) {
			System.out.println("Entering run mode...");
		}else if(arg0.getActionCommand().equals("Restart Game")) {
			System.out.println("Restarting game...");
		}else if(arg0.getActionCommand().equals("Enter Build Mode")) {
			System.out.println("Entering build mode...");
		}else if(arg0.getActionCommand().equals("Exit Game")) {
			System.out.println("Exitting game...");
		}
	}

}
