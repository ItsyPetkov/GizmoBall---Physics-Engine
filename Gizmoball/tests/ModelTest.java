import model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {

    Model model = new Model();

    @Test
    void wallsTest(){
        assertEquals(model.getWallTL().x(), 0);
        assertEquals(model.getWallTL().y(), 0);
        assertEquals(model.getWallBR().x(), 20);
        assertEquals(model.getWallBR().y(), 20);
    }

    @Test
    void deleteGizmosTest(){
        assertEquals(model.getGizmos().size(), 0);
        model.addGizmo("Square", "S1", 2, 3);
        assertEquals(model.getGizmos().size(), 1);
        assertEquals(model.getGizmos().get(0).getPos().x(), 2);
        assertEquals(model.getGizmos().get(0).getPos().y(), 3);
        model.deleteGizmo(model.gizmoSearch(2,3));
        assertEquals(model.getGizmos().size(), 0);
    }

    @Test
    void addGizmoOcc(){
        model.addGizmo("Square","S1", 2, 3);
        assertEquals(model.getGizmos().size(), 1);
        model.addGizmo("Triangle", "T1", 2, 3);
        assertEquals(model.getGizmos().size(), 1);
    }

    @Test
    void deleteBallsTest(){
        assertEquals(model.getBalls().size(), 0);
        model.addBall("B1", 2, 3, 10, -20);
        assertEquals(model.getBalls().size(), 1);
        assertEquals(model.getBalls().get(0).getPos().x(), 2);
        assertEquals(model.getBalls().get(0).getPos().y(), 3);
        model.deleteBall(model.ballSearch(2,3));
        assertEquals(model.getBalls().size(), 0);
    }

    @Test
    void addBallOcc(){
        model.addBall("B1", 2, 3, 10, -20);
        assertEquals(model.getBalls().size(), 1);
        model.addBall("B2", 2, 3, -7, -6);
        assertEquals(model.getBalls().size(), 1);
    }

    @Test
    void gizmoSearch(){
        model.addGizmo("Square", "S1", 2, 3);
        Gizmo g = new SquareBumper("S1", 2, 3);
        assertEquals(model.gizmoSearch(2,3), g);
        assertNull(model.gizmoSearch(2,4));
    }

    @Test
    void ballSearch(){
        model.addBall("B1", 2, 3, 10, -20);
        //assertEquals(model.ballSearch(2,3), b);
        assertNull(model.ballSearch(4,3));
    }

    @Test
    void gizmoIdTest(){
        model.addGizmo("Square", "S1", 2, 3);
        assertFalse(model.checkGizmoId("S1"));
        assertTrue(model.checkGizmoId("S2"));
    }

    @Test
    void ballIdTest(){
        model.addBall("B1", 2, 3, 10, -20);
        assertFalse(model.checkBallId("B1"));
        assertTrue(model.checkBallId("B2"));
    }

    @Test
    void moveGizmoTest(){
        model.addGizmo("Square", "S1", 2, 3);
        assertEquals(model.getGizmos().get(0).getPos().x(), 2);
        assertEquals(model.getGizmos().get(0).getPos().y(), 3);
        model.moveGizmo(model.gizmoSearch(2,3), 4, 5);
        assertEquals(model.getGizmos().get(0).getPos().x(), 4);
        assertEquals(model.getGizmos().get(0).getPos().y(), 5);
    }

    @Test
    void setBallPosTest(){
        model.addBall("B1", 2, 3, 10, -20);
        assertEquals(model.getBalls().get(0).getPos().x(), 2);
        assertEquals(model.getBalls().get(0).getPos().y(), 3);
        model.setBallPos(model.ballSearch(2,3), 4, 9);
        assertEquals(model.getBalls().get(0).getPos().x(), 4);
        assertEquals(model.getBalls().get(0).getPos().y(), 9);
    }

    @Test
    void rotateGizmoTest(){
        model.addGizmo("Square", "S1", 2, 3);
        assertEquals(model.gizmoSearch(2, 3).getRotation(), 0);
        model.rotateGizmo(model.gizmoSearch(2, 3));
        assertEquals(model.gizmoSearch(2, 3).getRotation(), 1);
    }

}
