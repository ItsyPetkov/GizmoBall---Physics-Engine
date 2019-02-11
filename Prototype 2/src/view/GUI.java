package view;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Model;

public class GUI extends JFrame {

	private JPanel contentPane;
	private Model model;
	private Board board;
	
	public GUI(Model model){
		this.model = model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 615);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setVisible(true);
		
		JLabel lblWelcomeToGizmoball = new JLabel("Welcome to Gizmoball Prototype 2");
		lblWelcomeToGizmoball.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblWelcomeToGizmoball.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWelcomeToGizmoball, BorderLayout.NORTH);
		
		board = new Board(model);
		contentPane.add(board, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,1));
		JButton button = new JButton("Tick");
		JButton button2 = new JButton("Pause");
		buttonPanel.add(button);
		buttonPanel.add(button2);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
	}
}
