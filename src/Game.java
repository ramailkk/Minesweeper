import javax.swing.*;

public class Game extends JFrame {
    public Game(int rows, int cols, int bombs) {
        add(new Panel(rows, cols,bombs));
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }


    public static void main(String[] args) {
        Game game = new Game(14,14,35);
    }
}