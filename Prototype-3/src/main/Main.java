package main;

import model.*;
import view.GUI;

public class Main {
	
	public static void main(String args[]) {
		Model model = new Model();
		Gizmo S1 = new SquareBumper(1,1);
		Gizmo T1 = new TriangleBumper(3,1);
		Gizmo C1 = new CircleBumper(5, 1);
		model.addGizmo(S1);
		model.addGizmo(T1);
		model.addGizmo(C1);
		GUI gui = new GUI(model);
	}
}
