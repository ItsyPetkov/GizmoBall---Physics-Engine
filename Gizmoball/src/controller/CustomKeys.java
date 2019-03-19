package controller;

import model.IGizmo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class CustomKeys {

    private static List<CustomKeyListener> keys = new ArrayList<>();
    private static char lastKey = '\0';


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
        lastKey = key.getKeyChar();
    }

    public static char getLastKey() {
        return lastKey;
    }

    public static void remove(IGizmo gizmo) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).getGizmo().equals(gizmo)) {
                keys.remove(i);
            }
        }
    }
}
