package SudokuGame;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;

/**
 * Manages user input for making guesses in the Sudoku game.
 * Provides a text field for entering guesses and a button to submit them.
 */
public class Guess {

    private Button submitGuessButton;
    private TextField guessField;
    private SudokuGame game; 

    /**
     * Constructs a Guess object with associated UI components.
     * @param game The main game class, used to interact back with the game state.
     */
    public Guess(SudokuGame game, CanvasWindow canvas, Grid grid) {
        this.game = game; 
        guessField = new TextField();
        guessField.setCenter(170, 550);

        submitGuessButton = new Button("Guess!");
        submitGuessButton.setCenter(280, 550);
        submitGuessButton.onClick( () -> {
            addGuess(canvas, grid);
            guessField.setText("");
        });
    }

    /**
     * Getter for the submit guess button
     * @return submit guess button
     */
    public Button getSubmitGuessButton() {
        return submitGuessButton; 
    }

     /**
     * Getter for the guess text field 
     * @return guess text field 
     */
    public TextField getGuessField() {
        return guessField; 
    }

    /**
     * Validates inputted guesses and adds them to the canvas.
     * @param canvas is the main Sudoku game canvas
     * @param grid is the grid being played on
     */
    public void addGuess(CanvasWindow canvas, Grid grid) {
        try {
            int guess = Integer.parseInt(guessField.getText());
            if (guess >= 1 && guess <= 9) {
                game.updateGridWithGuess(guess, game.getSelectedRow(), game.getSelectedCol());
                gameWon(canvas, grid);
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        }
    }

    /**
     * Checks if all cells in the guessGrid match the correctGrid, indicating the game is won.
     * @return true if all guessed numbers match the solution
     */
    public boolean checkAllGuessed(Grid grid){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(grid.getGuessGrid()[i][j]!=(grid.getCorrectGrid()[i][j])){
                    return false;
                }
            }
        }
        return true;
    }

     /**
     * Displays the win message if the game is won after all cells are correctly guessed.
     */
    public void gameWon(CanvasWindow canvas, Grid grid){
        if (checkAllGuessed(grid)) {
            canvas.removeAll();
            GraphicsText win = new GraphicsText("YOU WIN!!", 175,225);
            win.setFont("monospaced", FontStyle.BOLD_ITALIC, 24);
            canvas.add(win);
            canvas.draw();
        }
    }
    
}
