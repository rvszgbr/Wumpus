import org.example.Direction;
import org.example.Hero;
import org.example.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeroTest {

    @Test
    public void testInitialPosition() {
        Position initialPosition = new Position(1, 5);
        Hero hero = new Hero(initialPosition, Direction.FEL, 3);
        assertEquals(initialPosition, hero.getInitialPosition());
    }

    @Test
    public void testMove() {
        Hero hero = new Hero(new Position(1, 5), Direction.FEL, 3);
        hero.move();
        assertEquals(new Position(0, 5), hero.getPosition());
    }

    // További tesztek lehetnek a Hero osztály többi metódusára is...
}