package Game;

public class GamePlayer {
    private char playerSign; //символ игрока
    private boolean realPlayer; //компьютерный или реальный игрок

    public GamePlayer(boolean isRealPlayer, char playerSign) { //конструктор класса
        this.realPlayer = isRealPlayer;
        this.playerSign = playerSign;
    }

    public boolean isRealPlayer() { //геттер
        return realPlayer;
    }
    public char getPlayerSign() {
        return playerSign;
    } //геттер
}
