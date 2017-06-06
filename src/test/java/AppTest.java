import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test public void testAppHasAGreeting() {
        final int[] input = new int[] { 1, 0, 1, 0, 0, 0, 1, 1, 0, 1 };
        final int[] expected_result = new int[] { 0, 1, 1, 1, 0 };

        assertArrayEquals(expected_result, App.crc_with_padding(input));
        assertArrayEquals(expected_result, App.crc_without_padding(input));
    }
}
