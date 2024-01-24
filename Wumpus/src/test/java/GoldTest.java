import org.example.Gold;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoldTest {

    @Test
    public void testGoldCollect() {
        Gold gold = new Gold();
        assertFalse(gold.isCollected());
        gold.collect();
        assertTrue(gold.isCollected());
    }
}
