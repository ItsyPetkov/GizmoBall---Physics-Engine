package controller;

import model.Gizmo;
import model.Model;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class BuildModeMoveGizmoListener implements MouseListener {

    Model model;
    Gizmo target;
    int LtoPx;

    public BuildModeMoveGizmoListener(Model m, int LtoPx){
        this.model = m;
        this.LtoPx = LtoPx;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(target == null){
            target = model.gizmoSearch(e.getX()/LtoPx, e.getY()/LtoPx);
        } else {
            model.moveGizmo(target, (e.getX() / LtoPx), (e.getY() / LtoPx));
            target = null;
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
}
