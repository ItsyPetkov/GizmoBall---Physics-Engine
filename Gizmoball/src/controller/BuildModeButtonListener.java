package controller;

import model.Gizmo;
import model.Model;
import view.Board;

import java.awt.event.*;

public class BuildModeButtonListener implements ActionListener {

    Model model;
    Board board;
    int LtoPx = 25;
    Gizmo target;
    boolean dragging = false;

    public BuildModeButtonListener(Model m, Board b) {
        this.model = m;
        this.board = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        MouseListener[] mss = board.getMouseListeners();
        for(int i=0; i<mss.length; i++){
            board.removeMouseListener(mss[i]);
        }

        MouseListener ml = new BuildModeMouseListener(model, e.getActionCommand(),board.getLtoPx());
        board.addMouseListener(ml);
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (e.getActionCommand().equals("Move")) {
                    target = model.gizmoSearch(me.getX() / LtoPx, me.getY() / LtoPx);
                    if (target != null) {
                        if (target.getPos().x() == me.getX() / LtoPx && target.getPos().y() == me.getY() / LtoPx) {
                            model.moveGizmo(target, me.getX() / LtoPx, me.getY() / LtoPx);
                        } else {
                            dragging = true;
                        }
                    }
                }
            }
        });
        board.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                if (e.getActionCommand().equals("Move")) {
                    if (target != null) {
                        if (!dragging) {
                            model.moveGizmo(target, me.getX() / LtoPx, me.getY() / LtoPx);
                        }
                    }
                }
            }
        });
        if(e.getActionCommand().equals("Move")) {
            System.out.println("Click an object to move, then click a position to move to");
        } else if(e.getActionCommand().equals("Rotate")) {
            System.out.println("Click an object to rotate");
        } else if(e.getActionCommand().equals("Delete")) {
            System.out.println("Click an object to delete");
        } else if(e.getActionCommand().equals("Connect")) {
            System.out.println("Connecting...");
        } else if(e.getActionCommand().equals("Disconnect")) {
            System.out.println("Disconneecting...");
        } else if(e.getActionCommand().equals("Key Connect")) {
            System.out.println("Connecting Key...");
        } else if(e.getActionCommand().equals("Key Disconnect")) {
            System.out.println("Disconnecting Key...");
        }
    }

}
