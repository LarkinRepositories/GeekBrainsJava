package Game;
import Algorythms.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static java.lang.Double.POSITIVE_INFINITY;

public class GameActionListener implements ActionListener {
    private int row;
    private int cell;
    private GameButton button;

    public GameActionListener(int row, int cell, GameButton gButton) {
        this.row = row;
        this.cell = cell;
        this.button = gButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();
        if (board.isTurnable(row, cell)) {
            updateByPlayersData(board);
            if (board.isFull()) {
                board.getGame().showMessage("Ничья");
                board.emptyField();
            }
            else {
                updateByAIData(board);
            }
        }
        else  {
            board.getGame().showMessage("Некорректный ход!");
        }
    }
    /**
     * Ход человека
     * @param board - ссылка на игровое поле
     */

    private void updateByPlayersData(GameBoard board) {
        //обновить матрицу игры
        board.updateGameField(row,cell);

        //обновить содержимое кнопки
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Вы выиграли!");
            board.emptyField();
        }
        else {
            board.getGame().passTurn();
        }
    }
    private void updateByAIData(GameBoard board) {
       /* int x,y;
        Random rnd = new Random();
        do {
            x = rnd.nextInt(GameBoard.DIMENSION);
            y = rnd.nextInt(GameBoard.DIMENSION);
        }
        while(!board.isTurnable(x,y));
        //обновить матрицу игры
        board.updateGameField(x,y);
        //обновить содержимое кнопки
        int cellIndex = GameBoard.DIMENSION * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
        */
        Algorythms.MiniMax.run(board, POSITIVE_INFINITY);

       /* if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Выиграл компьютер");
            board.emptyField();
        }
        else {
            board.getGame().passTurn();
        } */
    }

}
