package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GUI extends JFrame {

	private JPanel contentPane;
	private model.Model model;
	private view.Board board;
	private Timer timer;
	
	public GUI(model.Model model){
		this.model = model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setVisible(true);
		this.addKeyListener(new controller.AbsorberKeyListener(model, this));
		this.setFocusable(true);
		timer = new Timer(100, new controller.TimerTickListener(model));
		
		JLabel lblWelcomeToGizmoball = new JLabel("Welcome to Gizmoball Prototype 4");
		lblWelcomeToGizmoball.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblWelcomeToGizmoball.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWelcomeToGizmoball, BorderLayout.NORTH);
		
		board = new view.Board(model);
		contentPane.add(board, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,1));
		JButton button = new JButton("Tick");

//		button.addActionListener(new TickButtonListener(model));
		JButton button2 = new JButton("Pause");
//		button2.addActionListener(new PauseButtonListener(model));
		
		buttonPanel.add(button);
		buttonPanel.add(button2);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		
	}
	
	public Timer getTimer() {
		return timer;
	}
}