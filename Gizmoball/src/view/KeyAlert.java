package view;

import controller.BuildModeKeyListener;
import controller.CustomKeys;
import model.IGizmo;

import javax.swing.*;
import java.awt.*;
import java.security.Key;

public class KeyAlert {
    Character key = '\0';
    JFrame frame;
    JDialog jd;
    final Object sync = new Object();


    public KeyAlert(IGizmo target) {
        frame = new JFrame();

        jd = new JDialog(frame, "Key Select");
        jd.setLayout(new FlowLayout());
        JLabel instr = new JLabel("Please press the key you wish to activate the gizmo with.");
        jd.add(instr);

        jd.setSize(400, 100);

        BuildModeKeyListener bmkl = new BuildModeKeyListener(this, target);
        jd.addKeyListener(bmkl);
        jd.setVisible(true);

    }


    public void setKey(char key) {
        this.key = key;
    }

    public char getKey() {
        return key;
    }

    public void close() {
        jd.setVisible(false);
    }

}