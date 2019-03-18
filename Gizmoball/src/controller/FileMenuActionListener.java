package controller;

import model.*;
import view.BuildMode;
import view.MainMenu;
import view.RunMode;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileMenuActionListener implements ActionListener {

    private ILoadFile loadfile;
    private ISaveFile savefile;
    private IModel model;
    private BuildMode gui;
    private JFileChooser chooser;

    public FileMenuActionListener(IModel m, BuildMode g) {
        model = m;
        gui = g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Clearing the board
        if(e.getActionCommand().equals("Clear Board")) {
            System.out.println("Clearing board..");
            int gsize = model.getGizmos().size();
            for(int i=0; i<gsize; i++){
                model.deleteGizmo(model.getGizmos().get(0));
            }

            int bsize = model.getBalls().size();
            for(int i=0; i<bsize; i++){
                model.deleteBall(model.getBalls().get(0));
            }

            //Saving the board
        }else if(e.getActionCommand().equals("Save Board")) {
            savefile = new SaveFile(model);
            chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                savefile.save(chooser.getSelectedFile()+".txt");
                System.out.println(chooser.getSelectedFile()+".txt saved successfully");
            }
            //Loading the board
        }else if(e.getActionCommand().equals("Load Board")) {
            loadfile = new LoadFile(model);
            chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files","txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                if (loadfile.load(chooser.getSelectedFile().getName())) {
                    System.out.println(chooser.getSelectedFile().getName()+" loaded successfully");
                }
            }
            for(Frame f: BuildMode.getFrames()) {
                f.dispose();
            }
            gui = new BuildMode(model);
        }
    }
}
