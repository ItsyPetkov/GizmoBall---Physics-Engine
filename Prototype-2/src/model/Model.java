package model;

import java.sql.SQLOutput;
import java.util.Observable;
import java.lang.Math.*;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import physics.Circle;

public class Model extends Observable{

    private Absorber abs;
    private Ball ball;
    private Walls walls;
    private double gravity = 625;
    private boolean absCaptured = true;
    private boolean absCollision = false;
    private boolean absClosest = false;

    public Model(){
        // 25px = 1L
        ball = new Ball(425, 355, 0, 0);
        walls = new Walls(-20, -20, 500, 500);
        abs = new Absorber(0,400,500,500);
        absorberCapture(ball, abs);
    }

    public void moveBall(){
        double moveTime = 0.05;

        if(absCollision){
            absorberCapture(ball, abs);
            absCollision = false;
            absClosest = false;
        } else if(!(ball.getVelo().x() == 0 && ball.getVelo().y() == 0)) {
            CollisionDetails cd = timeUntilCollision();
            double tuc = cd.getTuc();
            if (tuc > moveTime) {
                ball = moveBallForTime(ball, moveTime);
                if(absCaptured && ball.getPos().getY() < abs.getTLPos().getY()){
                    absCaptured = false;
                }
            } else {
                ball = moveBallForTime(ball, tuc);
                ball.setVelo(cd.getVelo());
                absCollision = absClosest;
            }
        }
        this.setChanged();
        this.notifyObservers();
    }

    private CollisionDetails timeUntilCollision(){
        Circle ballCircle = ball.getCircle();
        Vect ballVelo = ball.getVelo();


        List<LineSegment> wallSegs = walls.getLineSegments();
        double shortestTime = Geometry.timeUntilWallCollision(wallSegs.get(0), ballCircle, ballVelo) - (ball.getRadius()/getTotalVector(ball));
        Vect newVelo = Geometry.reflectWall(wallSegs.get(0), ballVelo, 1.0);
        double currentTime = Geometry.timeUntilWallCollision(wallSegs.get(0), ballCircle, ballVelo);
        for(int i=1; i<wallSegs.size(); i++){
            currentTime = Geometry.timeUntilWallCollision(wallSegs.get(i), ballCircle, ballVelo) - (ball.getRadius()/getTotalVector(ball));
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = Geometry.reflectWall(wallSegs.get(i), ballVelo, 1.0);
            }
        }
        
        if(!absCaptured) {
	        List<LineSegment> absSegs = abs.getLineSegments();
	        for(int i=0; i<absSegs.size(); i++){
	            currentTime = Geometry.timeUntilWallCollision(absSegs.get(i), ballCircle, ballVelo) - (ball.getRadius()/getTotalVector(ball));
	            if(shortestTime > currentTime){
	                shortestTime = currentTime;
	                newVelo = new Vect(0.0,0.0);
	                absClosest = true;
	            }
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
        setBallVelo(ball, velo.x(), velo.y()+(gravity*time));
        ball.setPos(newX, newY);
        return ball;
    }

    //0.25L = 6.25px
    private Ball absorberCapture(Ball ball, Absorber abs){
        Coordinate bottomRight = abs.getBRPos();
        ball.setPos( bottomRight.getX() - (ball.getRadius()) - 6.25, bottomRight.getY() - (ball.getRadius()) - 6.25);
        absCaptured = true;
        return ball;
    }

    //50L = 1250px
    public Ball absorberShoot(Ball ball){
        setBallVelo(ball, 0.0, -1250);
        return ball;
    }

    public void setBallVelo(Ball ball, double x, double y){
        ball.setVelo(new Vect(x, y));
    }

    public Ball getBall(){
        return ball;
    }

    public Coordinate getBallPos(){
        return ball.getPos();
    }

    public double getBallRadius(){
        return ball.getRadius();
    }

    public Coordinate getAbsTLPos(){
        return abs.getTLPos();
    }

    public Coordinate getAbsBRPos(){
        return abs.getBRPos();
    }

    public double getTotalVector(Ball ball){
        return Math.sqrt((ball.getVelo().y()*ball.getVelo().y())+(ball.getVelo().x()*ball.getVelo().x()));
    }

}
