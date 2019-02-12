package model;

import physics.Vect;
import physics.Circle;

public class Ball {

    private Vect velocity;
    private Coordinate pos;
    private double radius;

    public Ball(double x, double y, double xv, double yv){
        pos = new Coordinate(x, y);
        velocity = new Vect(xv, yv);
        radius = 10;
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

}
