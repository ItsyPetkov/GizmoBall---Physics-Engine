package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CircleBumper extends Gizmo {
    private Color color;
    private String type = "Circle";

    public CircleBumper(String id, int x, int y) {
        super(id, x, y);
        color = Color.RED;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public Color getColour() {
        return null;
    }

    @Override
    public void setColour(Color c) {

    }

    @Override
    public List<LineSegment> getSides() {
        return new ArrayList<LineSegment>();
    }

    @Override
    public List<Circle> getCorners() {
        List<Circle> cs = new ArrayList<>();
        //cs.add(new physics.Circle((int)(super.getPos().getX()+0.5), super.getPos().getY()+0.5, 0.5));
        return cs;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getId() {
        return null;
    }

    public void rotate() {

    }
}
