package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ExitFrameButtonListener;
import controller.MainMenuButtonListener;

public class MainMenu {
	
	private static JPanel contentPane;
	static JFrame exitFrame;
	static JFrame mainMenuFrame;

	public static void mainMenuFrame() {
		mainMenuFrame = new JFrame("Gizmoball - Main Menu");
		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenuFrame.setBounds(100,100,640,480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		mainMenuFrame.setContentPane(contentPane);
		JLabel lblGizmoball = new JLabel("Gizmoball 2019 - Main Menu");
		lblGizmoball.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblGizmoball.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblGizmoball, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(200);
		flowLayout.setVgap(50);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new MainMenuButtonListener());
		btnNewGame.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new MainMenuButtonListener());
		btnLoadGame.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.addActionListener(new MainMenuButtonListener());
		btnExitGame.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		panel.add(btnNewGame);
		panel.add(btnLoadGame);
		panel.add(btnExitGame);
		
		mainMenuFrame.setVisible(true);
		mainMenuFrame.setLocationRelativeTo(null);
	}

	public static void exitGameFrame() {
		exitFrame = new JFrame("Exit?");
		Container content = exitFrame.getContentPane();
		JLabel label = new JLabel("Are you sure you want to exit?");
		label.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel panel = new JPanel(new GridLayout(1,1));
		panel.add(label);
		JButton btnLeave = new JButton("Leave");
		btnLeave.addActionListener(new ExitFrameButtonListener());
		JButton btnStay = new JButton("Stay");
		btnStay.addActionListener(new ExitFrameButtonListener());
		JPanel btnPanel = new JPanel(new GridLayout(1,1));
		btnPanel.add(btnLeave);
		btnPanel.add(btnStay);
		content.add(panel, BorderLayout.CENTER);
		content.add(btnPanel, BorderLayout.SOUTH);
		exitFrame.setSize(420,120);
		exitFrame.setLocationRelativeTo(null);
		exitFrame.setVisible(true);
	}
	
	public static JFrame getExitFrame() {
		return exitFrame;
	}
	
	public static JFrame getMainMenuFrame() {
		return mainMenuFrame;
	}
	
}
	

