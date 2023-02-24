import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {


    @org.junit.jupiter.api.Test
    void initialize_map() {
    }

    @org.junit.jupiter.api.Test
    void find_empty() {
        int[][] map = {
                { 1, 1,-1, 1,-1},
                { 0,-1,-1,-1,-1},
                { 1, 1,-1, 1,-1},
                { 1, 1, 0,-1, 0},
                {-1,-1, 1, 1, 1}
        };
        assertThat(Main.find_empty(map), anyOf(is(new int[]{1,0}), is(new int[]{3,2}), is(new int[]{3,4})));
    }

    @org.junit.jupiter.api.Test
    void isUnsatisfied() {
        int[][] map = {
                { 1, 1,-1, 1,-1},
                { 0,-1,-1,-1,-1},
                { 1, 1,-1, 1,-1},
                { 1, 1, 0,-1, 0},
                {-1,-1, 1, 1, 1}
        };
        assertFalse(Main.isUnsatisfied(0, 1, map, 0.3));
        assertFalse(Main.isUnsatisfied(2, 3, map, 0.3));
        assertTrue(Main.isUnsatisfied(1, 0, map, 0.3));
        assertTrue(Main.isUnsatisfied(0, 2, map, 0.3));
        assertTrue(Main.isUnsatisfied(1, 1, map, 0.3));
    }

    @org.junit.jupiter.api.Test
    void find_unsatisfied() {
        int[][] map1 = {
                { 1, 1, 1, 1, 1},
                { 1, 1, 1, 1, 1},
                { 1, 1, 1, 1, 1},
                { 1, 1, 0, 1, 0},
                { 1, 1, 1, 1, 1}
        };

        int[][] map2 = {
                { 1, 1, 1, 1, 1},
                { 1, 1, 1, 1, 1},
                { 1, 1,-1, 1, 1},
                { 1, 1, 0, 1, 0},
                { 1, 1, 1, 1, 1}
        };
        assertEquals(0, Main.find_unsatisfied(5, map1, 0.3).size());
        assertNotEquals(0, Main.find_unsatisfied(5, map2, 0.3).size());
    }

    @org.junit.jupiter.api.Test
    void swap() {
        int[][] map = {
                { 1, 1, 1, 1, 1},
                { 1, 1, 1, 1, 1},
                { 1, 1,-1, 1, 1},
                { 1, 1, 0, 1,-1},
                { 1, 1, 1, 1, 1}
        };

        int[][] exp = {
                { 1, 1, 1, 1, 1},
                { 1, 1, 1, 1, 1},
                { 1, 1, 0, 1, 1},
                { 1, 1,-1, 1,-1},
                { 1, 1, 1, 1, 1}
        };
        Main.swap(new int[]{2,2}, new int[]{3,2}, map);
        assertArrayEquals(exp, map);
    }

    @org.junit.jupiter.api.Test
    void move() {
        int[][] map = {
                { 1, 1, 1, 1, 1},
                { 1, 1, 1, 1, 1},
                { 1, 1,-1, 1, 1},
                { 1, 1, 0, 1,-1},
                { 1, 1, 1, 1, 1}
        };

        List<int[]> unsatisfied = new ArrayList<>();
        unsatisfied.add(new int[]{2,2});

        int[][] exp = {
                { 1, 1, 1, 1, 1},
                { 1, 1, 1, 1, 1},
                { 1, 1, 0, 1, 1},
                { 1, 1,-1, 1,-1},
                { 1, 1, 1, 1, 1}
        };
        Main.move(unsatisfied, map);
        assertArrayEquals(exp, map);
    }
}