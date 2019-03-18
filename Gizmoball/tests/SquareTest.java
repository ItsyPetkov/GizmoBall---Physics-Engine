import model.SquareBumper;
import org.junit.jupiter.api.Test;
import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {

    SquareBumper s = new SquareBumper("S1", 2, 3);

    @Test
    void typeTest(){
        assertEquals(s.type(), "Square");
    }

    @Test
    void colourTest(){
        assertEquals(s.getColour(), Color.BLUE);
        s.setColour(Color.RED);
        assertEquals(s.getColour(), Color.RED);
    }

    @Test
    void getSidesTest(){
        List<LineSegment> ls = s.getSides();
        assertEquals(ls.size(), 4);
        assertEquals(ls.get(0).length(), 1);
        assertEquals(ls.get(1).length(), 1);
        assertEquals(ls.get(2).length(), 1);
        assertEquals(ls.get(3).length(), 1);
    }

    @Test
    void getCornersTest(){
        List<Circle> cs = s.getCorners();
        assertEquals(cs.size(), 4);
        assertEquals(cs.get(0).getRadius(), 0);
        assertEquals(cs.get(1).getRadius(), 0);
        assertEquals(cs.get(2).getRadius(), 0);
        assertEquals(cs.get(3).getRadius(), 0);
    }
}
