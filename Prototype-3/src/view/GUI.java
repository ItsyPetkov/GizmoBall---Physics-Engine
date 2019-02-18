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

import controler.PauseButtonListener;
import controler.StartButtonListener;
import controler.TimerTickListener;
import model.Model;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
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
//		this.addKeyListener(new AbsorberKeyListener(model, this));
//		this.setFocusable(true);
		timer = new Timer(100, new TimerTickListener(model));
		
		JLabel lblWelcomeToGizmoball = new JLabel("Welcome to Gizmoball Prototype 3");
		lblWelcomeToGizmoball.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblWelcomeToGizmoball.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWelcomeToGizmoball, BorderLayout.NORTH);
		
		board = new Board(model);
		contentPane.add(board, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,1));
		JButton button = new JButton("Start");

		button.addActionListener(new StartButtonListener(model, this));
		JButton button2 = new JButton("Pause");
		button2.addActionListener(new PauseButtonListener(this));
		
		buttonPanel.add(button);
		buttonPanel.add(button2);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		
	}
	
	public Timer getTimer() {
		return timer;
	}
}