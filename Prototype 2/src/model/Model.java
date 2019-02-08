package model;

import java.util.Observable;
import physics.Vect;

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
        Vect vel = ball.getVelo();
        Coordinate p = ball.getPos();
        double newX = p.getX() + (vel.x() * time);
        double newY = p.getY() + (vel.y() * time);
        ball.setPos(newX, newY);
        return ball;
    }

    public void setBallSpeed(int x, int y){

    }

}
