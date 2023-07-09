import java.util.Random;

/*
    Responsible for all logic related to constructing a magic square
 */
public class MagicSquare {
    // Private variables - final as array will not change, new games will instantiate new MagicSquare object
    private final int[][] grid;
    private final int LENGTH;
    private final int LENGTH_SQUARED;
    private int maxStringWidth;

    /**
     * Constructor - initializes an empty 2D array
     *
     * @param length In an n x n magic square, length corresponds to the n length of the created magic square
     */
    public MagicSquare(int length) {
        // Sets variables
        this.LENGTH = length;
        LENGTH_SQUARED = length * length;
        grid = new int[length][length];

        // Makes the grid
        initializeMagicSquare();
        // Needed to format string version of grid for toString()
        setMaxStringWidth();
    }

    // Accessors
    public int[][] getGrid() {
        return grid;
    }

    public int getLENGTH() {
        return LENGTH;
    }

    public int getLENGTH_SQUARED() {
        return LENGTH_SQUARED;
    }

    public int getMaxStringWidth() {
        return maxStringWidth;
    }

    // Override toString Method
    @Override
    public String toString() {
        StringBuilder grid = new StringBuilder();
        // Used to create values of the correct width - initialized to 0
        String formattedValue = "0";

        //Set color to red for col numbers
        grid.append(VFX.ANSI_RED);

        // Add column numbers
        for (int i = 0; i <= LENGTH + 2; i++) {

            if (i == 0) {
                grid.append("  ");
            } else if (i == 1 || i == LENGTH + 2) {
                grid.append(" \t");
            } else {
                grid.append(String.format("%" + maxStringWidth + "s", i - 1)).append("\t");
            }
        }

        grid.append(VFX.ANSI_RED).append("\n");

        // Add separators between column numbers and grid
        for (int i = 0; i <= LENGTH + 2; i++) {
            if (i == 0) {
                grid.append("  ");
            } else if (i == 1) {
                grid.append(VFX.ANSI_YELLOW).append(String.format("%" + maxStringWidth + "s", "*")).append("\t");
            } else if (i == LENGTH + 2) {
                grid.append(VFX.ANSI_YELLOW).append("*\t");
            } else {
                grid.append(VFX.ANSI_RED).append(String.format("%" + maxStringWidth + "s", "--")).append("\t");
            }
        }

        grid.append("\n\n");

        // Add values + row numbers
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j <= LENGTH + 2; j++) {

                // Format value so uses same fixed width - width calculated by setMaxString method
                if (j >= 2 && j < LENGTH + 2) {
                    formattedValue = String.format("%" + maxStringWidth + "s", this.grid[i][j - 2]);
                }

                if (j == 0) {
                    // Append row numbers
                    grid.append(VFX.ANSI_RED).append(String.format("%" + maxStringWidth + "s", i + 1)).append(" ");
                } else if (j == 1 || j == LENGTH + 2) {
                    grid.append(VFX.ANSI_RED).append("|\t");
                } else {
                    // Append Magic Square values
                    grid.append(VFX.ANSI_YELLOW).append(formattedValue).append("\t");
                }
            }
            grid.append("\n\n");
        }

        // Add bottom separators of grid
        for (int i = 0; i <= LENGTH + 2; i++) {
            if (i == 0) {
                grid.append("  ");
            } else if (i == 1) {
                grid.append(VFX.ANSI_YELLOW).append(String.format("%" + maxStringWidth + "s", "*")).append("\t");
            } else if (i == LENGTH + 2) {
                grid.append(VFX.ANSI_YELLOW).append("*\t");
            } else {
                grid.append(VFX.ANSI_RED).append(String.format("%" + maxStringWidth + "s", "--")).append("\t");
            }
        }
        // Turn off colored text
        grid.append(VFX.ANSI_RESET);

        return grid.toString();
    }


    // *** PUBLIC METHODS ***

    /**
     * Gets the value in the magic square at the provided row and col indexes
     *
     * @param x Row grid index in the magic square
     * @param y Column grid index in the magic square
     * @return The value of (row, col) in the magic square
     */
    public int getMagicSquareValue(int x, int y) {
        return grid[x][y];
    }

    /**
     * Sets a value in the magic square - used by shuffle
     *
     * @param pos   Grid index position provided as an array (x, y)
     * @param value Value to be set at the provided grid index
     */
    public void setMagicSquareValue(int[] pos, int value) {
        grid[pos[0]][pos[1]] = value;
    }

    /**
     * Overloaded version of setMagicSquareValue - used by game
     *
     * @param x     Row grid index in the magic square
     * @param y     Column grid index in the magic square
     * @param value Value to be set at the provided grid index
     */
    public void setMagicSquareValue(int x, int y, int value) {
        grid[x][y] = value;
    }

    /**
     * Shuffles magicSquare n squared times by picking a random value and swapping with a neighboring value in EITHER
     * the x or y direction - diagonals excluded and can swap with self
     */
    public void shuffle() {
        // pos and swapPos hold swapping coordinates, posValue and swapPosValue hold the values
        int[] pos, swapPos;
        int posValue, swapPosValue;

        for (int i = 0; i < LENGTH_SQUARED; i++) {
            // Find pos and swap pos
            pos = randomGridPos();
            swapPos = randomSwapPos(pos);
            // Grab values from pos and swapPos
            posValue = grid[pos[0]][pos[1]];
            swapPosValue = grid[swapPos[0]][swapPos[1]];
            // Swap the two values
            setMagicSquareValue(pos, swapPosValue);
            setMagicSquareValue(swapPos, posValue);
        }
    }


    // *** PRIVATE METHODS ***

    /**
     * Initialize the magic square according to the algorithm in 1a
     */
    private void initializeMagicSquare() {
        // Set x = 1, y = (n + 1) / 2
        int x = 1, y = (LENGTH + 1) / 2;
        // Temporary variables to hold x - 1 and y - 1
        int wrappedX, wrappedY;

        setMagicSquareValue(x - 1, y - 1, 1);

        for (int i = 2; i <= LENGTH_SQUARED; i++) {
            // Use temporary variables as they may need to wrap
            wrappedX = x - 1 < 1 ? LENGTH : x - 1;
            wrappedY = y - 1 < 1 ? LENGTH : y - 1;

            // Check if wrappedX and wrappedY is empty
            if (getMagicSquareValue(wrappedX - 1, wrappedY - 1) == 0) {
                x = wrappedX;
                y = wrappedY;
            } else {
                x = x + 1 <= LENGTH ? x + 1 : 1;
            }

            // Insert i at x, y (minus 1 again to convert row/ col to index value)
            setMagicSquareValue(x - 1, y - 1, i);
        }
    }

    /**
     * Calculate max width of string - needed so toString() printed correctly
     */
    private void setMaxStringWidth() {
        maxStringWidth = Integer.toString(LENGTH_SQUARED).length();
    }

    /**
     * Helper method to calculate swap position - randomly generate a number from 1 to 4 corresponding to
     * swap left, swap right, swap up and swap down respectively
     *
     * @param pos Array coordinates in the format (x, y) of a square in the magic square
     * @return A new array containing the coordinates of a random neighbouring square to swap values with
     */
    private int[] randomSwapPos(int[] pos) {
        Random random = new Random();
        int xSwap = pos[0], ySwap = pos[1], swapNum;

        // Generate a random number from 1 to 4
        swapNum = random.nextInt(4) + 1;

        // Four possible swap positions: 1 = left, 2 = right, 3 = up, 4 = down
        switch (swapNum) {
            case 1 -> xSwap -=1;
            case 2 -> xSwap += 1;
            case 3 -> ySwap -= 1;
            case 4 -> ySwap += 1;
            default -> System.err.println("Out of bounds number generated!");
        }

        // Reassign xSwap if out of range
        if (xSwap >= LENGTH) {
            xSwap = 0;
        } else if (xSwap < 0) {
            xSwap = LENGTH - 1;
        }

        // Reassign ySwap if out of range
        if (ySwap >= LENGTH) {
            ySwap = 0;
        } else if (ySwap < 0) {
            ySwap = LENGTH - 1;
        }

        return new int[]{xSwap, ySwap};
    }

    /**
     * Helper method that uses square size to find a random coordinate between 0 and square size (exclusive)
     *
     * @return A new array containing the coordinates of a random square in the grid in the format (x, y)
     */
    private int[] randomGridPos() {
        Random random = new Random();

        return new int[]{random.nextInt(LENGTH), random.nextInt(LENGTH)};
    }
}

/*
 University ID Number: c1948118
 Name: Suhail Ahmed
 */
