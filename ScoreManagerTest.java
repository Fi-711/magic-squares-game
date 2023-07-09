import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreManagerTest {
    private ScoreManager scoreManager;

    // Setup
    @Before
    public void setup() {
        scoreManager = new ScoreManager();
    }


    @Test
    public void getMoveCount() {
        // Arrange
        scoreManager.incMoveCount();

        // Act
        int actual = scoreManager.getMoveCount();
        int expected = 1;

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void resetMoveCount() {
        // Arrange
        scoreManager.resetMoveCount();

        // Act
        int actual = scoreManager.getMoveCount();
        int expected = 0;

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    public void incMoveCount() {
        // Arrange
        scoreManager.incMoveCount();
        scoreManager.incMoveCount();

        // Act
        int actual = scoreManager.getMoveCount();
        int expected = 2;

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    public void addHighScore() {
        // Arrange
        scoreManager.incMoveCount();
        scoreManager.incMoveCount();
        scoreManager.addHighScore(3);

        // Act
        int actual = scoreManager.getHighScore().get(3);
        int expected = 2;

        // Assert
        assertEquals(expected, actual);

    }

}

/*
 University ID Number: c1948118
 Name: Suhail Ahmed
 */
