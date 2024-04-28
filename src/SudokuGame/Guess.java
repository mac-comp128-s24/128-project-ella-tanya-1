package SudokuGame;

import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;


public class Guess {

    private Button submitGuessButton;
    private TextField guessField;
    private SudokuGame game; 

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

    public Button getSubmitGuessButton() {
        return submitGuessButton; 
    }

    public TextField getGuessField() {
        return guessField; 
    }

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
