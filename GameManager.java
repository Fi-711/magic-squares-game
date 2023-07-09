import java.util.Arrays;
import java.util.Scanner;

/*
    Controls Game logic and decomposes tasks to Manager classes
 */
public class GameManager {
    // Scanner for getting user input
    private Scanner scanner = new Scanner(System.in);

    // Manager classes
    private UIManager uiManager;
    private ScoreManager scoreManager;

    // Magic square and associated variables
    private MagicSquare magicSquare;
    private int[][] currentGrid;
    private int gridSize, solutionRowTotal;

    /**
     * Constructor - initializes GameManager class by instantiating helper classes UIManager and ScoreManager. It then
     * plays the intro and instructions
     */
    public GameManager() {
        // Initialize the manager classes
        uiManager = new UIManager();
        scoreManager = new ScoreManager();

        // Play into
        uiManager.intro();

        // Print instructions
        uiManager.instructions();
    }

    // Accessors
    public int getGridSize() {
        return gridSize;
    }

    public int[][] getCurrentGrid() {
        return currentGrid;
    }

    public int getSolutionRowTotal() {
        return solutionRowTotal;
    }

    // *** PUBLIC METHODS ***

    /**
     * Starts a game by instantiating a magic square, shuffling it and initializing appropriate variables.
     */
    public void startNewGame() {
        // Make a magic square
        magicSquare = makeMagicSquare();

        // Set class variables
        gridSize = magicSquare.getLENGTH();
        solutionRowTotal = gridSize * (magicSquare.getLENGTH_SQUARED() + 1) / 2;

        // Shuffle grid and store it in currentGrid
        magicSquare.shuffle();
        // Will point to same array as MagicSquare GRID so does not need to be updated individually
        currentGrid = magicSquare.getGrid();

        // Set move count to 0
        scoreManager.resetMoveCount();

        // Play a game
        playGame();
    }


     // *** PRIVATE METHODS ***

    /**
     * Main game loop - prints grid to screen and takes in user input and processes their moves. On successful
     * completion displays appropriate messages and gives the player the option to play again.
     */
    private void playGame() {
        // Store user input - move is the input string, moves is the string split into 3 for processing
        String move;
        String[] moves;

        // To parse enter key from instructions
        scanner.nextLine();

        while (!hasGameWon()) {
            // Clear screen with 100 empty lines and then print grid - prevents screen cluttered with multiple grids
            uiManager.clearScreen();
            VFX.asciiText("GRID", VFX.ANSI_YELLOW, 0);
            uiManager.emptyLines(1);
            System.out.println(magicSquare);
            uiManager.emptyLines(1);
            System.out.println("\nEnter move (type c for more commands): ");

            // Grab command and trim trailing whitespace
            move = scanner.nextLine().trim();

            // Enhanced switch case to handle commands
            switch (move.toLowerCase()) {
                case "c" -> uiManager.commands(gridSize, scoreManager.getMoveCount(), solutionRowTotal);
                case "h" -> uiManager.help(gridSize);
                case "m" -> uiManager.moveNumber(scoreManager.getMoveCount());
                case "q" -> uiManager.quit();
                case "t" -> uiManager.target(solutionRowTotal);
                default -> {
                    // Split moves[] into 3 parts ignoring all extra whitespaces (through trim and regex \\s+)
                    moves = move.trim().split("\\s+", 3);
                    // Process only valid moves
                    if (isValidMove(moves)) {
                        updateGrid(moves);
                    } else {
                        System.out.printf("\n(Press %sEnter%s to Continue)", VFX.ANSI_RED, VFX.ANSI_RESET);
                        scanner.nextLine();
                    }
                }
            }
        }

        uiManager.clearScreen();

        // Play game complete message
        uiManager.gameCompleteMessage(scoreManager.getMoveCount());
        // Check if score was a high score
        scoreManager.addHighScore(gridSize);

        // Play new game?
        playAnotherGame();
    }

    /**
     * Asks user for magic square size and when a valid number is entered, the matrix is made
     *
     * @return A Magic square object of the size entered by the user
     */
    private MagicSquare makeMagicSquare() {
        int squareSize;

        System.out.printf("\nPlease enter a size for the magic square (Number must be %sodd%s and %s3%s or more): ", VFX.ANSI_RED, VFX.ANSI_RESET, VFX.ANSI_RED, VFX.ANSI_RESET);

        while (true) {
            try {
                squareSize = scanner.nextInt();

                // Checks square is odd and > 3
                if (squareSize < 3 || squareSize % 2 == 0) {
                    System.out.println("That number is not odd or less than 3. Please enter an odd number greater than or equal to 3: ");
                } else {
                    break;
                }

            } catch (Exception e) {
                // Catches wrong value type errors
                System.out.println("That's not a number. Please enter an odd number greater than or equal to 3: ");
                // Needed so can parse the enter the key
                scanner.nextLine();
            }
        }

        return new MagicSquare(squareSize);
    }

