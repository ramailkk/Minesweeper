import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener {
    JButton[][] cells;
    boolean firstMove = true;
    int bombs;
    Board game;

    public Panel(int rows, int cols, int bombs) {
        this.cells = new JButton[rows][cols];
        this.bombs = bombs;
        setLayout(null);
        setBackground(Color.GRAY);
        setFocusable(true);
        initializeBoard();
    }
    public void initializeBoard(){
        for (int i = 0; i <cells.length ; i++) {
            for (int j = 0; j <cells[0].length ; j++) {
                cells[i][j] = new JButton();
                this.add(cells[i][j]);
                cells[i][j].addActionListener(this);
                cells[i][j].setBackground(Color.pink);
                cells[i][j].setRolloverEnabled(false);
                cells[i][j].setVisible(true);
            }
        }
    }
    public void drawBoard(int x, int y, int width, int height){
        int offsetX = x;
        int offsetY = y;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j <cells[0].length ; j++) {
                cells[i][j].setBounds(offsetX,offsetY,width,height);
                offsetX = offsetX + width;
            }
            offsetX = x;
            offsetY = offsetY + height;
        }
    }

    public void setGame(Board game) {
        this.game = game;
    }

    @Override
    public void paint(Graphics g) {
        drawBoard(50,50,50,50);
    }

    @Override
    protected void paintComponent(Graphics g) {
    }
    public void updateCells(){
        for (int i = 0; i < cells.length ; i++) {
            for (int j = 0; j <cells[0].length ; j++) {
                if (game.displayBoard[i][j] > 0) {
                    cells[i][j].setText(String.valueOf(game.displayBoard[i][j]));
                } else if (game.displayBoard[i][j] == 0){
                    cells[i][j].setBackground(Color.GRAY);
                    cells[i][j].setEnabled(false);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i <cells.length ; i++) {
            for (int j = 0; j <cells[0].length ; j++) {
                if (e.getSource() == cells[i][j]){
                    if (firstMove){
                        firstMove = false;
                        setGame(new Board(cells.length,cells[0].length,bombs,i,j));
                    }
                    else {
                        game.makeMove(i,j);
                    }
                    updateCells();
                }
            }
        }
    }
}
