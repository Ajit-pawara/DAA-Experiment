/*
8-Queens using backtracking
Time: exponential worst-case O(N!) roughly for N-Queens
Space: O(N^2) board or O(N) for column/diagonals
CO4
*/
public class EightQueens {
static int N = 8;
static boolean solve() {
int[] board = new int[N]; // board[col] = row of queen in that column
return place(board, 0);
}
static boolean place(int[] board, int col) {
if (col==N) { print(board); return true; }
for (int row=0; row<N; row++){
if (isSafe(board,col,row)){
board[col]=row;
if (place(board,col+1)) return true; // find one solution
}
}
return false;
}
static boolean isSafe(int[] board, int col, int row){
for (int c=0;c<col;c++){
int r = board[c];
if (r==row) return false;
if (Math.abs(r-row)==Math.abs(c-col)) return false;
}
return true;
}
static void print(int[] b){
for (int r=0;r<N;r++){
for (int c=0;c<N;c++) System.out.print((b[c]==r?"Q":".") + " ");
System.out.println();
}
}
public static void main(String[] args){ solve(); }
}

