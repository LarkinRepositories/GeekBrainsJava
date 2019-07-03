package Game;

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
    ///

}
