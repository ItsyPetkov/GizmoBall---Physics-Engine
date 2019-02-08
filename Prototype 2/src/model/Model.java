package model;

import java.util.Observable;
import physics.Vect;

public class Model extends Observable{

    private Absorber abs;
    private Ball ball;
    private Walls gws;
    private double gravity = 9.8;

    public Model(){
        ball = new Ball(100, 400, 0, 0);
        gws = new Walls(0, 0, 500, 500);
        abs = new Absorber(0,400,500,500);
    }

    public void moveBall(){
        double moveTime = 0.05;
        ball = moveBallForTime(ball, moveTime);
    }

    private Ball moveBallForTime(Ball ball, double time){
        Vect velo = ball.getVelo();
        Coordinate p = ball.getPos();
        double newX = p.getX() + (velo.x() * time);
        double yDist = (velo.y() * time) + 0.5*(-gravity*(time*time));
        double newY = p.getY() + yDist;
        setBallVelo(ball, velo.x(), velo.y()+(yDist/time));
        ball.setPos(newX, newY);
        return ball;
    }

    public void setBallVelo(Ball ball, double x, double y){
        ball.setVelo(new Vect(x, y));
    }

}
