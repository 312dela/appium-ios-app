package project.utils;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AssertionManager {
    
    public void assertDataEquals(Object expected, Object actual) {
        assertEquals(expected, actual,
                () -> String.format("Expected: [%s], Actual: [%s]", expected, actual));
    }
    
}
