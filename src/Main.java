import static java.lang.System.exit;

public class Main {
    // initialize param
    public static final int size = 30;
    public static final double satisfied = 0.3;
    public static final double rate_RED = 0.5;
    public static final double rate_BLUE = 1 - rate_RED;
    public static final double empty = 0.1;
    public static int[][] map = new int[size][size]; // red -> 1 | blue -> -1 | empty -> 0

    public static void main(String[] args) {
        // TODO: initialize map: randomly place red and blue on the map
        initialize_map();

        // TODO: draw the grid
        StdDraw.setXscale(0, size);
        StdDraw.setYscale(0, size);
        draw();

        // TODO: start simulation
            // TODO: keep running until no more movement will happen

    }

    public static void draw() {

    }

    public static void initialize_map() {
        int numEmpty = (int) (size * size * empty);
        int numBlue = (int) (size * size * rate_BLUE);
        int numRed = (int) (size * size * rate_RED);

        int countEmpty = size * size;
        int countBlue = 0;
        int countRed = 0;

        // put blue on map
        for (int i = 0; i < numBlue; ++i) {
            while (true) {
                int x = StdRandom.uniformInt(map.length);
                int y = StdRandom.uniformInt(map.length);
                if (map[x][y] == 0) {
                    map[x][y] = -1;
                    countBlue++;
                    countEmpty--;
                    break;
                }
            }
        }
        
        // put red on map
        for (int i = 0; i < numRed; ++i) {
            while (true) {
                int x = StdRandom.uniformInt(map.length);
                int y = StdRandom.uniformInt(map.length);
                if (map[x][y] == 0) {
                    map[x][y] = 1;
                    countRed++;
                    countEmpty--;
                    break;
                }
            }
        }

        // check
        if (countBlue != numBlue || countRed != numRed || countEmpty != numEmpty) {
            System.out.println("initialization failed");
            exit(1);
        } else {
            System.out.println("initialization successful");
        }
    }
}