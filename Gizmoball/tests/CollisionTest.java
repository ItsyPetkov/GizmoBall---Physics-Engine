import model.CollisionDetails;
import org.junit.jupiter.api.Test;
import physics.Vect;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionTest {

    CollisionDetails cd = new CollisionDetails(0.03, new Vect(10, 7));

    @Test
    void getTucTest(){
        assertEquals(cd.getTuc(), 0.03);
    }

    @Test
    void getVeloTest(){
        assertEquals(cd.getVelo().x(), 10);
        assertEquals(cd.getVelo().y(), 7);
    }
}