    /**
     * Checks each row, column and diagonal in currentGrid vs solutionsRowTotal to see if game has been won
     *
     * @return A boolean whether the magic square has been completed
     */
    private boolean hasGameWon() {
        // Store to keep count of diagonal values sum
        int leftDiagSum = 0, rightDiagSum = 0;

        // Check each row
        for (int i = 0; i < gridSize; i++) {
            // If any rows sum != solutionTotal, break loop and return false
            if (Arrays.stream(currentGrid[i]).sum() != solutionRowTotal) {
                return false;
            }
        }

        // Check each column total
        for (int i = 0; i < gridSize; i++) {
            int sum = 0;
            for (int j = 0; j < gridSize; j++) {
                sum += currentGrid[j][i];
            }

            // If any column sum != solutionTotal, break loop and return false
            if (sum != solutionRowTotal) {
                return false;
            }
        }

        // Check each diagonal total
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (i == j) {
                    leftDiagSum += currentGrid[i][j];
                }

                if (i + j == gridSize - 1) {
                    rightDiagSum += currentGrid[i][j];
                }
            }
        }

        // If either diagonal sum != solutionTotal, return false
        return leftDiagSum == solutionRowTotal && rightDiagSum == solutionRowTotal;
    }

    /**
     * Takes a string array and swaps the values in the magic square using methods from the MagicSquare class
     *
     * @param moves A string array of size 3 in the format row, column, direction
     */
    private void updateGrid(String[] moves) {
        // swapRowIndex and swapColIndex are the x and y swap index values
        int swapRowIndex, swapColIndex, swapRowColValue;

        // Minus 1 to convert move values to indices
        int rowIndex = Integer.parseInt(moves[0]) - 1;
        int colIndex = Integer.parseInt(moves[1]) - 1;
        int rowColVal = magicSquare.getMagicSquareValue(rowIndex, colIndex);

        // strip white space
        String direction = moves[2].strip();

        // Finds corresponding swapRowIndex, swapColIndex and wrapping them if needed
        if (direction.equalsIgnoreCase("l")) {
            swapColIndex = colIndex - 1 >= 0 ? colIndex - 1 : gridSize - 1;
            swapRowIndex = rowIndex;
        } else if (direction.equalsIgnoreCase("r")) {
            swapColIndex = colIndex + 1 < gridSize ? colIndex + 1 : 0;
            swapRowIndex = rowIndex;
        } else if (direction.equalsIgnoreCase("u")) {
            swapRowIndex = rowIndex - 1 >= 0 ? rowIndex - 1 : gridSize - 1;
            swapColIndex = colIndex;
        } else {
            swapRowIndex = rowIndex + 1 < gridSize ? rowIndex + 1 : 0;
            swapColIndex = colIndex;
        }

        // Gets the value at the swap index
        swapRowColValue = magicSquare.getMagicSquareValue(swapRowIndex, swapColIndex);

        // Swaps magic square values with move
        magicSquare.setMagicSquareValue(rowIndex, colIndex, swapRowColValue);
        magicSquare.setMagicSquareValue(swapRowIndex, swapColIndex, rowColVal);
        // Increment move count by 1
        scoreManager.incMoveCount();
    }

    /**
     * Processes the move[] and checks if it is valid otherwise prints out an error message why move was invalid.
     *
     * @param move String array which should be in the format: row column direction
     * @return If the move was valid or not
     */
    private boolean isValidMove(String[] move) {
        if (move.length != 3) {
            System.out.printf("\n%sInvalid Move%s. Move must have 3 parts only.\n", VFX.ANSI_RED, VFX.ANSI_RESET);
            return false;
        }

        try {
            int row = Integer.parseInt(move[0]);
            int column = Integer.parseInt(move[1]);
            String direction = move[2];

            // Check if valid row/ column move - row and column are index value + 1
            if (row < 1 || row > gridSize || column < 1 || column > gridSize) {
                System.out.printf("\n%sInvalid Move%s. Please choose values within the grid.\n", VFX.ANSI_RED, VFX.ANSI_RESET);
                return false;
            }

            // Check if valid direction - ignore case
            if (!direction.equalsIgnoreCase("u") && !direction.equalsIgnoreCase("d") && !direction.equalsIgnoreCase("l") && !direction.equalsIgnoreCase("r")) {
                System.out.printf("\n%sInvalid Move%s. Not a valid direction.\n", VFX.ANSI_RED, VFX.ANSI_RESET);
                return false;
            }

        } catch (Exception e) {
            System.out.printf("\n%sInvalid Move%s. Use the following format: row column direction e.g. 1 1 U\n", VFX.ANSI_RED, VFX.ANSI_RESET);
            return false;
        }

        return true;
    }

    /**
     * Processes whether user wants to play another game
     */
    private void playAnotherGame() {
        System.out.println("Play another game, there's still epic loot to be had... maybe? Y/N");
        String res = scanner.nextLine();

        if (res.equalsIgnoreCase("y") || res.equalsIgnoreCase("yes")) {
            // Prints instructions only is user wants
            System.out.println("Do you want to see the instructions again? Y/N");
            res = scanner.nextLine();

            if (res.equalsIgnoreCase("y") || res.equalsIgnoreCase("yes")) {
                uiManager.instructions();
            }

            startNewGame();

        } else {
            uiManager.thankYouMessage();
        }
    }
}

/*
 University ID Number: c1948118
 Name: Suhail Ahmed
 */
