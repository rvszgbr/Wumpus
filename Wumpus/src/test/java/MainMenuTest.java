import org.example.MainMenu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainMenuTest {

    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    public void testGetCurrentMainMenu() {
        // Teszt a getCurrentMainMenu metódusra
        MainMenu mainMenu = MainMenu.getCurrentMainMenu();
        assertNotNull(mainMenu);
    }

    @Test
    public void testGetGame() {
        // Teszt a getGame metódusra
        MainMenu mainMenu = new MainMenu();
        assertNull(mainMenu.getGame());
    }

    @Test
    public void testPrintMenu() {
        // Teszt a printMenu metódusra
        MainMenu mainMenu = new MainMenu();
        mainMenu.printMenu();
        assertEquals("\n==============================\n===     WUMPUS      ===\n==============================\n\n1. Uj játék indítasa\n2. Jatek betoltese\n3. Jatek mentese\n4. Highscore megtekintese\n5. Kilepes", outputStreamCaptor.toString().trim());
    }

    // Egyéb tesztek a MainMenu osztály további metódusaira írhatók hasonlóan
}
