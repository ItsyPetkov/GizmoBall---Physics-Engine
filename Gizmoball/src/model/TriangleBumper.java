package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TriangleBumper extends Gizmo {
    private Color colour;
    private String type;

    public TriangleBumper(String id,int x, int y){
        super(id,x,y);
        colour = Color.GREEN;
        type = "Triangle";
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
        switch(rotation){
            case 0:
                ls.add(new LineSegment(super.getPos().x(), super.getPos().y(), super.getPos().x()+1, super.getPos().y()));
                ls.add(new LineSegment(super.getPos().x(), super.getPos().y(), super.getPos().x(), super.getPos().y()+1));
                ls.add(new LineSegment(super.getPos().x(), super.getPos().y()+1, super.getPos().x()+1, super.getPos().y()));
                break;
            case 1:
                ls.add(new LineSegment(super.getPos().x(), super.getPos().y(), super.getPos().x()+1, super.getPos().y()));
                ls.add(new LineSegment(super.getPos().x()+1, super.getPos().y(), super.getPos().x()+1, super.getPos().y()+1));
                ls.add(new LineSegment(super.getPos().x(), super.getPos().y(), super.getPos().x()+1, super.getPos().y()+1));
                break;
            case 2:
                ls.add(new LineSegment(super.getPos().x()+1, super.getPos().y(), super.getPos().x()+1, super.getPos().y()+1));
                ls.add(new LineSegment(super.getPos().x(), super.getPos().y()+1, super.getPos().x()+1, super.getPos().y()+1));
                ls.add(new LineSegment(super.getPos().x(), super.getPos().y()+1, super.getPos().x()+1, super.getPos().y()));
                break;
            case 3:
                ls.add(new LineSegment(super.getPos().x(), super.getPos().y(), super.getPos().x(), super.getPos().y()+1));
                ls.add(new LineSegment(super.getPos().x(), super.getPos().y()+1, super.getPos().x()+1, super.getPos().y()+1));
                ls.add(new LineSegment(super.getPos().x(), super.getPos().y(), super.getPos().x()+1, super.getPos().y()+1f));
                break;
        }
        return ls;

    }

    public List<Circle> getCorners(){
        List<Circle> cs = new ArrayList<Circle>();
        switch(rotation){
            case 0:
                cs.add(new Circle(super.getPos().x(), super.getPos().y(), 0));
                cs.add(new Circle(super.getPos().x()+1, super.getPos().y(), 0));
                cs.add(new Circle(super.getPos().x(), super.getPos().y()+1, 0));
                break;
            case 1:
                cs.add(new Circle(super.getPos().x(), super.getPos().y(), 0));
                cs.add(new Circle(super.getPos().x()+1, super.getPos().y(), 0));
                cs.add(new Circle(super.getPos().x()+1, super.getPos().y()+1, 0));
                break;
            case 2:
                cs.add(new Circle(super.getPos().x()+1, super.getPos().y()+1, 0));
                cs.add(new Circle(super.getPos().x()+1, super.getPos().y(), 0));
                cs.add(new Circle(super.getPos().x(), super.getPos().y()+1, 0));
                break;
            case 3:
                cs.add(new Circle(super.getPos().x(), super.getPos().y(), 0));
                cs.add(new Circle(super.getPos().x(), super.getPos().y()+1, 0));
                cs.add(new Circle(super.getPos().x(), super.getPos().y()+1, 0));
                break;
        }
        return cs;
    }
}
