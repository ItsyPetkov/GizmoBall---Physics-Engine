package main;

import model.*;
import view.GUI;

import java.awt.*;

public class Main {
	
	public static void main(String args[]) {
		Model model = new Model();
		Gizmo S1 = new SquareBumper("S1",1,1);
		Gizmo T1 = new TriangleBumper("T1",3,1);
		Gizmo C1 = new CircleBumper("C1",5, 1);
		Flipper LF5 = new Flipper("LF5", 10,10,true, Color.RED);
		Flipper RF2 = new Flipper("RF2", 4, 5, false, Color.GREEN);


		model.addGizmo(S1);
		model.addGizmo(T1);
		model.addGizmo(C1);

		model.addFlipper(LF5);
		model.addFlipper(RF2);


		model.loadFile("sample.txt");
		model.saveFile("savedGizmo.txt");
		GUI gui = new GUI(model);
	}
}
