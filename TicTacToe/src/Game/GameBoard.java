package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class GameBoard extends JFrame {
    public static final int DIMENSION = 3; //размерность
    static int cellSize = 150; //размер одной клетки
    private static final char NULLSYMBOL = '\u0000';
    private char[][] gameField; // матрица игры
    private HashSet<Integer> movesAvailable; // множество доступных ходов для Algorhythms.MiniMax
    private int moveCount; //количество ходов - для Algorhythms.MiniMax
    private GameButton[] gameButtons; //массив кнопок
    private Game game; //ссылка на игру
    private boolean gameOver; // закончена ли игра для Algorhythms.MiniMax

    public GameBoard(Game currentGame) {
        this.game = currentGame;
        movesAvailable = new HashSet<>();
        initField();
        emptyField();
    }
    public GameButton getButton(int buttonIndex) {
        return gameButtons[buttonIndex];
    }
    /**
     * Метод инициализации и отрисовки игрового поля
     */

    private void initField() {
        //задаем основные настройки окна игры
        setBounds(cellSize*DIMENSION, cellSize*DIMENSION, 400,300 );
        setTitle("Крестики нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel controlPanel = new JPanel(); // панель управления  игрой
        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyField(); //метод очистки поля
            }
        });
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.setSize(cellSize*DIMENSION, 150);

        JPanel gameFieldPanel = new JPanel(); //панель самой игры
        gameFieldPanel.setLayout(new GridLayout(DIMENSION, DIMENSION));
        gameFieldPanel.setSize(cellSize*DIMENSION, cellSize*DIMENSION);

        gameField = new char[DIMENSION][DIMENSION];
        gameButtons = new GameButton[DIMENSION*DIMENSION];

        //инициализируем игровое поле
        for (int i = 0; i < (DIMENSION * DIMENSION); i++) {
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            gameButtons[i] = fieldButton;
            movesAvailable.add(i);
        }
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Метод очистки поля
     */
    void emptyField() {
        for (int i = 0; i < DIMENSION*DIMENSION; i++) {
            gameButtons[i].setText("");
            movesAvailable.clear();
            int x = i / GameBoard.DIMENSION;
            int y = i % GameBoard.DIMENSION;

            gameField[y][x] = NULLSYMBOL;
        }
    }
   public Game getGame() {
        return game;
   }
   public boolean isTurnable(int x, int y) {
        if  (gameField[y][x] == NULLSYMBOL) return true;
        else return false;
   }
   public void updateGameField(int x , int y) {
        gameField[y][x] = game.getCurrentPlayer().getPlayerSign();
        moveCount++;
        movesAvailable.remove(y *DIMENSION + x);

   }
   public boolean checkWin() {
        char playerSymbol = getGame().getCurrentPlayer().getPlayerSign();
        if (checkWinDiagonals(playerSymbol) || checkWinLines(playerSymbol) || checkWinVerticals(playerSymbol)) {
         gameOver = true;
         return true;
        }
        return false;
        }
   private boolean checkWinVerticals(char playerSymbol) {
       for (int i = 0; i < DIMENSION; i++) { //проверка по вертикали
           int count = 0;
           for (int j = 0; j < DIMENSION; j++ ) {
               if (gameField[j][i] == playerSymbol)
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
       for (int i = 0; i < DIMENSION; i++) { //проверка по диагонали
           if (gameField[i][i] == playerSymbol || gameField[i][DIMENSION-1] == playerSymbol)
               count++;
       }
       if (count == DIMENSION)
           return true;

       return false;
   }
   private boolean checkWinLines(char playerSymbol) {
       for (int i = 0; i < DIMENSION; i++) { //проверка по горизонтали
           int count = 0;
           for (int j = 0; j < DIMENSION; j++ ) {
               if (gameField[i][j] == playerSymbol)
                   count++;
           }
           if (count == DIMENSION) {
               return true;
           }
       }
       return false;
   }

   public boolean isFull() {
       for (int i = 0; i < DIMENSION; i++) {
           for (int j = 0; j < DIMENSION; j++) {
               if (gameField[i][j] == NULLSYMBOL) {
                   gameOver = false;
                   return false;
               }
           }
       }
   gameOver = true;
   return true;
   }
   public boolean isGameOver() {
        return gameOver;
   }

   public HashSet<Integer> getAvialiableMoves() {
        return movesAvailable;
   }

   public GameBoard getDeepCopy() {
        GameBoard board = new GameBoard(game);
        for (int i = 0; i < board.gameButtons.length; i++) {
            board.gameButtons[i] = this.gameButtons[i];
        }

        board.gameField = this.gameField;
        board.movesAvailable = new HashSet<>();
        board.movesAvailable.addAll(this.movesAvailable);
        board.moveCount = this.moveCount;
        board.gameOver = this.gameOver;
        return board;
   }
}