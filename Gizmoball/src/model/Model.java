package model;

import physics.LineSegment;
import physics.Vect;
import physics.Circle;
import physics.Geometry;
import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends IModel{

    private Walls walls;
    private List<IGizmo> gizmoList;
    private List<IBall> ballList;
    private double gravity = 25;
    private double mu = 0.025;
    private double mu2 = 0.025;
    private int collisionType = 0;
    private IGizmo collidingG;
    private IBall collidingB;

    public Model(){
        walls = new Walls(0,0,20,20);
        gizmoList = new ArrayList<>();
        ballList = new ArrayList<>();

    }

    public void moveBall(){
        double moveTime = 0.05;

        for(int i=0; i<ballList.size(); i++) {
            if (!(ballList.get(i).getVelo().x() == 0 && ballList.get(i).getVelo().y() == 0) || !(ballList.get(i).isCaptured())) {
                CollisionDetails cd = timeUntilCollision(ballList.get(i), i);
                double tuc = cd.getTuc();
                if (tuc > moveTime) {
                    //no collision
                    moveBallForTime(ballList.get(i), moveTime);
                } else {
                    //collision
                    if(ballList.get(i).isCaptured()){
                        moveBallForTime(ballList.get(i), moveTime+0.005);
                        ballList.get(i).release();
                    } else {
                        moveBallForTime(ballList.get(i), tuc);
                        ballList.get(i).setVelo(cd.getVelo().x(), cd.getVelo().y());
                        switch(collisionType){
                            case 0:
                                //collision with wall
                                break;
                            case 1:
                                //collision with gizmo collidingG
                                if(collidingG.type().equals("Absorber")){
                                    absorberCapture(ballList.get(i), (Absorber) collidingG);
                                    ballList.get(i).capture();
                                }
                                break;
                            case 2:
                                //collision with ball collidingB
                                break;
                        }
                    }
                }
            }
        }

        this.setChanged();
        this.notifyObservers();
    }

    public CollisionDetails timeUntilCollision(IBall ball, int ballNo){
        Circle ballCircle = ball.getCircle();
        Vect ballVelo = ball.getVelo();

        //collisions with walls
        List<LineSegment> wallSegs = walls.getLineSegments();
        double shortestTime = Geometry.timeUntilWallCollision(wallSegs.get(0), ballCircle, ballVelo);
        Vect newVelo = Geometry.reflectWall(wallSegs.get(0), ballVelo, 1.0);
        collisionType = 0;
        for(int i=1; i<wallSegs.size(); i++){
            double currentTime = Geometry.timeUntilWallCollision(wallSegs.get(i), ballCircle, ballVelo);
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = Geometry.reflectWall(wallSegs.get(i), ballVelo, 1.0);
            }
        }

        //collisions with gizmos
        for(int i=0; i<gizmoList.size(); i++){
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

    private void moveBallForTime(IBall ball, double time){
        Vect velo = ball.getVelo();
        Vect pos = ball.getPos();

        Vect fv = friction(ball.getVelo(), time);
        ball.setVelo(fv.x(), fv.y());

        double newX = pos.x() + (velo.x()*time);

        double yDist = (velo.y() * time) + 0.5*(gravity*(time*time));
        double newY = pos.y() + yDist;

        ball.setVelo(ball.getVelo().x(), ball.getVelo().y()+(gravity*time));

        ball.setPos(newX, newY);
    }

    private Vect friction(Vect old, double moveTime){
        double x = (old.x()*(1-mu*moveTime-mu2*(Math.abs(old.x()))*moveTime));
        double y = (old.y()*(1-mu*moveTime-mu2*(Math.abs(old.y()))*moveTime));
        return new Vect(x,y);
    }

    private void absorberCapture(IBall ball, Absorber abs){
        Vect bottomRight = abs.getPos2();
        ball.setPos( bottomRight.x() - (ball.getRadius()) - 0.25, bottomRight.y() - (ball.getRadius()) - 0.25);
        ball.setVelo(0.0,0.0);
    }

    public void absorberShoot(IBall ball){
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

    public List<IGizmo> getGizmos(){
        return gizmoList;
    }

    public List<IBall> getBalls(){
        return ballList;
    }

    private void nob(){
        this.setChanged();
        this.notifyObservers();
    }

    public boolean addGizmo(IGizmo g){
        if(g.type().equals("Absorber")){
            boolean trigger = true;
            Absorber abs = (Absorber) g;
            double height = abs.getPos2().y() - abs.getPos().y();
            double width = abs.getPos2().x() - abs.getPos().x();
            for(int h=0; h<height; h++){
                for(int w=0; w<width; w++){
                    if(isOccupied(abs.getPos().x()+w, abs.getPos().y()+h)){
                        trigger = false;
                    }
                }
            }
            if(trigger){
                gizmoList.add(g);
                nob();
                return true;
            }
        } else if(g.type().equals("LeftFlipper") || g.type().equals("RightFlipper")){
            boolean trigger = true;
            double height = 2;
            double width = 2;
            for(int h=0; h<height; h++){
                for(int w=0; w<width; w++){
                    if(isOccupied(g.getPos().x()+w, g.getPos().y()+h)){
                        trigger = false;
                    }
                }
            }
            if(trigger){
                gizmoList.add(g);
                nob();
                return true;
            }
        } else if(!isOccupied(g.getPos().x(), g.getPos().y())){
            gizmoList.add(g);
            nob();
            return true;
        }
        nob();
        return false;
    }

    public void addBall(IBall b){
        if(!isOccupied(b.getPos().x(), b.getPos().y())) {
            ballList.add(b);
        }
        nob();
    }

    public IGizmo gizmoSearch(int x, int y){

        for(int i=0; i<gizmoList.size(); i++){
            if(gizmoList.get(i).type().equals("Absorber")) {

                IGizmo absorber =(Absorber) gizmoList.get(i);
                double rangeX = ((Absorber) absorber).getPos2().x() - absorber.getPos().x();
                double rangeY = ((Absorber) absorber).getPos2().y() - absorber.getPos().y();
                boolean vertical = ((absorber.getPos().x()+rangeX >= x) && (x >= absorber.getPos().x()));
                boolean horizontal = ((absorber.getPos().y()+rangeY >= y) && (y >= absorber.getPos().y()));
                if (vertical && horizontal) {
                    return gizmoList.get(i);
                }
            }

            if(gizmoList.get(i).getPos().x() == x && gizmoList.get(i).getPos().y() == y){
                return gizmoList.get(i);
            }

            if(gizmoList.get(i).type().equals("LeftFlipper") || gizmoList.get(i).type().equals("RightFlipper")){
                int height = 2;
                int width = 2;
                for(int h=0; h<height; h++){
                    for(int w=0; w<width; w++){
                        if(gizmoList.get(i).getPos().x()+w == x && gizmoList.get(i).getPos().y()+h == y){
                            return gizmoList.get(i);
                        }
                    }
                }
            }
        }
        return null;
    }

    public IBall ballSearch(int x, int y){
        int range = 1;
        for(int i=0; i<ballList.size(); i++) {
            if (ballList.get(i).getPos().x() == x && ballList.get(i).getPos().y() == y ||
                    ballList.get(i).getPos().x() - range == x && ballList.get(i).getPos().y() == y ||
                    ballList.get(i).getPos().x() == x && ballList.get(i).getPos().y() - range == y ||
                    ballList.get(i).getPos().x() - range == x && ballList.get(i).getPos().y() - range == y) {
                return ballList.get(i);
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

    public void moveGizmo(IGizmo g, int x, int y){
        if(gizmoList.contains(g) && (!isOccupied(x, y))){
            System.out.println("moving");
            gizmoList.get(gizmoList.indexOf(g)).setPos(x,y);
        }
        nob();
    }

    public void setBallPos(IBall b, int x, int y){
        if(ballList.contains(b) && (!isOccupied(x, y))){
            ballList.get(ballList.indexOf(b)).setPos(x,y);
        }
        nob();
    }

    public void rotateGizmo(IGizmo g){
        if(gizmoList.contains(g)){
            gizmoList.get(gizmoList.indexOf(g)).rotate();
        }
        nob();
    }

    public void deleteGizmo(IGizmo g){
        gizmoList.remove(g);
        nob();
    }

    public void deleteBall(IBall b){
        ballList.remove(b);
        nob();
    }

    private boolean isOccupied(double x, double y){
        for(int i=0; i<gizmoList.size(); i++){
            if(gizmoList.get(i).getPos().x() == x && gizmoList.get(i).getPos().y() == y){
                return true;
            }

            if(gizmoList.get(i).type().equals("Absorber")){
                Absorber abs = (Absorber) gizmoList.get(i);
                double height = abs.getPos2().y() - abs.getPos().y();
                double width = abs.getPos2().x() - abs.getPos().x();
                for(int h=0; h<height; h++){
                    for(int w=0; w<width; w++){
                        if(abs.getPos().x()+w == x && abs.getPos().y()+h == y){
                            return true;
                        }
                    }
                }
            }

            if(gizmoList.get(i).type().equals("LeftFlipper") || gizmoList.get(i).type().equals("RightFlipper")){
                int height = 2;
                int width = 2;
                for(int h=0; h<height; h++){
                    for(int w=0; w<width; w++){
                        if(gizmoList.get(i).getPos().x()+w == x && gizmoList.get(i).getPos().y()+h == y){
                            return true;
                        }
                    }
                }
            }
        }

        for(int i=0; i<ballList.size(); i++){
            if(ballList.get(i).getPos().x() == x && ballList.get(i).getPos().y() == y){
                return true;
            }
        }
        return false;
    }

    public boolean dragAbs(Absorber abs, Vect last, int x2, int y2){

        double height = abs.getPos2().y()-abs.getPos().y();
        double width = abs.getPos2().x()-abs.getPos().x();

        boolean check = true;

        if(x2 > last.x()){
            if (last.x()+1 == abs.getPos2().x() && last.y()+1 == abs.getPos2().y()){
                //BR
                for(int h=0; h<height; h++){
                    for(int w=0; w<Math.abs(abs.getPos2().x() - (x2+1)); w++){
                        if(isOccupied(x2-w, abs.getPos().y()+h)){
                            check = false;
                        }
                    }
                }
                if(check){
                    abs.setPos2(x2+1, y2+1);
                }
            } else if(last.x() == abs.getPos().x() && last.y() == abs.getPos().y()){
                if(width == 1){
                    //TR
                    for(int h=0; h<height; h++){
                        for(int w=0; w<Math.abs(abs.getPos().x() - x2); w++){
                            if(isOccupied(x2-w, abs.getPos().y()+h)){
                                check = false;
                            }
                        }
                    }
                    if(check){
                        abs.setPos2(x2+1, (int) abs.getPos2().y());
                    }
                } else {
                    //TL backpedal
                    abs.setPos(x2, (int) abs.getPos().y());
                }
            } else if(last.x()+1 == abs.getPos2().x() && last.y() == abs.getPos().y()){
                //TR
                for(int h=0; h<height; h++){
                    for(int w=0; w<Math.abs(last.x() - x2); w++){
                        if(isOccupied(x2-w, abs.getPos().y()+h)){
                            check = false;
                        }
                    }
                }
                if(check){
                    abs.setPos2(x2+1, (int) abs.getPos2().y());
                }
            } else if(last.x() == abs.getPos().x() && last.y()+1 == abs.getPos2().y()){
                //BL backpedal
                abs.setPos(x2, (int) abs.getPos().y());
            }
        } else if(x2 < last.x()){
            if(last.x() == abs.getPos().x() && last.y() == abs.getPos().y()){
                //TL
                for(int h=0; h<height; h++){
                    for(int w=0; w<Math.abs(abs.getPos().x() - x2); w++){
                        if(isOccupied(x2+w, abs.getPos().y()+h)){
                            check = false;
                        }
                    }
                }
                if(check){
                    abs.setPos(x2, (int) abs.getPos().y());
                }
            } else if (last.x()+1 == abs.getPos2().x() && last.y()+1 == abs.getPos2().y()){
                if(width == 1){
                    //BL
                    for(int h=0; h<height; h++){
                        for(int w=0; w<Math.abs(abs.getPos().x() - x2); w++){
                            if(isOccupied(x2+w, abs.getPos().y()+h)){
                                check = false;
                            }
                        }
                    }
                    if(check){
                        abs.setPos(x2, (int) abs.getPos().y());
                    }
                } else {
                    //BR backpedal
                    abs.setPos2(x2+1, (int) abs.getPos2().y());
                }
            } else if(last.x() == abs.getPos().x() && last.y()+1 == abs.getPos2().y()){
                //BL
                for(int h=0; h<height; h++){
                    for(int w=0; w<Math.abs(abs.getPos().x() - x2); w++){
                        if(isOccupied(x2+w, abs.getPos().y()+h)){
                            check = false;
                        }
                    }
                }
                if(check){
                    abs.setPos(x2, (int) abs.getPos().y());
                }
            } else if(last.x()+1 == abs.getPos2().x() && last.y() == abs.getPos().y()){
                //TR backpedal
                abs.setPos2(x2+1, (int) abs.getPos2().y());
            }
        } else if(y2 > last.y()){
            if (last.x()+1 == abs.getPos2().x() && last.y()+1 == abs.getPos2().y()){
                //BR
                for(int w=0; w<width; w++){
                    for(int h=0; h<Math.abs(abs.getPos2().y() - (y2+1)); h++){
                        if(isOccupied(abs.getPos().x()+w, y2-h)){
                            check = false;
                        }
                    }
                }
                if(check){
                    abs.setPos2((int) abs.getPos2().x(), y2+1);
                }
            } else if(last.x() == abs.getPos().x() && last.y() == abs.getPos().y()){
                if(height == 1){
                    //BL
                    for(int w=0; w<width; w++){
                        for(int h=0; h<Math.abs(abs.getPos().y() - y2); h++){
                            if(isOccupied(abs.getPos().x()+w, y2-h)){
                                check = false;
                            }
                        }
                    }
                    if(check){
                        abs.setPos2((int) abs.getPos2().x(), y2+1);
                    }
                } else {
                    //TL backpedal
                    abs.setPos(x2, y2);
                }
            } else if(last.x()+1 == abs.getPos2().x() && last.y() == abs.getPos().y()){
                //TR backpedal
                abs.setPos((int) abs.getPos().x(), y2);
            } else if(last.x() == abs.getPos().x() && last.y()+1 == abs.getPos2().y()){
                //BL
                for(int w=0; w<width; w++){
                    for(int h=0; h<Math.abs(last.y() - y2); h++){
                        if(isOccupied(abs.getPos().x()+w, y2-h)){
                            check = false;
                        }
                    }
                }
                if(check){
                    abs.setPos2((int) abs.getPos2().x(), y2+1);
                }
            }
        } else if(y2 < last.y()){
            if(last.x() == abs.getPos().x() && last.y() == abs.getPos().y()){
                //TL
                for(int w=0; w<width; w++){
                    for(int h=0; h<Math.abs(abs.getPos().y() - y2); h++){
                        if(isOccupied(abs.getPos().x()+w, y2+h)){
                            check = false;
                        }
                    }
                }
                if(check){
                    abs.setPos((int) abs.getPos().x(), y2);
                }
            } else if(last.x()+1 == abs.getPos2().x() && last.y()+1 == abs.getPos2().y()){
                if(height == 1){
                    //TR
                    for(int w=0; w<width; w++){
                        for(int h=0; h<Math.abs(abs.getPos().y() - y2); h++){
                            if(isOccupied(abs.getPos().x()+w, y2+h)){
                                check = false;
                            }
                        }
                    }
                    if(check){
                        abs.setPos((int) abs.getPos().x(), y2);
                    }
                } else {
                    //BR backpedal
                    abs.setPos2(x2+1, y2+1);
                }
            } else if(last.x() == abs.getPos().x() && last.y()+1 == abs.getPos2().y()){
                //BL backpedal
                abs.setPos2((int) abs.getPos2().x(), y2+1);
            } else if(last.x()+1 == abs.getPos2().x() && last.y() == abs.getPos().y()){
                //TR
                for(int w=0; w<width; w++){
                    for(int h=0; h<Math.abs(abs.getPos().y() - y2); h++){
                        if(isOccupied(abs.getPos().x()+w, y2+h)){
                            check = false;
                        }
                    }
                }
                if(check){
                    abs.setPos((int) abs.getPos().x(), y2);
                }
            }
        }
        nob();
        return check;
    }

    public void setGravity(double g){
        gravity = g;
    }

    public void setFriction(double xf, double yf){
        mu = xf;
        mu2 = yf;
    }
}
