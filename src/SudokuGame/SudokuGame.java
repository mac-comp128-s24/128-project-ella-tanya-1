package SudokuGame;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random; 

/**
 * Main class that initializes and manages the Sudoku game interface.
 * This class handles the creation of the game window, user interactions,
 * and the display of the Sudoku grid.
 */
public class SudokuGame {
    private static final int CANVAS_WIDTH = 450;
    private static final int CANVAS_HEIGHT = 1000;
    private static final int BOX_DIM = 50; 
    private Grid grid; 
    private CanvasWindow canvas;  
    private GraphicsText sudokuGame; 
    private Button showAnswer; 
    private Button hideAnswer; 
    private int selectedRow = -1; 
    private int selectedCol = -1; 
    private Guess guess; 

    // Game 1: Initial state of the Sudoku grid, showing zeros where user inputs are expected
    int[][] initialGrid1 = {
        {9, 6, 2, 7, 0, 0, 0, 5, 0},
        {7, 0, 0, 4, 5, 6, 0, 0, 9},
        {8, 4, 0, 2, 0, 0, 3, 0, 0},
        {5, 2, 0, 0, 0, 1, 0, 7, 0},
        {0, 0, 4, 0, 3, 0, 8, 0, 5},
        {0, 0, 0, 0, 7, 5, 1, 0, 0},
        {0, 0, 6, 5, 9, 0, 0, 3, 4},
        {4, 9, 8, 0, 0, 7, 0, 0, 0},
        {0, 5, 0, 1, 0, 4, 0, 2, 0}
    };

    // Game 1: Correct solutions for the Sudoku puzzle
    int[][] correctGrid1 = {
        {9, 6, 2, 7, 8, 3, 4, 5, 1},
        {7, 3, 1, 4, 5, 6, 2, 8, 9},
        {8, 4, 5, 2, 1, 9, 3, 6, 7},
        {5, 2, 9, 8, 4, 1, 6, 7, 3},
        {1, 7, 4, 6, 3, 2, 8, 9, 5},
        {6, 8, 3, 9, 7, 5, 1, 4, 2},
        {2, 1, 6, 5, 9, 8, 7, 3, 4},
        {4, 9, 8, 3, 2, 7, 5, 1, 6},
        {3, 5, 7, 1, 6, 4, 9, 2, 8}
    };

    // Game 1: Grid reflecting current user guesses
    public int[][] guessGrid1 = {
        {9, 6, 2, 7, 0, 0, 0, 5, 0},
        {7, 0, 0, 4, 5, 6, 0, 0, 9},
        {8, 4, 0, 2, 0, 0, 3, 0, 0},
        {5, 2, 0, 0, 0, 1, 0, 7, 0},
        {0, 0, 4, 0, 3, 0, 8, 0, 5},
        {0, 0, 0, 0, 7, 5, 1, 0, 0},
        {0, 0, 6, 5, 9, 0, 0, 3, 4},
        {4, 9, 8, 0, 0, 7, 0, 0, 0},
        {0, 5, 0, 1, 0, 4, 0, 2, 0}
    };

    // Game 2: Initial state of the Sudoku grid, showing zeros where user inputs are expected
    int[][] initialGrid2 = {
        {3, 0, 0, 0, 1, 0, 2, 9, 4},
        {1, 5, 2, 0, 9, 0, 0, 0, 0},
        {0, 0, 9, 7, 2, 3, 0, 1, 6},
        {0, 0, 6, 8, 0, 0, 4, 0, 3},
        {4, 0, 5, 1, 3, 0, 0, 0, 0},
        {0, 0, 0, 6, 0, 7, 1, 2, 0},
        {0, 0, 0, 0, 0, 0, 3, 4, 7},
        {0, 8, 3, 2, 6, 0, 0, 0, 0},
        {0, 9, 0, 0, 7, 1, 6, 0, 0}
    };

    // Game 2: Correct solutions for the Sudoku puzzle
    int[][] correctGrid2 = {
        {3, 6, 7, 5, 1, 8, 2, 9, 4},
        {1, 5, 2, 4, 9, 6, 7, 3, 8},
        {8, 4, 9, 7, 2, 3, 5, 1, 6},
        {2, 1, 6, 8, 5, 9, 4, 7, 3},
        {4, 7, 5, 1, 3, 2, 8, 6, 9},
        {9, 3, 8, 6, 4, 7, 1, 2, 5},
        {6, 2, 1, 9, 8, 5, 3, 4, 7},
        {7, 8, 3, 2, 6, 4, 9, 5, 1},
        {5, 9, 4, 3, 7, 1, 6, 8, 2}
    };

