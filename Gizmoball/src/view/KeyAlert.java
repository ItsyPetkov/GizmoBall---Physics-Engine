package view;

import controller.BuildModeKeyListener;

import javax.swing.*;
import java.awt.*;

public class KeyAlert {
    char key = '\0';
    JDialog jd;

    public KeyAlert() {

        JFrame frame = new JFrame();

        jd = new JDialog(frame, "Key Select");
        jd.setLayout(new FlowLayout());
        JLabel instr = new JLabel("Please press the key you wish to activate the gizmo with.");
        jd.add(instr);

        jd.setSize(400,100);
        jd.addKeyListener(new BuildModeKeyListener(this));
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
