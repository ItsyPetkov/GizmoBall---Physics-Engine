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

import controler.InGameMenuButtonListener;

public class InGameMenuRunMode {
	
	private JPanel contentPane;
	
	public InGameMenuRunMode() {
		inGameMenuFrame();
	}
	
	private void inGameMenuFrame() {
		JFrame inGameMenuFrame = new JFrame("Gizmoball - In - Game Menu");
		inGameMenuFrame.setBounds(100,100,640,560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		inGameMenuFrame.setContentPane(contentPane);
		JLabel lblGizmoball = new JLabel("Gizmoball 2019: In - Game Menu");
		lblGizmoball.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblGizmoball.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblGizmoball, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(200);
		flowLayout.setVgap(50);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JButton btnResumeGame = new JButton("Resume Game");
		btnResumeGame.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnResumeGame.addActionListener(new InGameMenuButtonListener());
		
		JButton btnRestartGame = new JButton("Restart Game");
		btnRestartGame.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnRestartGame.addActionListener(new InGameMenuButtonListener());
		
		JButton btnEnterBuildMode = new JButton("Enter Build Mode");
		btnEnterBuildMode.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnEnterBuildMode.addActionListener(new InGameMenuButtonListener());
		
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnExitGame.addActionListener(new InGameMenuButtonListener());
		
		panel.add(btnResumeGame);
		panel.add(btnRestartGame);
		panel.add(btnEnterBuildMode);
		panel.add(btnExitGame);
		
		inGameMenuFrame.setVisible(true);
		inGameMenuFrame.setLocationRelativeTo(null);
	}
	
	private void inGameMenuExitGameFrame() {
		JFrame exitFrame = new JFrame("Exit?");
		Container content = exitFrame.getContentPane();
		JLabel label = new JLabel("Are you sure you want to exit?");
		label.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel panel = new JPanel(new GridLayout(1,1));
		panel.add(label);
		JButton btnLeave = new JButton("To Main Menu");
		JButton btnStay = new JButton("To Desktop");
		JPanel btnPanel = new JPanel(new GridLayout(1,1));
		btnPanel.add(btnLeave);
		btnPanel.add(btnStay);
		content.add(panel, BorderLayout.CENTER);
		content.add(btnPanel, BorderLayout.SOUTH);
		exitFrame.setSize(420,120);
		exitFrame.setLocationRelativeTo(null);
		exitFrame.setVisible(true);
	}
}
