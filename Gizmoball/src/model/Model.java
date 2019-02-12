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
    private boolean absCheck = true;
    private boolean absCollide = false;

    public Model(){
        // 25px = 1L
        ball = new Ball(425, 355, 0, 0);
        walls = new Walls(0, 0, 500, 500);
        abs = new Absorber(0,350,500,500);
    }

    public void moveBall(){
        double moveTime = 0.05;

        if(absCheck){
            absorberShoot(ball);
        }
        
        if(!(ball.getVelo().x() == 0 && ball.getVelo().y() == 0)) {
            CollisionDetails cd = timeUntilCollision();
            double tuc = cd.getTuc();
            if (tuc > moveTime) {
                ball = moveBallForTime(ball, moveTime);
                absCheck = false;
            } else {
                ball = moveBallForTime(ball, tuc);
                ball.setVelo(cd.getVelo());
                if(!absCollide) {
                	absCheck = false;
                }
            }
        } 
        this.setChanged();
        this.notifyObservers();
    }

    private CollisionDetails timeUntilCollision(){
        Circle ballCircle = ball.getCircle();
        Vect ballVelo = ball.getVelo();
        Vect newVelo = new Vect(0,0);

        List<LineSegment> wallSegs = walls.getLineSegments();
        double shortestTime = Geometry.timeUntilWallCollision(wallSegs.get(0), ballCircle, ballVelo);
        double currentTime = 0.0;
        for(int i=0; i<wallSegs.size(); i++){
            currentTime = Geometry.timeUntilWallCollision(wallSegs.get(i), ballCircle, ballVelo);
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = Geometry.reflectWall(wallSegs.get(i), ballVelo, 1.0);
                absCollide = false;
            }
        }
        
        if(!absCheck) {
	        List<LineSegment> absSegs = abs.getLineSegments();
	        for(int i=0; i<absSegs.size(); i++){
	            currentTime = Geometry.timeUntilWallCollision(absSegs.get(i), ballCircle, ballVelo);
	            if(shortestTime > currentTime){
	                shortestTime = currentTime;
	                newVelo = new Vect(0.0,0.0);
	                absorberCapture(ball, abs);
	                absCollide = true;
	            }
	        }
        }
        return new CollisionDetails(shortestTime, newVelo);
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

    //0.25L = 6.25px
    private Ball absorberCapture(Ball ball, Absorber abs){
        Coordinate bottomRight = abs.getBRPos();
        ball.setPos( bottomRight.getX() - 6.25, bottomRight.getY() + 6.25);
        absCheck = true;
        return ball;
    }

    //50L = 1250px
    private Ball absorberShoot(Ball ball){
        setBallVelo(ball, 0.0, -250);
        return ball;
    }

    public void setBallVelo(Ball ball, double x, double y){
        ball.setVelo(new Vect(x, y));
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

}
