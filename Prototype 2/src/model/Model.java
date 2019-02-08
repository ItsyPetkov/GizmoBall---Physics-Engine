package model;

import java.util.Observable;

public class Model extends Observable{

    private Absorber abs;
    private Ball ball;
    private Walls gws;

    public Model(){

        ball = new Ball(25, 25, 0, 0);
        gws = new Walls(0, 0, 500, 500);
        abs = new Absorber(0,400,500,500);

    }

    public void moveBall(){

    }

    private Ball moveBallForTime(Ball ball, double time){

        return ball;
    }

    public void setBallSpeed(int x, int y){

    }

}
