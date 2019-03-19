package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.List;

public interface IGizmo {
    Vect getPos();
    void setPos(int x, int y);
    String type();
    Color getColour();
    void setColour(Color c);
    List<LineSegment> getSides();
    List<Circle> getCorners();
    String getId();
    void rotate();
    int getRotation();
    boolean addConnection(IGizmo g);
    List<IGizmo> getConnections();
}
