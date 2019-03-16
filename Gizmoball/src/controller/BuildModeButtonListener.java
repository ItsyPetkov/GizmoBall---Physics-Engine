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

        MouseMotionListener[] mml = board.getMouseMotionListeners();
        for(int i=0; i<mml.length; i++){
            board.removeMouseMotionListener(mml[i]);
        }

        if (e.getActionCommand().equals("Move")){
            board.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    target = model.gizmoSearch(me.getX() / LtoPx, me.getY() / LtoPx);
                    if (target != null) {
                        if (target.getPos().x() == me.getX() / LtoPx && target.getPos().y() == me.getY() / LtoPx) {
                            model.moveGizmo(target, me.getX() / LtoPx, me.getY() / LtoPx);
                        } else {
                            dragging = true;
                        }
                    }
                }
            });

            board.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent me) {
                    if (target != null) {
                        if (!dragging) {
                            model.moveGizmo(target, me.getX() / LtoPx, me.getY() / LtoPx);
                        }
                    }
                }
            });
        } else {
            MouseListener ml = new BuildModeMouseListener(model, e.getActionCommand(),board.getLtoPx());
            board.addMouseListener(ml);
        }
    }

}
