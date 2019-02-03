package Model;
import physics.Vect;
import physics.Circle;

public class ball {

    Vect velocity;
    double xpos;
    double ypos;
    double radius;

    public ball(double x, double y, double xv, double yv){
        xpos = x;
        ypos = y;
        velocity = new Vect(xv, yv);
        radius = 10;
    }

    public Vect getVelo(){
        return velocity;
    }

    public void setVelo(Vect newV){
        velocity = newV;
    }

    public double getRadius(){
        return radius;
    }

    public Circle getCircle(){
        return new Circle(xpos, ypos, radius);
    }

}