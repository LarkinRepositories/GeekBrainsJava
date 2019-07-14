package Game;

import java.util.Random;

public class GamePlayer {
    private char playerSign;
    private boolean isRealPlayer;

    public char getPlayerSign() {
        return playerSign;
    }
    public boolean isRealPlayer() {
        return isRealPlayer;
    }

    GamePlayer(boolean isRealPlayer, char playerSign) {
        this.isRealPlayer = isRealPlayer;
        this.playerSign = playerSign;
    }
    void updateByPlayersData(GameButton button) {
        int row = button.getButtonIndex() / GameBoard.getDIMENSION();
        int cell = button.getButtonIndex() % GameBoard.getDIMENSION();
        button.getBoard().updateGameField(row,cell);
        button.setText(Character.toString(button.getBoard().getGame().getCurrentPlayer().getPlayerSign()));
        if (button.getBoard().checkWin()) {
            button.getBoard().getGame().showMessage("Вы выиграли");
            button.getBoard().emptyField();
        }
        else {
            button.getBoard().getGame().passTurn();
        }
    }
    void updateByAIData(GameButton button) {
        Random random = new Random();
        int scoreX = -1;
        int scoreY = -1;
        int highScore = 0;
        char[][] map = button.getBoard().getGameField();
        int row = -1;
        int cell = -1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                int cellScore = 0;
                if (map[i][j] == GameBoard.NULLSYMBOL) {
                    if (i - 1 >= 0 && map[i - 1][j] == button.getBoard().getGame().getCurrentPlayer().getPlayerSign()) { //смотрим вверх, ищем свой символ
                        cellScore++;
                    }
                    if (i - 1 >= 0 && j - 1 >= 0 && map[i - 1][j - 1] == button.getBoard().getGame().getCurrentPlayer().getPlayerSign()) { //лево вверх
                        cellScore++;
                    }
                    if (i - 1 >= 0 && map[i - 1][j] == button.getBoard().getGame().getCurrentPlayer().getPlayerSign()) { //право вверх
                        cellScore++;
                    }
                    if (j - 1 >= 0 && map[i][j - 1] == button.getBoard().getGame().getCurrentPlayer().getPlayerSign()) { //право
                        cellScore++;
                    }
                    if (i + 1 < map.length && j + 1 < map.length && map[i + 1][j + 1] == button.getBoard().getGame().getCurrentPlayer().getPlayerSign()) { // низ право
                        cellScore++;
                    }
                    if (i + 1 < map.length && map[i + 1][j] == button.getBoard().getGame().getCurrentPlayer().getPlayerSign()) { //вниз
                        cellScore++;
                    }
                    if (i + 1 < map.length && j - 1 >= 0 && map[i + 1][j - 1] == button.getBoard().getGame().getCurrentPlayer().getPlayerSign()) { //влево
                        cellScore++;
                    }
                    if (j - 1 >= 0 && map[i][j - 1] == button.getBoard().getGame().getCurrentPlayer().getPlayerSign()) {
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
        if(scoreX == -1) {
            do {
                row = random.nextInt(GameBoard.getDIMENSION());
                cell = random.nextInt(GameBoard.getDIMENSION());
            }
            while (!button.getBoard().isTurnable(row, cell));
        }
        if (scoreX != -1) {
            row = scoreX;
            cell = scoreY;
        }
        button.getBoard().updateGameField(row,cell);
        int buttonIndex = GameBoard.getDIMENSION()* row + cell;
        button.getBoard().getButton(buttonIndex).setText(Character.toString(button.getBoard().getGame().getCurrentPlayer().getPlayerSign()));
        if (button.getBoard().checkWin()) {
            button.getBoard().getGame().showMessage("Выиграл компьютер");
            button.getBoard().emptyField();
        }
        else {
            button.getBoard().getGame().passTurn();
        }

    }

}
