package controller;

import model.Model;
import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class BuildModeButtonListener implements ActionListener {

	Model model;
	Board board;

	public BuildModeButtonListener(Model m, Board b) {
		this.model = m;
		this.board = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Move")) {
			board.addMouseListener(new BuildModeMouseListener(model));
			System.out.println("Click an object to move");
		}else if(e.getActionCommand().equals("Rotate")) {
			System.out.println("Rotating object...");
		}else if(e.getActionCommand().equals("Delete")) {
			System.out.println("Deleting object...");
		}else if(e.getActionCommand().equals("Connect")) {
			System.out.println("Connecting...");
		}else if(e.getActionCommand().equals("Disconnect")) {
			System.out.println("Disconneecting...");
		}else if(e.getActionCommand().equals("Key Connect")) {
			System.out.println("Connecting Key...");
		}else if(e.getActionCommand().equals("Key Disconnect")) {
			System.out.println("Disconnecting Key...");
		}
	}

}
