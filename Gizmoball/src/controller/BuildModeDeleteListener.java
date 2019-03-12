package controller;

import model.Gizmo;
import model.Model;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class BuildModeDeleteListener implements MouseListener {

    Model model;
    Gizmo target;
    int LtoPx;

    public BuildModeDeleteListener(Model m, int LtoPx){
        this.model = m;
        this.LtoPx = LtoPx;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        target = model.gizmoSearch(e.getX()/LtoPx, e.getY()/LtoPx);
        if(!(target == null)){
            model.deleteGizmo(target);
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
