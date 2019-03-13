package view;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.EscapeKeyListener;
import controller.ModeMenuActionListener;
import controller.RunModeButtonListener;
import controller.TimerTickListener;
import model.Model;

public class RunMode extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar jmb;
	private JMenu mode;
	private Model model;
	private Board board;
	private static Timer timer;
	
	public RunMode(Model model) {
		this.model = model;
		
		jmb = new JMenuBar();
		mode = new JMenu("Mode");
		
		jmb.add(mode);
		
		JMenuItem buildMode = new JMenuItem("Enter Build Mode");
		buildMode.addActionListener(new ModeMenuActionListener(model));
		
		mode.add(buildMode);
		
		setJMenuBar(jmb);
		setTitle("Gizmoball - Run Mode");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setVisible(true);
//		this.addKeyListener(new AbsorberKeyListener(model, this));
//		this.setFocusable(true);
		this.addKeyListener(new EscapeKeyListener(state()));
		this.setFocusable(true);
		timer = new Timer(50, new TimerTickListener(model));
		
		JLabel lblWelcomeToGizmoball = new JLabel("Welcome to Gizmoball - Run Mode");
		lblWelcomeToGizmoball.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblWelcomeToGizmoball.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWelcomeToGizmoball, BorderLayout.NORTH);
		
		board = new Board(model);
		contentPane.add(board, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,1));
		JButton btnTick = new JButton("Tick");
		btnTick.addActionListener(new RunModeButtonListener(model, this));
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new RunModeButtonListener(model, this));
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new RunModeButtonListener(model, this));
		
		buttonPanel.add(btnTick);
		buttonPanel.add(btnRun);
		buttonPanel.add(btnPause);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
	}

	public static Timer getTimer(){
		return timer;
	}
	
	public static String state() {
		return "running";
	}
}
