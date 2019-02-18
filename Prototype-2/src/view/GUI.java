package view;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import controller.AbsorberKeyListener;
import controller.PauseButtonListener;
import controller.TickButtonListener;
import controller.TimerTickListener;
import model.Model;

public class GUI extends JFrame {

	private JPanel contentPane;
	private Model model;
	private Board board;
	private Timer timer;
	
	public GUI(Model model){
		this.model = model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setVisible(true);
		this.addKeyListener(new AbsorberKeyListener(model, this));
		this.setFocusable(true);
		timer = new Timer(100, new TimerTickListener(model));
		
		JLabel lblWelcomeToGizmoball = new JLabel("Welcome to Gizmoball Prototype 2");
		lblWelcomeToGizmoball.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblWelcomeToGizmoball.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWelcomeToGizmoball, BorderLayout.NORTH);
		
		board = new Board(model);
		contentPane.add(board, BorderLayout.CENTER);
		
	}
	
	public Timer getTimer() {
		return timer;
	}
}
