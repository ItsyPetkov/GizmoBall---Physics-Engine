package model;

import physics.*;

import java.awt.*;
import java.util.Observable;

public class Flipper extends Observable{

    //An enumerated type to keep track of how the flipper is moving
    public enum StateOfMotion {
        UPWARDS, DOWNWARDS, STATIONARY
    }
    //An enumerated type to keep
    public enum Position {
        VERTICAL, HORIZONTAL, TRANSIENT
    }
    //Left flipper is true, right flipper is false
    private boolean leftOrRight;

    //Constituent flipper parts
    private Color color;
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
    private String type = "Flipper";
    private String id = "";
    private int x;
    private int y;
    //Creates a flipper, represented by circles, lines and vectors
    public Flipper(String id, int x, int y, boolean leftOrRight, Color color) {

        this.id = id;
        this.x = x;
        this.y = y;
        //Offset differs depending on left or right flipper
        double offset;
        if (leftOrRight == true){
            offset = 0;

        } else{
            offset = 1.6;
        }
        //90 degrees to rotate through initially
        rotationLeft = Angle.DEG_90;
        rotation = new Angle(0.95);
        this.leftOrRight = leftOrRight;
        this.color=color;

        //Constituent Parts of Flipper
        //centre created with the x and y coordinates
        center = new Vect((double)x, (double)y);

        topCircle = new Circle(center.x() + 0.25 + offset, center.y() + 0.25, 0.25);

        bottomCircle = new Circle(center.x() + 0.25 + offset, center.y() + 1.75, 0.25);

        topSideLine = new LineSegment(center.x() + offset, center.y() + 0.25, center.x() + offset  +0.5, center.y() + 0.25);

        bottomSideLine = new LineSegment(center.x() + offset, center.y() + 1.75, center.x() + offset + 0.5, center.y() + 1.75);

        rightSideLine = new LineSegment(center.x() + offset + 0.5, center.y() + 0.25, center.x() + offset + 0.5, center.y() + 1.75);

        leftSideLine = new LineSegment(center.x() + offset, center.y() + 0.25, center.x() + offset, center.y() + 1.75);




        //Flipper rotates at the top
        centerOfRotation = topCircle.getCenter();
        //Flipper starting position
        movement = StateOfMotion.STATIONARY;
        position = Position.VERTICAL;

    }

    public Angle moveThroughAngle(Angle left) {

        if(movement == movement.UPWARDS){
            if(left.compareTo(rotation) > 0) {
                applyMovement(rotation);
                rotationLeft = left.minus(rotation);
                setPosition(Position.TRANSIENT);
                return rotationLeft;
            } else if(left.compareTo(Angle.DEG_90) == 0) {
                movement = StateOfMotion.STATIONARY;
                rotationLeft = Angle.ZERO;
                setPosition(Position.HORIZONTAL);
                return rotationLeft;
            } else {

                applyMovement(left);
                movement = StateOfMotion.STATIONARY;
                setPosition(Position.HORIZONTAL);
                rotationLeft = Angle.ZERO;
                return rotationLeft;
            }
        }

        if(movement == movement.DOWNWARDS) {
            if(left.compareTo(rotation) > 0) {
                applyMovement(Angle.ZERO.minus(rotation));
                rotationLeft = left.minus(rotation);
                setPosition(Position.TRANSIENT);
                return rotationLeft;
            } else if(left.compareTo(Angle.DEG_90) == 0) {
                movement = StateOfMotion.STATIONARY;
                rotationLeft = Angle.DEG_90;
                setPosition(Position.VERTICAL);
                return Angle.ZERO;
            } else {
                applyMovement(Angle.ZERO.minus(left));
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
        //if its a left flipper adjust angle
        if(leftOrRight == true){
            angle = Angle.ZERO.minus(angle);
        }
        topCircle = Geometry.rotateAround(topCircle, centerOfRotation, angle);
        bottomCircle = Geometry.rotateAround(bottomCircle, centerOfRotation, angle);

        topSideLine = Geometry.rotateAround(topSideLine, centerOfRotation, angle);
        bottomSideLine = Geometry.rotateAround(bottomSideLine, centerOfRotation, angle);
        leftSideLine = Geometry.rotateAround(leftSideLine, centerOfRotation, angle);
        rightSideLine = Geometry.rotateAround(rightSideLine, centerOfRotation, angle);

        setChanged();
        notifyObservers();
    }


    //Getters and Setters
    public Color getColor() {
        return color;
    }

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

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return  y;
    }

}
