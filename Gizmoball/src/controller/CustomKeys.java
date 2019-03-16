package controller;

import model.Gizmo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class CustomKeys {

    private static List<CustomKeyListener> keys = new ArrayList<>();
    private static KeyEvent lastKey = null;


    public static void addKey(CustomKeyListener newListener) {
        keys.add(newListener);
    }

    public static List<CustomKeyListener> getKeys() {
        return keys;
    }

    public static CustomKeyListener getKey(int i) {
        return keys.get(i);
    }

    public static int size() {
        return keys.size();
    }

    public static void setLast(KeyEvent key) {
        lastKey = key;
    }

    public static KeyEvent getLastKey() {
        return lastKey;
    }

    public static void remove(Gizmo gizmo) {
        for(CustomKeyListener ke : keys) {
            if (ke.getGizmo().equals(gizmo)) {
                keys.remove(ke);
            }
        }
    }
}
