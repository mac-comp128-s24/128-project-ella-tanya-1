package SudokuGame;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;

public class SudokuGame {
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 1000;
    private static final int BOX_DIM = 50; 
    private CanvasWindow canvas;  

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

    for (int i = 0; i < 81; i++) {
        int row = i / 9; // Calculate row index
        int col = i % 9; // Calculate column index
        int x = col * BOX_DIM;
        int y = row * BOX_DIM;

        Rectangle box = new Rectangle(x, y, BOX_DIM, BOX_DIM);
        canvas.add(box);

        int number = initialGrid[row][col];
        if (number != 0) {  // If the number is not zero, display it
            GraphicsText text = new GraphicsText(String.valueOf(number), x + BOX_DIM / 2, y + BOX_DIM / 2);
            text.setCenter(x + BOX_DIM / 2, y + BOX_DIM / 2);
            canvas.add(text);
        }
    } 
    canvas.draw();

    // Print 2D array in terminal
    for (int i = 0; i < initialGrid.length; i++) {
        for (int j = 0; j < initialGrid[i].length; j++) {
            System.out.print(initialGrid[i][j] + " ");
        }
        System.out.println(); // Moves to the next line after printing 9 numbers
    }
    }

   public static void main(String[] args) {
    new SudokuGame();
   }
}

