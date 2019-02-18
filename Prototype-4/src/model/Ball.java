package model;

public class Ball {

    private physics.Vect velocity;
    private model.Coordinate pos;
    private double radius;
    private String id;
    private String type = "Ball";

    public Ball(String id, double x, double y, double xv, double yv){
        this.id = id;
        pos = new model.Coordinate(x, y);
        velocity = new physics.Vect(xv, yv);
        radius = 10;
    }

    public physics.Vect getVelo(){
        return velocity;
    }

    public void setVelo(physics.Vect newV){
        velocity = newV;
    }

    public model.Coordinate getPos(){
        return pos;
    }

    public void setPos(double newX, double newY){
        pos.setX(newX);
        pos.setY(newY);
    }

    public double getRadius(){
        return radius;
    }

    public physics.Circle getCircle(){
        return new physics.Circle(pos.getX(), pos.getY(), radius);
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
