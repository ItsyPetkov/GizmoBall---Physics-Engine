package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class SaveFile {
    Model model;
    private List<Gizmo> gizmoList;
    private List<LeftFlipper> leftFlipperList;
    private List<RightFlipper> rightFlipperList;
    private List<Ball> ballList;
    private List<Absorber> absList;
    public SaveFile(Model m) {
        model = m;
        gizmoList = model.getGizmos();
        leftFlipperList = model.getLeftFlippers();
        rightFlipperList = model.getRightFlippers();
        ballList = model.getBalls();
        absList = model.getAbsorbers();
    }
    public void save(String filename) {
        String[] bumpers = {"Square","Triangle","Circle"};
        try {
            Writer wr = new FileWriter(filename);
            //Saving Gizmos
            for (Gizmo g : gizmoList) {
                for (int i = 0; i < bumpers.length; i++) {
                    if (g.getType().equals(bumpers[i])) {
                        wr.write(g.getType()+ " ");
                        wr.write(g.getId() + " ");
                        wr.write(Integer.toString((int)g.getPos().x())+ " ");
                        wr.write(Integer.toString((int)g.getPos().y()));
                        break;
                    }
                }
                wr.write("\n");
            }
            wr.write("\n");
            //Saving LeftFlippers
            for (LeftFlipper lf : leftFlipperList) {
                if (lf.getType().equals("LeftFlipper")) {
                    wr.write(lf.getType()+ " ");
                    wr.write(lf.getId()+ " ");
                    wr.write(Integer.toString((int)lf.getPos().x())+ " ");
                    wr.write(Integer.toString((int)lf.getPos().y()));
                    wr.write("\n");
                }
            }
            wr.write("\n");
            //Saving RightFlippers
            for (RightFlipper rf : rightFlipperList) {
                if (rf.getType().equals("RightFlipper")) {
                    wr.write(rf.getType()+ " ");
                    wr.write(rf.getId()+ " ");
                    wr.write(Integer.toString((int)rf.getPos().x())+ " ");
                    wr.write(Integer.toString((int)rf.getPos().y()));
                    wr.write("\n");
                }
            }
            wr.write("\n");
            //Saving Balls
            for (Ball b : ballList) {
                wr.write(b.getType()+" ");
                wr.write(b.getId()+" ");
                wr.write(Double.toString(b.getPos().x())+ " ");
                wr.write(Double.toString(b.getPos().y())+ " ");
                wr.write(Double.toString(b.getVelo().x())+ " ");
                wr.write(Double.toString(b.getVelo().y()));
                wr.write("\n");
            }
            wr.write("\n");
            //Saving Absorbers
            for (Absorber abs : absList) {
                wr.write(abs.getType()+" ");
                wr.write(abs.getId()+" ");
                wr.write((int)abs.getPos().x()+" ");
                wr.write((int)abs.getPos().y()+" ");
                wr.write(Integer.toString((int)abs.getPos2().x())+" ");
                wr.write(Integer.toString((int)abs.getPos2().y())+" ");
                wr.write("\n");
            }
            wr.write("\n");

            wr.flush();
            wr.close();
        } catch(IOException ex) {
            System.out.println("Failed to write to file "+filename);
        }


    }

}
