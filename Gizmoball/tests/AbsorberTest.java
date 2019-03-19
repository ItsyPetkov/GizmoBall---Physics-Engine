import model.Absorber;
import model.Ball;
import org.junit.jupiter.api.Test;
import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AbsorberTest {

    Absorber abs = new Absorber("A", 5, 4, 8, 6);

    @Test
    void typeTest(){
        assertEquals(abs.type(), "Absorber");
    }

    @Test
    void pos2Test(){
        assertEquals(abs.getPos2().x(), 8);
        assertEquals(abs.getPos2().y(), 6);
        abs.setPos2(9, 7);
        assertEquals(abs.getPos2().x(), 9);
        assertEquals(abs.getPos2().y(), 7);
    }

    @Test
    void colourTest(){
        assertEquals(abs.getColour(), Color.MAGENTA);
        abs.setColour(Color.BLACK);
        assertEquals(abs.getColour(), Color.BLACK);
    }

    @Test
    void getSidesTest(){
        List<LineSegment> ls = abs.getSides();
        assertEquals(ls.size(), 4);
    }

    @Test
    void getCornersTest(){
        List<Circle> cs = abs.getCorners();
        assertEquals(cs.size(), 4);
        assertEquals(cs.get(0).getRadius(), 0);
        assertEquals(cs.get(1).getRadius(), 0);
        assertEquals(cs.get(2).getRadius(), 0);
        assertEquals(cs.get(3).getRadius(), 0);
    }

    @Test
    void triggerTest(){
        Ball b = new Ball("B1",10, 10, 20, -10);
        assertTrue(abs.capture(b));
        assertFalse(abs.capture(b));
        abs.trigger();
        assertEquals(b.getVelo().y(), -50);
    }
}
