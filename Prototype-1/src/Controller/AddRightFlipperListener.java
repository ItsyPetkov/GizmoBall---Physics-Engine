package Controller;
import View.GUI;
import physics.Angle;
import Model.RightFlipper;
import Model.RightFlipper.StateOfMotion;
import Model.RightFlipper.Position;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AddRightFlipperListener implements KeyListener, ActionListener{
    private GUI gui;
    private RightFlipper rightflipper;
    private physics.Angle angle;

    public AddRightFlipperListener(GUI gui, RightFlipper rightflipper){
        Timer timer;
        this.gui = gui;
        this.rightflipper = rightflipper;
        timer = new Timer(50, this);
        timer.start();
        this.angle = Angle.DEG_90;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //If the flipper is set to move up or down, start moving through th angle
        if(rightflipper.getMovement() == StateOfMotion.UPWARDS ||
                rightflipper.getMovement() == StateOfMotion.DOWNWARDS){
            angle = rightflipper.moveThroughAngle(angle);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        //If it's not started it needs to move upwards, set the angle to the angle left to move through
        if(rightflipper.getPosition() == Position.VERTICAL) {
            angle = rightflipper.getRotationLeft();
            System.out.println("key pressed");
            //If its started, but not finished do the same unless its going back down again,
            //in which case make it move up back up through the angle it just came down through
        } else if(rightflipper.getPosition() == Position.TRANSIENT) {
            if(rightflipper.getMovement() == StateOfMotion.DOWNWARDS) {
                rightflipper.setRotationLeft(Angle.DEG_90.minus(rightflipper.getRotationLeft()));
            }
            angle = rightflipper.getRotationLeft();
        }
        rightflipper.setMovement(StateOfMotion.UPWARDS);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        //if its finished its movement change the angle so it can move back down
        if(rightflipper.getPosition() == Position.HORIZONTAL) {
            angle = Angle.DEG_90.minus(rightflipper.getRotationLeft());
            //If its started, but not finished  make it go back down through the angle it just came through
        } else if(rightflipper.getPosition() == Position.TRANSIENT) {
            if(rightflipper.getMovement() == StateOfMotion.UPWARDS) {
                rightflipper.setRotationLeft(Angle.DEG_90.minus(rightflipper.getRotationLeft()));
            }
            angle = rightflipper.getRotationLeft();
        }
        rightflipper.setMovement(StateOfMotion.DOWNWARDS);
    }
}
