package model;

import physics.Vect;

import java.util.List;
import java.util.Observable;

public abstract class IModel extends Observable {
    public abstract void moveBall();
    public abstract void absorberShoot(Ball ball);
    public abstract Vect getWallTL();
    public abstract Vect getWallBR();
    public abstract List<Gizmo> getGizmos();
    public abstract List<Ball> getBalls();
    public abstract boolean addGizmo(Gizmo g);
    public abstract void addBall(Ball b);
    public abstract Gizmo gizmoSearch(int x, int y);
    public abstract Ball ballSearch(int x, int y);
    public abstract boolean checkGizmoId(String id);
    public abstract boolean checkBallId(String id);
    public abstract void moveGizmo(Gizmo g, int x, int y);
    public abstract void setBallPos(Ball b, int x, int y);
    public abstract void rotateGizmo(Gizmo g);
    public abstract void deleteGizmo(Gizmo g);
    public abstract void deleteBall(Ball b);
    public abstract boolean dragAbs(Absorber abs, Vect last, int x2, int y2);
    public abstract void setGravity(double g);
    public abstract void setFriction(double xf, double yf);
}
