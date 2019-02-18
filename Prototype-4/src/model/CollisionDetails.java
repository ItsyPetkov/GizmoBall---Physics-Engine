package model;

public class CollisionDetails {

    private double tuc;
    private physics.Vect velo;

    public CollisionDetails(double tuc, physics.Vect velo){
        this.tuc = tuc;
        this.velo = velo;
    }

    public double getTuc(){
        return tuc;
    }

    public physics.Vect getVelo(){
        return velo;
    }

}
