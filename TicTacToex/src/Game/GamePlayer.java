package Game;

public class GamePlayer {
    private char playerSign;

    public char getPlayerSign() {
        return playerSign;
    }

    public boolean isRealPlayer() {
        return isRealPlayer;
    }

    private boolean isRealPlayer = true;

    public GamePlayer(boolean isRealPlayer, char playerSign) {
        this.isRealPlayer = isRealPlayer;
        this.playerSign = playerSign;
    }


}
