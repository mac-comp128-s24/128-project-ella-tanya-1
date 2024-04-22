package SudokuGame;

import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;


public class Guess {

    private Button submitGuessButton;
    private TextField guessField;

    public Guess() {
        guessField = new TextField();
        guessField.setCenter(200, 700);

        submitGuessButton = new Button("Guess!");
        // submitGuessButton.onClick( () -> addGuess());
    }

    public void addGuess() {
        int guessText = Integer.valueOf(guessField.getText()); 
        if (0 < guessText && guessText <= 9) {

    
        }
    

    
    }
