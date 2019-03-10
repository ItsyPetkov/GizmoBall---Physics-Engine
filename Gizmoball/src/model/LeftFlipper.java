package model;
import physics.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LeftFlipper extends Gizmo {
    private String type = "LeftFlipper";
    //An enumerated type to keep track of how the flipper is moving
    public enum StateOfMotion {
        UPWARDS, DOWNWARDS, STATIONARY
    }
    //An enumerated type to keep track of the flippers position
    public enum Position {
        VERTICAL, HORIZONTAL, TRANSIENT
    }


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

    private StateOfMotion movement;
    private Position position;

    //Creates a flipper, represented by circles, lines and vectors
    public LeftFlipper(String id,int x, int y) {
        super(id,x,y);

        //centre created with the x and y coordinates
        center = new Vect((double)x, (double)y);

        //90 degrees to rotate through initially
        rotationLeft = Angle.DEG_90;
        rotation = new Angle(1);
        this.colour=colour.BLACK;

        //Constituent Parts of Flipper
        topSideLine = new LineSegment(center.x() , center.y() + 0.25, center.x() + 0.5, center.y() + 0.25);
        bottomSideLine = new LineSegment(center.x() , center.y() + 1.75, center.x() +  0.5, center.y() + 1.75);
        rightSideLine = new LineSegment(center.x() + 0.5, center.y() + 0.25, center.x() + 0.5, center.y() + 1.75);
        leftSideLine = new LineSegment(center.x() , center.y() + 0.25, center.x() , center.y() + 1.75);

        topCircle = new Circle(center.x()  +0.25, center.y() + 0.25, 0.25);
        bottomCircle = new Circle(center.x() +  0.25, center.y() + 1.75, 0.25);

        //Flipper rotates at the top
        centerOfRotation = topCircle.getCenter();
        //Flipper starting position
        movement = StateOfMotion.STATIONARY;
        position = Position.VERTICAL;

    }
    //where the actual movement happens
    public Angle moveThroughAngle(Angle leftToGo) {

        if(movement == movement.UPWARDS){
            if(leftToGo.compareTo(rotation) > 0) {
                applyMovement(rotation);
                rotationLeft = leftToGo.minus(rotation);
                setPosition(Position.TRANSIENT);
            } else if(leftToGo.compareTo(Angle.DEG_90) == 0) {
                movement = StateOfMotion.STATIONARY;
                rotationLeft = Angle.ZERO;
                setPosition(Position.HORIZONTAL);
            } else {
                applyMovement(leftToGo);
                movement = StateOfMotion.STATIONARY;
                setPosition(Position.HORIZONTAL);
                rotationLeft = Angle.ZERO;

            }
            return rotationLeft;
        }

        if(movement == movement.DOWNWARDS) {
            if(leftToGo.compareTo(rotation) > 0) {
                applyMovement(Angle.ZERO.minus(rotation));
                rotationLeft = leftToGo.minus(rotation);
                setPosition(Position.TRANSIENT);
                return rotationLeft;
            } else if(leftToGo.compareTo(Angle.DEG_90) == 0) {
                movement = StateOfMotion.STATIONARY;
                rotationLeft = Angle.DEG_90;
                setPosition(Position.VERTICAL);
                return Angle.ZERO;
            } else {
                applyMovement(Angle.ZERO.minus(leftToGo));
                movement = StateOfMotion.STATIONARY;
                rotationLeft = Angle.DEG_90;
                setPosition(Position.VERTICAL);
                return Angle.ZERO;
            }
        } else

            return Angle.ZERO;

    }
    //Method for visually changing the flipper
    public void applyMovement(Angle angle) {



        topCircle = Geometry.rotateAround(topCircle, centerOfRotation, angle);
        bottomCircle = Geometry.rotateAround(bottomCircle, centerOfRotation, angle);

        topSideLine = Geometry.rotateAround(topSideLine, centerOfRotation, angle);
        bottomSideLine = Geometry.rotateAround(bottomSideLine, centerOfRotation, angle);
        leftSideLine = Geometry.rotateAround(leftSideLine, centerOfRotation, angle);
        rightSideLine = Geometry.rotateAround(rightSideLine, centerOfRotation, angle);

        //setChanged();
        //notifyObservers();
    }


    @Override
    public String type() {
        return "leftflipper";
    }

    //Getters and Setters
    public Color getColour() {
        return colour;
    }

    @Override
    public void setColour(Color c) {
        colour = c;
    }

    public Angle getRotationLeft() {
        return rotationLeft;
    }

    public void setRotationLeft(Angle left) {
        rotationLeft = left;
    }

    public StateOfMotion getMovement() {
        return movement;
    }

    public void setMovement(StateOfMotion movement) {
        this.movement = movement;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /*

    public Circle getTopCircle(){
        return topCircle;
    }

    public Circle getBottomCircle(){
        return bottomCircle;
    }

    public LineSegment getTopSideLine(){
        return topSideLine;

    }

    public LineSegment getRightSideLine(){

        return rightSideLine;
    }

    public LineSegment getBottomSideLine(){
        return bottomSideLine;
    }

    public LineSegment getLeftSideLine(){
        return leftSideLine;
    }

    */

    public List<LineSegment> getSides(){
        List<LineSegment> ls = new ArrayList<LineSegment>();
        ls.add(topSideLine);
        ls.add(bottomSideLine);
        ls.add(rightSideLine);
        ls.add(leftSideLine);
        return ls;
    }
    public List<Circle> getCorners(){
        List<Circle> cs = new ArrayList<Circle>();
        cs.add(topCircle);
        cs.add(bottomCircle);
        return cs;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void rotate() {

    }

}