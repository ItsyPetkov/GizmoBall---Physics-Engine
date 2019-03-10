package model;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadFile {
    Model model;
    private List<Gizmo> gizmoList = new ArrayList<>();
    private List<Ball> ballList = new ArrayList<>();
    private List<LeftFlipper> leftFlipperList = new ArrayList<>();
    private List<RightFlipper> rightFlipperList = new ArrayList<>();

    public LoadFile(Model m) {
        model = m;
    }

    public void load(String filename) {
        String[] bumpers = {"Square","Triangle","Circle","LeftFlipper","RightFlipper"};
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
                //Rotate Commands
                if(line.startsWith("Rotate")) {
                    rotateCommands.add(temp);
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
        }
        createBumpers(bumperCommands);
        createBall(ballCommands);
        moveGizmos(moveCommands);
        deleteGizmos(deleteCommands);
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
                    gizmoList.add(new TriangleBumper(id, posX, posY));
                    break;
                case "Square":
                    gizmoList.add(new SquareBumper(id, posX, posY));
                    break;
                case "Circle":
                    gizmoList.add(new CircleBumper(id, posX, posY));
                    break;
                case "LeftFlipper":
                    leftFlipperList.add(new LeftFlipper(id, posX, posY));
                    break;
                case "RightFlipper":
                    rightFlipperList.add(new RightFlipper(id, posX, posY));
                    break;
                default:
                    System.out.println("Shape unrecognized.");
                    break;
            }
        }
        System.out.println(gizmoList);
        model.setGizmos(gizmoList);
        model.setRightFlippers(rightFlipperList);
        model.setLeftFlippers(leftFlipperList);
    }
    public void moveGizmos(List<String[]> commands) {
        String id;
        int x = 0, y = 0;
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i)[0].equals("Move")) {
                id = commands.get(i)[1];
                x = Integer.parseInt(commands.get(i)[2]);
                y = Integer.parseInt(commands.get(i)[3]);
                for (Gizmo g : gizmoList) {
                    if (g.getId().equals(id)) {
                        g.move(x, y);
                    }
                }
            }
        }
    }
    public void createBall(List<String[]> commands) {
        String id = "";
        double x = 0, y = 0, velX = 0, velY = 0;

        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i)[0].equals("Ball")) {
                id = commands.get(i)[1];
                x = Double.parseDouble(commands.get(i)[2]);
                y = Double.parseDouble(commands.get(i)[3]);
                velX = Double.parseDouble(commands.get(i)[4]);
                velY = Double.parseDouble(commands.get(i)[5]);
                ballList.add(new Ball(x, y, velX, velY));

            }
        }
        model.setBallList(ballList);
    }
    public void deleteGizmos(List<String[]> commands) {
        String id;
        List<Gizmo> toRemove = new ArrayList<>();
        for (int i = 0; i < commands.size(); i++) {
            id = commands.get(i)[1];
            if (commands.get(i)[0].equals("Delete")) {
                for (Gizmo g : gizmoList) {
                    if (g.getId().equals(id)) {
                        if (gizmoList.contains(g)) {
                            toRemove.add(g);
                        }
                    }
                }
                for (int j = 0; j < toRemove.size(); j++) {
                    gizmoList.remove(toRemove.get(j));
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
                for (Gizmo g : gizmoList) {
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
                for (Gizmo g : gizmoList) {
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
