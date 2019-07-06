package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
    private GameButton button;

    public GameActionListener(int row, int cell, GameButton gameButton) {
        this.button = gameButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();
        int row = button.getButtonIndex() / board.getDIMENSION();
        int cell = button.getButtonIndex() % board.getDIMENSION();

        if (board.isTurnable(row, cell) ) {
            board.getGame().getCurrentPlayer().updateByPlayersData(button);
            if(board.isFull()) {
                board.getGame().showMessage("Ничья");
                board.emptyField();
            }
            else {
                board.getGame().getCurrentPlayer().updateByAIData(button);
            }
        }
        else {
            board.getGame().showMessage("Некорректный хол");
        }
    }

}
