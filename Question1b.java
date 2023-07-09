/**
 * Solution to 1b - Magic Square Game
 */
public class Question1b {

    public static void main(String[] args) {
        // Check if command line argument passed to turn off colours
        if (args.length > 0 && args[0].equalsIgnoreCase("off")) {
            VFX.offColours();
        }

        GameManager gameManager = new GameManager();
        // Starts a new game
        gameManager.startNewGame();
    }
}
/*
 University ID Number: c1948118
 Name: Suhail Ahmed
 */