    // Game 2: Grid reflecting current user guesses
    public int[][] guessGrid2 = {
        {3, 0, 0, 0, 1, 0, 2, 9, 4},
        {1, 5, 2, 0, 9, 0, 0, 0, 0},
        {0, 0, 9, 7, 2, 3, 0, 1, 6},
        {0, 0, 6, 8, 0, 0, 4, 0, 3},
        {4, 0, 5, 1, 3, 0, 0, 0, 0},
        {0, 0, 0, 6, 0, 7, 1, 2, 0},
        {0, 0, 0, 0, 0, 0, 3, 4, 7},
        {0, 8, 3, 2, 6, 0, 0, 0, 0},
        {0, 9, 0, 0, 7, 1, 6, 0, 0}
    };

     // Game 3: Initial state of the Sudoku grid, showing zeros where user inputs are expected
     int[][] initialGrid3 = {
        {9, 0, 0, 1, 0, 4, 5, 0, 0},
        {0, 0, 7, 0, 8, 0, 0, 6, 0},
        {8, 0, 0, 9, 0, 3, 0, 0, 1},
        {0, 0, 3, 0, 0, 0, 6, 0, 0},
        {0, 1, 0, 4, 0, 0, 0, 0, 0},
        {7, 0, 8, 0, 0, 0, 2, 0, 0},
        {0, 4, 0, 3, 0, 7, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 4, 0, 0},
        {0, 7, 0, 0, 0, 0, 0, 9, 0}
    };

    // Game 3: Correct solutions for the Sudoku puzzle
    int[][] correctGrid3 = {
        {9, 2, 6, 1, 7, 4, 5, 3, 8},
        {1, 3, 7, 2, 8, 5, 9, 6, 4},
        {8, 5, 4, 9, 6, 3, 7, 2, 1},
        {4, 9, 3, 7, 2, 8, 6, 1, 5},
        {2, 1, 5, 4, 3, 6, 8, 7, 9},
        {7, 6, 8, 5, 1, 9, 2, 4, 3},
        {6, 4, 9, 3, 5, 7, 1, 8, 2},
        {3, 8, 2, 6, 9, 1, 4, 5, 7},
        {5, 7, 1, 8, 4, 2, 3, 9, 6}
    };

    // Game 3: Grid reflecting current user guesses
    public int[][] guessGrid3 = {
        {9, 0, 0, 1, 0, 4, 5, 0, 0},
        {0, 0, 7, 0, 8, 0, 0, 6, 0},
        {8, 0, 0, 9, 0, 3, 0, 0, 1},
        {0, 0, 3, 0, 0, 0, 6, 0, 0},
        {0, 1, 0, 4, 0, 0, 0, 0, 0},
        {7, 0, 8, 0, 0, 0, 2, 0, 0},
        {0, 4, 0, 3, 0, 7, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 4, 0, 0},
        {0, 7, 0, 0, 0, 0, 0, 9, 0}
    };

