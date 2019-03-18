package controller;

import model.Ball;
import model.Gizmo;
import model.IModel;
import view.Board;

import javax.swing.*;
import java.awt.event.*;

public class BuildModeButtonListener implements ActionListener {

    IModel model;
    Board board;
    int LtoPx = 25;
    Gizmo target;
    Ball targetBall;
    boolean dragging = false;

    public BuildModeButtonListener(IModel m, Board b) {
        this.model = m;
        this.board = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        MouseListener[] mss = board.getMouseListeners();
        for (int i = 0; i < mss.length; i++) {
            board.removeMouseListener(mss[i]);
        }

        MouseMotionListener[] mml = board.getMouseMotionListeners();
        for (int i = 0; i < mml.length; i++) {
            board.removeMouseMotionListener(mml[i]);
        }

        if (e.getActionCommand().equals("Gravity")) {
            try {
                String gravity = "";
                gravity = JOptionPane.showInputDialog("Gravity value: ");
                double g = Double.parseDouble(gravity);
                if (!(gravity.equals("")) && !(Double.isNaN(g))) {
                    model.setGravity(g);
                }
            }catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Only Numbers Allowed",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getActionCommand().equals("Friction")) {
            try {
                JTextField fieldVelX = new JTextField();
                JTextField fieldVelY = new JTextField();
                String xf = "", yf = "";
                Object[] input = {
                        "Friction X: ", fieldVelX,
                        "Friction Y: ", fieldVelY,
                };

                int velDialog = JOptionPane.showConfirmDialog(null, input, "Input friction: ", JOptionPane.OK_CANCEL_OPTION);
                if (velDialog == JOptionPane.OK_OPTION) {
                    xf = fieldVelX.getText();
                    yf = fieldVelY.getText();

                }
                double x = Double.parseDouble(xf);
                double y = Double.parseDouble(yf);
                if (!(xf.equals("")) && !(yf.equals("")) && !(Double.isNaN(x)) && !(Double.isNaN(y))) {
                    model.setFriction(x, y);
                }
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Only Numbers Allowed",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getActionCommand().equals("Move")){
            board.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    //Moving Gizmos
                    target = model.gizmoSearch(me.getX() / LtoPx, me.getY() / LtoPx);
                    if (target != null) {
                        dragging = true;
                    } else {
                        //Moving Balls
                        targetBall = model.ballSearch(me.getX() / LtoPx, me.getY() / LtoPx);
                        if (targetBall != null) {
                            dragging = true;
                        }
                    }
                }
            });

            board.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent me) {
                    if (target != null) {
                        if (dragging) {
                            model.moveGizmo(target, me.getX() / LtoPx, me.getY() / LtoPx);
                        }
                    } else if (targetBall != null) {
                        if (dragging) {
                            model.setBallPos(targetBall, me.getX()/LtoPx, me.getY()/LtoPx);
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
