import org.example.Wall;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WallTest {

    @Test
    public void testGetCharAtPosition() {
        Wall wall = new Wall("path_to_your_file.txt");
        assertEquals('W', wall.getCharAtPosition(0, 0));
    }

    // További tesztek lehetnek a Wall osztály többi metódusára is...
}