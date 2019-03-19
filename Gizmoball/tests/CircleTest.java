import model.CircleBumper;
import org.junit.jupiter.api.Test;
import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CircleTest {

    CircleBumper c = new CircleBumper("C1", 5,8);

    @Test
    void typeTest(){
        assertEquals(c.type(), "Circle");
    }

    @Test
    void colourTest(){
        assertEquals(c.getColour(), Color.RED);
        c.setColour(Color.GREEN);
        assertEquals(c.getColour(), Color.GREEN);
    }

    @Test
    void getSidesTest(){
        java.util.List<LineSegment> ls = c.getSides();
        assertEquals(ls.size(), 0);
    }

    @Test
    void getCornersTest(){
        List<Circle> cs = c.getCorners();
        assertEquals(cs.size(), 1);
        assertEquals(cs.get(0).getRadius(), 0.5);
    }

    @Test
    void triggerTest(){
        assertEquals(c.getColour(), Color.RED);
        c.trigger();
        assertEquals(c.getColour(), Color.YELLOW);
    }
}
