package controller;

import model.IGizmo;
import view.KeyAlert;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class BuildModeKeyListener implements KeyListener {

    KeyAlert ka = null;
    IGizmo target;
    public static Map<String, Integer> keyConnects = new HashMap<>();

    public BuildModeKeyListener(KeyAlert ka, IGizmo target) {
        this.ka = ka;
        this.target = target;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        CustomKeys.setLast(e);

        try {
            keyConnects.put(target.getId(), (int) e.getKeyChar());
        } catch (NullPointerException ex) {

        }

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
