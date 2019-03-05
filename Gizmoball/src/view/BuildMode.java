package view;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controler.BuildModeButtonListener;
import controler.EscapeKeyListener;
import controler.FileMenuActionListener;
import controler.InsertMenuActionListener;
import model.Model;

public class BuildMode extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar jmb;
	private JMenu file;
	private JMenu insert;
	private Board board;
	private Model model;
	
	public BuildMode(Model model) {
		this.model = model;
		
		jmb = new JMenuBar();
		file = new JMenu("File");
		insert = new JMenu("Insert");
		
		jmb.add(file);
		jmb.add(insert);
		
		JMenuItem clear = new JMenuItem("Clear Board");
		clear.addActionListener(new FileMenuActionListener());
		
		JMenuItem save = new JMenuItem("Save Board");
		save.addActionListener(new FileMenuActionListener());
		
		JMenuItem load = new JMenuItem("Load Board");
		load.addActionListener(new FileMenuActionListener());
		
		file.add(clear);
		file.add(save);
		file.add(load);
		
		JMenuItem square = new JMenuItem("Square");
		square.addActionListener(new InsertMenuActionListener());
		
		JMenuItem triangle = new JMenuItem("Triangle");
		triangle.addActionListener(new InsertMenuActionListener());
		
		JMenuItem circle = new JMenuItem("Circle");
		circle.addActionListener(new InsertMenuActionListener());
		
		JMenuItem leftFlipper = new JMenuItem("Left Flipper");
		leftFlipper.addActionListener(new InsertMenuActionListener());
		
		JMenuItem rightFlipper = new JMenuItem("Right Flipper");
		rightFlipper.addActionListener(new InsertMenuActionListener());
		
		JMenuItem absorber = new JMenuItem("Absorber");
		absorber.addActionListener(new InsertMenuActionListener());
		
		JMenuItem ball = new JMenuItem("Ball");
		ball.addActionListener(new InsertMenuActionListener());
		
		insert.add(square);
		insert.add(triangle);
		insert.add(circle);
		insert.add(leftFlipper);
		insert.add(rightFlipper);
		insert.add(absorber);
		insert.add(ball);
		
		setJMenuBar(jmb);
		setTitle("Gizmoball - Build Mode");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setVisible(true);
		
		this.addKeyListener(new EscapeKeyListener(state()));
		this.setFocusable(true);
		
		JLabel lblWelcomeToGizmoball = new JLabel("Welcome to Gizmoball - Build Mode");
		lblWelcomeToGizmoball.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblWelcomeToGizmoball.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWelcomeToGizmoball, BorderLayout.NORTH);
		
		board = new Board(model);
		board.setRunningState(false);
		contentPane.add(board, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new GridLayout(3,1));
		JButton btnMove = new JButton("Move");
		btnMove.addActionListener(new BuildModeButtonListener());
		
		JButton btnRotate = new JButton("Rotate");
		btnRotate.addActionListener(new BuildModeButtonListener());
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new BuildModeButtonListener());
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new BuildModeButtonListener());
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.addActionListener(new BuildModeButtonListener());
		
		JButton btnKeyConnect = new JButton("Key Connect");
		btnKeyConnect.addActionListener(new BuildModeButtonListener());
		
		JButton btnKeyDisconnect = new JButton("Key Disconnect");
		btnKeyDisconnect.addActionListener(new BuildModeButtonListener());
		
		buttonPanel.add(btnMove);
		buttonPanel.add(btnRotate);
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnConnect);
		buttonPanel.add(btnDisconnect);
		buttonPanel.add(btnKeyConnect);
		buttonPanel.add(btnKeyDisconnect);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public static String state() {
		return "not running";
	}
	
}
