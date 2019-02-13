package model;

import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Circle extends Gizmo {

    private Color colour;

    public Circle(int x, int y) {
        super(x,y);
        colour = Color.RED;
    }

    public Color getColour(){
        return colour;
    }

    public List<physics.Circle> getCorners() {
        List<physics.Circle> cs = new ArrayList<physics.Circle>();
        cs.add(new physics.Circle(super.getPos().getX()+0.5, super.getPos().getY()+0.5, 0.5));
        return cs;
    }

}
