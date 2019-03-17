package controller;

import model.Ball;
import model.Gizmo;
import model.Model;
import view.Board;

import javax.swing.*;
import java.awt.event.*;

public class BuildModeButtonListener implements ActionListener {

    Model model;
    Board board;
    int LtoPx = 25;
    Gizmo target;
    Ball targetBall;
    boolean dragging = false;

    public BuildModeButtonListener(Model m, Board b) {
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
                String gravity = JOptionPane.showInputDialog("Gravity value: ");
                double g = Double.parseDouble(gravity);
                if (!(gravity.equals("")) && !(Double.isNaN(g))) {
                    model.setGravity(g);
                    System.out.println(Double.isNaN(Double.parseDouble(gravity)));
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
                        if (target.getPos().x() == me.getX() / LtoPx && target.getPos().y() == me.getY() / LtoPx) {
                            model.moveGizmo(target, me.getX() / LtoPx, me.getY() / LtoPx);
                        } else {
                            dragging = true;
                        }

                        //Moving Balls
                    } else {
                        targetBall = model.ballSearch(me.getX() / LtoPx, me.getY() / LtoPx);
                        if (targetBall != null) {
                            if (targetBall.getPos().x() == me.getX() / LtoPx && targetBall.getPos().y() == me.getY() / LtoPx) {
                                model.setBallPos(targetBall, me.getX() / LtoPx, me.getY() / LtoPx);
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
                    if (target != null) {
                        if (!dragging) {
                            model.moveGizmo(target, me.getX() / LtoPx, me.getY() / LtoPx);
                        }
                    } else if (targetBall != null) {
                        if (!dragging) {
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
