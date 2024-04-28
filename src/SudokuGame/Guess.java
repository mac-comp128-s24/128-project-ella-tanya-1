package SudokuGame;

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
    public Guess(SudokuGame game) {
        this.game = game; 
        guessField = new TextField();
        guessField.setCenter(170, 550);

        submitGuessButton = new Button("Guess!");
        submitGuessButton.setCenter(280, 550);
        submitGuessButton.onClick( () -> {
            addGuess();
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
     * Submits a guess made by the user and updates the game state.
     */
    public void addGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            if (guess >= 1 && guess <= 9) {
                game.updateGridWithGuess(guess, game.getSelectedRow(), game.getSelectedCol());
                game.gameWon();
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        }
    }
    
}
