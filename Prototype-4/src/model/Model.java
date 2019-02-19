package model;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

public class Model extends Observable {

    private Ball ball;
    private Walls walls;
    private List<Gizmo> gizmoList;
    private List<Flipper> flippersList;
    List<Ball> ballList = new ArrayList<>();

    public Model(){
        ball = new Ball("B1", 10,10,10,10);
        walls = new Walls(0,0,20,20);
        gizmoList = new ArrayList<Gizmo>();
        flippersList = new ArrayList<>();
    }

    public void moveBall(){
        double moveTime = 0.05;

        if(!(ball.getVelo().x() == 0 && ball.getVelo().y() == 0)) {
            CollisionDetails cd = timeUntilCollision();
            double tuc = cd.getTuc();
            if (tuc > moveTime) {
                ball = moveBallForTime(ball, moveTime);
            } else {
                ball = moveBallForTime(ball, tuc);
                ball.setVelo(cd.getVelo());
            }
        }
    }

    public CollisionDetails timeUntilCollision(){
        physics.Circle ballCircle = ball.getCircle();
        Vect ballVelo = ball.getVelo();


        List<LineSegment> wallSegs = walls.getLineSegments();
        double shortestTime = Geometry.timeUntilWallCollision(wallSegs.get(0), ballCircle, ballVelo);
        Vect newVelo = Geometry.reflectWall(wallSegs.get(0), ballVelo, 1.0);
        double currentTime;

        for(int i=1; i<wallSegs.size(); i++){
            currentTime = Geometry.timeUntilWallCollision(wallSegs.get(i), ballCircle, ballVelo);
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = Geometry.reflectWall(wallSegs.get(i), ballVelo, 1.0);
            }
        }

        // search gizmoList getSides and getCorners
        List<LineSegment> gizmoSides = new ArrayList<LineSegment>();
        List<Circle> gizmoCorners = new ArrayList<Circle>();
        for(int i=0; i<gizmoList.size(); i++){
            gizmoSides.addAll(gizmoList.get(i).getSides());
            gizmoCorners.addAll(gizmoList.get(i).getCorners());
        }

