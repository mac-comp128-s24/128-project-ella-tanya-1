package SudokuGame;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;

import java.awt.Color;

public class SudokuGame {
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 1000;
    private static final int BOX_DIM = 50; 
    private CanvasWindow canvas;  
    private Button showAnswer; 
    // private TextField numField; 
    // private GraphicsText numLabel; 

public SudokuGame() {
    canvas = new CanvasWindow("Sudoku!", CANVAS_WIDTH, CANVAS_HEIGHT);
    canvas.setBackground(Color.WHITE);
    
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

    for (int i = 0; i < 81; i++) {
        int row = i / 9; // Calculate row index
        int col = i % 9; // Calculate column index
        int x = col * BOX_DIM;
        int y = row * BOX_DIM;

        Rectangle box = new Rectangle(x, y, BOX_DIM, BOX_DIM);
        canvas.add(box);

        int number = initialGrid[row][col];
        if (number != 0) {  // If the number is not zero, display it
            GraphicsText text = new GraphicsText(String.valueOf(number));
            text.setCenter(x + BOX_DIM / 2, y + BOX_DIM / 2);
            canvas.add(text);
        }
    } 
    canvas.draw();

    // Print initial 2D array in terminal
    for (int i = 0; i < initialGrid.length; i++) {
        for (int j = 0; j < initialGrid[i].length; j++) {
            System.out.print(initialGrid[i][j] + " ");
        }
        System.out.println(); // Moves to the next line after printing 9 numbers
    }

    System.out.println("-----------------------");

    // Print correct 2D array in terminal
    for (int i = 0; i < correctGrid.length; i++) {
        for (int j = 0; j < correctGrid[i].length; j++) {
            System.out.print(correctGrid[i][j] + " ");
        }
        System.out.println(); // Moves to the next line after printing 9 numbers
    }

    // Back up: if we can't figure out clicks on screen, we can use text fields to get the number, x and y 
    // numLabel = new GraphicsText("Enter number: "); 
    // numLabel.setCenter(100, 580); 
    // canvas.add(numLabel); 

    // numField = new TextField(); 
    // numField.setCenter(100, 600);
    // canvas.add(numField);

    showAnswer = new Button("Show Answer"); 
    showAnswer.setCenter(500, 600);
    canvas.add(showAnswer);
    showAnswer.onClick( () -> showAnswerOnGrid(correctGrid));

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
            if (number != 0) {  // If the number is not zero, display it
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

