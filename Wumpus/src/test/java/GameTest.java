import org.example.Direction;
import org.example.Game;
import org.example.Hero;
import org.example.MainMenu;
import org.example.Position;
import org.example.Wall;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;

public class GameTest {

    @Test
    public void testIsValidMoveWithValidMove() {
        Wall gameBoard = Mockito.mock(Wall.class);
        Hero player = Mockito.mock(Hero.class);
        MainMenu mainMenu = Mockito.mock(MainMenu.class);

        Mockito.when(gameBoard.getCharAtPosition(Mockito.anyInt(), Mockito.anyInt())).thenReturn(' ');

        Game game = new Game(gameBoard, player, mainMenu);

        assertTrue(game.isValidMove(Direction.FEL));
    }

    @Test
    public void testIsValidMoveWithInvalidMove() {
        Wall gameBoard = Mockito.mock(Wall.class);
        Hero player = Mockito.mock(Hero.class);
        MainMenu mainMenu = Mockito.mock(MainMenu.class);

        Mockito.when(gameBoard.getCharAtPosition(Mockito.anyInt(), Mockito.anyInt())).thenReturn('W');

        Game game = new Game(gameBoard, player, mainMenu);

        assertFalse(game.isValidMove(Direction.FEL));
    }

    @Test
    public void testCalculateNextPosition() {
        Wall gameBoard = Mockito.mock(Wall.class);
        Hero player = new Hero(new Position(3, 3));
        MainMenu mainMenu = Mockito.mock(MainMenu.class);

        Game game = new Game(gameBoard, player, mainMenu);

        Position expectedPosition = new Position(3, 2);
        assertEquals(expectedPosition, game.calculateNextPosition(Direction.FEL));
    }

    @Test
    public void testPlayGameExitOption() {
        Wall gameBoard = Mockito.mock(Wall.class);
        Hero player = Mockito.mock(Hero.class);
        MainMenu mainMenu = Mockito.mock(MainMenu.class);

        Game game = new Game(gameBoard, player, mainMenu);

        // Mock Scanner objektum a felhasználói bemenet szimulálásához
        Scanner mockScanner = Mockito.mock(Scanner.class);
        Mockito.when(mockScanner.nextLine()).thenReturn("KILEPES");

        // Teszt: Kilépési opcióval való interakció
        game.playGame();
        Mockito.verify(player, Mockito.never()).shootArrow(); // Nyíllozási opció soha nem történik meg
        Mockito.verify(gameBoard, Mockito.never()).removeGold(Mockito.anyInt(), Mockito.anyInt()); // Aranylás soha nem történik meg
        Mockito.verify(mainMenu).returnToMainMenu(); // A főmenübe való visszatérés megtörténik
    }

    @Test
    public void testPlayGameShootArrowOption() {
        Wall gameBoard = Mockito.mock(Wall.class);
        Hero player = Mockito.mock(Hero.class);
        MainMenu mainMenu = Mockito.mock(MainMenu.class);

        Game game = new Game(gameBoard, player, mainMenu);

        // Mock Scanner objektum a felhasználói bemenet szimulálásához
        Scanner mockScanner = Mockito.mock(Scanner.class);
        Mockito.when(mockScanner.nextLine()).thenReturn("LO");

        // Teszt: Nyíllozási opcióval való interakció
        game.playGame();
        Mockito.verify(player).shootArrow(); // Nyíllozás megtörténik
        Mockito.verify(gameBoard, Mockito.never()).removeGold(Mockito.anyInt(), Mockito.anyInt()); // Aranylás soha nem történik meg
        Mockito.verify(mainMenu, Mockito.never()).returnToMainMenu(); // A főmenübe való visszatérés soha nem történik meg
    }

    // További tesztesetek és metódusok...
}