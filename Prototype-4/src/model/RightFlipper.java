package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

public class RightFlipper extends Gizmo {
    String name;
    String type = "RightFlipper";
    public RightFlipper(String id, int x, int y) {
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

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
