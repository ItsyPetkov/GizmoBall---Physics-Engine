package View;

import javax.swing.*;


import Controller.AddLeftFlipperListener;
import Controller.AddRightFlipperListener;

import Model.LeftFlipper;
import Model.RightFlipper;
import java.awt.*;

public class GUI {
    private static GUI gui;
    private JFrame frame;

    private AddLeftFlipperListener leftController;
    private AddRightFlipperListener rightController;


    private LeftFlipper leftFlipper;
    private RightFlipper rightFlipper;



    public static void main(String[] args) {
        gui = new GUI();
        gui.frame.setVisible(true);

    }

    //Create a both flippers
    public GUI() {

        leftFlipper = new LeftFlipper(12,3,new Color(0,0,0));
        rightFlipper = new RightFlipper(7,3,new Color(0,0,0));

        leftController = new AddLeftFlipperListener(gui,leftFlipper);
        rightController = new AddRightFlipperListener(gui,rightFlipper);

        setUp();
    }

    //Set Up JFrame size and add the game board
    private void setUp() {
        //Set Up Frame
        frame = new JFrame("Prototype 1: Flipper");
        //Need to fix height
        frame.setBounds(150, 100, 407, 430);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addKeyListener(leftController);
        frame.addKeyListener(rightController);


        //Set Up Board, give it flippers
        JPanel panel = new Board(leftFlipper, rightFlipper);
        frame.getContentPane().add(panel);
    }
}
