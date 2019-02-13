package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

public abstract class Gizmo {

    private Coordinate pos;

    public Gizmo(int x, int y){
        pos = new Coordinate(x,y);
    }

    public Coordinate getPos(){
        return pos;
    }

    abstract Color getColour();

    abstract public List<Circle> getCorners();

}
