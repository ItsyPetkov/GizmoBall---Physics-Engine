package controller;

import model.IModel;
import model.IGizmo;
import model.LeftFlipper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomKeyListener implements KeyListener {

    private char key;
    private IGizmo gizmo;

    public CustomKeyListener(char key, IGizmo gizmo) {
        this.key = key;
        this.gizmo = gizmo;

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
                    System.out.println("lfp");
                    break;
                case "RightFlipper":
                    //flipper flips
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
                    System.out.println("lfr");
                    break;
                case "RightFlipper":
                    //flipper unflips
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
}
