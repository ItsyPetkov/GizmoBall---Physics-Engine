package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileMenuActionListener implements ActionListener {

	public FileMenuActionListener() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Clear Board")) {
			System.out.println("Clearing Board...");
		}else if(e.getActionCommand().equals("Save Board")) {
			System.out.println("Saving Board...");
		}else if(e.getActionCommand().equals("Load Board")) {
			System.out.println("Loading Board...");
		}
	}

}
