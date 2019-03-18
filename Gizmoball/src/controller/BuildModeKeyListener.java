package controller;

import view.KeyAlert;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BuildModeKeyListener implements KeyListener {

    KeyEvent key = null;
    KeyAlert ka = null;

    public BuildModeKeyListener(KeyAlert ka) {
        this.ka = ka;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        CustomKeys.setLast(e);
        System.out.println(CustomKeys.getLastKey().getKeyChar());
        ka.setKey(e.getKeyChar());
        ka.close();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
