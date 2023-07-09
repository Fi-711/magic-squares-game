import java.util.Scanner;

/**
 * Solution to Q1a
 */
public class Question1a {

    public static void main(String[] args) {
        // Check if command line argument passed to turn off colours
        if (args.length > 0 && args[0].equalsIgnoreCase("off")) {
            VFX.offColours();
        }

        // Make a magic square using user input
        MagicSquare magicSquare = makeMagicSquare();
        // Print magic square to console using toString()
        System.out.println(magicSquare);
    }

    /**
     * Asks user for magic square size and when a valid number is entered, the matrix is made
     *
     * @return A magic square object with the user's desired grid size
     */
    public static MagicSquare makeMagicSquare() {
        Scanner scanner = new Scanner(System.in);
        int squareSize;

        System.out.println("\nPlease enter a size for the magic square (Number must be odd and 1 or more): ");

        while (true) {
            try {
                squareSize = scanner.nextInt();

                // Checks square is odd and > 0
                if (squareSize < 1 || squareSize % 2 == 0) {
                    System.out.println("\nThat number is not odd or less than 1. Please enter an odd number greater than or equal to 1: ");
                } else {
                    break;
                }

            } catch (Exception e) {
                // Catches wrong value type errors
                System.out.println("\nThat's not a number. Please enter an odd number greater than or equal to 1: ");
                // Needed so can parse the enter the key
                scanner.nextLine();
            }
        }

        return new MagicSquare(squareSize);
    }
}

/*
 University ID Number: c1948118
 Name: Suhail Ahmed
*/

