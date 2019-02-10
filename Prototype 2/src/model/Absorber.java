package model;

import java.util.ArrayList;
import physics.LineSegment;
import physics.Circle;

public class Absorber {

    private int xpos1;
    private int ypos1;
    private int xpos2;
    private int ypos2;
    Circle topleft;
    Circle topright;
    Circle bottomleft;
    Circle bottomright;

    // top left and bottom right corners of the shape
    public Absorber(int x1, int y1, int x2, int y2){
        xpos1 = x1;
        ypos1 = y1;
        xpos2 = x2;
        ypos2 = y2;
        topleft = new Circle(xpos1, ypos1, 0);
        topright = new Circle(xpos2, ypos1, 0);
        bottomleft = new Circle(xpos1, ypos2, 0);
        bottomright = new Circle(xpos2, ypos2, 0);
    }

    // returns coordinates of bottom right corner
    public Coordinate getPos(){
        return new Coordinate(xpos2, ypos2);
    }

    public ArrayList<LineSegment> getLineSegments(){
        ArrayList<LineSegment> ls = new ArrayList<LineSegment>();
        LineSegment l1 = new LineSegment(xpos1, ypos1, xpos2, ypos1);
        LineSegment l2 = new LineSegment(xpos1, ypos1, xpos1, ypos2);
        LineSegment l3 = new LineSegment(xpos2, ypos1, xpos2, ypos2);
        LineSegment l4 = new LineSegment(xpos1, ypos2, xpos2, ypos2);
        ls.add(l1);
        ls.add(l2);
        ls.add(l3);
        ls.add(l4);
        return ls;
    }

}