    /**
     * Constructs the game window and initializes all components.
     */
    public SudokuGame() {
        ArrayList<Grid> gridList = new ArrayList<>();
        gridList.add(new Grid(initialGrid1, correctGrid1, guessGrid1));
        gridList.add(new Grid(initialGrid2, correctGrid2, guessGrid2));
        gridList.add(new Grid(initialGrid3, correctGrid3, guessGrid3));
        
        int gridNumber = new Random().nextInt(gridList.size());

        grid = gridList.get(gridNumber); 

        canvas = new CanvasWindow("Sudoku!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.WHITE);

        // Sudoku Text
        sudokuGame = new GraphicsText("Sudoku!"); 
        sudokuGame.setFontSize(30);
        sudokuGame.setCenter(225, 500);
        sudokuGame.setFillColor(Color.decode("#6A0E3C"));
        sudokuGame.setFont("monospaced", FontStyle.BOLD, 25);
        sudokuGame.setFilled(true);
        canvas.add(sudokuGame);

        // Guess text field and submit guess button
        guess = new Guess(this, canvas, grid);
        canvas.add(guess.getGuessField());
        canvas.add(guess.getSubmitGuessButton()); 

        // 'Show Answer' button
        showAnswer = new Button("Show Answer"); 
        showAnswer.setCenter(225, 600);
        canvas.add(showAnswer);

        // 'Show Answer' button event handler 
        showAnswer.onClick( () -> {
            canvas.removeAll();
            canvas.add(sudokuGame);
            createBigGrid();
            createGrid(grid.getCorrectGrid());
            showAnswerOnGrid(grid.getCorrectGrid());
            canvas.add(guess.getGuessField());
            canvas.add(guess.getSubmitGuessButton()); 
            canvas.add(showAnswer);
            canvas.add(hideAnswer);
        });

        // 'Hide Answer' button
        hideAnswer = new Button("Hide Answer");
        hideAnswer.setCenter(225, 650);
        canvas.add(hideAnswer);
        
        // 'Hide Answer' button event handler  
        hideAnswer.onClick( () ->  {
            canvas.removeAll();
            canvas.add(sudokuGame);
            createBigGrid();
            createGrid(grid.getGuessGrid());
            canvas.add(guess.getGuessField());
            canvas.add(guess.getSubmitGuessButton()); 
            canvas.add(showAnswer);
            canvas.add(hideAnswer);
        });

        // Event handler for clicking on boxes in the grid
        canvas.onClick(event -> {
            int x = (int) event.getPosition().getX();
            int y = (int) event.getPosition().getY();
            selectedCol = x / BOX_DIM;
            selectedRow = y / BOX_DIM;
            if (selectedRow >= 0 && selectedRow < 9 && selectedCol >= 0 && selectedCol < 9) {
                System.out.println("Selected box at (" + selectedRow + "," + selectedCol + ")");
            }
        });

        createBigGrid();
        createGrid(grid.getGuessGrid());

    }

    /*
     * Getter method for selected row 
     */
    public int getSelectedRow() {
        return selectedRow; 
    }

    /*
     * Getter method for selected column 
     */
    public int getSelectedCol() {
        return selectedCol; 
    }

    /**
     * Creates the large background grid that creates a visual separation of Sudoku sub-grids.
     */
    public void createBigGrid() {
        for (int i = 0; i < 9; i++) {
            int row = i / 3; // Calculate row index
            int col = i % 3; // Calculate column index
            int x = col * BOX_DIM*3;
            int y = row * BOX_DIM*3;
            Rectangle bigBox = new Rectangle(x, y, BOX_DIM*3, BOX_DIM*3);
            bigBox.setStrokeWidth(2);
            bigBox.setStrokeColor(Color.decode("#A52A67"));
            bigBox.setStroked(true);
            bigBox.setFillColor(Color.decode("#FDD5DF"));
            bigBox.setFilled(true);
            canvas.add(bigBox); 
        }
    }

    /**
     * Creates the interactive grid for the Sudoku game where users can make guesses.
     * @param grid the current state of the Sudoku grid to be displayed
     */
    public void createGrid(int[][] gridType) {
        for (int i = 0; i < 81; i++) {
            int row = i / 9; // Calculate row index
            int col = i % 9; // Calculate column index
            int x = col * BOX_DIM;
            int y = row * BOX_DIM;
            
            Rectangle box = new Rectangle(x, y, BOX_DIM, BOX_DIM);
            box.setStrokeColor(Color.decode("#A52A67"));
            box.setStroked(true);
            canvas.add(box);
    
            int number = gridType[row][col];
            if (number != 0) { // If the number is not zero, display it
                GraphicsText text = new GraphicsText(String.valueOf(number));
                text.setCenter(x + BOX_DIM / 2, y + BOX_DIM / 2); 
               text.setFilled(true);
               text.setFillColor(Color.decode("#6A0E3C"));
                canvas.add(text);
                if (gridType[row][col] == (grid.getInitalGrid()[row][col])) {
                    text.setFont("monospaced", FontStyle.BOLD, 14);
                } else {
                    if (grid.getGuessGrid()[row][col] != 0&& grid.getGuessGrid()[row][col] != grid.getCorrectGrid()[row][col]) {
                        grid.getGuessGrid()[row][col] = 0;
                        createGrid(grid.getGuessGrid());
                        canvas.remove(text);
                    } 
                }
            }
        } 
        canvas.draw();
    }

    /**
     * Updates the grid with a user's guess, replacing the existing number in the grid.
     * 
     * @param guess the user's guess to place in the grid
     * @param row the row index where the guess is to be placed
     * @param col the column index where the guess is to be placed
     */
    public void updateGridWithGuess(int guess, int row, int col) {
        if (row >= 0 && col >= 0) {
            int x = col * BOX_DIM;
            int y = row * BOX_DIM;
            GraphicsText guessText = new GraphicsText(String.valueOf(guess), x + BOX_DIM / 2, y + BOX_DIM / 2);
            guessText.setFont("monospaced", FontStyle.PLAIN, 14);
            guessText.setCenter(x + BOX_DIM / 2, y + BOX_DIM / 2);
            grid.getGuessGrid()[row][col] = guess; 
            createGrid(grid.getGuessGrid());
            canvas.draw();
        } else {
            System.out.println("No cell selected.");
        }
    }

    /**
     * Displays the correct and complete Sudoku puzzle on the grid. 
     * @param correctGrid the correct and complete grid
     */
    public void showAnswerOnGrid(int [][] correctGrid) {
        for (int i = 0; i < 81; i++) {
            int row = i / 9; // Calculate row index
            int col = i % 9; // Calculate column index
            int x = col * BOX_DIM;
            int y = row * BOX_DIM;

            Rectangle box = new Rectangle(x, y, BOX_DIM, BOX_DIM);
            canvas.add(box);

            int number = correctGrid[row][col];
            if (number != 0 || number > 9) {  // If the number is not zero, display it
                GraphicsText text = new GraphicsText(String.valueOf(number));
                text.setCenter(x + BOX_DIM / 2, y + BOX_DIM / 2);
                canvas.add(text);
            }
        } 
        canvas.draw();
    }


   public static void main(String[] args) {
    new SudokuGame();
   }
}

