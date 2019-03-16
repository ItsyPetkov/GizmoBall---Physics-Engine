package controller;

import model.Gizmo;
import model.Model;
import model.RightFlipper;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomKeyListener implements KeyListener {

    private char key;
    private Gizmo gizmo;
    private Model model;

    public CustomKeyListener(Model model, char key, Gizmo gizmo) {
        this.key = key;
        this.gizmo = gizmo;
        this.model = model;

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
                    //change colour
                    System.out.println("c");
                    break;
                case "Square":
                    //change colour
                    System.out.println("s");
                    break;
                case "Triangle":
                    //change colour
                    System.out.println("t");
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

    public Gizmo getGizmo() {
        return gizmo;
    }

    public char getKey() {
        return key;
    }
}
