/** 289. Game of Life
  * Link: https://leetcode.com/problems/game-of-life/description/
  * This is an interesting problem. We can easily do it with O(mn) space (make another copy of the matrix) or with O(min(m, n))
  * (cache each row or each column). But this problem requires to solve IN-PLACE. 
  *
  * For this problem, the values in the matrix are in int type but they only use 0 and 1. The key point here is that we could 
  * use other integers to encode more information (e.g. use 2 to mean that it is live in this round but dead in the next round)
  *
  * /
  
public class Solution {
    // the key point of this problem is that how we update the information in place that it will not affect the rest.
    
    public void gameOfLife(int[][] board) {
        int r = board.length;
        int c = board[0].length;
        
        for (int i = 0; i < r; i++) 
            for (int j = 0; j < c; j++) {
                int livingCells = countLivingCells(board, i, j);
                if (board[i][j] != 0 && (livingCells < 2 || livingCells > 3)) board[i][j] = 2;
                if (board[i][j] == 0 && livingCells == 3) board[i][j] = 3;
            }
        
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 2) board[i][j] = 0; 
                if (board[i][j] == 3) board[i][j] = 1;
            }
    }
    
    private int countLivingCells(int[][] board, int i, int j) {
        int r = board.length;
        int c = board[0].length;
        
        int count = 0;
        for (int row = i-1; row <= i+1; row++)
            for (int col = j-1; col <= j+1; col++) {
                int val = (row >= 0 && row < r && col >= 0 && col < c)? board[row][col] : 0;
                if ((val == 1 || val == 2) && !(row == i && col == j)) count++;
            }
        return count;
    }
}
