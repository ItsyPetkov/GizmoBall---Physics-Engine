package main;

import model.Model;
import view.GUI;

public class Main {
	
	public static void main(String args[]) {
		Model model = new Model();
		GUI gui = new GUI(model);
	}
}
