package controller;

import model.Gizmo;
import model.Model;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class BuildModeMouseListener implements MouseInputListener {

    Model model;
    Gizmo target;
    int LtoPx;
    String action;

    public BuildModeMouseListener(Model m, String action, int LtoPx){
        this.model = m;
        this.LtoPx = LtoPx;
        this.action = action;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(action){
            case "Move":
                if(target == null){
                    target = model.gizmoSearch(e.getX()/LtoPx, e.getY()/LtoPx);
                } else {
                    model.moveGizmo(target, (e.getX() / LtoPx), (e.getY() / LtoPx));
                    target = null;
                }
                break;
            case "Delete":
                target = model.gizmoSearch(e.getX()/LtoPx, e.getY()/LtoPx);
                if(!(target == null)){
                    model.deleteGizmo(target);
                    target = null;
                }
                break;
            case "Rotate":
                target = model.gizmoSearch(e.getX()/LtoPx, e.getY()/LtoPx);
                if(!(target == null)){
                    model.rotateGizmo(target);
                    target = null;
                }
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (action) {
            case "Move":
                if (target == null) {
                    target = model.gizmoSearch(e.getX() / LtoPx, e.getY() / LtoPx);
                } else {
                    model.moveGizmo(target, (e.getX() / LtoPx), (e.getY() / LtoPx));
                    target = null;
                }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
