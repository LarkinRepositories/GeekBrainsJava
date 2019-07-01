package Game;

import java.util.ArrayList;

class GameBoard  {

    private static final int DIMENSION = 3;
    private static int cellSize = 150;
    private static final char NULLSYMBOL = '\u0000';
    private char[][] gameField;
    private Game game; //ссылка на игру
    private ArrayList<Integer> availableMoves;


    GameBoard(Game currentGame) {
        this.availableMoves = new ArrayList<>();
        this.game = currentGame;
        initialize();
        emptyField();

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
    public void initialize() {
        gameField = new char[DIMENSION][DIMENSION];
        for (int i = 0; i < (DIMENSION * DIMENSION); i++) {
            availableMoves.add(i);
            int x = i / GameBoard.DIMENSION;
            int y = i % GameBoard.DIMENSION;
            gameField[y][x] = NULLSYMBOL;
        }

    }

    public void emptyField() {
        for (int i = 0; i < DIMENSION * DIMENSION; i++) {
            int x = i / GameBoard.DIMENSION;
            int y = i % GameBoard.DIMENSION;
            gameField[y][x] = NULLSYMBOL;
        }
        availableMoves.clear();
    }

    public boolean isTurnable(int x, int y) {
        return gameField[y][x] == NULLSYMBOL;
    }

    public void updateGameField(int x, int y) {
        gameField[y][x] = game.getCurrentPlayer().getPlayerSign();
        availableMoves.remove(Integer.valueOf(y * DIMENSION +x));

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



    public ArrayList<Integer> getAvailableMoves() {
        return availableMoves;
    }

    public void setGameField(char[][] gameField) {
        this.gameField = gameField;
        gameField.clone();
    }

    public GameBoard deepCopy() {
        GameBoard board = new GameBoard(this.game);
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                board.gameField[i][j] = gameField[i][j];
            }
        }
        board.availableMoves = new ArrayList<>();
        board.availableMoves.addAll(this.availableMoves);

        return board;
    }

}
