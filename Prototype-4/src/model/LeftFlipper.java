package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

public class LeftFlipper extends Gizmo {
    String name;
    private String type = "LeftFlipper";

    public LeftFlipper(String id, int x, int y) {
        super (id, x, y);
    }

    @Override
    public Color getColour() {
        return null;
    }

    @Override
    public List<LineSegment> getSides() {
        return null;
    }

    @Override
    public List<Circle> getCorners() {
        return null;
    }

    @Override
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
