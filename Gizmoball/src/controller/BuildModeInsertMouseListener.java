package controller;

import model.*;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BuildModeInsertMouseListener implements MouseInputListener {

    Model model;
    int LtoPx;
    String type;
    String id;

    public BuildModeInsertMouseListener(Model m, int LtoPx, String type, String id){
        this.model = m;
        this.LtoPx = LtoPx;
        this.type= type;
        this.id = id;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(type);
        switch(type){
            case "Square":
                model.addGizmo(new SquareBumper(id, e.getX()/LtoPx, e.getY()/LtoPx));
                break;
            case "Circle":
                model.addGizmo(new CircleBumper(id, e.getX()/LtoPx, e.getY()/LtoPx));
                break;
            case "Triangle":
                model.addGizmo(new TriangleBumper(id, e.getX()/LtoPx, e.getY()/LtoPx));
                break;
            case "LeftFlipper":
                model.addGizmo(new LeftFlipper(id, e.getX()/LtoPx, e.getY()/LtoPx));
                break;
            case "RightFlipper":
                model.addGizmo(new RightFlipper(id, e.getX()/LtoPx, e.getY()/LtoPx));
                break;
            case "Absorber":
                System.out.println("add abs");
                break;
            case "Ball":
                model.addBall(new Ball(id, e.getX()/LtoPx, e.getY()/LtoPx, 0,0));
                break;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(type.equals("Absorber")){

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(type.equals("Absorber")){

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(type.equals("Absorber")){

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
