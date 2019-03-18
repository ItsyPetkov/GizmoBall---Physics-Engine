import model.Gizmo;
import model.TriangleBumper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GizmoTest {

    Gizmo t = new TriangleBumper("T1", 2,3);

    @Test
    void posTest(){
        assertEquals(t.getPos().x(), 2);
        assertEquals(t.getPos().y(), 3);
        t.setPos(5,6);
        assertEquals(t.getPos().x(), 5);
        assertEquals(t.getPos().y(), 6);
    }

    @Test
    void getIdTest(){
        assertEquals(t.getId(), "T1");
    }

    @Test
    void rotationTest(){
        assertEquals(t.getRotation(), 0);
        t.rotate();
        assertEquals(t.getRotation(), 1);
        t.rotate();
        assertEquals(t.getRotation(), 2);
        t.rotate();
        assertEquals(t.getRotation(), 3);
        t.rotate();
        assertEquals(t.getRotation(), 0);
    }

}
