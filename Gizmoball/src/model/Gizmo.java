package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Gizmo implements IGizmo{

    Vect pos;
    String id;
    int rotation;
    List<IGizmo> connections;

    public Gizmo(String id, double x, double y){
        pos = new Vect(x, y);
        this.id = id;
        rotation = 0;
        connections = new ArrayList<>();
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

    public List<IGizmo> getConnections(){
        return connections;
    }

    public boolean addConnection(IGizmo g){
        return connections.add(g);
    }

    abstract public void trigger();
}
