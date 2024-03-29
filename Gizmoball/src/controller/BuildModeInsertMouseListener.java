package controller;

import model.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class BuildModeInsertMouseListener implements MouseInputListener {

    IModel model;
    int LtoPx;
    String type;
    String id;

    private int sx, sy, ex, ey; //used for absorber

    public BuildModeInsertMouseListener(IModel m, int LtoPx, String type, String id) {
        this.model = m;
        this.LtoPx = LtoPx;
        this.type = type;
        this.id = id;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(type.equals("Ball")){
            try {
                Double[] velocities = getVelocity();
                if (velocities != null) {
                    model.addBall(id, e.getX() / LtoPx, e.getY() / LtoPx, velocities[0], velocities[1]);
                }
            } catch (NullPointerException ex) {

            }
        } else {
            model.addGizmo(type, id, e.getX() / LtoPx, e.getY() / LtoPx);
        }

    }

    public Double[] getVelocity() {
        try {
            Double[] velocities = new Double[2];
            JTextField fieldVelX = new JTextField();
            JTextField fieldVelY = new JTextField();
            String velX = "", velY = "";
            Object[] input = {
                    "Velocity X: ", fieldVelX,
                    "Velocity Y: ", fieldVelY,
            };

            int velDialog = JOptionPane.showConfirmDialog(null, input, "Input velocity: ", JOptionPane.OK_CANCEL_OPTION);
            if (velDialog == JOptionPane.OK_OPTION) {
                velX = fieldVelX.getText();
                velY = fieldVelY.getText();

                velocities[0] = 0.0;
                velocities[1] = 0.0;

                boolean emptyX = velX.equals("");
                boolean emptyY = velY.equals("");

                if (!(velX.equals("") || velY.equals(""))) {
                    velocities[0] = Double.parseDouble(velX);
                    velocities[1] = Double.parseDouble(velY);
                    return velocities;
                } else {
                    if (emptyX && emptyY) {
                        return velocities;
                    } else if (emptyX) {
                        velocities[1] = Double.parseDouble(velY);
                        return velocities;
                    } else if (emptyY) {
                        velocities[0] = Double.parseDouble(velX);
                        return velocities;
                    }
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,
                    "Only Numbers Allowed",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {

        }
        return null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(type.equals("Absorber")){
            sx = e.getX();
            sy = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(type.equals("Absorber")){
            ex = e.getX();
            ey = e.getY();
            int x1, x2, y1, y2;
            x1 = Math.min(sx, ex);
            x2 = Math.max(sx, ex);
            y1 = Math.min(sy, ey);
            y2 = Math.max(sy, ey);
            model.addAbsorber(id, x1/LtoPx, y1/LtoPx, x2/LtoPx + 1, y2/LtoPx + 1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {



    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
