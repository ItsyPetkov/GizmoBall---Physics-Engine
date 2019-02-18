package model;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {

    private Ball ball;
    private Walls walls;
    private List<Gizmo> gizmoList;
    private boolean collision;

    public Model(){
        ball = new Ball(10,10,20,-10);
        walls = new Walls(-1,-1,20,19);
        gizmoList = new ArrayList<Gizmo>();
        collision = false;
    }

    public void moveBall(){
        double moveTime = 0.05;

        if(!(ball.getVelo().x() == 0 && ball.getVelo().y() == 0)) {
            CollisionDetails cd = timeUntilCollision();
            double tuc = cd.getTuc();
            if (tuc > moveTime) {
                ball = moveBallForTime(ball, moveTime);
                collision = false;
            } else {
                ball = moveBallForTime(ball, tuc);
                ball.setVelo(cd.getVelo());
                collision = true;
            }
        }

        this.setChanged();
        this.notifyObservers();
    }

    public CollisionDetails timeUntilCollision(){
        physics.Circle ballCircle = ball.getCircle();
        Vect ballVelo = ball.getVelo();


        List<LineSegment> wallSegs = walls.getLineSegments();
        double shortestTime = Geometry.timeUntilWallCollision(wallSegs.get(0), ballCircle, ballVelo) - (ball.getRadius()/getTotalVector(ball));
        Vect newVelo = Geometry.reflectWall(wallSegs.get(0), ballVelo, 1.0);
        double currentTime;

        for(int i=1; i<wallSegs.size(); i++){
            currentTime = Geometry.timeUntilWallCollision(wallSegs.get(i), ballCircle, ballVelo) - (ball.getRadius()/getTotalVector(ball));
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = Geometry.reflectWall(wallSegs.get(i), ballVelo, 1.0);
            }
        }

        // search gizmoList getSides and getCorners
        List<LineSegment> gizmoSides = new ArrayList<LineSegment>();
        List<physics.Circle> gizmoCorners = new ArrayList<Circle>();
        for(int i=0; i<gizmoList.size(); i++){
            gizmoSides.addAll(gizmoList.get(i).getSides());
            gizmoCorners.addAll(gizmoList.get(i).getCorners());
        }

        for(int i=0; i<gizmoSides.size(); i++){
            currentTime = Geometry.timeUntilWallCollision(gizmoSides.get(i), ballCircle, ballVelo) - (ball.getRadius()/getTotalVector(ball));
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = Geometry.reflectWall(gizmoSides.get(i), ballVelo, 1.0);
            }
        }

        for(int i=0; i<gizmoCorners.size(); i++){
            currentTime = Geometry.timeUntilCircleCollision(gizmoCorners.get(i), ballCircle, ballVelo) - (ball.getRadius()/getTotalVector(ball));
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = Geometry.reflectCircle(gizmoCorners.get(i).getCenter(), ball.getCircle().getCenter(), ball.getVelo(), 1.0);
            }
        }

        return new CollisionDetails(shortestTime, newVelo);
    }

    private Ball moveBallForTime(Ball ball, double time){
        Vect velo = ball.getVelo();
        Coordinate p = ball.getPos();

        double newX = p.getX() + (velo.x()*time);
        double newY = p.getY() + (velo.y()*time);

        ball.setPos(newX, newY);

        return ball;
    }

    public void addGizmo(Gizmo g){
        gizmoList.add(g);
    }

    public List<Coordinate> getGizmoPos(){
        List<Coordinate> gizmoPos = new ArrayList<Coordinate>();
        for(int i=0; i<gizmoList.size(); i++){
            gizmoPos.add(gizmoList.get(i).getPos());
        }
        return gizmoPos;
    }
    
    public List<Gizmo> getGizmos(){
    	return gizmoList;
    }
    
    public Ball getBall(){
        return ball;
    }

    public Vect getWallTL(){
        return walls.getTL();
    }

    public Vect getWallBR(){
        return walls.getBR();
    }

    public boolean getCollision(){
        return collision;
    }

    private double getTotalVector(Ball ball){
        return Math.sqrt((ball.getVelo().y()*ball.getVelo().y())+(ball.getVelo().x()*ball.getVelo().x()));
    }

}
