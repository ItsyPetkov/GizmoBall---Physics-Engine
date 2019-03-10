package controller;

import model.Model;
import model.SaveFile;
import view.BuildMode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveFileButtonListener implements ActionListener {
    private SaveFile savefile;
    private Model model;
    private BuildMode gui;

    public SaveFileButtonListener(Model m, BuildMode g) {
        model = m;
        gui = g;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        savefile = new SaveFile(model);
        System.out.println("Saving file..");
        String filename = JOptionPane.showInputDialog("What do you wanna save it as? Don't forget to add .txt at the end!");
        if (filename != null) {
            savefile.save(filename);
            System.out.println(filename + " has been saved.");
            gui.repaint();
        }
    }
}
