import java.util.Scanner;

/**
 * Handles UI elements of the game - text and clearing the screen but can be extended
 */
public class UIManager {
    // Create scanner object to get user data
    private Scanner scanner = new Scanner(System.in);

     // *** PUBLIC METHODS ***

    /**
     * Clears screen by printing 100 empty lines
     */
    public void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    /**
     * Generates n number of empty lines - useful for spacing out elements correctly
     *
     * @param n Number of empty lines to generate
     */
    public void emptyLines(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
    }

    /**
     * Plays the game's intro sequence
     */
    public void intro() {
        // Game name text
        VFX.asciiText("Magic Square Game", VFX.ANSI_YELLOW, 10);
        emptyLines(3);
        System.out.printf("(Press %sEnter%s to continue)", VFX.ANSI_RED, VFX.ANSI_RESET);
        scanner.nextLine();

        // Wizard ascii art
        VFX.asciiWizard();
        System.out.printf("\nLocation: %sRed Wizard's%s Castle\n", VFX.ANSI_RED, VFX.ANSI_RESET);
        System.out.printf("\n(Press %sEnter%s to continue)", VFX.ANSI_RED, VFX.ANSI_RESET);
        scanner.nextLine();
        emptyLines(10);
        // Intro flavour text
        System.out.printf("""
                
                Welcome to the %sRed Wizard's%s Castle!
                                
                Did you know in any n sized magic square, where n is odd, if you arrange the items in a specific way,
                the total of any given row or column add up to the same number.
                                
                Why should you care? Because of Treasure... TREASURE I said!
                
                Each of the Red Wizard's loot is guarded by a Magic Square lock. Crack the grid and you might be able to give up your day job! 
                
                First select a grid size and then use the terminal to enter commands to swap numbers around in order to solve the square.                
                """, VFX.ANSI_RED, VFX.ANSI_RESET);
    }

    /**
     * Prints out instructions on how to play the game to the terminal
     */
    public void instructions() {
        System.out.printf("""
                
                Move Format: %s2 1 U%s
                                
                Where the first number is the row (%sred%s numbers left of the grid), the second number is the column 
                (%sred%s numbers above grid) and the last letter is the direction.
                                
                So %s2 1 U%s will swap the number at grid position (row 2, column 1) with the one above it, (row 1, column 1).
                Type %s'c'%s for a full commands list.
                 
                Finish a game by correctly positioning all the numbers so the total in each row, column and the main diagonals are the same.
                                
                Your total move count will be recorded so try and use as few moves as possible!
                                
                Can you fix the grid so all the numbers are in the correct order and claim your bounty?
                                
                Play the game below to try. (Press %sEnter%s to continue)              
                """, VFX.ANSI_RED, VFX.ANSI_RESET, VFX.ANSI_RED, VFX.ANSI_RESET, VFX.ANSI_RED, VFX.ANSI_RESET, 
                VFX.ANSI_RED, VFX.ANSI_RESET, VFX.ANSI_RED, VFX.ANSI_RESET, VFX.ANSI_RED, VFX.ANSI_RESET);
        scanner.nextLine();
    }

    /**
     * Prints help tips using multi line string and string formatting
     */
    public void help(int gridSize) {
        clearScreen();
        System.out.printf("""
                                
                Valid Move: | row | column | direction | e.g. %s1 1 U%s
                Valid row values: %s1%s : %s%s%s
                Valid column values: %s1%s : %s%s%s
                Valid direction values: %sU%s(p), %sD%s(own), %sL%s(eft) and %sR%s(ight)
                                
                (Press %sEnter%s to Continue)
                """, VFX.ANSI_RED, VFX.ANSI_RESET, VFX.ANSI_RED, VFX.ANSI_RESET, VFX.ANSI_RED, gridSize, VFX.ANSI_RESET,
                VFX.ANSI_RED, VFX.ANSI_RESET, VFX.ANSI_RED, gridSize, VFX.ANSI_RESET, VFX.ANSI_RED, VFX.ANSI_RESET,
                VFX.ANSI_RED, VFX.ANSI_RESET,VFX.ANSI_RED, VFX.ANSI_RESET,VFX.ANSI_RED, VFX.ANSI_RESET, VFX.ANSI_RED, VFX.ANSI_RESET);

        scanner.nextLine();
    }

    /**
     * Prints help tips using multi line string and string formatting
     */
    public void moveNumber(int moveCount) {
        clearScreen();
        System.out.printf("""
                 
                You have used %s%s%s moves so far. 
                 
                (Press %sEnter%s to Continue)
                 """, VFX.ANSI_RED, moveCount, VFX.ANSI_RESET, VFX.ANSI_RED, VFX.ANSI_RESET);

        scanner.nextLine();
    }

    /**
     * Prints solution row total target
     */
    public void target(int solutionRowTotal) {
        clearScreen();
        System.out.printf("""
                                
                Your target is: %s%s%s
                        
                (Press %sEnter%s to continue)
                """, VFX.ANSI_RED, solutionRowTotal, VFX.ANSI_RESET, VFX.ANSI_RED, VFX.ANSI_RESET);

        scanner.nextLine();
    }

    /**
     * Quits the game
     */
    public void quit() {
        System.out.println("\nSorry to see you go but the loot will still be here so hurry back, ok?\n");
        System.exit(0);
    }

    /**
     * Prints available commands
     */
    public void commands(int gridSize, int moveCount, int solutionRowTotal) {
        clearScreen();
        System.out.printf("""
                
                You can type the command here %sOR%s in the grid move section.

                Type %s'h'%s for help
                Type %s'm'%s for number of moves used so far
                Type %s't'%s to display row target
                Type %s'q'%s to quit the game
                                
                (Press %sEnter%s to continue)
                """, VFX.ANSI_RED, VFX.ANSI_RESET, VFX.ANSI_RED, VFX.ANSI_RESET, VFX.ANSI_RED, VFX.ANSI_RESET, 
                VFX.ANSI_RED, VFX.ANSI_RESET, VFX.ANSI_RED, VFX.ANSI_RESET, VFX.ANSI_RED, VFX.ANSI_RESET);
        String move = scanner.nextLine();

        // Enhanced switch case to handle commands
        switch (move.toLowerCase()) {
            case "h" -> help(gridSize);
            case "m" -> moveNumber(moveCount);
            case "q" -> quit();
            case "t" -> target(solutionRowTotal);
        }
    }

    /**
     * Prints out game complete message to the terminal
     *
     * @param moveCount Number of moves needed to complete the game
     */
    public void gameCompleteMessage(int moveCount) {
        // Congratulations Message
        VFX.asciiText("Congratulations", VFX.ANSI_YELLOW, 10);
        System.out.printf("\nYou beat the game in %s%s%s moves!\n", VFX.ANSI_RED, moveCount, VFX.ANSI_RESET);
        System.out.printf("\nAnd now for your reward...\n\nPress %sEnter%s to continue\n", VFX.ANSI_RED, VFX.ANSI_RESET);
        scanner.nextLine();

        // Reward Message
        System.out.println("A digital thumbs up and my eternal gratitude!");
        VFX.asciiThumbsUp();
        System.out.println("\nWhat? Eternal gratitude is the best prize possible! - Matt Morgan (CM1210 Lecture, 2021)\n");
    }

    /**
     * Prints out Thank You Message to the terminal
     */
    public void thankYouMessage() {
        VFX.asciiText("Thank You!", VFX.ANSI_RED, 10);
        System.out.print(VFX.ANSI_RESET);
    }
}

/*
 University ID Number: c1948118
 Name: Suhail Ahmed
 */
