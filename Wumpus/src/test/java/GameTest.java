package org.example;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void testCalculateNextPosition() {
        // Arrange
        Wall wall = new Wall("C:\\Users\\rivas\\Wumpus\\src\\main\\resources\\palya.txt");
        Hero hero = new Hero(new Position(1, 5), Direction.FEL, 3);
        MainMenu mainMenu = new MainMenu();
        Game game = new Game(wall, hero, mainMenu);

        // Act
        Position nextPosition = game.calculateNextPosition(Direction.LE);

        // Assert
        assertEquals(1, nextPosition.getX());
        assertEquals(6, nextPosition.getY());
    }
}
