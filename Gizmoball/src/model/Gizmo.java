package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.List;

public abstract class Gizmo {

    Vect pos;
    String id;
    int rotation;

    public Gizmo(String id, double x, double y){
        pos = new Vect(x, y);
        this.id = id;
        rotation = 0;
    }

    public Vect getPos(){
        return pos;
    }

    public void setPos(int x, int y){
        pos = new Vect(x, y);
    }

    abstract public String type();

    abstract public Color getColour();

    abstract public void setColour(Color c);

    abstract public List<LineSegment> getSides();

    abstract public List<Circle> getCorners();

    public String getId() {
        return id;
    }

    public void rotate(){
        if(rotation == 3){
            rotation = 0;
        } else {
            rotation++;
        }
    }

    public int getRotation(){
        return rotation;
    }

    public void move(int x, int y) {
        pos = new Vect(x,y);
    }
}
