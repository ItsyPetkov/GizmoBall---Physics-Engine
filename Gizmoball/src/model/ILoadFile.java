package model;

import java.util.List;

public interface ILoadFile {
    boolean load(String filename);
    void createBumpers(List<String[]> commands);
    void moveGizmos(List<String[]> commands);
    void createBall(List<String[]> commands);
    void deleteGizmos(List<String[]> commands);
    void keyConnectGizmos(List<String[]> commands);
    void rotateGizmos(List<String[]> commands);
    void connectGizmos(List<String[]> commands);
}
