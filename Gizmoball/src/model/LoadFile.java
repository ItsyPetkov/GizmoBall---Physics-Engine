package model;

import view.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadFile implements ILoadFile{
    IModel model;

    public LoadFile(IModel m) {
        model = m;
    }

    public boolean load(String filename) {
        String[] bumpers = {"Square","Triangle","Circle","LeftFlipper","RightFlipper", "Absorber"};
        List<String[]> bumperCommands = new ArrayList<>();
        List<String[]> rotateCommands = new ArrayList<>();
        List<String[]> keyConnectCommands = new ArrayList<>();
        List<String[]> connectCommands = new ArrayList<>();
        List<String[]> ballCommands = new ArrayList<>();
        List<String[]> moveCommands = new ArrayList<>();
        List<String[]> deleteCommands = new ArrayList<>();

        try {
            Scanner reader = new Scanner(new File(filename));
            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                String temp[] = line.split(" ");

                //Bumper Commands
                for (int i = 0; i < bumpers.length; i++) {
                    if (line.startsWith(bumpers[i])) {
                        bumperCommands.add(temp);
                    }
                }
                //Rotate Command
                if(line.startsWith("Rotate")) {
                    rotateCommands.add(temp);
                    //KeyConnect Command
                } else if (line.startsWith("KeyConnect")) {
                    keyConnectCommands.add(temp);
                    //Connect Command
                } else if (line.startsWith("Connect")) {
                    connectCommands.add(temp);
                    //Ball Command
                } else if (line.startsWith("Ball")) {
                    ballCommands.add(temp);
                    //Delete Command
                } else if (line.startsWith("Delete")) {
                    deleteCommands.add(temp);
                    //Move Command
                } else if (line.startsWith("Move")) {
                    moveCommands.add(temp);
                    //Gravity command
                } else if (line.startsWith("Gravity")) {
                    model.setGravity(Double.parseDouble(temp[1]));
                    //Friction command
                } else if (line.startsWith("Friction")) {
                    double xf = Double.parseDouble(temp[1]);
                    double yf = Double.parseDouble(temp[2]);
                    model.setFriction(xf, yf);
                }
            }
        } catch(FileNotFoundException ex) {
            System.out.println("File "+filename+" failed to load.");
            JOptionPane.showMessageDialog(null,
                    filename+" does not exist.",
                    "Error 404 - File not found",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        createBumpers(bumperCommands);
        rotateGizmos(rotateCommands);
        createBall(ballCommands);
        moveGizmos(moveCommands);
        deleteGizmos(deleteCommands);
        connectGizmos(connectCommands);
        return true;
    }

    private void createBumpers(List<String[]> commands) {
        String shape, id;
        int posX, posY;

        for (int i = 0; i < commands.size(); i++) {
            String[] comd = commands.get(i);
            shape = comd[0];
            id = comd[1];
            posX = Integer.parseInt(comd[2]);
            posY = Integer.parseInt(comd[3]);
            if(shape.equals("Absorber")){
                double x2 = Double.parseDouble(commands.get(i)[4]);
                double y2 = Double.parseDouble(commands.get(i)[5]);
                model.addAbsorber(id, posX, posY, (int) x2, (int) y2);
            } else {
                model.addGizmo(shape, id, posX, posY);
            }
        }
    }

    private void moveGizmos(List<String[]> commands) {
        String id;
        int x, y;
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i)[0].equals("Move")) {
                id = commands.get(i)[1];
                x = Integer.parseInt(commands.get(i)[2]);
                y = Integer.parseInt(commands.get(i)[3]);
                for (IGizmo g : model.getGizmos()) {
                    if (g.getId().equals(id)) {
                        g.setPos(x, y);
                    }
                }
            }
        }
    }
    private void createBall(List<String[]> commands) {
        String id = "";
        double x, y, velX, velY;

        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i)[0].equals("Ball")) {
                id = commands.get(i)[1];
                x = Double.parseDouble(commands.get(i)[2]);
                y = Double.parseDouble(commands.get(i)[3]);
                velX = Double.parseDouble(commands.get(i)[4]);
                velY = Double.parseDouble(commands.get(i)[5]);
                model.addBall(id, x, y, velX, velY);

            }
        }
    }

    private void deleteGizmos(List<String[]> commands) {
        String id;
        List<IGizmo> toRemove = new ArrayList<>();
        for (int i = 0; i < commands.size(); i++) {
            id = commands.get(i)[1];
            if (commands.get(i)[0].equals("Delete")) {
                for (IGizmo g : model.getGizmos()) {
                    if (g.getId().equals(id)) {
                        if (model.getGizmos().contains(g)) {
                            toRemove.add(g);
                        }
                    }
                }
                for (int j = 0; j < toRemove.size(); j++) {
                    model.getGizmos().remove(toRemove.get(j));
                }
            }
        }
    }
    private void keyConnectGizmos(List<String[]> commands) {
        String[] comd = commands.get(0);
        System.out.println(comd[0]);
        if (comd[0].equals("KeyConnect")) {
            System.out.println(comd[1]);
            System.out.println(comd[2]);
            System.out.println(comd[3]);
        }
    }

    private void rotateGizmos(List<String[]> commands) {
        String id;
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i)[0].equals("Rotate")) {
                id = commands.get(i)[1];
                for (IGizmo g : model.getGizmos()) {
                    if (g.getId().equals(id)) {

                        g.rotate();
                    }
                }
            }
        }
    }

    private void connectGizmos(List<String[]> commands) {
        String id1, id2;
        IGizmo g1 = null, g2 = null;

        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i)[0].equals("Connect")) {
                id1 = commands.get(i)[1];
                id2 = commands.get(i)[2];
                for (IGizmo g : model.getGizmos()) {
                    if (g.getId().equals(id1)) {
                        g1 = g;
                    }
                    if (g.getId().equals(id2)) {
                        g2 = g;
                    }
                }
                if (g1 != null && g2 != null) {
                    g1.addConnection(g2);
                }
            }
        }
    }
}
