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

    private int sx, sy, ex, ey; //used for absorber

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
            case "Ball":
                model.addBall(new Ball(id, e.getX()/LtoPx, e.getY()/LtoPx, 0,0));
                break;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(type.equals("Absorber")){
            sx = e.getX();
            sy = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(type.equals("Absorber")){
            ex = e.getX();
            ey = e.getY();
            int x1, x2, y1, y2;
            x1 = Math.min(sx, ex);
            x2 = Math.max(sx, ex);
            y1 = Math.min(sy, ey);
            y2 = Math.max(sy, ey);
            model.addGizmo(new Absorber(id, x1/LtoPx, y1/LtoPx, x2/LtoPx + 1, y2/LtoPx + 1));
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

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
