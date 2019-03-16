package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BuildModeKeyListener implements KeyListener {

    KeyEvent key = null;

    public BuildModeKeyListener() {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        CustomKeys.setLast(e);
        System.out.println(CustomKeys.getLastKey().getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
