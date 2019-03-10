package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Absorber extends Gizmo{

    private Color colour;
    private Vect pos2;
    private String type = "Absorber";

    public Absorber(String id,double x1, double y1, double x2, double y2){
        super(id,x1,y1);
        pos2 = new Vect(x2, y2);
        colour = Color.MAGENTA;
    }

    public Vect getPos2(){
        return pos2;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public Color getColour() {
        return colour;
    }

    @Override
    public void setColour(Color c) {
        colour = c;
    }

    @Override
    public List<LineSegment> getSides() {
        List<LineSegment> ls = new ArrayList<>();
        ls.add(new LineSegment(super.getPos().x(), super.getPos().y(), pos2.x(), super.getPos().y()));
        ls.add(new LineSegment(super.getPos().x(), super.getPos().y(), super.getPos().x(), pos2.y()));
        ls.add(new LineSegment(pos2.x(), pos2.y(), super.getPos().x(), pos2.y()));
        ls.add(new LineSegment(pos2.x(), pos2.y(), pos2.x(), super.getPos().y()));
        return ls;
    }

    @Override
    public List<Circle> getCorners() {
        List<Circle> cs = new ArrayList<>();
        cs.add(new Circle(super.getPos().x(), super.getPos().y(),0));
        cs.add(new Circle(super.getPos().x(), pos2.y(),0));
        cs.add(new Circle(pos2.x(), super.getPos().y(),0));
        cs.add(new Circle(pos2.x(), pos2.y(),0));
        return cs;
    }

    @Override
    public void rotate() {

    }
}
