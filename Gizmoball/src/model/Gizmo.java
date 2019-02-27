package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.List;

public abstract class Gizmo {

    Vect pos;

    public Gizmo(double x, double y){
        pos = new Vect(x, y);
    }

    public Vect getPos(){
        return pos;
    }

    abstract public Color getColour();

    abstract public List<LineSegment> getSides();

    abstract public List<Circle> getCorners();
}
