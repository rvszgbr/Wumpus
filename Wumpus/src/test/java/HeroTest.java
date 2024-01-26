import org.example.Hero;
import org.example.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeroTest {

    @Test
    public void testMoveUp() {
        Hero hero = new Hero(new Position(1, 1));
        hero.move();
        assertEquals(new Position(0, 1), hero.getPosition());
    }

    @Test
    public void testMoveDown() {
        Hero hero = new Hero(new Position(1, 1));
        hero.move();
        assertEquals(new Position(2, 1), hero.getPosition());
    }

    @Test
    public void testMoveLeft() {
        Hero hero = new Hero(new Position(1, 1));
        hero.move();
        assertEquals(new Position(1, 0), hero.getPosition());
    }

    @Test
    public void testMoveRight() {
        Hero hero = new Hero(new Position(1, 1));
        hero.move();
        assertEquals(new Position(1, 2), hero.getPosition());
    }

    @Test
    public void testGetGoldCount() {
        Hero hero = new Hero(new Position(1, 1));
        assertEquals(0, hero.getGoldCount());
        hero.collectGold();
        assertEquals(1, hero.getGoldCount());
    }

    @Test
    public void testShootArrowWithArrows() {
        Hero hero = new Hero(new Position(1, 1));
        hero.shootArrow();
        assertEquals(2, hero.getArrows());
    }

    @Test
    public void testShootArrowWithoutArrows() {
        Hero hero = new Hero(new Position(1, 1));
        hero.shootArrow();
        // Nyilak elfogytak, a hívás után is 0-nak kell maradnia
        assertEquals(0, hero.getArrows());
    }
}
