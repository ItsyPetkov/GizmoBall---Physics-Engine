package model;

import physics.Angle;
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
    private IBall captured;

    public Absorber(String id,double x1, double y1, double x2, double y2){
        super(id,x1,y1);
        pos2 = new Vect(x2, y2);
        colour = Color.MAGENTA;
        captured = null;
    }

    public Vect getPos2(){
        return pos2;
    }

    public void setPos2(int x, int y){
        pos2 = new Vect(x, y);
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
    public void trigger() {
        if(!(captured == null)){
            captured.setVelo(0.0, -50);
            captured = null;
        }
    }

    public boolean capture(IBall b){
        if(captured == null){
            captured = b;
            return true;
        }
        return false;
    }

    public boolean getUpwards() {
        return false;
    }

    @Override
    public boolean getDownwards() {
        return false;
    }

    @Override
    public Angle moveThroughAngle(Angle angle) {
        return null;
    }

    @Override
    public boolean getVertical() {
        return false;
    }

    @Override
    public Angle getRotationLeft() {
        return null;
    }

    @Override
    public boolean getTransient() {
        return false;
    }

    @Override
    public void setRotationLeft(Angle minus) {

    }

    @Override
    public void goUpwards() {

    }

    @Override
    public boolean getHorizontal() {
        return false;
    }

    @Override
    public void goDownwards() {
    }
}
