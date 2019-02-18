package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TriangleBumper extends Gizmo {

    private Color colour;
    private String type = "Triangle";
    public TriangleBumper(String id, int x, int y){
        super(id, x, y);
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
        List<Circle> cs = new ArrayList<Circle>();
        cs.add(new Circle(super.getPos().getX(), super.getPos().getY(), 0));
        cs.add(new Circle(super.getPos().getX()+1, super.getPos().getY(), 0));
        cs.add(new Circle(super.getPos().getX(), super.getPos().getY()+1, 0));
        return cs;
    }

    @Override
    public String getType() {
        return type;
    }

}
