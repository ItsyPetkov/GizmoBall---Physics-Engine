package model;

import view.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadFile {
    Model model;

    public LoadFile(Model m) {
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
                }
            }
        } catch(FileNotFoundException ex) {
            System.out.println("File "+filename+" failed to load.");
            JOptionPane.showMessageDialog(null,
                    filename+" does not exist.",
                    "Error 404 - File not found",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        createBumpers(bumperCommands);
        rotateGizmos(rotateCommands);
        createBall(ballCommands);
        moveGizmos(moveCommands);
        deleteGizmos(deleteCommands);
        return true;
    }

    public void createBumpers(List<String[]> commands) {
        String shape, id;
        int posX, posY;

        for (int i = 0; i < commands.size(); i++) {
            String[] comd = commands.get(i);
            shape = comd[0];
            id = comd[1];
            posX = Integer.parseInt(comd[2]);
            posY = Integer.parseInt(comd[3]);
            switch(shape) {
                case "Triangle":
                    model.addGizmo(new TriangleBumper(id, posX, posY));
                    break;
                case "Square":
                    model.addGizmo(new SquareBumper(id, posX, posY));
                    break;
                case "Circle":
                    model.addGizmo(new CircleBumper(id, posX, posY));
                    break;
                case "LeftFlipper":
                    model.addGizmo(new LeftFlipper(id, posX, posY));
                    break;
                case "RightFlipper":
                    model.addGizmo(new RightFlipper(id, posX, posY));
                    break;
                case "Absorber":
                    double x2 = Double.parseDouble(commands.get(i)[4]);
                    double y2 = Double.parseDouble(commands.get(i)[5]);
                    model.addGizmo(new Absorber(id, posX, posY, x2, y2));
                    break;
                default:
                    System.out.println("Shape unrecognized.");
                    break;
            }
        }
    }

    public void moveGizmos(List<String[]> commands) {
        String id;
        int x, y;
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i)[0].equals("Move")) {
                id = commands.get(i)[1];
                x = Integer.parseInt(commands.get(i)[2]);
                y = Integer.parseInt(commands.get(i)[3]);
                for (Gizmo g : model.getGizmos()) {
                    if (g.getId().equals(id)) {
                        g.move(x, y);
                    }
                }
            }
        }
    }
    public void createBall(List<String[]> commands) {
        String id = "";
        double x, y, velX, velY;

        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i)[0].equals("Ball")) {
                id = commands.get(i)[1];
                x = Double.parseDouble(commands.get(i)[2]);
                y = Double.parseDouble(commands.get(i)[3]);
                velX = Double.parseDouble(commands.get(i)[4]);
                velY = Double.parseDouble(commands.get(i)[5]);
                model.addBall(new Ball(id, x, y, velX, velY));

            }
        }
    }

    public void deleteGizmos(List<String[]> commands) {
        String id;
        List<Gizmo> toRemove = new ArrayList<>();
        for (int i = 0; i < commands.size(); i++) {
            id = commands.get(i)[1];
            if (commands.get(i)[0].equals("Delete")) {
                for (Gizmo g : model.getGizmos()) {
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
    public void keyConnectGizmos(List<String[]> commands) {
        String[] comd = commands.get(0);
        System.out.println(comd[0]);
        if (comd[0].equals("KeyConnect")) {
            System.out.println(comd[1]);
            System.out.println(comd[2]);
            System.out.println(comd[3]);
        }
    }

    public void rotateGizmos(List<String[]> commands) {
        String id;
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i)[0].equals("Rotate")) {
                id = commands.get(i)[1];
                System.out.println(id);
                for (Gizmo g : model.getGizmos()) {
                    if (g.getId().equals(id)) {

                        g.rotate();
                    }
                }
            }
        }
    }

    public void connectGizmos(List<String[]> commands) {
        String id1, id2;
        Gizmo g1 = null, g2 = null;

        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i)[0].equals("Connect")) {
                id1 = commands.get(i)[1];
                id2 = commands.get(i)[2];
                for (Gizmo g : model.getGizmos()) {
                    if (g.getId().equals(id1)) {
                        g1 = g;
                    }
                    if (g.getId().equals(id2)) {
                        g2 = g;
                    }
                }
                if (g1 != null && g2 != null) {
                    System.out.println("Gizmo 1 id: " + g1.getId());
                    System.out.println("Gizmo 2 id: " + g2.getId());
                }
            }
        }
    }
}
