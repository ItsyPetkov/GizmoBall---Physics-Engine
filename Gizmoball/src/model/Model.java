package model;

import physics.LineSegment;
import physics.Vect;
import physics.Circle;
import physics.Geometry;
import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {

    private Walls walls;
    private List<Gizmo> gizmoList;
    private List<Ball> ballList;

    public Model(){
        walls = new Walls(0,0,20,20);
        gizmoList = new ArrayList<Gizmo>();
        ballList = new ArrayList<Ball>();
    }

    public void moveBall(){
        double moveTime = 0.05;

        for(int i=0; i<ballList.size(); i++) {
            if (!(ballList.get(i).getVelo().x() == 0 && ballList.get(i).getVelo().y() == 0)) {
                CollisionDetails cd = timeUntilCollision(ballList.get(i));
                double tuc = cd.getTuc();
                if (tuc > moveTime) {
                    //no collision
                    moveBallForTime(ballList.get(i), moveTime);
                } else {
                    //collision
                    moveBallForTime(ballList.get(i), tuc);
                    ballList.get(i).setVelo(cd.getVelo().x(), cd.getVelo().y());
                }
            }
        }

        this.setChanged();
        this.notifyObservers();
    }

    public CollisionDetails timeUntilCollision(Ball ball){
        Circle ballCircle = ball.getCircle();
        Vect ballVelo = ball.getVelo();

        //collisions with walls
        List<LineSegment> wallSegs = walls.getLineSegments();
        double shortestTime = Geometry.timeUntilWallCollision(wallSegs.get(0), ballCircle, ballVelo);
        Vect newVelo = Geometry.reflectWall(wallSegs.get(0), ballVelo, 1.0);
        for(int i=1; i<wallSegs.size(); i++){
            double currentTime = Geometry.timeUntilWallCollision(wallSegs.get(i), ballCircle, ballVelo);
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = Geometry.reflectWall(wallSegs.get(i), ballVelo, 1.0);
            }
        }

        //search gizmoList getSides and getCorners
        List<LineSegment> gizmoSides = new ArrayList<LineSegment>();
        List<physics.Circle> gizmoCorners = new ArrayList<Circle>();
        for(int i=0; i<gizmoList.size(); i++){
            gizmoSides.addAll(gizmoList.get(i).getSides());
            gizmoCorners.addAll(gizmoList.get(i).getCorners());
        }

        //collisions with gizmoSides
        for(int i=0; i<gizmoSides.size(); i++){
            double currentTime = Geometry.timeUntilWallCollision(gizmoSides.get(i), ballCircle, ballVelo);
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = Geometry.reflectWall(gizmoSides.get(i), ballVelo, 1.0);
            }
        }

        //collisions with gizmoCorners
        for(int i=0; i<gizmoCorners.size(); i++){
            double currentTime = Geometry.timeUntilCircleCollision(gizmoCorners.get(i), ballCircle, ballVelo);
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = Geometry.reflectCircle(gizmoCorners.get(i).getCenter(), ball.getCircle().getCenter(), ball.getVelo(), 1.0);
            }
        }

        //return time until closest collision, with velocity after bouncing off
        return new CollisionDetails(shortestTime, newVelo);
    }

    public Ball moveBallForTime(Ball ball, double time){
        Vect velo = ball.getVelo();
        Vect pos = ball.getPos();

        double newX = pos.x() + (velo.x()*time);
        double newY = pos.y() + (velo.y()*time);

        ball.setPos(newX, newY);

        return ball;
    }

    public Vect getWallTL(){
        return walls.getTL();
    }

    public Vect getWallBR(){
        return walls.getBR();
    }

    public List<Gizmo> getGizmos(){
        return gizmoList;
    }

    public List<Ball> getBalls(){
        return ballList;
    }

    public void addGizmo(Gizmo g){
        gizmoList.add(g);
    }

    public void addBall(Ball b){
        ballList.add(b);
    }

    public List<Vect> getGizmoPos(){
        List<Vect> gizmoPos = new ArrayList<Vect>();
        for(int i=0; i<gizmoList.size(); i++){
            gizmoPos.add(gizmoList.get(i).getPos());
        }
        return gizmoPos;
    }

    public List<Vect> getBallPos(){
        List<Vect> ballPos = new ArrayList<Vect>();
        for(int i=0; i<ballList.size(); i++){
            ballPos.add(ballList.get(i).getPos());
        }
        return ballPos;
    }
}
