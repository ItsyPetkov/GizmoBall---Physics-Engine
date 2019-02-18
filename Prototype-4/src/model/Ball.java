package model;

import physics.Circle;
import physics.Vect;

import java.awt.*;

public class Ball {

    private Vect velocity;
    private Coordinate pos;
    private double radius;
    private Color colour;
    private String id;
    private String type = "Ball";

    public Ball(String id, double x, double y, double xv, double yv){
        this.id = id;
        pos = new Coordinate(x, y);
        velocity = new Vect(xv, yv);
        radius = 0.5;
        colour = Color.BLUE;
    }

    public Vect getVelo(){
        return velocity;
    }

    public void setVelo(Vect newV){
        velocity = newV;
    }

    public Coordinate getPos(){
        return pos;
    }

    public void setPos(double newX, double newY){
        pos.setX(newX);
        pos.setY(newY);
    }

    public double getRadius(){
        return radius;
    }

    public Circle getCircle(){
        return new Circle(pos.getX(), pos.getY(), radius);
    }

    public Color getColour(){
        return colour;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

}
