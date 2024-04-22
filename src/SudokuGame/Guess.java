package SudokuGame;

import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;


public class Guess {

    private Button submitGuessButton;
    private TextField guessField;

    public Guess() {
        guessField = new TextField();

        submitGuessButton = new Button("Guess!");
        // submitGuessButton.onClick( () -> addGuess());
    }

    

    
}
