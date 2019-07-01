package Game;

public class MiniMax {
    public int miniMax(GameBoard board) {
        if (board.checkWin()) {
            return  score(board); //TODO: добавить функцию скоринга
        }
    }
}
