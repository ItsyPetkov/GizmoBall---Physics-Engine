import model.Walls;
import org.junit.jupiter.api.Test;
import physics.LineSegment;
import physics.Vect;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class WallsTest {

    Walls walls = new Walls(0,0,20,20);

    @Test
    void getTLTest(){
        Vect TL = walls.getTL();
        assertEquals(TL.x(), 0);
        assertEquals(TL.y(), 0);
    }

    @Test
    void getBRTest(){
        Vect TL = walls.getBR();
        assertEquals(TL.x(), 20);
        assertEquals(TL.y(), 20);
    }

    @Test
    void getLineSegsTest(){
        List<LineSegment> ls = walls.getLineSegments();
        assertEquals(ls.size(), 4);
        //top
        assertEquals(ls.get(0).p1(), new Vect(0,0));
        assertEquals(ls.get(0).p2(), new Vect(20,0));
        //left
        assertEquals(ls.get(1).p1(), new Vect(0,0));
        assertEquals(ls.get(1).p2(), new Vect(0,20));
        //right
        assertEquals(ls.get(2).p1(), new Vect(20,20));
        assertEquals(ls.get(2).p2(), new Vect(20,0));
        //bottom
        assertEquals(ls.get(3).p1(), new Vect(20,20));
        assertEquals(ls.get(3).p2(), new Vect(0,20));
    }

}
