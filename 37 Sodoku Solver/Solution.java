class Solution {   
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }
    
    private boolean backtrack(char[][] board, int row, int col) {
        if (col == 9) {
            col = 0;
            row++;
        }
        
        if (row == 9) 
            return true;
        
        if (board[row][col] != '.') 
            return backtrack(board, row, col+1);
        
        for (char c = '1'; c <= '9'; c++) 
            if (isValidFill(board, row, col, c)) {
                board[row][col] = c;
                if (backtrack(board, row, col+1)) 
                    return true;
                board[row][col] = '.';
            }
        
        return false;
    }
    
    private boolean isValidFill(char[][] board, int x, int y, char c) {
        for (int i = 0; i < 9; i++)
            if (board[i][y] == c || board[x][i] == c || board[x/3*3+i/3][y/3*3+i%3] == c) 
                return false;
        
        return true;
    }
}
