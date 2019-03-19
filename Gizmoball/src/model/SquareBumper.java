package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SquareBumper extends Gizmo{
    private Color colour;
    private String type;

    public SquareBumper(String id, int x, int y){
        super(id,x,y);
        colour = Color.BLUE;
        type = "Square";
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public Color getColour(){
        return colour;
    }

    @Override
    public void setColour(Color c) {
        colour = c;
    }

    public List<LineSegment> getSides(){
        List<LineSegment> ls = new ArrayList<LineSegment>();
        ls.add(new LineSegment(super.getPos().x(), super.getPos().y(), super.getPos().x()+1, super.getPos().y()));
        ls.add(new LineSegment(super.getPos().x(), super.getPos().y(), super.getPos().x(), super.getPos().y()+1));
        ls.add(new LineSegment(super.getPos().x()+1, super.getPos().y(), super.getPos().x()+1, super.getPos().y()+1));
        ls.add(new LineSegment(super.getPos().x(), super.getPos().y()+1, super.getPos().x()+1, super.getPos().y()+1));
        return ls;
    }

    public List<Circle> getCorners(){
        List<Circle> cs = new ArrayList<Circle>();
        cs.add(new Circle(super.getPos().x(), super.getPos().y(), 0));
        cs.add(new Circle(super.getPos().x()+1, super.getPos().y(), 0));
        cs.add(new Circle(super.getPos().x(), super.getPos().y()+1, 0));
        cs.add(new Circle(super.getPos().x()+1, super.getPos().y()+1, 0));
        return cs;
    }

    @Override
    public void trigger() {
        setColour(Color.GREEN);
    }
}
