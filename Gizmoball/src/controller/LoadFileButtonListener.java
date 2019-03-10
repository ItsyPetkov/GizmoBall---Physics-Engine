package controller;

import model.LoadFile;
import model.Model;
import view.BuildMode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadFileButtonListener implements ActionListener {
    private LoadFile loadfile;
    private BuildMode gui;
    private Model model;

    public LoadFileButtonListener(Model m, BuildMode g) {
        model = m;
        gui = g;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        loadfile = new LoadFile(model);
        System.out.println("Loading file..");
        String filename = JOptionPane.showInputDialog("Load File Name?");
        if (filename != null) {
            loadfile.load(filename);
            System.out.println(filename + " has been loaded.");
            gui.repaint();
        }
    }
}
