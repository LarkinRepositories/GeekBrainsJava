package Game;

import javax.swing.*;

public class GameButton extends JButton {
    private int buttonIndex;
    private GameBoard board;

    public GameButton(int gameButtonIndex, GameBoard board) {
        buttonIndex = gameButtonIndex;
        this.board = board;

        int rowNum = buttonIndex/ GameBoard.getDIMENSION();
        int cellNum = buttonIndex % GameBoard.getDIMENSION();

        setSize(GameBoard.getCellSize() -5, GameBoard.getCellSize() - 5);
        addActionListener(new GameActionListener(rowNum, cellNum, this));

    }
    ///
    public GameBoard getBoard() {
        return board;
    }
}
