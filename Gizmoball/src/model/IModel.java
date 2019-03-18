package model;

import physics.Vect;

import java.util.List;
import java.util.Observable;

public abstract class IModel extends Observable {
    public abstract void moveBall();
    public abstract void absorberShoot(IBall ball);
    public abstract Vect getWallTL();
    public abstract Vect getWallBR();
    public abstract List<IGizmo> getGizmos();
    public abstract List<IBall> getBalls();
    public abstract boolean addGizmo(IGizmo g);
    public abstract void addBall(IBall b);
    public abstract IGizmo gizmoSearch(int x, int y);
    public abstract IBall ballSearch(int x, int y);
    public abstract boolean checkGizmoId(String id);
    public abstract boolean checkBallId(String id);
    public abstract void moveGizmo(IGizmo g, int x, int y);
    public abstract void setBallPos(IBall b, int x, int y);
    public abstract void rotateGizmo(IGizmo g);
    public abstract void deleteGizmo(IGizmo g);
    public abstract void deleteBall(IBall b);
    public abstract boolean dragAbs(Absorber abs, Vect last, int x2, int y2);
    public abstract void setGravity(double g);
    public abstract void setFriction(double xf, double yf);
}
