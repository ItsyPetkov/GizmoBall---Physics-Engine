package model; /**
package model;

import view.GUI;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class SaveFile {
    public static void main(String[] args) {
        Model m = new Model();
         List<Gizmo> gizmoList = new ArrayList<>();
         List<Ball> ballList = new ArrayList<>();
         List<Gizmo> deletedGizmos = new ArrayList<>();
         List<Gizmo> movedGizmos = new ArrayList<>();
         String[] bumpers = {"Square", "Triangle", "Circle", "LeftFlipper", "RightFlipper"};

         Gizmo T1 = new TriangleBumper("T1", 2, 4);
         Gizmo C1 = new CircleBumper("C1", 4, 6);
         Gizmo S3 = new SquareBumper("S3", 3, 3);
         Gizmo LF1 = new LeftFlipper("LF1", 5, 18);

         gizmoList.add(T1);
         gizmoList.add(C1);
         gizmoList.add(S3);
         gizmoList.add(LF1);

         deletedGizmos.add(T1);
         deletedGizmos.add(S3);

         movedGizmos.add(T1);
         movedGizmos.add(LF1);

        GUI gui = new GUI(m);

         ballList.add(new Ball("B", 1.0, 11.0, 5.0, 2.0));
         ballList.add(new Ball("B2", 4.0, 5.0, 3.0, 7.0));

         try {
             Writer wr = new FileWriter("gizmo.txt");

             for (Gizmo g : gizmoList) {
                 for (int i = 0; i < bumpers.length; i++) {
                     if (g.getType().equals(bumpers[i])) {
                         wr.write(g.getType()+ " ");
                         wr.write(g.getId() + " ");
                         wr.write(Double.toString(g.getPos().getX())+ " ");
                         wr.write(Double.toString(g.getPos().getY()));
                         wr.write("\n");
                         break;
                     }
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
}
**/