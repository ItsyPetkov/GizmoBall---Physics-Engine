package model;

import physics.Angle;
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
    boolean removeConnection(IGizmo g);
    List<IGizmo> getConnections();
    void trigger();

    boolean getUpwards();

    boolean getDownwards();

    Angle moveThroughAngle(Angle angle);

    boolean getVertical();

    Angle getRotationLeft();

    boolean getTransient();

    void setRotationLeft(Angle minus);

    void goUpwards();

    boolean getHorizontal();

    void goDownwards();
}
