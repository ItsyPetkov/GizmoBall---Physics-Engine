package controller;

import view.GUI;
import physics.Angle;
import model.Flipper;
import model.Flipper.StateOfMotion;
import model.Flipper.Position;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AddFlipperListener implements KeyListener, ActionListener {
    private GUI gui;
    private Flipper flipper;
    private Angle angle;

    public AddFlipperListener(GUI gui, Flipper flipper){
        Timer timer;
        this.gui = gui;
        this.flipper = flipper;
        timer = new Timer(50, this);
        timer.start();
        this.angle = Angle.DEG_90;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(flipper.getMovement() == StateOfMotion.UPWARDS ||
                flipper.getMovement() == StateOfMotion.DOWNWARDS){
            angle = flipper.moveThroughAngle(angle);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        if(flipper.getPosition() == Position.VERTICAL) {
            angle = flipper.getRotationLeft();
            flipper.setMovement(StateOfMotion.UPWARDS);
            System.out.println("key pressed");
        } else if(flipper.getPosition() == Position.TRANSIENT) {
            if(flipper.getMovement() == StateOfMotion.DOWNWARDS) {
                flipper.setRotationLeft(Angle.DEG_90.minus(flipper.getRotationLeft()));
            }
            angle = flipper.getRotationLeft();
            flipper.setMovement(StateOfMotion.UPWARDS);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {


        if(flipper.getPosition() == Position.HORIZONTAL) {

            angle = Angle.DEG_90.minus(flipper.getRotationLeft());
            flipper.setMovement(StateOfMotion.DOWNWARDS);
        } else if(flipper.getPosition() == Position.TRANSIENT) {
            if(flipper.getMovement() == StateOfMotion.UPWARDS) {
                flipper.setRotationLeft(Angle.DEG_90.minus(flipper.getRotationLeft()));
            }
            angle = flipper.getRotationLeft();
            flipper.setMovement(StateOfMotion.DOWNWARDS);
        }

    }
}
