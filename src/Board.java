import java.util.Arrays;

public class Board {
    public int[][] board;
    public int rows;
    public int cols;
    public int bombs;
    public int[][] displayBoard;
    public Board(int rows, int cols,int bombs, int r, int c) {
        this.rows = rows;
        this.cols = cols;
        this.board = new int[this.rows][this.cols];
        this.bombs = bombs;
        this.displayBoard = new int[this.rows][this.cols];
        for (int i = 0; i <displayBoard.length ; i++) {
            Arrays.fill(displayBoard[i],-1);
        }
        firstGenerate(r,c);
        bombDirections();
        makeMove(r,c);
    }
    public void firstGenerate(int r, int c){
        while (bombs > 0){
            int ranR =(int) (Math.random()*rows);
            int ranC =(int) (Math.random()*cols);
            if (!(ranR == r && ranC == c) && board[ranR][ranC] != -1){
                board[ranR][ranC] = -1;
                bombs--;
            }
         }
        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j <cols ; j++) {
                if (board[i][j] != -1)
                    board[i][j] = 1;
            }
        }
    }

    public void bombDirections(){
        int nearbyBombs = 0;
        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j <cols ; j++) {
                if (board[i][j] != -1){
//                  Left side check
                    if ((i-1)>=0)
                        nearbyBombs = nearbyBombs + incrementBomb(i-1,j);
//                  Right check
                    if ((i+1) != rows)
                        nearbyBombs = nearbyBombs + incrementBomb(i+1,j);
//                  Up Check
                    if ((j-1) >= 0)
                        nearbyBombs = nearbyBombs + incrementBomb(i,j-1);
                    if ((j+1) != cols)
                        nearbyBombs = nearbyBombs + incrementBomb(i,j+1);
//                  Up Left Check
                    if ((i-1) >= 0 && (j-1) >= 0)
                        nearbyBombs = nearbyBombs + incrementBomb(i-1,j-1);
//                  Down Left Check
                    if ((i-1) >= 0 && (j+1) != cols)
                        nearbyBombs = nearbyBombs + incrementBomb(i-1,j+1);
//                  Up Right Check
                    if ((i+1) !=rows && (j-1) >= 0)
                        nearbyBombs = nearbyBombs + incrementBomb(i+1,j-1);
//                  Down  Right Check
                    if ((i+1) !=rows && (j+1) != cols)
                        nearbyBombs = nearbyBombs + incrementBomb(i+1,j+1);
                }
                else if (board[i][j] == -1)
                    nearbyBombs = -1;
                board[i][j] = nearbyBombs;
                nearbyBombs = 0;
            }
        }
    }
    public int incrementBomb(int row,int col){
        if (board[row][col] == -1)
            return 1;
        return 0;
    }

    public void makeMove(int i, int j){
        if (board[i][j] == 0){
            expand(i,j);
        }
        else
            displayBoard[i][j] = board[i][j];
    }
    public void expand(int i, int j){
        displayBoard[i][j] = board[i][j];
        if (board[i][j] != 0)
            return;
//        Left Check
        if ((i-1)>=0 && displayBoard[i-1][j] != 0)
            expand(i-1,j);
//        Right check
        if ((i+1) != rows && displayBoard[i+1][j] != 0)
            expand(i+1,j);
//         Up Check
        if ((j-1) >= 0 && displayBoard[i][j-1] != 0)
            expand(i,j-1);
        if ((j+1) != cols && displayBoard[i][j+1] != 0)
            expand(i,j+1);
//      Up Left Check
        if ((i-1) >= 0 && (j-1) >= 0 && displayBoard[i-1][j-1] != 0)
            expand(i-1,j-1);
//       Down Left Check
        if ((i-1) >= 0 && (j+1) != cols && displayBoard[i-1][j+1] != 0)
            expand(i-1,j+1);
//       Up Right Check
        if ((i+1) !=rows && (j-1) >= 0 && displayBoard[i+1][j-1] != 0)
            expand(i+1,j-1);
//       Down  Right Check
        if ((i+1) !=rows && (j+1) != cols && displayBoard[i+1][j+1] != 0)
            expand(i+1,j+1);
    }
}
