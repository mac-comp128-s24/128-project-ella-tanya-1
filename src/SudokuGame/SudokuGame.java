package SudokuGame;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;

import java.awt.Color;

public class SudokuGame {
    private static final int CANVAS_WIDTH = 450;
    private static final int CANVAS_HEIGHT = 1000;
    private static final int BOX_DIM = 50; 
    private CanvasWindow canvas;  
    private GraphicsText sudokuGame; 
    private Button showAnswer; 
    private Button hideAnswer; 
    private int selectedRow = -1; 
    private int selectedCol = -1; 
    private Guess guess; 

    int[][] initialGrid = {
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

    int[][] correctGrid = {
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

    public int[][] guessGrid = {
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

public SudokuGame() {
    canvas = new CanvasWindow("Sudoku!", CANVAS_WIDTH, CANVAS_HEIGHT);
    canvas.setBackground(Color.WHITE);

    sudokuGame = new GraphicsText("Sudoku!"); 
   // sudokuGame.setStrokeWidth(1.5); 
    sudokuGame.setFontSize(30);
    sudokuGame.setCenter(225, 500);
    sudokuGame.setFillColor(Color.decode("#6A0E3C"));
    sudokuGame.setFont("monospaced", FontStyle.BOLD, 25);
    sudokuGame.setFilled(true);
    canvas.add(sudokuGame);

    guess = new Guess(this);
    canvas.add(guess.getGuessField());
    canvas.add(guess.getSubmitGuessButton()); 

    showAnswer = new Button("Show Answer"); 
    showAnswer.setCenter(225, 600);
    canvas.add(showAnswer);
    showAnswer.onClick( () -> {
        canvas.removeAll();
        createBigGrid();
        createGrid(correctGrid);
        showAnswerOnGrid(correctGrid);
        canvas.add(guess.getGuessField());
        canvas.add(guess.getSubmitGuessButton()); 
        canvas.add(showAnswer);
        canvas.add(hideAnswer);
    });

    hideAnswer = new Button("Hide Answer");
    hideAnswer.setCenter(225, 650);
    canvas.add(hideAnswer);
    
    hideAnswer.onClick( () ->  {
        canvas.removeAll();
        createBigGrid();
        createGrid(guessGrid);
         canvas.add(guess.getGuessField());
         canvas.add(guess.getSubmitGuessButton()); 
         canvas.add(showAnswer);
         canvas.add(hideAnswer);
    });

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
    createGrid(guessGrid);

    }

    public int getSelectedRow() {
        return selectedRow; 
    }

    public int getSelectedCol() {
        return selectedCol; 
    }

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

    public void createGrid(int[][] grid) {
        for (int i = 0; i < 81; i++) {
            int row = i / 9; // Calculate row index
            int col = i % 9; // Calculate column index
            int x = col * BOX_DIM;
            int y = row * BOX_DIM;
            
            Rectangle box = new Rectangle(x, y, BOX_DIM, BOX_DIM);
            box.setStrokeColor(Color.decode("#A52A67"));
            box.setStroked(true);
            canvas.add(box);
    
            int number = grid[row][col];
            if (number != 0) { // If the number is not zero, display it
                GraphicsText text = new GraphicsText(String.valueOf(number));
                text.setCenter(x + BOX_DIM / 2, y + BOX_DIM / 2); 
               text.setFilled(true);
               text.setFillColor(Color.decode("#6A0E3C"));
                canvas.add(text);
                if (grid[row][col] == (initialGrid[row][col])) {
                    text.setFont("monospaced", FontStyle.BOLD, 14);
                } else {
                    if (guessGrid[row][col] != 0&& guessGrid[row][col] != correctGrid[row][col]) {
                        guessGrid[row][col] = 0;
                        createGrid(guessGrid);
                        canvas.remove(text);
                    } 
                }
            }
        } 
        canvas.draw();
    }

    public void updateGridWithGuess(int guess, int row, int col) {
        if (row >= 0 && col >= 0) {
            int x = col * BOX_DIM;
            int y = row * BOX_DIM;
            GraphicsText guessText = new GraphicsText(String.valueOf(guess), x + BOX_DIM / 2, y + BOX_DIM / 2);
            guessText.setFont("monospaced", FontStyle.PLAIN, 14);
            guessText.setCenter(x + BOX_DIM / 2, y + BOX_DIM / 2);
            guessGrid[row][col] = guess; 
            createGrid(guessGrid);
            canvas.draw();
        } else {
            System.out.println("No cell selected.");
        }
    }
    
    // Print initial 2D array in terminal
    public void printInitialGrid() {
        for (int i = 0; i < initialGrid.length; i++) {
            for (int j = 0; j < initialGrid[i].length; j++) {
                System.out.print(initialGrid[i][j] + " ");
            }
            System.out.println(); // Moves to the next line after printing 9 numbers
        }
    
        System.out.println("-----------------------");
    
    }
    
    // Print correct 2D array in terminal
    public void printCorrectGrid() {
        for (int i = 0; i < correctGrid.length; i++) {
            for (int j = 0; j < correctGrid[i].length; j++) {
                System.out.print(correctGrid[i][j] + " ");
            }
            System.out.println(); // Moves to the next line after printing 9 numbers
        }
    }

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

