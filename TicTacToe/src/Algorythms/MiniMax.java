package Algorythms;

import Game.GameBoard;
import Game.GamePlayer;

public class MiniMax {
    private static double maxPly;
    private MiniMax() {}

    /**
     * Вызывает алгоритм
     * //@param player - игрок
     * @param board - игровое поле
     * @param maxPly - максимальная глубина прохода алгоритма
     */
   public static void run(GameBoard board, double maxPly) {
         if (maxPly < 1) {
             throw new IllegalArgumentException("Maximum depth must be greater than 0");
         }
         
        MiniMax.maxPly =  maxPly;
        miniMax(board, 0);

   }

    /**
     * Основа алгоритма
     * @param board - игровое поле
     * @param currentPly - текущая глубина прохода
     * @return  - скоринг поля при текущей глубине
     */
    private static int miniMax(GameBoard board, int currentPly) {
        if (currentPly++ == maxPly || board.isGameOver()) {
            return score(board);
        }
        if (!board.getGame().getCurrentPlayer().isRealPlayer()) {
            return getMax(board, currentPly);
        }
        else {
            return getMin(board,currentPly);

        }
    }
    /**
     * Делаем ход с максимальной выгодой
     * @param board - игровое поле
     * @param currentPly - текущая глуьбина прохода алгоритма
     * @return - скоринг поля при текущей глубине
     */
    private static int getMax(GameBoard board, int currentPly) {
        double bestScore = Double.NEGATIVE_INFINITY;
        int indexOfBestMove = -1;

        for (Integer theMove : board.getAvialiableMoves()) {
            GameBoard modifiedBoard = board.getDeepCopy();
            modifiedBoard.updateGameField(theMove / board.DIMENSION, theMove % board.DIMENSION);
            int score = miniMax(modifiedBoard, currentPly);

            if (score >= bestScore) {
                bestScore = score;
                indexOfBestMove = theMove;
            }
        }

        board.updateGameField(indexOfBestMove / board.DIMENSION, indexOfBestMove % board.DIMENSION);
        int cellIndex = GameBoard.DIMENSION * indexOfBestMove / board.DIMENSION + indexOfBestMove % board.DIMENSION;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
        return (int) bestScore;
    }

    private static int getMin(GameBoard board, int currentPly) {
        double bestScore = Double.POSITIVE_INFINITY;
        int indexOfBestMove = -1;

        for (Integer theMove : board.getAvialiableMoves()) {
            GameBoard modifiedBoard = board.getDeepCopy();
            modifiedBoard.updateGameField(theMove / board.DIMENSION, theMove % board.DIMENSION);

            int score = miniMax(modifiedBoard, currentPly);

            if (score <= bestScore) {
                bestScore = score;
                indexOfBestMove = theMove;
            }
        }
        board.updateGameField(indexOfBestMove / board.DIMENSION, indexOfBestMove % board.DIMENSION);
        //int cellIndex = GameBoard.DIMENSION * indexOfBestMove / board.DIMENSION + indexOfBestMove / board.DIMENSION;
        board.getButton(indexOfBestMove).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
        return (int) bestScore;
    }
    /**
     * скоринг поля
     * @param board - игровое поле
     * @return - скоринг поля
     */
    private static int score(GameBoard board) {

        if ( !board.getGame().getCurrentPlayer().isRealPlayer() && board.checkWin()) {
            return 10;
        }
        else if (board.getGame().getCurrentPlayer().isRealPlayer() && board.checkWin()) {
            return -10;
        }
        else {
            return 0;
        }
    }
}
