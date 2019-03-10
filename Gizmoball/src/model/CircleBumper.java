package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CircleBumper extends Gizmo {
    private Color colour;
    private String type;

    public CircleBumper(String id, int x, int y) {
        super(id, x, y);
        colour = Color.RED;
        type = "Circle";
    }

    @Override
    public String type(){
        return type;
    }

    @Override
    public Color getColour(){
        return colour;
    }

    @Override
    public void setColour(Color c){
        colour = c;
    }

    @Override
    public List<LineSegment> getSides(){
        return new ArrayList<>();
    }

    @Override
    public List<Circle> getCorners(){
        List<Circle> cs = new ArrayList<>();
        cs.add(new Circle((int)(super.getPos().x()+0.5), super.getPos().y()+0.5, 0.5));
        return cs;
    }
}
