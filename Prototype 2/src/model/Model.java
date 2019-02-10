package model;

import java.util.Observable;

import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import physics.Circle;

public class Model extends Observable{

    private Absorber abs;
    private Ball ball;
    private Walls walls;
    private double gravity = 9.8;

    public Model(){
        // 25px = 1L
        ball = new Ball(100, 390, 0, 0);
        walls = new Walls(0, 0, 500, 500);
        abs = new Absorber(0,400,500,500);
    }

    public void moveBall(){
        double moveTime = 0.05;

        if(!(ball.getVelo().x() == 0 && ball.getVelo().y() == 0)) {
            CollisionDetails cd = timeUntilCollision();
            double tuc = cd.getTuc();
            if (tuc > moveTime) {
                ball = moveBallForTime(ball, moveTime);
            } else {
                ball = moveBallForTime(ball, tuc);
                ball.setVelo(cd.getVelo());
            }
        }

        this.setChanged();
        this.notifyObservers();
    }

    private CollisionDetails timeUntilCollision(){
        Circle ballCircle = ball.getCircle();
        Vect ballVelo = ball.getVelo();
        Vect newVelo = new Vect(0,0);

        double shortestTime = 0.0;
        double currentTime = 0.0;

        List<LineSegment> wallSegs = walls.getLineSegments();
        for(int i=0; i<wallSegs.size(); i++){
            currentTime = Geometry.timeUntilWallCollision(wallSegs.get(i), ballCircle, ballVelo);
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = Geometry.reflectWall(wallSegs.get(i), ballVelo, 1.0);
            }
        }

        return new CollisionDetails(shortestTime, newVelo);
    }

    private Ball moveBallForTime(Ball ball, double time){
        Vect velo = ball.getVelo();
        Coordinate p = ball.getPos();
        double newX = p.getX() + (velo.x() * time);
        double yDist = (velo.y() * time) + 0.5*(gravity*(time*time));
        double newY = p.getY() + yDist;
        setBallVelo(ball, velo.x(), velo.y()+(yDist/time));
        ball.setPos(newX, newY);
        return ball;
    }

    //0.25L = 6.25px
    private Ball absorberCapture(Ball ball, Absorber abs){
        Coordinate bottomRight = abs.getPos();
        ball.setPos( bottomRight.getX() - 6.25, bottomRight.getY() + 6.25);
        setBallVelo(ball, 0.0,0.0);
        return ball;
    }

    //50L = 1250px
    private Ball absorberShoot(Ball ball){
        setBallVelo(ball, 0.0, -1250);
        return ball;
    }

    public void setBallVelo(Ball ball, double x, double y){
        ball.setVelo(new Vect(x, y));
    }

}
