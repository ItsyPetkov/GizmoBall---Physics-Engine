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
    public SaveFile(Model m) {
        model = m;
        gizmoList = model.getGizmos();
        leftFlipperList = model.getLeftFlippers();
        rightFlipperList = model.getRightFlippers();
        ballList = model.getBalls();
    }
    public void save(String filename) {
        String[] bumpers = {"Square","Triangle","Circle"};
        try {
            Writer wr = new FileWriter(filename);
            for (Gizmo g : gizmoList) {
                for (int i = 0; i < bumpers.length; i++) {
                    if (g.getType().equals(bumpers[i])) {
                        System.out.println(g.getType());
                        wr.write(g.getType()+ " ");
                        wr.write(g.getId() + " ");
                        wr.write(Double.toString(g.getPos().x())+ " ");
                        wr.write(Double.toString(g.getPos().y()));
                        break;
                    }
                }
                wr.write("\n");
            }

            wr.flush();
            wr.close();
        } catch(IOException ex) {
            System.out.println("Failed to write to file "+filename);
        }


    }

}
