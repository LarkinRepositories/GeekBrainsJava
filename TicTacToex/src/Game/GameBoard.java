package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameBoard extends JFrame {

    private static final int DIMENSION = 3;
    private static int cellSize = 150;

    public static char getNULLSYMBOL() {
        return NULLSYMBOL;
    }

    private static final char NULLSYMBOL = '\u0000';
    private char[][] gameField;
    private GameButton[] gameButtons;
    private Game game; //ссылка на игру

    GameBoard(Game currentGame) {
        this.game = currentGame;
        initField();

    }

    public static int getDIMENSION() {
        return DIMENSION;
    }

    public static int getCellSize() {
        return cellSize;
    }
    public char[][] getGameField() {
        return gameField;
    }
    private void initField() {
        setBounds(cellSize * DIMENSION, cellSize * DIMENSION, 400, 300);
        setTitle("Крестики нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        JPanel controlPanel = new JPanel();
        JButton newGameButton = new JButton("Новая игра");

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyField(); //метод очистки поля
            }
        });
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.setSize(cellSize * DIMENSION, 150);

        JPanel gameFieldPanel = new JPanel(); //панель самой игры
        gameFieldPanel.setLayout(new GridLayout(DIMENSION, DIMENSION));
        gameFieldPanel.setSize(cellSize * DIMENSION, cellSize * DIMENSION);

        gameField = new char[DIMENSION][DIMENSION];
        gameButtons = new GameButton[DIMENSION * DIMENSION];

        for (int i = 0; i < (DIMENSION * DIMENSION); i++) {
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            gameButtons[i] = fieldButton;
        }
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void emptyField() {
        for (int i = 0; i < DIMENSION * DIMENSION; i++) {
            gameButtons[i].setText("");
            int x = i / GameBoard.DIMENSION;
            int y = i % GameBoard.DIMENSION;

            gameField[y][x] = NULLSYMBOL;
        }
    }

    public boolean isTurnable(int x, int y) {
        return gameField[y][x] == NULLSYMBOL;
    }

    public void updateGameField(int x, int y) {
        gameField[y][x] = game.getCurrentPlayer().getPlayerSign();

    }

    public boolean isFull() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (gameField[i][j] == NULLSYMBOL) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin() {
        char playerSymbol = getGame().getCurrentPlayer().getPlayerSign();
        return checkWinDiagonals(playerSymbol) || checkWinLines(playerSymbol) || checkWinVerticals(playerSymbol);
    }

    private boolean checkWinLines(char playerSymbol) {
        for (char[] chars : gameField) {
            int count = 0;
            for (int j = 0; j < gameField.length; j++) {
                if (chars[j] == playerSymbol)
                    count++;
            }
            if (count == DIMENSION) {
                return true;
            }
        }
        return false;
    }

    private boolean checkWinDiagonals(char playerSymbol) {
        int count = 0;
        for (int i = 0; i < gameField.length; i++) { //проверка по диагонали
            if (gameField[i][i] == playerSymbol || gameField[i][gameField.length - i - 1] == playerSymbol)
                count++;
        }
        return count == DIMENSION;
    }

    private boolean checkWinVerticals(char playerSymbol) {
        for (int i = 0; i < gameField.length; i++) {
            int count = 0;
            for (char[] chars : gameField)
                if (chars[i] == playerSymbol)
                    count++;
            if (count == DIMENSION) {
                return true;
            }
        }
        return false;
    }

    public Game getGame() {
        return game;
    }

    public GameButton getButton(int buttonIndex) {
        return gameButtons[buttonIndex];
    }
}
