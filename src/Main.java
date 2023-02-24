import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class Main {
    // initialize param


    public static void main(String[] args) {
        final int SIZE = 30;
        final double SATISFIED = 0.3;
        final double RATE_RED = 0.5;
        final double RATE_BLUE = 1 - RATE_RED;
        final double EMPTY = 0.1;
        int[][] MAP = new int[SIZE][SIZE]; // red -> 1 | blue -> -1 | empty -> 0


        // TODO: initialize map: randomly place red and blue on the map
        initialize_map(SIZE, EMPTY, RATE_BLUE, RATE_RED, MAP);

        // TODO: draw the grid
        StdDraw.setXscale(0, SIZE);
        StdDraw.setYscale(0, SIZE);
        draw();


        // TODO: start simulation
        // TODO: keep running until no mor e movement will happen
        while (true) {
            List<int[]> unsatisfied = find_unsatisfied(SIZE, MAP, SATISFIED);
            if (unsatisfied.size() != 0) {
                move(unsatisfied, MAP);
                draw();
            } else {
                break;
            }
        }


    }

    /**
     * Draw the map
     */
    public static void draw() {

    }

    /**
     * Initialize the map, randomly arrange two colors on the map
     */
    public static void initialize_map(int SIZE, double EMPTY, double RATE_BLUE, double RATE_RED, int[][] MAP) {
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
    public static int[] find_empty(int[][] MAP) {
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
    public static boolean isUnsatisfied(int x, int y, int[][] MAP, double SATISFIED) {
        if (MAP[x][y] == 0) return true; // it's empty, return true

        double count_same = 0, amount = 0;

        for (int y2 = y - 1; y2 <= y + 1; ++y2) {
            for (int x2 = x - 1; x2 <= x + 1; ++x2) {
                if (y2 >= 0 && y2 < MAP.length && x2 >= 0 && x2 < MAP.length) {
                    if (MAP[x][y] == MAP[x2][y2]) count_same += 1;
                    amount += 1;
                }
            }
        }
        return --count_same / amount >= SATISFIED; // count_diff needs to decrease 1, because [x,y] is itself
    }

    /**
     * FInd all unsatisfied agents
     * @return a list which includes all unsatisfied agents
     */
    public static List<int[]> find_unsatisfied(int SIZE, int[][] MAP, double SATISFIED) {
        List<int[]> unsatisfied = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!isUnsatisfied(i, j, MAP, SATISFIED)) unsatisfied.add(new int[] {i, j});
            }
        }

        return unsatisfied;
    }

    /**
     * Move agent from origin to target
     * @param origin array that represent original coordinate [0]->X | [1]->Y
     * @param target array that represent target coordinate   [0]->X | [1]->Y
     */
    public static void swap(int[] origin, int[] target, int[][] MAP) {
        if (MAP[origin[0]][origin[1]] == 0 || MAP[target[0]][target[1]] != 0) {
            System.out.println("move failed: origin is empty or target isn't empty");
            exit(1);
        }

        int color = MAP[origin[0]][origin[1]];
        MAP[origin[0]][origin[1]] = 0;
        MAP[target[0]][target[1]] = color;
    }

    /**
     * Arrange a new position for each unsatisfied agent
     * @param unsatisfied A List that includes all unsatisfied agents
     */
    public static void move(List<int[]> unsatisfied, int[][] MAP) {
        for (int[] c: unsatisfied) {
            int[] target = find_empty(MAP);
            swap(c, target, MAP);
        }
    }
}