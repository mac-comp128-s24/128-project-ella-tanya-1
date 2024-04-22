package SudokuGame;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
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
    
    int x = 0;
    int y = 0;
    
    for (int i = 0; i < 81; i++) {
        if (i % 9 == 0 && i != 0) {
            x = 0;
            y += BOX_DIM;
        }

        Rectangle box = new Rectangle(x, y, BOX_DIM, BOX_DIM);
        canvas.add(box);
        x += BOX_DIM;
    }
    
    canvas.draw();
}


   public static void main(String[] args) {
    new SudokuGame();
   }
}
