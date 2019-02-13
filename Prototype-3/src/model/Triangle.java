package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Triangle extends Gizmo{

    private Color colour;

    public Triangle(int x, int y){
        super(x,y);
        colour = Color.GREEN;
    }

    public Color getColour(){
        return colour;
    }

    public List<LineSegment> getSides(){
        List<LineSegment> ls = new ArrayList<LineSegment>();
        ls.add(new LineSegment(super.getPos().getX(), super.getPos().getY(), super.getPos().getX()+1, super.getPos().getY()));
        ls.add(new LineSegment(super.getPos().getX(), super.getPos().getY(), super.getPos().getX(), super.getPos().getY()+1));
        ls.add(new LineSegment(super.getPos().getX(), super.getPos().getY()+1, super.getPos().getX()+1, super.getPos().getY()));
        return ls;
    }

    public List<Circle> getCorners(){
        List<Circle> cs = new ArrayList<physics.Circle>();
        cs.add(new Circle(super.getPos().getX(), super.getPos().getY(), 0));
        cs.add(new Circle(super.getPos().getX()+1, super.getPos().getY(), 0));
        cs.add(new Circle(super.getPos().getX(), super.getPos().getY()+1, 0));
        return cs;
    }

}
