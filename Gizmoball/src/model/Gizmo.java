package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.List;

public abstract class Gizmo {

    Vect pos;
    String id;

    public Gizmo(String id, double x, double y){
        pos = new Vect(x, y);
        this.id = id;
    }

    public Vect getPos(){
        return pos;
    }

    abstract public String type();

    abstract public Color getColour();

    abstract public void setColour(Color c);

    abstract public List<LineSegment> getSides();

    abstract public List<Circle> getCorners();

    abstract public String getType();

    public String getId() {
        return id;
    }

    abstract public void rotate();
    public void move(int x, int y) {
        pos = new Vect(x,y);
    }

}
