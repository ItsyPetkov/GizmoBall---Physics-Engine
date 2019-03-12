package view;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.*;
import model.Model;

public class BuildMode extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar jmb;
	private JMenu file;
	private JMenu insert;
	private JMenu mode;
	private Board board;
	private Model model;
	
	public BuildMode(Model model) {
		this.model = model;
		
		jmb = new JMenuBar();
		file = new JMenu("File");
		insert = new JMenu("Insert");
		mode = new JMenu("Mode");
		
		jmb.add(file);
		jmb.add(insert);
		jmb.add(mode);
		
		JMenuItem runMode = new JMenuItem("Enter Run Mode");
		runMode.addActionListener(new ModeMenuActionListener(model));
		
		mode.add(runMode);
		
		setJMenuBar(jmb);
		setTitle("Gizmoball - Build Mode");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setVisible(true);
		this.setResizable(false);
		
		this.addKeyListener(new EscapeKeyListener(state()));
		this.setFocusable(true);
		
		JLabel lblWelcomeToGizmoball = new JLabel("Welcome to Gizmoball - Build Mode");
		lblWelcomeToGizmoball.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblWelcomeToGizmoball.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWelcomeToGizmoball, BorderLayout.NORTH);

		board = new Board(model);
		board.setRunningState(false);
		contentPane.add(board, BorderLayout.CENTER);

		ActionListener fm = new FileMenuActionListener(model, this);
		JMenuItem clear = new JMenuItem("Clear Board");
		clear.addActionListener(fm);

		JMenuItem save = new JMenuItem("Save Board");
		//save.addActionListener(new FileMenuActionListener());
		save.addActionListener(fm);

		JMenuItem load = new JMenuItem("Load Board");
		//load.addActionListener(new FileMenuActionListener());
		load.addActionListener(fm);

		file.add(clear);
		file.add(save);
		file.add(load);

		JMenuItem square = new JMenuItem("Square");
		ActionListener il = new InsertMenuActionListener(model, board);
		square.addActionListener(il);

		JMenuItem triangle = new JMenuItem("Triangle");
		triangle.addActionListener(il);

		JMenuItem circle = new JMenuItem("Circle");
		circle.addActionListener(il);

		JMenuItem leftFlipper = new JMenuItem("Left Flipper");
		leftFlipper.addActionListener(il);

		JMenuItem rightFlipper = new JMenuItem("Right Flipper");
		rightFlipper.addActionListener(il);

		JMenuItem absorber = new JMenuItem("Absorber");
		absorber.addActionListener(il);

		JMenuItem ball = new JMenuItem("Ball");
		ball.addActionListener(il);

		insert.add(square);
		insert.add(triangle);
		insert.add(circle);
		insert.add(leftFlipper);
		insert.add(rightFlipper);
		insert.add(absorber);
		insert.add(ball);
		
		JPanel buttonPanel = new JPanel(new GridLayout(3,1));
		JButton btnMove = new JButton("Move");
		ActionListener bl = new BuildModeButtonListener(model, board);
		btnMove.addActionListener(bl);
		
		JButton btnRotate = new JButton("Rotate");
		btnRotate.addActionListener(bl);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(bl);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(bl);
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.addActionListener(bl);
		
		JButton btnKeyConnect = new JButton("Key Connect");
		btnKeyConnect.addActionListener(bl);
		
		JButton btnKeyDisconnect = new JButton("Key Disconnect");
		btnKeyDisconnect.addActionListener(bl);
		
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
