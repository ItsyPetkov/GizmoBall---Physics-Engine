package model;

import physics.Vect;
import physics.Circle;
import java.awt.Color;

public class Ball implements IBall{

    private Vect velo;
    private Vect pos;
    private double radius;
    private Color colour;
    private String type = "Ball";
    private String id;
    private boolean absCaptured;

    public Ball(String id, double x, double y, double xv, double yv){
        this.id = id;
        pos = new Vect(x, y);
        velo = new Vect(xv, yv);
        radius = 0.25;
        colour = Color.BLUE;
        absCaptured = false;
    }

    public Vect getVelo(){
        return velo;
    }

    public void setVelo(double xv, double yv){
        if(xv > 200){
            xv = 200;
        } else if(xv < -200){
            xv = -200;
        } else if(xv < 0.01 && xv > 0 || (xv > -0.01 && xv < 0)){
            xv = 0;
        }

        if(yv > 200){
            yv = 200;
        } else if(yv < -200){
            yv = -200;
        } else if((yv < 0.01 && yv > 0) || (yv > -0.01 && yv < 0)){
            yv = 0;
        }

        velo = new Vect(xv, yv);
    }

    public Vect getPos(){
        return pos;
    }

    public void setPos(double x, double y){
        pos = new Vect(x, y);
    }

    public double getRadius(){
        return radius;
    }

    public Circle getCircle(){
        return new Circle(pos.x(), pos.y(), radius);
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

    public boolean isCaptured(){
        return absCaptured;
    }

    public void capture(){
        absCaptured = true;
    }

    public void release(){
        absCaptured = false;
    }

}
