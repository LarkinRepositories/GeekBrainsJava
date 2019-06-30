package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class GameBoard extends JFrame {

    private static final int DIMENSION = 3;
    private static int cellSize = 150;
    private char[][] gameField;
    private GameButton[] gameButtons;
    private Game game; //ссылка на игру
    private HashSet<Integer> movesAvailable; //множество допустимых значений хода для алгоритма MiniMax
    private static char nullSymbol = '\u0000';

    public GameBoard(Game currentGame) {
        this.game = currentGame;
        initField();

    }

    public static int getDIMENSION() {
        return DIMENSION;
    }
    public static int getCellSize() {
        return cellSize;
    }

    private void initField() {
        setBounds(cellSize * DIMENSION, cellSize * DIMENSION, 400, 300);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyField();
            }
        });

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.setSize(cellSize * DIMENSION, 150);

        JPanel gameFieldPanel = new JPanel();
        gameFieldPanel.setLayout(new GridLayout(DIMENSION, DIMENSION));
        gameFieldPanel.setSize(cellSize*DIMENSION, cellSize * DIMENSION);

        gameField = new char[DIMENSION][DIMENSION];
        gameButtons = new GameButton[DIMENSION*DIMENSION];
        movesAvailable = new HashSet<>();

        for (int i = 0; i  < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                gameField[i][j] = nullSymbol;
            }
        }

        movesAvailable.clear();

        for (int i = 0; i < DIMENSION * DIMENSION; i++) {
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            gameButtons[i] = fieldButton;
            movesAvailable.add(i);
        }

        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);
        setVisible(true);


    }
    private void emptyField() {
        for ( int i = 0; i < DIMENSION*DIMENSION; i++) {
            gameButtons[i].setText("");

            int x = i / GameBoard.DIMENSION;
            int y = i % GameBoard.DIMENSION;

            gameField[x][y] = nullSymbol;
        }
    }
    public Game getGame() {
        return game;
    }

    public boolean isTurnable(int x, int y) {
        return gameField[x][y] == nullSymbol;
    }

    public void updateGameField(int x, int y ) {
        gameField[y][x] = game.getCurrentPlayer().getPlayerSign();

    }
    public boolean checkWin() {
        char playerSymbol = getGame().getCurrentPlayer().getPlayerSign();
        return (checkWinDiagonals(playerSymbol) || checkWinVerticals(playerSymbol) || checkWinLines(playerSymbol));

    }
    private boolean checkWinDiagonals(char playerSymbol) {
        int count = 0;
        for (int i = 0; i < gameField.length; i++) { //проверка по диагонали
            if (gameField[i][i] == playerSymbol || gameField[i][gameField.length-i-1] == playerSymbol)
                count++;
        }
        if (count == DIMENSION)
            return true;
        return false;
    }
    private boolean checkWinLines(char playerSymbol) {
        for (int i = 0; i < gameField.length; i++) { //проверка по горизонтали
            int count = 0;
            for (int j = 0; j < gameField.length; j++ ) {
                if (gameField[i][j] == playerSymbol)
                    count++;
            }
            if (count == DIMENSION) {
                return true;
            }
        }
        return false;
    }
    private boolean checkWinVerticals(char playerSymbol) {
        for (int i = 0; i < gameField.length; i++) { //проверка по вертикали
            int count = 0;
            for (int j = 0; j < gameField.length; j++ ) {
                if (gameField[j][i] == playerSymbol)
                    count++;
            }
            if (count == DIMENSION) {
                return true;
            }
        }

    }
    public boolean isFull() {
       for (int i = 0; i < DIMENSION; i++) {
           for (int j = 0; j < DIMENSION; j++) {
               if (gameField[i][j] == nullSymbol) return true;
           }
        }
       return false;
    }


}
