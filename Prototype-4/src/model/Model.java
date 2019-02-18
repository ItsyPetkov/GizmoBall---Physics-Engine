package model;

import physics.Circle;
import physics.LineSegment;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {

    private model.Ball ball;
    private model.Walls walls;
    private List<model.Gizmo> gizmoList;

    public Model(){
        ball = new model.Ball("ball1",10,10,10,10);
        walls = new model.Walls(0,0,20,20);
        gizmoList = new ArrayList<model.Gizmo>();
    }

    public void addGizmo(model.Gizmo g){
        gizmoList.add(g);
    }

    public void moveBall(){
        double moveTime = 0.05;

        if(!(ball.getVelo().x() == 0 && ball.getVelo().y() == 0)) {
            model.CollisionDetails cd = timeUntilCollision();
            double tuc = cd.getTuc();
            if (tuc > moveTime) {
                ball = moveBallForTime(ball, moveTime);
            } else {
                ball = moveBallForTime(ball, tuc);
                ball.setVelo(cd.getVelo());
            }
        }

    }

    public model.CollisionDetails timeUntilCollision(){
        physics.Circle ballCircle = ball.getCircle();
        physics.Vect ballVelo = ball.getVelo();


        List<LineSegment> wallSegs = walls.getLineSegments();
        double shortestTime = physics.Geometry.timeUntilWallCollision(wallSegs.get(0), ballCircle, ballVelo);
        physics.Vect newVelo = physics.Geometry.reflectWall(wallSegs.get(0), ballVelo, 1.0);
        double currentTime;

        for(int i=1; i<wallSegs.size(); i++){
            currentTime = physics.Geometry.timeUntilWallCollision(wallSegs.get(i), ballCircle, ballVelo);
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = physics.Geometry.reflectWall(wallSegs.get(i), ballVelo, 1.0);
            }
        }

        // search gizmoList getSides and getCorners
        List<LineSegment> gizmoSides = new ArrayList<LineSegment>();
        List<Circle> gizmoCorners = new ArrayList<Circle>();
        for(int i=0; i<gizmoList.size(); i++){
            gizmoSides.addAll(gizmoList.get(i).getSides());
            gizmoCorners.addAll(gizmoList.get(i).getCorners());
        }

        for(int i=0; i<gizmoSides.size(); i++){
            currentTime = physics.Geometry.timeUntilWallCollision(gizmoSides.get(i), ballCircle, ballVelo);
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = physics.Geometry.reflectWall(gizmoSides.get(i), ballVelo, 1.0);
            }
        }

        for(int i=0; i<gizmoCorners.size(); i++){
            currentTime = physics.Geometry.timeUntilCircleCollision(gizmoCorners.get(i), ballCircle, ballVelo);
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = physics.Geometry.reflectCircle(gizmoCorners.get(i).getCenter(), ball.getCircle().getCenter(), ball.getVelo(), 1.0);
            }
        }

        return new model.CollisionDetails(shortestTime, newVelo);
    }

    private model.Ball moveBallForTime(model.Ball ball, double time){
        physics.Vect velo = ball.getVelo();
        model.Coordinate p = ball.getPos();

        double newX = p.getX() + (velo.x()*time);
        double newY = p.getY() + (velo.y()*time);

        ball.setPos(newX, newY);

        return ball;
    }

    public model.Coordinate getBallPos(){
        return ball.getPos();
    }

    public List<model.Coordinate> getGizmoPos(){
        List<model.Coordinate> gizmoPos = new ArrayList<model.Coordinate>();
        for(int i=0; i<gizmoList.size(); i++){
            gizmoPos.add(gizmoList.get(i).getPos());
        }
        return gizmoPos;
    }

}
