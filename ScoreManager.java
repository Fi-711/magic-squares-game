import java.util.HashMap;

/**
 * Handles move logic and high scores
 */
public class ScoreManager {
    private HashMap<Integer, Integer> highScore = new HashMap<>();
    private int moveCount = 0;

    // Accessors
    public int getMoveCount() {
        return moveCount;
    }

    public HashMap<Integer, Integer> getHighScore() {
        return highScore;
    }

// *** PUBLIC METHODS ***

    /**
     * Reset moveCount - used at the start of every game
     */
    public void resetMoveCount() {
        moveCount = 0;
    }

    /**
     * Increment moveCount by one
     */
    public void incMoveCount() {
        moveCount++;
    }

    /**
     * Compares final score to scores in the highScore hash map and informs user how well they performed
     *
     * @param gridSize The size of the grid played to be checked against hsh map keys
     */
    public void addHighScore(int gridSize) {
        // Check if grid size played before
        if (highScore.containsKey(gridSize)) {
            // Check if moves is lower than current grid size high score in hash map
            if (moveCount < highScore.get(gridSize)) {
                System.out.printf("""
                                                
                        %sNEW HIGH SCORE!%s That's a new high score. %s%s%s moves beats your previous move total of %s%s%s for 
                        grid size %d
                                                
                        """, VFX.ANSI_RED, VFX.ANSI_RESET, VFX.ANSI_RED, moveCount, VFX.ANSI_RESET, VFX.ANSI_YELLOW,
                        highScore.get(gridSize), VFX.ANSI_RESET, gridSize);
                // Add new high score
                highScore.put(gridSize, moveCount);
            } else {
                System.out.printf("""
                                                
                        You beat the game but not your previous lowest moves total was %s%d%s moves for grid size %d
                                                
                        """, VFX.ANSI_RED, highScore.get(gridSize), VFX.ANSI_RESET, gridSize);
            }
        } else {
            // Add high score if one does not exist
            System.out.printf("""
                                            
                    %sNEW HIGH SCORE!%s This is your first time beating grid size %s and consequently your move total of %s%d%s moves is a new high score!
                                            
                    """, VFX.ANSI_RED, VFX.ANSI_RESET, gridSize, VFX.ANSI_RED, moveCount, VFX.ANSI_RESET);
            highScore.put(gridSize, moveCount);
        }
    }
}

/*
 University ID Number: c1948118
 Name: Suhail Ahmed
 */
