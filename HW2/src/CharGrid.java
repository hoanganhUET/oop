import java.util.Scanner;
public class CharGrid {
    public static char[][] inputGrid(){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        sc.nextLine();

        char[][] grid = new char[a][];
        for (int i = 0; i < a; i++) {
            grid[i] = sc.nextLine().toCharArray();
        }
        return grid;
    }
    public static char inputCharacter(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a character: ");
        char ch = sc.next().charAt(0);
        return ch;
    }
    public static void countPlus(char[][] grid){
        int count = 0;
        for (int i = 1; i < grid.length - 1; ++i) {
            for (int j = 1; j < grid[i].length - 1; ++j) {
                if (isCross(grid, i, j)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static boolean isCross(char[][] grid, int x, int y) {
        char value = grid[x][y];
        int distance = 1;
        while (true) {
            if (x - distance < 0 || x + distance >= grid.length || y - distance < 0 || y + distance >= grid[0].length) {
                break;
            }
            if (grid[x - distance][y] != value || grid[x + distance][y] != value || grid[x][y - distance] != value || grid[x][y + distance] != value) {
                return false;
            }
            distance++;
        }
        return true;
    }

    public static void charArea(char[][] grid){
        char ch;
        ch = inputCharacter();
        double maxleft=1e6, maxright=-1e6, maxtop=1e6, maxbottom=-1e6;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[i].length; j++) {
                if (grid[i][j] == ch) {
                    if (maxleft> j) {
                        maxleft = j;
                    }
                    if (maxright < j) {
                        maxright = j;
                    }
                    if (maxtop > i) {
                        maxtop = i;
                    }
                    if (maxbottom < i) {
                        maxbottom = i;
                    }
                }
            }
        }
        System.out.println((maxbottom - maxtop + 1) * (maxright - maxleft + 1));
    }
    public static void main(String args[]) {
        char [][] grid = inputGrid();
        charArea(grid);
        countPlus(grid);
    }
}