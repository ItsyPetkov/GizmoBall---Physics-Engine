package controller;

import model.IGizmo;
import view.KeyAlert;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BuildModeKeyListener implements KeyListener {

    KeyAlert ka = null;
    IGizmo target;

    public BuildModeKeyListener(KeyAlert ka, IGizmo target) {
        this.ka = ka;
        this.target = target;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        CustomKeys.setLast(e);

        ka.close();
        new CustomKeyListener(CustomKeys.getLastKey(), target);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
