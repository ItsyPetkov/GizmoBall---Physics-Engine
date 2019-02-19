package View;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import Model.LeftFlipper;
import Model.RightFlipper;

public class Board extends JPanel implements Observer {

    private LeftFlipper leftFlipper;
    private RightFlipper rightFlipper;

    public Board(LeftFlipper lf, RightFlipper rf){

        this.leftFlipper = lf;
        this.rightFlipper = rf;
        lf.addObserver(this);
        rf.addObserver(this);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        drawLeftFlipper(g);
        drawRightFlipper(g);

    }


    public void drawLeftFlipper(Graphics g){
        g.setColor(leftFlipper.getColor());
        g.fillOval((int)((leftFlipper.getTopCircle().getCenter().x()-0.25)*20), (int)((leftFlipper.getTopCircle().getCenter().y()-0.25)*20), 10, 10);
        g.fillOval((int)((leftFlipper.getBottomCircle().getCenter().x()-0.25)*20), (int)((leftFlipper.getBottomCircle().getCenter().y()-0.25)*20), 10, 10);

        //Make middle of flipper with a polynomial, rectangle doesn't work
        int[] X = new int[4];
        int[] Y = new int[4];
        int[] X1 = new int[4];
        int[] Y1 = new int[4];

       /* int offset = 4;
        X[0]=(int)(flipper.getTopCircle().getCenter().x()*20+offset);
        X[1]=(int)(flipper.getBottomCircle().getCenter().x()*20+offset);
        X[2]=(int)(flipper.getBottomCircle().getCenter().x()*20-offset);
        X[3]=(int)(flipper.getTopCircle().getCenter().x()*20-offset);

        Y[0]=(int)(flipper.getTopCircle().getCenter().y()*20+offset);
        Y[1]=(int)(flipper.getBottomCircle().getCenter().y()*20+offset);
        Y[2]=(int)(flipper.getBottomCircle().getCenter().y()*20-offset);
        Y[3]=(int)(flipper.getTopCircle().getCenter().y()*20-offset);*/

        X[0]=(int)(leftFlipper.getTopSideLine().p1().x()*20);
        X[1]=(int)(leftFlipper.getTopSideLine().p2().x()*20);
        X[2]=(int)(leftFlipper.getBottomSideLine().p1().x()*20);
        X[3]=(int)(leftFlipper.getBottomSideLine().p2().x()*20);

        Y[0]=(int)(leftFlipper.getTopSideLine().p1().y()*20);
        Y[1]=(int)(leftFlipper.getTopSideLine().p2().y()*20);
        Y[2]=(int)(leftFlipper.getBottomSideLine().p1().y()*20);
        Y[3]=(int)(leftFlipper.getBottomSideLine().p2().y()*20);

        X1[0]=(int)(leftFlipper.getRightSideLine().p1().x()*20);
        X1[1]=(int)(leftFlipper.getRightSideLine().p2().x()*20);
        X1[2]=(int)(leftFlipper.getLeftSideLine().p1().x()*20);
        X1[3]=(int)(leftFlipper.getLeftSideLine().p2().x()*20);

        Y1[0]=(int)(leftFlipper.getRightSideLine().p1().y()*20);
        Y1[1]=(int)(leftFlipper.getRightSideLine().p2().y()*20);
        Y1[2]=(int)(leftFlipper.getLeftSideLine().p1().y()*20);
        Y1[3]=(int)(leftFlipper.getLeftSideLine().p2().y()*20);



        g.fillPolygon(X,Y,4);
        g.fillPolygon(X1,Y1,4);


    }

    public void drawRightFlipper(Graphics g){
        g.setColor(rightFlipper.getColor());
        g.fillOval((int)((rightFlipper.getTopCircle().getCenter().x()-0.25)*20), (int)((rightFlipper.getTopCircle().getCenter().y()-0.25)*20), 10, 10);
        g.fillOval((int)((rightFlipper.getBottomCircle().getCenter().x()-0.25)*20), (int)((rightFlipper.getBottomCircle().getCenter().y()-0.25)*20), 10, 10);

        //Make middle of flipper with a polynomial, rectangle doesn't work
        int[] X = new int[4];
        int[] Y = new int[4];
        int[] X1 = new int[4];
        int[] Y1 = new int[4];

       /* int offset = 4;
        X[0]=(int)(flipper.getTopCircle().getCenter().x()*20+offset);
        X[1]=(int)(flipper.getBottomCircle().getCenter().x()*20+offset);
        X[2]=(int)(flipper.getBottomCircle().getCenter().x()*20-offset);
        X[3]=(int)(flipper.getTopCircle().getCenter().x()*20-offset);

        Y[0]=(int)(flipper.getTopCircle().getCenter().y()*20+offset);
        Y[1]=(int)(flipper.getBottomCircle().getCenter().y()*20+offset);
        Y[2]=(int)(flipper.getBottomCircle().getCenter().y()*20-offset);
        Y[3]=(int)(flipper.getTopCircle().getCenter().y()*20-offset);*/

        X[0]=(int)(rightFlipper.getTopSideLine().p1().x()*20);
        X[1]=(int)(rightFlipper.getTopSideLine().p2().x()*20);
        X[2]=(int)(rightFlipper.getBottomSideLine().p1().x()*20);
        X[3]=(int)(rightFlipper.getBottomSideLine().p2().x()*20);

        Y[0]=(int)(rightFlipper.getTopSideLine().p1().y()*20);
        Y[1]=(int)(rightFlipper.getTopSideLine().p2().y()*20);
        Y[2]=(int)(rightFlipper.getBottomSideLine().p1().y()*20);
        Y[3]=(int)(rightFlipper.getBottomSideLine().p2().y()*20);

        X1[0]=(int)(rightFlipper.getRightSideLine().p1().x()*20);
        X1[1]=(int)(rightFlipper.getRightSideLine().p2().x()*20);
        X1[2]=(int)(rightFlipper.getLeftSideLine().p1().x()*20);
        X1[3]=(int)(rightFlipper.getLeftSideLine().p2().x()*20);

        Y1[0]=(int)(rightFlipper.getRightSideLine().p1().y()*20);
        Y1[1]=(int)(rightFlipper.getRightSideLine().p2().y()*20);
        Y1[2]=(int)(rightFlipper.getLeftSideLine().p1().y()*20);
        Y1[3]=(int)(rightFlipper.getLeftSideLine().p2().y()*20);



        g.fillPolygon(X,Y,4);
        g.fillPolygon(X1,Y1,4);


    }


    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
