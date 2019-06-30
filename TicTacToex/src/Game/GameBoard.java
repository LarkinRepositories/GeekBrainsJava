package Game;

import javax.swing.*;

public class GameBoard extends JFrame {

    private static final int DIMENSION = 3;
    private static int cellSize = 150;
    private char[][] gameField;
    private GameButton[] gameButtons;
    private Game game; //ссылка на игру

    public GameBoard(Game currentGame) {
        this.game = currentGame;

    }

    public static int getDIMENSION() {
        return DIMENSION;
    }
    public static int getCellSize() {
        return cellSize;
    }
}
