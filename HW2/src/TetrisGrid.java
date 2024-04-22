import java.util.Scanner;
public class TetrisGrid {

    public static void main (String[] args){
        boolean[][] grid = inputGrid();
        System.out.println(clearRows(grid));
        getGrid(grid);
    }
    public static void getGrid(boolean[][] grid){
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static boolean[][] inputGrid(){
        int rows, cols;
        Scanner sc = new Scanner(System.in);
        rows = sc.nextInt();
        cols = sc.nextInt();
        boolean[][] grid = new boolean[rows][cols];
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                grid[i][j] = sc.nextBoolean();
            }
        }
        return grid;
    }
    public static int clearRows(boolean[][] grid){
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        for (int i=0; i<rows; ++i) {
            boolean isFull = true;
            for (int j=0; j<cols; ++j) {
                if (!grid[i][j]) {
                    isFull = false;
                    break;
                }
            }
            if (isFull) {
                count++;
                for (int k=i; k>0; --k) {
                    for (int j=0; j<cols; ++j) {
                        grid[k][j] = grid[k-1][j];
                    }
                }
                for (int j=0; j<cols; ++j) {
                    grid[0][j] = false;
                }
                i--;
            }
        }
        return count;
    }

}
