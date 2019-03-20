package model;

import controller.BuildModeKeyListener;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class SaveFile implements ISaveFile{
    IModel model;
    private List<IGizmo> gizmoList;
    private List<IBall> ballList;
    public SaveFile(IModel m) {
        model = m;
        gizmoList = model.getGizmos();
        ballList = model.getBalls();
    }
    public void save(String filename) {
        String[] bumpers = {"Square","Triangle","Circle", "LeftFlipper", "RightFlipper"};
        try {
            Writer wr = new FileWriter(filename);
            //Saving Gizmos
            for (IGizmo g : gizmoList) {
                for (int i = 0; i < bumpers.length; i++) {
                    if(g.type().equals("Absorber")){
                        Absorber abs = (Absorber) g;
                        wr.write(g.type()+ " ");
                        wr.write(g.getId() + " ");
                        wr.write((int) (abs.getPos().x()) + " ");
                        wr.write((int) (abs.getPos().y()) + " ");
                        wr.write((int) (abs.getPos2().x()) + " ");
                        wr.write((int) (abs.getPos2().y()) + " ");
                        break;
                    }
                    if (g.type().equals(bumpers[i])) {
                        wr.write(g.type()+ " ");
                        wr.write(g.getId() + " ");
                        wr.write(Integer.toString((int)g.getPos().x())+ " ");
                        wr.write(Integer.toString((int)g.getPos().y()));
                        wr.write("\n");
                        if (g.getRotation() > 0) {
                            for (int r = 0; r < g.getRotation(); r++) {
                                wr.write("Rotate ");
                                wr.write(g.getId());
                                wr.write("\n");
                            }
                            break;
                        }
                        break;
                    }

                }
                wr.write("\n");

            }
            //Saving Gravity
            double gravity = model.getGravity();
            wr.write("Gravity ");
            wr.write(gravity+"");
            wr.write("\n");

            //Saving Friction
            double[] friction = model.getFriction();
            wr.write("Friction ");
            wr.write(friction[0]+" ");
            wr.write(friction[1]+"");
            wr.write("\n");

            //Saving Balls
            for (IBall b : ballList) {
                wr.write(b.getType()+" ");
                wr.write(b.getId()+" ");
                wr.write(Double.toString(b.getPos().x())+ " ");
                wr.write(Double.toString(b.getPos().y())+ " ");
                wr.write(Double.toString(b.getVelo().x())+ " ");
                wr.write(Double.toString(b.getVelo().y()));
                wr.write("\n");
            }
            //Saving KeyConnect
            for (IGizmo g : gizmoList) {
                if (BuildModeKeyListener.keyConnects.get(g.getId()) != null) {
                    wr.write("KeyConnect ");
                    wr.write("key ");
                    wr.write(BuildModeKeyListener.keyConnects.get(g.getId())+ " ");
                    wr.write("down ");
                    wr.write(g.getId());
                    wr.write("\n");
                }
            }

            //Saving Connect


            wr.write("\n");


            wr.flush();
            wr.close();
        } catch(IOException ex) {
            System.out.println("Failed to write to file "+filename);
        }


    }

}
