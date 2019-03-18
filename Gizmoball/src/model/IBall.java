package model;

import physics.Circle;
import physics.Vect;

import java.awt.*;

public interface IBall {
    Vect getVelo();
    void setVelo(double xv, double yv);
    Vect getPos();
    void setPos(double x, double y);
    double getRadius();
    Circle getCircle();
    Color getColour();
    String getType();
    String getId();
    boolean isCaptured();
    void capture();
    void release();
}
