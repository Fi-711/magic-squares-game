import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Unit tests for MagicSquare class
 */
public class MagicSquareTest {

    // Test Constructor - maxStringWidth: max number is n ^ 2
    @Test
    public void magicSquareSizeThreeMaxStringWidthOne() {
        // Arrange
        MagicSquare magicSquare = new MagicSquare(3);

        // Act
        int actual = magicSquare.getMaxStringWidth();
        int expected = 1;

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void magicSquareSizeElevenMaxStringWidthThree() {
        // Arrange
        MagicSquare magicSquare = new MagicSquare(11);

        // Act - three because 11^2 = 121 i.e. three characters wide
        int actual = magicSquare.getMaxStringWidth();
        int expected = 3;

        // Assert
        assertEquals(expected, actual);
    }

    // Test toString()
    @Test
    public void toStringMagicSquareSizeThree() {
        // Arrange
        MagicSquare magicSquare = new MagicSquare(3);

        // Act
        // Strip ANSI colors and whitespaces as value is most important
        String actual = magicSquare.toString().replaceAll("\u001B\\[0m|\u001B\\[31m|\u001B\\[33m|\\s", "");
        String expected = "123*------*1|618|2|753|3|294|*------*";

        // Assert
        assertEquals(expected, actual);
    }


    // Testing other public methods
    @Test
    public void getMagicSquareThreeValueAtTwoTwo() {
        // Arrange
        MagicSquare magicSquare = new MagicSquare(3);

        // Act
        int actual = magicSquare.getMagicSquareValue(2, 2);
        int expected = 4;

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void getMagicSquareFifteenValueAtSevenSeven() {
        // Arrange
        MagicSquare magicSquare = new MagicSquare(15);

        // Act
        int actual = magicSquare.getMagicSquareValue(7, 7);
        int expected = 113;

        // Assert - 113 calculated by hand using: middle grid value = (n ^ 2 + 1) / 2
        assertEquals(expected, actual);
    }

    @Test
    public void setMagicSquareThreeValueAtOneOneUsingArray() {
        // Arrange
        MagicSquare magicSquare = new MagicSquare(3);

        // Act
        int[] pos = {1, 1};
        magicSquare.setMagicSquareValue(pos, 7);
        int actual = magicSquare.getMagicSquareValue(1, 1);
        int expected = 7;

        // Assert
        assertEquals(expected, actual);
    }

    // Testing overloaded version
    @Test
    public void setMagicSquareThreeValueAtOneOneUsingInts() {
        // Arrange
        MagicSquare magicSquare = new MagicSquare(3);

        // Act
        magicSquare.setMagicSquareValue(1, 1, 7);
        int actual = magicSquare.getMagicSquareValue(1, 1);
        int expected = 7;

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void setMagicSquareNineValueAtEightEightUsingArray() {
        // Arrange
        MagicSquare magicSquare = new MagicSquare(9);

        // Act
        int[] pos = {8, 8};
        magicSquare.setMagicSquareValue(pos, 1);
        int actual = magicSquare.getMagicSquareValue(8, 8);
        int expected = 1;

        // Assert
        assertEquals(expected, actual);
    }

    // Testing overloaded version
    @Test
    public void setMagicSquareNineValueAtEightEightUsingInts() {
        // Arrange
        MagicSquare magicSquare = new MagicSquare(9);

        // Act
        magicSquare.setMagicSquareValue(8, 8, 1);
        int actual = magicSquare.getMagicSquareValue(8, 8);
        int expected = 1;

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void shuffleMagicSquareThree() {
        // Odd shuffle number and not allowed to swap with self => guaranteed grid will have at least one shuffled element
        // Arrange
        MagicSquare magicSquare = new MagicSquare(7);

        // Act - Copy original grid using Java 8 stream array API
        int[][] expected = Arrays.stream(magicSquare.getGrid()).map(int[]::clone).toArray(int[][]::new);
        magicSquare.shuffle();
        int[][] actual = magicSquare.getGrid();

        // Assert
        Assert.assertThat(expected, IsNot.not(IsEqual.equalTo(actual)));
    }
}

/*
 University ID Number: c1948118
 Name: Suhail Ahmed
 */
