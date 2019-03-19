package controller;

import model.IModel;
import model.IGizmo;
import model.LeftFlipper;
import physics.Angle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomKeyListener implements KeyListener, ActionListener {

    private char key;
    private IGizmo gizmo;
    private Angle angle;

    public CustomKeyListener(char key, IGizmo gizmo) {
        this.key = key;
        this.gizmo = gizmo;
        Timer timer;
        timer = new Timer(50, this);
        timer.start();
        this.angle = Angle.DEG_90;

        CustomKeys.addKey(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == key) {
            switch (gizmo.type()) {
                case "LeftFlipper":
                    //flipper flips

                    //If it's not started it needs to move upwards, set the angle to the angle left to move through
                    if(gizmo.getVertical() == true) {
                        angle = gizmo.getRotationLeft();
                        //System.out.println("key pressed");
                        //If its started, but not finished do the same unless its going back down again,
                        //in which case make it move up back up through the angle it just came down through
                    } else if(gizmo.getTransient() == true) {
                        if(gizmo.getDownwards() == true) {
                            gizmo.setRotationLeft(Angle.DEG_90.minus(gizmo.getRotationLeft()));
                        }
                        angle = gizmo.getRotationLeft();
                    }
                    gizmo.goUpwards();
                    System.out.println("lfp");
                    break;
                case "RightFlipper":
                    //flipper flips
                    //If it's not started it needs to move upwards, set the angle to the angle left to move through
                    if(gizmo.getVertical() == true) {
                        angle = gizmo.getRotationLeft();
                        //System.out.println("key pressed");
                        //If its started, but not finished do the same unless its going back down again,
                        //in which case make it move up back up through the angle it just came down through
                    } else if(gizmo.getTransient() == true) {
                        if(gizmo.getDownwards() == true) {
                            gizmo.setRotationLeft(Angle.DEG_90.minus(gizmo.getRotationLeft()));
                        }
                        angle = gizmo.getRotationLeft();
                    }
                    gizmo.goUpwards();
                    System.out.println("rfp");
                    break;
                case "Circle":
                    gizmo.setColour(new Color((int)(Math.random() * 0x1000000)));
                    break;
                case "Square":
                    gizmo.setColour(new Color((int)(Math.random() * 0x1000000)));
                    break;
                case "Triangle":
                    gizmo.setColour(new Color((int)(Math.random() * 0x1000000)));
                    break;
                case "Absorber":
                    //shoot
                    System.out.println("a");
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == key) {
            switch (gizmo.type()) {
                case "LeftFlipper":
                    //flipper unflips
                    //System.out.println(rightflipper.getHorizontal());
                    //if its finished its movement change the angle so it can move back down
                    if(gizmo.getHorizontal() == true) {
                        angle = Angle.DEG_90.minus(gizmo.getRotationLeft());
                        //System.out.println(angle);
                        //If its started, but not finished  make it go back down through the angle it just came through
                    } else if(gizmo.getTransient() == true) {
                        if(gizmo.getUpwards() == true) {
                            gizmo.setRotationLeft(Angle.DEG_90.minus(gizmo.getRotationLeft()));
                        }
                        angle = gizmo.getRotationLeft();
                    }
                    gizmo.goDownwards();

                    System.out.println("lfr");
                    break;
                case "RightFlipper":
                    //flipper unflips
                    //System.out.println(rightflipper.getHorizontal());
                    //if its finished its movement change the angle so it can move back down
                    if(gizmo.getHorizontal() == true) {
                        angle = Angle.DEG_90.minus(gizmo.getRotationLeft());
                        //System.out.println(angle);
                        //If its started, but not finished  make it go back down through the angle it just came through
                    } else if(gizmo.getTransient() == true) {
                        if(gizmo.getUpwards() == true) {
                            gizmo.setRotationLeft(Angle.DEG_90.minus(gizmo.getRotationLeft()));
                        }
                        angle = gizmo.getRotationLeft();
                    }
                    gizmo.goDownwards();
                    System.out.println("rfr");
                    break;
            }
        }
    }

    public IGizmo getGizmo() {
        return gizmo;
    }

    public char getKey() {
        return key;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (gizmo.type()) {
            case "LeftFlipper":
                if(gizmo.getUpwards() == true ||
                        gizmo.getDownwards() == true){
                    angle = gizmo.moveThroughAngle(angle);
                }
                break;
            case "RightFlipper":
                if(gizmo.getUpwards() == true ||
                        gizmo.getDownwards() == true){
                    angle = gizmo.moveThroughAngle(angle);
                }
                break;

        }
    }
}
