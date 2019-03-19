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
    public abstract boolean addGizmo(String type, String id, int x, int y);
    public abstract boolean addAbsorber(String id, int x1, int y1, int x2, int y2);
    public abstract boolean addBall(String id, double x, double y, double xv, double yv);
    public abstract IGizmo gizmoSearch(int x, int y);
    public abstract IBall ballSearch(int x, int y);
    public abstract boolean checkGizmoId(String id);
    public abstract boolean checkBallId(String id);
    public abstract void moveGizmo(IGizmo g, int x, int y);
    public abstract void setBallPos(IBall b, int x, int y);
    public abstract void rotateGizmo(IGizmo g);
    public abstract void deleteGizmo(IGizmo g);
    public abstract void deleteBall(IBall b);
    public abstract void setGizmoConnection(IGizmo g, IGizmo c);
    public abstract boolean dragAbs(Absorber abs, Vect last, int x2, int y2);
    public abstract void setGravity(double g);
    public abstract void setFriction(double xf, double yf);
    public abstract double getGravity();
    public abstract double[] getFriction();
}
