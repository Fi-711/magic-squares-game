import org.junit.Test;

import static org.junit.Assert.*;

public class DebugGameManagerTest {
    private final DebugGameManager debugGameManager = new DebugGameManager();

    // Test solution rows add up to correct total for square size 3 and 9
    @Test
    public void getSolutionRowTotalSquareSizeThree() {
        // Arrange
        debugGameManager.startNewGame(3);

        // Act
        int actual = debugGameManager.getSolutionRowTotal();
        int expected = 15;

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void getSolutionRowTotalSquareSizeNine() {
        // Arrange
        debugGameManager.startNewGame(9);

        // Act
        int actual = debugGameManager.getSolutionRowTotal();
        int expected = 369;

        // Assert
        assertEquals(expected, actual);
    }

    // Test if game has won for a shuffled and non shuffled grid
    @Test
    public void hasGameWonShuffledNonWinningGridSizeThree() {
        // Arrange
        debugGameManager.startNewGame(3);

        // Act
        boolean actual = debugGameManager.hasGameWon();
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void hasGameWonInWinningGrid_SizeThree() {
        // Arrange
        debugGameManager.startNewGame(3);
        // Make a fresh un-shuffled size three grid which should already be solved
        MagicSquare magicSquare = new MagicSquare(3);
        // Reset grid to un-shuffled version
        debugGameManager.setCurrentGrid(magicSquare.getGrid());

        // Act
        boolean actual = debugGameManager.hasGameWon();
        boolean expected = true;

        assertEquals(expected, actual);
    }

    // Test swapping - non wrapping version
    @Test
    public void updateGridSizeThreeSwappingTwoOneDown() {
        // Arrange
        debugGameManager.startNewGame(3);
        // Make a fresh un-shuffled size three grid which should already be solved
        MagicSquare magicSquare = new MagicSquare(3);
        // point debugManager's magic square at newly created one
        debugGameManager.setMagicSquare(magicSquare);
        // Reset grid to un-shuffled version
        debugGameManager.setCurrentGrid(magicSquare.getGrid());

        // Act
        debugGameManager.updateGrid(new String[]{"2", "1", "D"});
        int[][] actual = debugGameManager.getCurrentGrid();
        int[][] expected = new int[][]{{6, 1, 8}, {2, 5, 3}, {7, 9, 4}};

        // Assert
        assertArrayEquals(expected, actual);
    }

    // Tests if swapping wraps
    @Test
    public void updateGridSizeThreeSwappingOneOneLeft() {
        // Arrange
        debugGameManager.startNewGame(3);
        // Make a fresh un-shuffled size three grid which should already be solved
        MagicSquare magicSquare = new MagicSquare(3);
        // point debugManager's magic square at newly created one
        debugGameManager.setMagicSquare(magicSquare);
        // Reset grid to un-shuffled version
        debugGameManager.setCurrentGrid(magicSquare.getGrid());

        // Act
        debugGameManager.updateGrid(new String[]{"1", "1", "L"});
        int[][] actual = debugGameManager.getCurrentGrid();
        int[][] expected = new int[][]{{8, 1, 6}, {7, 5, 3}, {2, 9, 4}};

        // Assert
        assertArrayEquals(expected, actual);
    }

    // Tests if only 3 parts were entered to move
    @Test
    public void isValidMoveThreePartsOnly() {
        // Arrange
        debugGameManager.startNewGame(3);

        // Act
        boolean actual = debugGameManager.isValidMove(new String[]{"1", "2", "D", "4"});
        boolean expected = false;

        //Assert
        assertEquals(expected, actual);
    }


    // Tests if invalid direction was entered to move
    @Test
    public void isValidMoveNotADirection() {
        // Arrange
        debugGameManager.startNewGame(3);

        // Act
        boolean actual = debugGameManager.isValidMove(new String[]{"1", "2", "E"});
        boolean expected = false;

        //Assert
        assertEquals(expected, actual);
    }

    // Tests if invalid format used
    @Test
    public void isValidNotAValidFormat() {
        // Arrange
        debugGameManager.startNewGame(3);

        // Act
        boolean actual = debugGameManager.isValidMove(new String[]{"E", "E", "E"});
        boolean expected = false;

        //Assert
        assertEquals(expected, actual);
    }

    // Tests if out of index move was entered
    @Test
    public void isValidIndexOutOfBounds() {
        // Arrange
        debugGameManager.startNewGame(3);

        // Act
        boolean actual = debugGameManager.isValidMove(new String[]{"1", "4", "R"});
        boolean expected = false;

        //Assert
        assertEquals(expected, actual);
    }
    // Tests if invalid direction was entered to move
    @Test
    public void isValid_ValidFormat() {
        // Arrange
        debugGameManager.startNewGame(3);

        // Act
        boolean actual = debugGameManager.isValidMove(new String[]{"2", "2", "R"});
        boolean expected = true;

        //Assert
        assertEquals(expected, actual);
    }
}

/*
 University ID Number: c1948118
 Name: Suhail Ahmed
 */
