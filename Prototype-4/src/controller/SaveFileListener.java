package controller;

import model.Model;
import view.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveFileListener implements ActionListener {
    private Model model;
    private GUI gui;

    public SaveFileListener(Model m, GUI g) {
        model = m;
        gui = g;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        model.saveFile("savedGizmos.txt");
    }
}
