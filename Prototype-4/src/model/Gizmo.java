package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

public abstract class Gizmo {

    private Coordinate pos;
    private String id;

    public Gizmo(String id, int x, int y){
        this.id = id;
        pos = new Coordinate(x,y);
    }

    public Coordinate getPos(){
        return pos;
    }

    abstract public Color getColour();

    abstract public List<LineSegment> getSides();

    abstract public List<Circle> getCorners();

    public String getId() {
        return id;
    }
    abstract public String getType();

    public void rotate() {
        System.out.println("Rotating..");
    }

    public void delete() {
        System.out.println("Gizmo "+id+" deleted.");
    }

    public void move(int x, int y) {
        pos = new Coordinate(x, y);
    }

}
