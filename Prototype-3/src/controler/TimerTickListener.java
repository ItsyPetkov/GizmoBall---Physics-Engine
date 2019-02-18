package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;

public class TimerTickListener implements ActionListener {

	private Model model;
	
	public TimerTickListener(Model m) {
		model = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(model.getCollision()==true){
			try{
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException ex){
				ex.printStackTrace();
			}
		}
		model.moveBall();
	}

}
