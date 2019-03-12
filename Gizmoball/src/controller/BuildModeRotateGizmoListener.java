package controller;

import model.Gizmo;
import model.Model;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class BuildModeRotateGizmoListener implements MouseListener {

    Model model;
    Gizmo target;
    int LtoPx;

    public BuildModeRotateGizmoListener(Model m, int LtoPx){
        this.model = m;
        this.LtoPx = LtoPx;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        List<Gizmo> gizmoList = model.getGizmos();
        for(int i=0; i<gizmoList.size(); i++){
            if(gizmoList.get(i).getPos().x() == (e.getX()/LtoPx) && gizmoList.get(i).getPos().y() == (e.getY()/LtoPx)){
                target = gizmoList.get(i);
            }
        }
        if(!(target == null)){
            model.rotateGizmo(target);
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
