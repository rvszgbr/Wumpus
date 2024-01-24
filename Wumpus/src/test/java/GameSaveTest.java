package org.example;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameSaveTest {

    @Test
    public void testSaveAndLoadGame() {
        // Arrange
        Wall wall = new Wall("C:\\Users\\rivas\\Wumpus\\src\\main\\resources\\palya.txt");
        Hero hero = new Hero(new Position(1, 5), Direction.FEL, 3);
        MainMenu mainMenu = new MainMenu();
        Game gameToSave = new Game(wall, hero, mainMenu);

        // Act
        GameSave.createTable(); // Create the table if not exists
        GameSave.saveGame(gameToSave, "testUser");
        Game loadedGame = GameSave.loadGame("testUser", mainMenu);

        // Assert
        assertNotNull(loadedGame);
        assertEquals(gameToSave.getPlayer().getPosition(), loadedGame.getPlayer().getPosition());
        assertEquals(gameToSave.getPlayer().getDirection(), loadedGame.getPlayer().getDirection());
        assertEquals(gameToSave.getPlayer().getArrows(), loadedGame.getPlayer().getArrows());
        // Add more assertions based on your Game state

        // Clean up (optional)
        GameSave.deleteSavedGame("testUser");
    }
}
