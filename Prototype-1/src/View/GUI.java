package View;

import javax.swing.*;

import Controller.AddFlipperListener;
import Model.Flipper;

import java.awt.*;

public class GUI {
    private static GUI gui;
    private JFrame frame;
    private AddFlipperListener controller;
    private Flipper flipper;


    public static void main(String[] args) {
        gui = new GUI();
        gui.frame.setVisible(true);

    }

    //Create a Flipper and pass to controller
    public GUI() {
        flipper = new Flipper(2,3,false,new Color(0,0,0));
        controller = new AddFlipperListener(gui, flipper);

        setUp();
    }

    //Set Up JFrame size and add the game board
    private void setUp() {
        //Set Up Frame
        frame = new JFrame("Prototype 1: Flipper");
        //Need to fix height
        frame.setBounds(150, 100, 407, 430);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(controller);


        //Set Up Board
        JPanel panel = new Board(flipper);
        frame.getContentPane().add(panel);
    }
}
