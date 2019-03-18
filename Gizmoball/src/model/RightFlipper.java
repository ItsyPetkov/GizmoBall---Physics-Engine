package model;
import physics.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RightFlipper extends Gizmo {
    String type = "RightFlipper";

    //Constituent flipper parts
    private Color colour;
    private Circle topCircle;
    private Circle bottomCircle;
    private LineSegment topSideLine;
    private LineSegment leftSideLine;
    private LineSegment rightSideLine;
    private LineSegment bottomSideLine;
    private Vect center;

    private Angle rotation;
    private Angle rotationLeft;
    private Vect centerOfRotation;

    private boolean mUpwards;
    private boolean mDownwards;
    private boolean mStationary;

    private boolean mVertical;
    private boolean mHorizontal;
    private boolean mTransient;

    //Creates a flipper, represented by circles, lines and vectors
    public RightFlipper(String id,int x, int y) {
        super(id,x,y);

        //centre created with the x and y coordinates
        center = new Vect((double) (x+2), (double)y);

        //90 degrees to rotate through initially
        rotationLeft = Angle.DEG_90;
        rotation = new Angle(1);
        this.colour=colour.BLACK;

        //Constituent Parts of Flipper
        topSideLine = new LineSegment(center.x(), center.y() + 0.25, center.x() - 0.5, center.y() + 0.25);
        bottomSideLine = new LineSegment(center.x(), center.y() + 1.75, center.x() - 0.5, center.y() + 1.75);
        rightSideLine = new LineSegment(center.x(), center.y() + 0.25, center.x(), center.y() + 1.75);
        leftSideLine = new LineSegment(center.x() - 0.5, center.y() + 0.25, center.x() - 0.5, center.y() + 1.75);

        topCircle = new Circle(center.x() - 0.25 , center.y() + 0.25, 0.25);
        bottomCircle = new Circle(center.x() - 0.25 , center.y() + 1.75, 0.25);

        //Flipper rotates at the top
        centerOfRotation = topCircle.getCenter();
        //Flipper starting position
        beStationary();
        beVertical();

    }
    //where the actual movement happens
    public Angle moveThroughAngle(Angle leftToGo) {
        if(mUpwards){
            //System.out.println("doing Up");
            if(leftToGo.compareTo(rotation) > 0) {
                applyMovement(rotation);
                rotationLeft = leftToGo.minus(rotation);
                beTransient();
            } else if(leftToGo.compareTo(Angle.DEG_90) == 0) {
                beStationary();
                rotationLeft = Angle.ZERO;
                beHorizontal();
            } else {
                applyMovement(leftToGo);
                beStationary();
                beHorizontal();
                rotationLeft = Angle.ZERO;

            }
            return rotationLeft;
        }

        if(mDownwards) {
            //System.out.println("doing down");
            if(leftToGo.compareTo(rotation) > 0) {
                applyMovement(Angle.ZERO.minus(rotation));
                rotationLeft = leftToGo.minus(rotation);
                beTransient();
                return rotationLeft;
            } else if(leftToGo.compareTo(Angle.DEG_90) == 0) {
                beStationary();
                rotationLeft = Angle.DEG_90;
                beVertical();
                return Angle.ZERO;
            } else {
                applyMovement(Angle.ZERO.minus(leftToGo));
                beStationary();
                rotationLeft = Angle.DEG_90;
                beVertical();
                return Angle.ZERO;
            }
        } else

            return Angle.ZERO;

    }
    //Method for visually changing the flipper
    public void applyMovement(Angle angle) {
        // Angle is changed here to make it rotate around the opposite way to differentiate flipper types
        angle = Angle.ZERO.minus(angle);

        topCircle = Geometry.rotateAround(topCircle, centerOfRotation, angle);
        bottomCircle = Geometry.rotateAround(bottomCircle, centerOfRotation, angle);

        topSideLine = Geometry.rotateAround(topSideLine, centerOfRotation, angle);
        bottomSideLine = Geometry.rotateAround(bottomSideLine, centerOfRotation, angle);
        leftSideLine = Geometry.rotateAround(leftSideLine, centerOfRotation, angle);
        rightSideLine = Geometry.rotateAround(rightSideLine, centerOfRotation, angle);

        //setChanged();
        //notifyObservers();
    }

    //Getters and Setters

    public Circle getTopCircle(){
        return topCircle;
    }

    public Circle getBottomCircle(){
        return bottomCircle;
    }

    public Angle getRotationLeft() {
        return rotationLeft;
    }

    public void setRotationLeft(Angle left) {
        rotationLeft = left;
    }

    public void goUpwards(){
        mDownwards = false;
        mStationary = false;
        mUpwards = true;

    }

    public void goDownwards(){
        mUpwards = false;
        mStationary = false;
        mDownwards = true;
    }

    public void beStationary(){
        mUpwards = false;
        mDownwards = false;
        mStationary = true;
    }

    public void beVertical(){
        mHorizontal = false;
        mTransient = false;
        mVertical = true;
    }

    public void beHorizontal(){
        mVertical = false;
        mTransient = false;
        mHorizontal = true;
    }

    public void beTransient(){
        mVertical = false;
        mHorizontal = false;
        mTransient = true;
    }

    public boolean getUpwards(){
        return mUpwards;
    }

    public boolean getDownwards(){
        return mDownwards;
    }

    public boolean getVertical(){
        return mVertical;
    }

    public boolean getTransient(){
        return mTransient;
    }
    public boolean getHorizontal(){
        return mHorizontal;
    }

    @Override
    public Color getColour(){
        return colour;
    }

    @Override
    public void setColour(Color c){
        colour = c;
    }

    @Override
    public List<LineSegment> getSides(){
        List<LineSegment> ls = new ArrayList<LineSegment>();
        ls.add(topSideLine);
        ls.add(bottomSideLine);
        ls.add(rightSideLine);
        ls.add(leftSideLine);
        return ls;
    }

    @Override
    public List<Circle> getCorners(){
        List<Circle> cs = new ArrayList<Circle>();
        cs.add(topCircle);
        cs.add(bottomCircle);
        return cs;
    }

    @Override
    public String type(){
        return type;
    }
}
