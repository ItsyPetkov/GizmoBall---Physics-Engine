package main;

import model.*;
import view.GUI;

public class Main {
	
	public static void main(String args[]) {
		Model model = new Model();
		Gizmo S1 = new SquareBumper(1,3);
		Gizmo T1 = new TriangleBumper(3,3);
		Gizmo C1 = new CircleBumper(5, 3);
		Gizmo S2 = new SquareBumper(3,16);
		Gizmo T2 = new TriangleBumper(7,16);
		Gizmo C2 = new CircleBumper(11, 16);
		Gizmo S3 = new SquareBumper(15,3);
		Gizmo T3 = new TriangleBumper(15,7);
		Gizmo C3 = new CircleBumper(14, 11);
		model.addGizmo(S1);
		model.addGizmo(T1);
		model.addGizmo(C1);
		model.addGizmo(S2);
		model.addGizmo(T2);
		model.addGizmo(C2);
		model.addGizmo(S3);
		model.addGizmo(T3);
		model.addGizmo(C3);
		GUI gui = new GUI(model);
	}
}
