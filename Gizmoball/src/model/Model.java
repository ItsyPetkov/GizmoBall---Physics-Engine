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
    private double gravity = 25;
    private double mu = 0.025;
    private double mu2 = 0.025;
    private int collisionType = 0;
    private Gizmo collidingG;
    private Ball collidingB;

    public Model(){
        walls = new Walls(0,0,20,20);
        gizmoList = new ArrayList<Gizmo>();
        ballList = new ArrayList<Ball>();

    }

    public void moveBall(){
        double moveTime = 0.05;

        for(int i=0; i<ballList.size(); i++) {
            if (!(ballList.get(i).getVelo().x() == 0 && ballList.get(i).getVelo().y() == 0)) {
                CollisionDetails cd = timeUntilCollision(ballList.get(i), i);
                double tuc = cd.getTuc();
                if (tuc > moveTime) {
                    //no collision
                    moveBallForTime(ballList.get(i), moveTime);
                    ballList.get(i).release();
                } else {
                    if(!(ballList.get(i).isCaptured())){
                        //collision
                        moveBallForTime(ballList.get(i), tuc);
                        ballList.get(i).setVelo(cd.getVelo().x(), cd.getVelo().y());
                        switch(collisionType){
                            case 0:
                                System.out.println("wall");
                                break;
                            case 1:
                                System.out.println(collidingG.type());
                                if(collidingG.type().equals("Absorber")){
                                    absorberCapture(ballList.get(i), (Absorber) collidingG);
                                    ballList.get(i).capture();
                                }
                                break;
                            case 2:
                                System.out.println("ball");
                                break;
                        }
                    } else {
                        //captured ball
                        moveBallForTime(ballList.get(i), moveTime);
                    }
                }
            }
        }

        this.setChanged();
        this.notifyObservers();
    }

    public CollisionDetails timeUntilCollision(Ball ball, int ballNo){
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
                collisionType = 0;
            }
        }

        //collisions with gizmos
        for(int i=0; i<gizmoList.size(); i++){

            if(ball.isCaptured() && gizmoList.get(i).getId().equals(collidingG.getId())){
                System.out.println("abs detected");
            }

            List<LineSegment> gizmoSides = gizmoList.get(i).getSides();
            List<Circle> gizmoCorners = gizmoList.get(i).getCorners();

            //collisions with gizmoSides
            for(int j=0; j<gizmoSides.size(); j++){
                double currentTime = Geometry.timeUntilWallCollision(gizmoSides.get(j), ballCircle, ballVelo);
                if(shortestTime > currentTime){
                    shortestTime = currentTime;
                    newVelo = Geometry.reflectWall(gizmoSides.get(j), ballVelo, 1.0);
                    collisionType = 1;
                    collidingG = gizmoList.get(i);
                }
            }

            //collisions with gizmoCorners
            for(int j=0; j<gizmoCorners.size(); j++){
                double currentTime = Geometry.timeUntilCircleCollision(gizmoCorners.get(j), ballCircle, ballVelo);
                if(shortestTime > currentTime){
                    shortestTime = currentTime;
                    newVelo = Geometry.reflectCircle(gizmoCorners.get(j).getCenter(), ball.getCircle().getCenter(), ball.getVelo(), 1.0);
                    collisionType = 1;
                    collidingG = gizmoList.get(i);
                }
            }
        }

        //collisions with other balls
        for(int i=0; i<ballList.size(); i++){
            if(!(i==ballNo)){
                double currentTime = Geometry.timeUntilCircleCollision(ballList.get(i).getCircle(), ballCircle, ballVelo);
                if(shortestTime > currentTime){
                    shortestTime = currentTime;
                    newVelo = Geometry.reflectCircle(ballList.get(i).getCircle().getCenter(), ball.getCircle().getCenter(), ball.getVelo(), 1.0);
                    collisionType = 2;
                    collidingB = ballList.get(i);
                }
            }
        }

        //return time until closest collision, with velocity after bouncing off
        return new CollisionDetails(shortestTime, newVelo);
    }

    public Ball moveBallForTime(Ball ball, double time){
        Vect velo = ball.getVelo();
        Vect pos = ball.getPos();

        Vect fv = friction(ball.getVelo(), time);
        ball.setVelo(fv.x(), fv.y());

        double newX = pos.x() + (velo.x()*time);

        double yDist = (velo.y() * time) + 0.5*(gravity*(time*time));
        double newY = pos.y() + yDist;

        ball.setVelo(ball.getVelo().x(), ball.getVelo().y()+(gravity*time));

        ball.setPos(newX, newY);

        return ball;
    }

    private Vect friction(Vect old, double moveTime){
        double x = (old.x()*(1-mu*moveTime-mu2*(Math.abs(old.x()))*moveTime));
        double y = (old.y()*(1-mu*moveTime-mu2*(Math.abs(old.y()))*moveTime));
        return new Vect(x,y);
    }

    private void absorberCapture(Ball ball, Absorber abs){
        Vect bottomRight = abs.getPos2();
        ball.setPos( bottomRight.x() - (ball.getRadius()) - 0.25, bottomRight.y() - (ball.getRadius()) - 0.25);
        ball.setVelo(0.0,0.0);
    }

    public void absorberShoot(Ball ball){
        if(ball.isCaptured()){
            ball.setVelo(0.0, -50);

        }
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

    public void nob(){
        this.setChanged();
        this.notifyObservers();
    }

    public void addGizmo(Gizmo g){
        gizmoList.add(g);
        nob();
    }

    public void addBall(Ball b){
        ballList.add(b);
        nob();
    }

    public Gizmo gizmoSearch(int x, int y){
        for(int i=0; i<gizmoList.size(); i++){
            if(gizmoList.get(i).getPos().x() == x && gizmoList.get(i).getPos().y() == y){
                return gizmoList.get(i);
            }
        }
        return null;
    }

    public boolean checkGizmoId(String id){
        for(int i=0; i<gizmoList.size(); i++){
            if(gizmoList.get(i).getId().equals(id)){
                return false;
            }
        }
        return true;
    }

    public boolean checkBallId(String id){
        for(int i=0; i<ballList.size(); i++){
            if(ballList.get(i).getId().equals(id)){
                return false;
            }
        }
        return true;
    }

    public void moveGizmo(Gizmo g, int x, int y){
        if(gizmoList.contains(g)){
            gizmoList.get(gizmoList.indexOf(g)).move(x,y);
        }
        nob();
    }

    public void rotateGizmo(Gizmo g){
        if(gizmoList.contains(g)){
            gizmoList.get(gizmoList.indexOf(g)).rotate();
        }
        nob();
    }

    public void deleteGizmo(Gizmo g){
        gizmoList.remove(g);
        nob();
    }

    public void deleteBall(Ball b){
        ballList.remove(b);
        nob();
    }

    public boolean isOccupied(int x, int y){
        for(int i=0; i<gizmoList.size(); i++){
            if(gizmoList.get(i).getPos().x() == x && gizmoList.get(i).getPos().y() == y){
                return true;
            }
        }

        for(int i=0; i<ballList.size(); i++){
            if(ballList.get(i).getPos().x() == x && ballList.get(i).getPos().y() == y){
                return true;
            }
        }
        return false;
    }

    public void setGravity(double g){
        gravity = g;
    }

    public void setFriction(double xf, double yf){
        mu = xf;
        mu2 = yf;
    }
}
