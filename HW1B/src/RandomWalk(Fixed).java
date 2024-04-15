public class Main {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdDraw.setScale(-n - 0.5, n + 0.5);
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.enableDoubleBuffering();

        int x = 0, y = 0;
        int steps = 1;
        int dir = 0;
        int totalSteps = 0;
        while (Math.abs(x) < n && Math.abs(y) < n) {
            for (int i = 0; i < steps && Math.abs(x) < n && Math.abs(y) < n; i++) {
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.filledSquare(x, y, 0.45);
                switch (dir) {
                    case 0: x++; break;
                    case 1: y++; break;
                    case 2: x--; break;
                    case 3: y--; break;
                }
                totalSteps++;
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.filledSquare(x, y, 0.45);
                StdDraw.show();
                StdDraw.pause(40);
            }
            dir = (dir + 1) % 4;
            if (dir == 0 || dir == 2) {
                steps++;
            }
        }
        StdOut.println("Total steps = " + totalSteps);
    }

}
