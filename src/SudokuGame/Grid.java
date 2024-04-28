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

    public Grid (int [][] initialGrid, int [][] correctGrid, int [][] guessGrid){
        this.initialGrid = initialGrid; 
        this.correctGrid = correctGrid; 
        this.guessGrid = guessGrid; 
    }

    public int[][] getGuessGrid() {
        return guessGrid; 
    }

    public int[][] getCorrectGrid() {
        return correctGrid; 
    }

    public int[][] getInitalGrid() {
        return initialGrid; 
    }
}
