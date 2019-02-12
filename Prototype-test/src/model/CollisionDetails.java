package model;

import physics.Vect;

public class CollisionDetails {

    private double tuc;
    private Vect velo;

    public CollisionDetails(double tuc, Vect velo){
        this.tuc = tuc;
        this.velo = velo;
    }

    public double getTuc(){
        return tuc;
    }

    public Vect getVelo(){
        return velo;
    }

}
