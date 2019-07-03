package Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
    private int row;
    private int cell;
    private GameButton button;
    Random random = new Random();
    private static boolean sillyAI = false;


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
            board.getGame().showMessage("Вы выиграли!");
            board.emptyField();
        }
        else {
            board.getGame().passTurn();
        }
    }
    private void updateByAIData(GameBoard board) {
        int x = -1;
        int y = -1;
        if (sillyAI) {
            do {
                x = random.nextInt(board.getDIMENSION());
                y = random.nextInt(board.getDIMENSION());
            }
            while (board.isTurnable(x, y));
            System.out.println("Компьютер выбрал ячейку " + (y + 1) + " " + (x + 1));
            board.updateGameField(x,y);
            int cellIndex = GameBoard.getDIMENSION() * x + y;
            board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
        } else {
            int scoreY = -1;
            int scoreX = -1;
            int highScore = 0;
            for (int i = 0; i < board.getGameField().length; i++) {
                for (int j = 0; j < board.getGameField().length; j++) {
                    int cellScore = 0;
                    if (board.getGameField()[i][j] == board.getNULLSYMBOL()) {
                        if (i - 1 >= 0 && board.getGameField()[i - 1][j] == 'O') { //смотрим вверх, ищем свой символ
                            cellScore++;
                        }
                        if (i - 1 >= 0 && j - 1 >= 0 && board.getGameField()[i - 1][j - 1] == 'O') { //лево вверх
                            cellScore++;
                        }
                        if (i - 1 >= 0 && board.getGameField()[i - 1][j] == 'O') { //право вверх
                            cellScore++;
                        }
                        if (j - 1 >= 0 && board.getGameField()[i][j - 1] == 'O') { //право
                            cellScore++;
                        }
                        if (i + 1 < board.getGameField().length && j + 1 < board.getGameField().length && board.getGameField()[i + 1][j + 1] == 'O') { // низ право
                            cellScore++;
                        }
                        if (i + 1 < board.getGameField().length && board.getGameField()[i + 1][j] == 'O') { //вниз
                            cellScore++;
                        }
                        if (i + 1 < board.getGameField().length && j - 1 >= 0 && board.getGameField()[i + 1][j - 1] == 'O') { //влево
                            cellScore++;
                        }
                        if (j - 1 >= 0 && board.getGameField()[i][j - 1] == 'O') {
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
            if(scoreX != -1) {
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
        }
        if (board.checkWin()) {
            board.getGame().showMessage("Вы выиграли!");
            board.emptyField();
        }
        else {
            board.getGame().passTurn();
        }
    }
}
