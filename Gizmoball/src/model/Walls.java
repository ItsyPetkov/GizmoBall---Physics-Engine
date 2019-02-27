package model;

import physics.LineSegment;
import physics.Vect;

import java.util.ArrayList;
import java.util.List;

public class Walls {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Walls(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public List<LineSegment> getLineSegments(){
        List<LineSegment> ls = new ArrayList<>();
        ls.add(new LineSegment(x1, y1, x2, y1)); //top
        ls.add(new LineSegment(x1, y1, x1, y2)); //left
        ls.add(new LineSegment(x2, y2, x2, y1)); //right
        ls.add(new LineSegment(x2, y2, x1, y2)); //bottom
        return ls;
    }

    public Vect getTL(){
        return new Vect(x1, y1);
    }

    public Vect getBR(){
        return new Vect(x2, y2);
    }

}
