package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    private static final int DIMENSION = 3;
    private static int cellSize = 150;
    private GameButton[] gameButtons;
    private GameBoard gameBoard;
    private Game    game;
    public Window() {
        initWindow();
    }

}
    public void initField() {
        setBounds(cellSize * DIMENSION, cellSize * DIMENSION, 400, 300);
        setTitle("Крестики нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        JPanel controlPanel = new JPanel();
        JButton newGameButton = new JButton("Новая игра");

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyWindow(); //метод очистки поля
            }
        });
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.setSize(cellSize * DIMENSION, 150);

        JPanel gameFieldPanel = new JPanel(); //панель самой игры
        gameFieldPanel.setLayout(new GridLayout(DIMENSION, DIMENSION));
        gameFieldPanel.setSize(cellSize * DIMENSION, cellSize * DIMENSION);

        gameButtons = new GameButton[DIMENSION * DIMENSION];
        for (int i = 0; i < (DIMENSION * DIMENSION); i++) {
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            gameButtons[i] = fieldButton;
        }
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    void emptyWindow() {
        for (int i = 0; i < DIMENSION * DIMENSION; i++) {
            gameButtons[i].setText("");
        }
    }
}
