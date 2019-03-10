import model.Ball;
import org.junit.jupiter.api.Test;
import physics.Circle;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;

public class BallTest {

    Ball ball = new Ball("B1",10, 10, 20, -10);

    @Test
    void getPosTest(){
        assertEquals(ball.getPos().x(), 10);
        assertEquals(ball.getPos().y(), 10);
    }

    @Test
    void setPosTest(){
        ball.setPos(9,8);
        assertEquals(ball.getPos().x(), 9);
        assertEquals(ball.getPos().y(), 8);
    }

    @Test
    void getVeloTest(){
        assertEquals(ball.getVelo().x(), 20);
        assertEquals(ball.getVelo().y(), -10);
    }

    @Test
    void setVeloTest(){
        ball.setVelo(-5, 3);
        assertEquals(ball.getVelo().x(), -5);
        assertEquals(ball.getVelo().y(), 3);
    }

    @Test
    void getRadiusTest(){
        assertEquals(ball.getRadius(), 0.25);
    }

    @Test
    void getColourTest(){
        assertEquals(ball.getColour(), Color.BLUE);
    }

    @Test
    void getCircleTest(){
        Circle ballCircle = ball.getCircle();
        assertEquals(ballCircle.getRadius(), 0.25);
        assertEquals(ballCircle.getCenter().x(), 10);
        assertEquals(ballCircle.getCenter().y(), 10);
    }

}