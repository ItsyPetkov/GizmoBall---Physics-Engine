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
        // TODO Auto-generated method stub
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

        }else if(e.getActionCommand().equals("Save Board")) {
            savefile = new SaveFile(model);
//			System.out.println("Saving file..");
//			String filename = JOptionPane.showInputDialog("What do you wanna save it as? Don't forget to add .txt at the end!");
//			if (filename != null) {
//				savefile.save(filename);
//				System.out.println(filename + " has been saved.");
//				gui.repaint();
//			}
            chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                savefile.save(chooser.getSelectedFile()+".txt");
                System.out.println(chooser.getSelectedFile()+".txt saved successfully");
            }

        }else if(e.getActionCommand().equals("Load Board")) {
            loadfile = new LoadFile(model);
//			System.out.println("Loading file..");
//			String filename = JOptionPane.showInputDialog("Load File Name?");
//			if (filename != null) {
//				loadfile.load(filename);
//				System.out.println(filename + " has been loaded.");
            chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files","txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                if (loadfile.load(chooser.getSelectedFile().getName())) {
                    System.out.println(chooser.getSelectedFile().getName()+" loaded successfully");
                }
            }
//            gui.repaint();
            for(Frame f: BuildMode.getFrames()) {
                f.dispose();
            }
            gui = new BuildMode(model);
        }
    }
}
