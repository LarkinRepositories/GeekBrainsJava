package Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

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
            } else {
                //updateBySillyAIData(board);
                updateByAIData(board);
            }
        } else {
            board.getGame().showMessage("Некорректный ход!");
        }
    }
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
    private void updateBySillyAIData(GameBoard board) {
        int x,y;
        Random rnd = new Random();
        do {
            x = rnd.nextInt(GameBoard.getDIMENSION());
            y = rnd.nextInt(GameBoard.getDIMENSION());
        }
        while(!board.isTurnable(x,y));
        //обновить матрицу игры
        board.updateGameField(x,y);
        //обновить содержимое кнопки
        int cellIndex = GameBoard.getDIMENSION() * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Выиграл компьютер");
            board.emptyField();
        }
        else {
            board.getGame().passTurn();
        }
    }
    private void updateByAIData(GameBoard board) {
        int x = -1;
        int y = -1;
        Random random = new Random();
        int scoreY = -1;
        int scoreX = -1;
        int highScore = 0;
        for (int i = 0; i < board.getGameField().length; i++) {
            for (int j = 0; j < board.getGameField().length; j++) {
                int cellScore = 0;
                if (board.isTurnable(i, j)) {
                    if (i - 1 >= 0 && board.getGameField()[i - 1][j] == board.getGame().getCurrentPlayer().getPlayerSign()) { //смотрим вверх, ищем свой символ
                        cellScore++;
                    }
                    if (i - 1 >= 0 && j - 1 >= 0 && board.getGameField()[i - 1][j - 1] == board.getGame().getCurrentPlayer().getPlayerSign()) { //лево вверх
                        cellScore++;
                    }
                    if (i - 1 >= 0 && board.getGameField()[i - 1][j] == board.getGame().getCurrentPlayer().getPlayerSign()) { //право вверх
                        cellScore++;
                    }
                    if (j - 1 >= 0 && board.getGameField()[i][j - 1] == board.getGame().getCurrentPlayer().getPlayerSign()) { //право
                        cellScore++;
                    }
                    if (i + 1 < board.getGameField().length && j + 1 < board.getGameField().length && board.getGameField()[i + 1][j + 1] == board.getGame().getCurrentPlayer().getPlayerSign()) { // низ право
                        cellScore++;
                    }
                    if (i + 1 < board.getGameField().length && board.getGameField()[i + 1][j] == board.getGame().getCurrentPlayer().getPlayerSign()) { //вниз
                        cellScore++;
                    }
                    if (i + 1 < board.getGameField().length && j - 1 >= 0 && board.getGameField()[i + 1][j - 1] == board.getGame().getCurrentPlayer().getPlayerSign()) { //влево
                        cellScore++;
                    }
                    if (j - 1 >= 0 && board.getGameField()[i][j - 1] == board.getGame().getCurrentPlayer().getPlayerSign()) {
                        cellScore++;
                    }
                }
                if (cellScore > highScore) {
                    highScore = cellScore;
                    scoreX = j;
                    scoreY = i;
                }
            }
        }
        if (scoreX != -1) {
            x = scoreX;
            y = scoreY;
        }
        if (x == -1) {
            do {
                x = random.nextInt(board.getDIMENSION());
                y = random.nextInt(board.getDIMENSION());
            }
            while (board.isTurnable(x, y));
        }
        board.updateGameField(x,y);
        int cellIndex = GameBoard.getDIMENSION() * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Выиграл компьютер");
            board.emptyField();
        }
        else {
            board.getGame().passTurn();
        }


    }
}
