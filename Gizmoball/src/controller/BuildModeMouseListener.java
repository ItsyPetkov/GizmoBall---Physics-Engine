package controller;

import model.*;
import view.IKeyAlert;
import view.KeyAlert;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.security.Key;

public class BuildModeMouseListener implements MouseInputListener {

    IModel model;
    IGizmo target;
    IBall targetBall;
    int LtoPx;
    String action;
    Character key;

    public BuildModeMouseListener(IModel m, String action, int LtoPx){
        this.model = m;
        this.LtoPx = LtoPx;
        this.action = action;
        key = '\0';
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
                } else {
                    targetBall = model.ballSearch(e.getX()/LtoPx, e.getY()/LtoPx);
                    if (!(targetBall == null)) {
                        model.deleteBall(targetBall);
                    }
                }
                break;
            case "Rotate":
                target = model.gizmoSearch(e.getX()/LtoPx, e.getY()/LtoPx);
                if(!(target == null)){
                    model.rotateGizmo(target);
                    target = null;
                }
                break;
            case "Connect":
                if(target == null){
                    target = model.gizmoSearch(e.getX()/LtoPx, e.getY()/LtoPx);
                } else {
                    model.setGizmoConnection(target, model.gizmoSearch(e.getX()/LtoPx, e.getY()/LtoPx));
                }
                break;
            case "Disconnect":
                if(target == null){
                    target = model.gizmoSearch(e.getX()/LtoPx, e.getY()/LtoPx);
                } else {
                    model.setGizmoConnection(target, model.gizmoSearch(e.getX()/LtoPx, e.getY()/LtoPx));
                }
                break;
            case "Key Connect":
                target = model.gizmoSearch(e.getX()/LtoPx, e.getY()/LtoPx);
                IKeyAlert ka = new KeyAlert(target);
                break;
            case "Key Disconnect":
                target = model.gizmoSearch(e.getX()/LtoPx, e.getY()/LtoPx);
                CustomKeys.remove(target);
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