        for(int i=0; i<gizmoSides.size(); i++){
            currentTime = Geometry.timeUntilWallCollision(gizmoSides.get(i), ballCircle, ballVelo);
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = Geometry.reflectWall(gizmoSides.get(i), ballVelo, 1.0);
            }
        }

        for(int i=0; i<gizmoCorners.size(); i++){
            currentTime = Geometry.timeUntilCircleCollision(gizmoCorners.get(i), ballCircle, ballVelo);
            if(shortestTime > currentTime){
                shortestTime = currentTime;
                newVelo = Geometry.reflectCircle(gizmoCorners.get(i).getCenter(), ball.getCircle().getCenter(), ball.getVelo(), 1.0);
            }
        }

        return new CollisionDetails(shortestTime, newVelo);
    }

    private Ball moveBallForTime(Ball ball, double time){
        Vect velo = ball.getVelo();
        Coordinate p = ball.getPos();

        double newX = p.getX() + (velo.x()*time);
        double newY = p.getY() + (velo.y()*time);

        ball.setPos(newX, newY);

        return ball;
    }

    public void addGizmo(Gizmo g){
        gizmoList.add(g);
    }

    public void addFlipper(Flipper f) {
        flippersList.add(f);
    }

    public List<Coordinate> getGizmoPos(){
        List<Coordinate> gizmoPos = new ArrayList<Coordinate>();
        for(int i=0; i<gizmoList.size(); i++){
            gizmoPos.add(gizmoList.get(i).getPos());
        }
        return gizmoPos;
    }
    
     public List<Gizmo> getGizmos(){
    	return gizmoList;
    }

    public List<Flipper> getFlippers() {
        return flippersList;
    }
    
    public Ball getBall(){
        return ball;
    }

    public void saveFile(String filename) {
        List<Gizmo> deletedGizmos = new ArrayList<>();
        List<Gizmo> movedGizmos = new ArrayList<>();
        String[] bumpers = {"Square", "Triangle", "Circle"};

        try {
            Writer wr = new FileWriter(filename);
            for (Gizmo g : gizmoList) {
                for (int i = 0; i < bumpers.length; i++) {
                    if (g.getType().equals(bumpers[i])) {
                        wr.write(g.getType()+ " ");
                        wr.write(g.getId() + " ");
                        wr.write(Integer.toString((int) g.getPos().getX())+ " ");
                        wr.write(Integer.toString((int) g.getPos().getY()));
                        wr.write("\n");
                        break;
                    }
                }
            }
            wr.write("\n");
            for (Flipper f : flippersList) {
                if (f.getType().equals("Flipper")) {
                    wr.write(f.getType() + " ");
                    wr.write(f.getId() + " ");
                    wr.write(f.getX()+ " ");
                    wr.write(f.getY()+ " ");
                    wr.write("\n");
                }
            }
            wr.write("\n");
            for (Ball b : ballList) {
                if (b.getType().equals("Ball")) {
                    wr.write(b.getType()+" ");
                    wr.write(b.getId()+" ");
                    wr.write(Double.toString(b.getPos().getX())+" ");
                    wr.write(Double.toString(b.getPos().getY())+" ");
                    wr.write(Double.toString(b.getVelo().x())+" ");
                    wr.write(Double.toString(b.getVelo().y()));
                    wr.write("\n");
                    break;
                }
            }
            wr.write("\n");
            for (Gizmo g : deletedGizmos) {
                wr.write("Delete ");
                wr.write(g.getId());
                wr.write("\n");
                break;
            }
            wr.write("\n");
            for (Gizmo g : movedGizmos) {
                wr.write("Move ");
                wr.write(g.getId());
                wr.write(Double.toString(g.getPos().getX())+" ");
                wr.write(Double.toString(g.getPos().getY()));
                wr.write("\n");
            }
            wr.write("\n");

            wr.flush();
            wr.close();

        } catch(IOException e) {
            System.out.println("Failed to write to file gizmo.txt");
        }
    }

    public void loadFile(String fileName) {
        String[] bumpers = {"Square", "Triangle", "Circle", "Flipper"};
        List<String[]> bumperCommands = new ArrayList<>();
        List<String[]> rotateCommands = new ArrayList<>();
        List<String[]> keyConnectCommands = new ArrayList<>();
        List<String[]> connectCommands = new ArrayList<>();
        List<String[]> ballCommands = new ArrayList<>();
        List<String[]> moveCommands = new ArrayList<>();
        List<String[]> deleteCommands = new ArrayList<>();
        try {
            Scanner reader = new Scanner(new File(fileName));
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String temp[] = line.split(" ");
                //Bumper Command
                for (int i = 0; i < bumpers.length; i++) {
                    if (line.startsWith(bumpers[i])) {
                        bumperCommands.add(temp);
                        break;
                    }
                }
                //Rotate Command
                if (line.startsWith("Rotate")) {
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
            createBumpers(bumperCommands);
//            keyConnectGizmos(keyConnectCommands);
//            connectGizmos(connectCommands);
//            deleteGizmos(deleteCommands);
//            rotateGizmos(rotateCommands);
            createBall(ballCommands);
//            moveGizmos(moveCommands);
        } catch (FileNotFoundException e) {
            System.out.println("Error 404: File not found.");
        }
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
            switch (shape) {
                case "Triangle":
                    gizmoList.add(new TriangleBumper(id, posX, posY));
                    break;
                case "Square":
                    gizmoList.add(new SquareBumper(id, posX, posY));
                    break;
                case "Circle":
                    gizmoList.add(new CircleBumper(id, posX, posY));
                    break;
                case "Flipper":
                    flippersList.add(new Flipper(id, posX, posY, true, Color.RED));
                    break;
                case "RightFlipper":
                    //gizmoList.add(new RightFlipper(id, posX, posY));
                    break;
                default:
                    System.out.println("Shape unrecognized.");
                    break;
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

    public void deleteGizmos(List<String[]> commands) {
        String id;
        for (int i = 0; i < commands.size(); i++) {
            id = commands.get(i)[1];
            if (commands.get(i)[0].equals("Delete")) {
                for (Gizmo g : gizmoList) {
                    if (g.getId().equals(id)) {
                        g.delete();
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
                for (Gizmo g : gizmoList) {
                    if (!(g.getId().equals(id))) {
                        ballList.add(new Ball(id, x, y, velX, velY));
                    }
                }
            }
        }
    }

    public void moveGizmos(List<String[]> commands) {
        String id;
        double x = 0, y = 0;
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i)[0].equals("Move")) {
                id = commands.get(i)[1];
                x = Double.parseDouble(commands.get(i)[2]);
                y = Double.parseDouble(commands.get(i)[3]);
                for (Gizmo g : gizmoList) {
                    if (g.getId().equals(id)) {
                        g.move(x, y);
                    }
                }
            }
        }
    }
}