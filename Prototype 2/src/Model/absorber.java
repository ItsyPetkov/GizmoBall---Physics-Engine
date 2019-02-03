package Model;

import java.util.ArrayList;
import physics.LineSegment;
import physics.Circle;

public class absorber {

    int xpos1;
    int ypos1;
    int xpos2;
    int ypos2;
    Circle topleft;
    Circle topright;
    Circle bottomleft;
    Circle bottomright;

    // top left and bottom right corners of the shape
    public absorber(int x1, int y1, int x2, int y2){
        xpos1 = x1;
        ypos1 = y1;
        xpos2 = x2;
        ypos2 = y2;
        topleft = new Circle(xpos1, ypos1, 0);
        topright = new Circle(xpos2, ypos1, 0);
        bottomleft = new Circle(xpos1, ypos2, 0);
        bottomright = new Circle(xpos2, ypos2, 0);
    }

    public ArrayList<LineSegment> getPos(){
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
