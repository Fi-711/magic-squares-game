import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class UIManagerTest {
    // Arrange - to test print output
    private final PrintStream systemOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final UIManager uiManager = new UIManager();

    // Setup
    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outputStream));
    }

    // Testing functions with no scanner input
    @Test
    public void clearScreen() {
        // Act
        uiManager.clearScreen();
        String actual = outputStream.toString();
        String expected = "\r\n".repeat(100);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void tenEmptyLines() {
        // Act
        uiManager.emptyLines(10);
        String actual = outputStream.toString();
        String expected = "\r\n".repeat(10);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void thankYouMessage() {
        // Act
        uiManager.thankYouMessage();
        String actual = outputStream.toString().replaceAll("\u001B\\[0m|\u001B\\[31m|\u001B\\[33m|\\s", "").trim();
        String expected = "*".repeat(303);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(systemOut);
    }

}

/*
 University ID Number: c1948118
 Name: Suhail Ahmed
 */
