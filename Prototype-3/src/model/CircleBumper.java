package model;

import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CircleBumper extends Gizmo {

    private Color colour;

    public CircleBumper(int x, int y) {
        super(x,y);
        colour = Color.RED;
    }

    public Color getColour(){
        return colour;
    }

    public List<LineSegment> getSides(){
        return new ArrayList<LineSegment>();
    }

    public List<physics.Circle> getCorners() {
        List<physics.Circle> cs = new ArrayList<physics.Circle>();
        cs.add(new physics.Circle(super.getPos().getX()+0.5, super.getPos().getY()+0.5, 0.5));
        return cs;
    }

}
