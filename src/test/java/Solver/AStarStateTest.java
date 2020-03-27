package Solver;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AStarStateTest {
    private AStarState state;
    @Before
    public void setUp() throws Exception {
        int[] grid = new int[16];
        grid[0] = 5;grid[1] = 1;grid[2] = 12;grid[3] = 7;
        grid[4] = 6;grid[5] = 10;grid[6] = 11;grid[7] = 3;
        grid[8] = 2;grid[9] = 15;grid[10] = 0;grid[11] = 13;
        grid[12] = 14;grid[13] = 4;grid[14] = 9;grid[15] = 8;
        this.state = new AStarState(grid, 5, 10, new ArrayList<Integer>());
    }

    @Test
    public void hvalue() {
        assertEquals(32*state.hValueInflation, state.hvalue());
    }

    @Test
    public void makeMove() {
        int[] grid = new int[16];
        grid[0] = 5;grid[1] = 1;grid[2] = 12;grid[3] = 7;
        grid[4] = 6;grid[5] = 10;grid[6] = 11;grid[7] = 3;
        grid[8] = 2;grid[9] = 15;grid[10] = 13;grid[11] = 0;
        grid[12] = 14;grid[13] = 4;grid[14] = 9;grid[15] = 8;
        state.makeMove(11, 10);
        assertTrue(Arrays.equals(grid, state.getState()));
    }

    @Test
    public void getParent() {
        AStarState stateTest = new AStarState(new int[16], 3, 5, new ArrayList<Integer>());
        state.setParent(stateTest);

        assertEquals(stateTest, state.getParent());
    }

    @Test
    public void getGValue() {
        assertEquals(5, state.getGValue());
    }

    @Test
    public void getCurrPos() {
        assertEquals(10, state.getCurrPos());
    }

    @Test
    public void testEquals() {
        int[] grid = new int[16];
        grid[0] = 5;grid[1] = 1;grid[2] = 12;grid[3] = 7;
        grid[4] = 6;grid[5] = 10;grid[6] = 11;grid[7] = 3;
        grid[8] = 2;grid[9] = 15;grid[10] = 0;grid[11] = 13;
        grid[12] = 14;grid[13] = 4;grid[14] = 9;grid[15] = 8;
        AStarState stateTest = new AStarState(grid, 2, 3, new ArrayList<Integer>());

        assertTrue(state.equals(stateTest));
    }

    @Test
    public void comparingToNullWorks(){
        assertEquals(0, state.compareTo(null));
    }

    @Test
    public void comparingWorks() {
        int[] grid = new int[16];
        grid[0] = 5;grid[1] = 1;grid[2] = 12;grid[3] = 7;
        grid[4] = 6;grid[5] = 10;grid[6] = 11;grid[7] = 3;
        grid[8] = 2;grid[9] = 0;grid[10] = 15;grid[11] = 13;
        grid[12] = 14;grid[13] = 4;grid[14] = 9;grid[15] = 8;
        AStarState stateTest = new AStarState(grid, 2, 3, new ArrayList<Integer>());

        assertTrue(stateTest.compareTo(state) < 0);
    }
}