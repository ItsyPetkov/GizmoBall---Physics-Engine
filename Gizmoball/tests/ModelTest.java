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
        Gizmo g = new SquareBumper("S1", 2, 3);
        model.addGizmo(g);
        assertEquals(model.getGizmos().size(), 1);
        assertEquals(model.getGizmos().get(0).getPos().x(), 2);
        assertEquals(model.getGizmos().get(0).getPos().y(), 3);
        model.deleteGizmo(g);
        assertEquals(model.getGizmos().size(), 0);
    }

    @Test
    void addGizmoOcc(){
        Gizmo g = new SquareBumper("S1", 2, 3);
        model.addGizmo(g);
        assertEquals(model.getGizmos().size(), 1);
        Gizmo g2 = new TriangleBumper("T1", 2, 3);
        model.addGizmo(g2);
        assertEquals(model.getGizmos().size(), 1);
    }

    @Test
    void deleteBallsTest(){
        assertEquals(model.getBalls().size(), 0);
        Ball b = new Ball("B1", 2, 3, 10, -20);
        model.addBall(b);
        assertEquals(model.getBalls().size(), 1);
        assertEquals(model.getBalls().get(0).getPos().x(), 2);
        assertEquals(model.getBalls().get(0).getPos().y(), 3);
        model.deleteBall(b);
        assertEquals(model.getBalls().size(), 0);
    }

    @Test
    void addBallOcc(){
        Ball b = new Ball("B1", 2, 3, 10, -20);
        model.addBall(b);
        assertEquals(model.getBalls().size(), 1);
        Ball b2 = new Ball("B2", 2, 3, -7, -6);
        model.addBall(b2);
        assertEquals(model.getBalls().size(), 1);
    }

    @Test
    void gizmoSearch(){
        Gizmo g = new SquareBumper("S1", 2, 3);
        model.addGizmo(g);
        assertEquals(model.gizmoSearch(2,3), g);
        assertNull(model.gizmoSearch(2,4));
    }

    @Test
    void ballSearch(){
        Ball b = new Ball("B1", 2, 3, 10, -20);
        model.addBall(b);
        assertEquals(model.ballSearch(2,3), b);
        assertNull(model.ballSearch(4,3));
    }

    @Test
    void gizmoIdTest(){
        Gizmo g = new SquareBumper("S1", 2, 3);
        model.addGizmo(g);
        assertFalse(model.checkGizmoId("S1"));
        assertTrue(model.checkGizmoId("S2"));
    }

    @Test
    void ballIdTest(){
        Ball b = new Ball("B1", 2, 3, 10, -20);
        model.addBall(b);
        assertFalse(model.checkBallId("B1"));
        assertTrue(model.checkBallId("B2"));
    }

    @Test
    void moveGizmoTest(){
        SquareBumper s = new SquareBumper("S1", 2, 3);
        model.addGizmo(s);
        assertEquals(model.getGizmos().get(0).getPos().x(), 2);
        assertEquals(model.getGizmos().get(0).getPos().y(), 3);
        model.moveGizmo(s, 4, 5);
        assertEquals(model.getGizmos().get(0).getPos().x(), 4);
        assertEquals(model.getGizmos().get(0).getPos().y(), 5);
    }

    @Test
    void setBallPosTest(){
        Ball b = new Ball("B1", 2, 3, 10, -20);
        model.addBall(b);
        assertEquals(model.getBalls().get(0).getPos().x(), 2);
        assertEquals(model.getBalls().get(0).getPos().y(), 3);
        model.setBallPos(b, 4, 9);
        assertEquals(model.getBalls().get(0).getPos().x(), 4);
        assertEquals(model.getBalls().get(0).getPos().y(), 9);
    }

    @Test
    void rotateGizmoTest(){
        SquareBumper s = new SquareBumper("S1", 2, 3);
        model.addGizmo(s);
        assertEquals(model.gizmoSearch(2, 3).getRotation(), 0);
        model.gizmoSearch(2, 3).rotate();
        assertEquals(model.gizmoSearch(2, 3).getRotation(), 1);
    }

}
