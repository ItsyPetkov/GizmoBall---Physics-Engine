package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

public abstract class Gizmo {

    private String id;
    private model.Coordinate pos;

    public Gizmo( String id, int x, int y){
        this.id = id;
        pos = new model.Coordinate(x,y);
    }

    public model.Coordinate getPos(){
        return pos;
    }

    public String getId() {
        return id;
    }

    abstract Color getColour();

    abstract public List<LineSegment> getSides();

    abstract public List<Circle> getCorners();
    abstract public String getType();

    public void rotate() {
        System.out.println("Rotating.. :)");
    }

    public void delete() {
        System.out.println("Gizmo deleted.. :(");
    }

    public void move(double x, double y) {
        System.out.println("Gizmo moved to: "+"("+x+", "+y+")");
    }

}
