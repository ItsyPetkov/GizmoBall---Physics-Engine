package Controller;
import View.GUI;
import physics.Angle;
import Model.LeftFlipper;
import Model.LeftFlipper.StateOfMotion;
import Model.LeftFlipper.Position;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AddLeftFlipperListener implements KeyListener, ActionListener {
    private GUI gui;
    private LeftFlipper leftflipper;
    private physics.Angle angle;

    public AddLeftFlipperListener(GUI gui, LeftFlipper leftflipper){
        Timer timer;
        this.gui = gui;
        this.leftflipper = leftflipper;
        timer = new Timer(50, this);
        timer.start();
        this.angle = Angle.DEG_90;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //If the flipper is set to move up or down, start moving through th angle
        if(leftflipper.getMovement() == StateOfMotion.UPWARDS ||
                leftflipper.getMovement() == StateOfMotion.DOWNWARDS){
            angle = leftflipper.moveThroughAngle(angle);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        //If it's not started it needs to move upwards, set the angle to the angle left to move through
        if(leftflipper.getPosition() == Position.VERTICAL) {
            angle = leftflipper.getRotationLeft();
            System.out.println("key pressed");
            //If its started, but not finished do the same unless its going back down again,
            //in which case make it move up back up through the angle it just came down through
        } else if(leftflipper.getPosition() == Position.TRANSIENT) {
            if(leftflipper.getMovement() == StateOfMotion.DOWNWARDS) {
                leftflipper.setRotationLeft(Angle.DEG_90.minus(leftflipper.getRotationLeft()));
            }
            angle = leftflipper.getRotationLeft();
        }
        leftflipper.setMovement(StateOfMotion.UPWARDS);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        //if its finished its movement change the angle so it can move back down
        if(leftflipper.getPosition() == Position.HORIZONTAL) {
            angle = Angle.DEG_90.minus(leftflipper.getRotationLeft());
            //If its started, but not finished  make it go back down through the angle it just came through
        } else if(leftflipper.getPosition() == Position.TRANSIENT) {
            if(leftflipper.getMovement() == StateOfMotion.UPWARDS) {
                leftflipper.setRotationLeft(Angle.DEG_90.minus(leftflipper.getRotationLeft()));
            }
            angle = leftflipper.getRotationLeft();
        }
        leftflipper.setMovement(StateOfMotion.DOWNWARDS);
    }
}