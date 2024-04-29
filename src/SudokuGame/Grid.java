package SudokuGame;

/**
 * Stores all information about the grid for single gameplay
 */
public class Grid {
    // Initial state of the Sudoku grid, showing zeros where user inputs are expected
    public int[][] initialGrid; 

    // Correct solutions for the Sudoku puzzle
    public int[][] correctGrid; 

    // Grid reflecting current user guesses
    public int[][] guessGrid; 

    /**
     * Constructs a new Grid instance with specified initial, correct, and guess grids.
     *
     * @param initialGrid  The initial state of the Sudoku grid, with zeros indicating user inputs.
     * @param correctGrid  The solution grid containing the correct answers.
     * @param guessGrid    The grid reflecting current user guesses.
     */
    public Grid (int [][] initialGrid, int [][] correctGrid, int [][] guessGrid){
        this.initialGrid = initialGrid; 
        this.correctGrid = correctGrid; 
        this.guessGrid = guessGrid; 
    }

    /**
     * Getter for the guess grid.
     * @return the current state of user guesses.
     */
    public int[][] getGuessGrid() {
        return guessGrid; 
    }

    /**
     * Getter for the correct grid.
     * @return the solution grid for the Sudoku puzzle.
     */
    public int[][] getCorrectGrid() {
        return correctGrid; 
    }

    /**
     * Getter for the initial grid.
     * @return the initial state of the Sudoku grid.
     */
    public int[][] getInitalGrid() {
        return initialGrid; 
    }
}
