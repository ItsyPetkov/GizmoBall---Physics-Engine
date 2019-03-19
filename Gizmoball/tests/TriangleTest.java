import model.TriangleBumper;
import org.junit.jupiter.api.Test;
import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

    TriangleBumper t = new TriangleBumper("T1", 4,7);

    @Test
    void typeTest(){
        assertEquals(t.type(), "Triangle");
    }

    @Test
    void colourTest(){
        assertEquals(t.getColour(), Color.GREEN);
        t.setColour(Color.RED);
        assertEquals(t.getColour(), Color.RED);
    }

    @Test
    void getSidesTest(){
        java.util.List<LineSegment> ls = t.getSides();
        assertEquals(ls.size(), 3);
    }

    @Test
    void getCornersTest(){
        List<Circle> cs = t.getCorners();
        assertEquals(cs.size(), 3);
        assertEquals(cs.get(0).getRadius(), 0);
        assertEquals(cs.get(1).getRadius(), 0);
        assertEquals(cs.get(2).getRadius(), 0);
    }

    @Test
    void triggerTest(){
        assertEquals(t.getColour(), Color.GREEN);
        t.trigger();
        assertEquals(t.getColour(), Color.CYAN);
    }
}
