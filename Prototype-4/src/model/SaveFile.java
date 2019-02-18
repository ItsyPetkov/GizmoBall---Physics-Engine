package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class SaveFile {
    public static void main(String[] args) {
         List<model.Gizmo> gizmoList = new ArrayList<>();
         List<model.Ball> ballList = new ArrayList<>();
         List<model.Gizmo> deletedGizmos = new ArrayList<>();
         List<model.Gizmo> movedGizmos = new ArrayList<>();
         String[] bumpers = {"Square", "Triangle", "Circle", "LeftFlipper", "RightFlipper"};

         model.Gizmo T1 = new model.Triangle("T1", 2, 4);
         model.Gizmo C1 = new model.Circle("C1", 4, 6);
         model.Gizmo S3 = new Square("S3", 3, 3);
         model.Gizmo LF1 = new model.LeftFlipper("LF1", 5, 18);

         gizmoList.add(T1);
         gizmoList.add(C1);
         gizmoList.add(S3);
         gizmoList.add(LF1);

         deletedGizmos.add(T1);
         deletedGizmos.add(S3);

         movedGizmos.add(T1);
         movedGizmos.add(LF1);

         ballList.add(new model.Ball("B", 1.0, 11.0, 5.0, 2.0));
         ballList.add(new model.Ball("B2", 4.0, 5.0, 3.0, 7.0));

         try {
             Writer wr = new FileWriter("gizmo.txt");

             for (model.Gizmo g : gizmoList) {
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
             for (model.Ball b : ballList) {
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
             for (model.Gizmo g : deletedGizmos) {
                        wr.write("Delete ");
                        wr.write(g.getId());
                        wr.write("\n");
                        break;
             }
             wr.write("\n");
             for (model.Gizmo g : movedGizmos) {
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
