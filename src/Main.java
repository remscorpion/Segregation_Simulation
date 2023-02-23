import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Main {
    // initialize param
    public static final int SIZE = 30;
    public static final double SATISFIED = 0.3;
    public static final double RATE_RED = 0.5;
    public static final double RATE_BLUE = 1 - RATE_RED;
    public static final double EMPTY = 0.1;
    public static int[][] MAP = new int[SIZE][SIZE]; // red -> 1 | blue -> -1 | empty -> 0

    public static void main(String[] args) {
        // TODO: initialize map: randomly place red and blue on the map
        initialize_map();

        // TODO: draw the grid
        StdDraw.setXscale(0, SIZE);
        StdDraw.setYscale(0, SIZE);
        draw();

        // TODO: start simulation
            // TODO: keep running until no mor e movement will happen

    }

    /**
     * Draw the map
     */
    public static void draw() {

    }

    /**
     * Initialize the map, randomly arrange two colors on the map
     */
    public static void initialize_map() {
        int numEmpty = (int) (SIZE * SIZE * EMPTY);
        int numBlue = (int) (SIZE * SIZE * RATE_BLUE);
        int numRed = (int) (SIZE * SIZE * RATE_RED);

        int countEmpty = SIZE * SIZE;
        int countBlue = 0;
        int countRed = 0;

        // put blue on map
        for (int i = 0; i < numBlue; ++i) {
            while (true) {
                int x = StdRandom.uniformInt(MAP.length);
                int y = StdRandom.uniformInt(MAP.length);
                if (MAP[x][y] == 0) {
                    MAP[x][y] = -1;
                    countBlue++;
                    countEmpty--;
                    break;
                }
            }
        }

        // put red on map
        for (int i = 0; i < numRed; ++i) {
            while (true) {
                int x = StdRandom.uniformInt(MAP.length);
                int y = StdRandom.uniformInt(MAP.length);
                if (MAP[x][y] == 0) {
                    MAP[x][y] = 1;
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

    /**
     * Returns an empty position coordinate
     * @return An array of length 2, representing X & Y
     */
    public static int[] find_empty() {
        while(true) {
            int x = StdRandom.uniformInt(MAP.length);
            int y = StdRandom.uniformInt(MAP.length);
            if (MAP[x][y] == 0) return new int[]{x, y};
        }
    }

    /**
     * Returns true if the agent is satisfied; otherwise returns false
     * Satisfactory condition: at least 30% (@param SATISFIED) of the neighbors are also the same color
     * @param x X coordinate
     * @param y Y coordinate
     * @return A boolean value represents whether the satisfaction
     */
    public static boolean isUnsatisfied(int x, int y) {
        int count_same = 0, amount = 0;

        for (int y2 = y - 1; y2 <= y + 1; ++y2) {
            for (int x2 = x - 1; x2 <= x + 1; ++x2) {
                if (y2 >= 0 && y2 < MAP.length && x2 >= 0 && x2 < MAP.length) {
                    if (MAP[x][y] == MAP[x2][y2]) count_same++;
                    amount++;
                }
            }
        }

        return (double) (count_same / amount) >= SATISFIED;
    }

    public static ArrayList<Array> find_unsatisfied() {

    }
}