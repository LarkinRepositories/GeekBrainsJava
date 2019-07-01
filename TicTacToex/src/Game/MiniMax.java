package Game;

public class MiniMax {
    private static double maxPly;

    private MiniMax() {

    }
    static void run (GameBoard board, double maxPly) {
        if (maxPly < 1) {
            throw new IllegalArgumentException("Глубина должна быть больше 0");

        }
        MiniMax.maxPly = maxPly;
        miniMax(board.getGame().getCurrentPlayer(), board, 0);

    }
    private static int miniMax(GamePlayer player, GameBoard gameBoard, int currentPly) {
        if (currentPly++ == maxPly || gameBoard.checkWin()) {
            return score(player, gameBoard);
        }
        //if (gameBoard.getGame().getCurrentPlayer() == player) {
        if (gameBoard.getGame().getPlayersTurn() == 1) {
            return getMax(player, gameBoard, currentPly);
        }
        else {
            return getMin(player, gameBoard, currentPly);
        }
    }
    private static int getMax(GamePlayer player, GameBoard gameBoard, int currentPly) {
        double bestScore = Double.NEGATIVE_INFINITY;
        int indexOfBestMove = -1;
        for (Integer theMove: gameBoard.getAvailableMoves()) {
            GameBoard modifiedBoard = gameBoard.deepCopy();
            modifiedBoard.updateGameField(theMove / modifiedBoard.getDIMENSION(), theMove % modifiedBoard.getDIMENSION());
            int score = miniMax(player, modifiedBoard, currentPly);
            if (score >= bestScore) {
                bestScore = score;
                indexOfBestMove = theMove;
            }
        }
        gameBoard.updateGameField(indexOfBestMove / gameBoard.getDIMENSION(), indexOfBestMove % gameBoard.getDIMENSION());
        gameBoard.getButton(indexOfBestMove).setText(Character.toString(gameBoard.getGame().getCurrentPlayer().getPlayerSign()));
        return (int) bestScore;
    }
    private static int getMin(GamePlayer player, GameBoard gameBoard, int curentPly) {
        double bestScore = Double.POSITIVE_INFINITY;
        int indexOfBestMove = -1;
        for (Integer theMove: gameBoard.getAvailableMoves()) {
            GameBoard modifiedBoard = gameBoard.deepCopy();
            modifiedBoard.updateGameField(theMove / gameBoard.getDIMENSION(), theMove % modifiedBoard.getDIMENSION());
            int score = miniMax(player, modifiedBoard, curentPly);
            if (score <= bestScore) {
                bestScore = score;
                indexOfBestMove = theMove;
            }
        }
        gameBoard.updateGameField(indexOfBestMove / gameBoard.getDIMENSION(), indexOfBestMove % gameBoard.getDIMENSION());
        gameBoard.getButton(indexOfBestMove).setText(Character.toString(gameBoard.getGame().getCurrentPlayer().getPlayerSign()));
        return (int) bestScore;
    }
    private static int score(GamePlayer player, GameBoard gameBoard) {
       //GamePlayer opponent = (player.isRealPlayer()) ?
       if (gameBoard.checkWin() && !gameBoard.getGame().getCurrentPlayer().isRealPlayer()) {
           return 10;
       }
       else if (gameBoard.checkWin() && gameBoard.getGame().getCurrentPlayer().isRealPlayer()) {
           return -10;
       }
       else {
           return 0;
       }
    }

}
