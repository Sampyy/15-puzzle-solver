package Solver.IDAStarSolver;

import Solver.AStarSolver.Solver;
import Solver.DataStructures.AStarState;
import Solver.DataStructures.CustomArrayList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IDASolverTest {
    private IDASolver solver;
    private AStarState state;
    private int[] grid;

    @Before
    public void setUp() throws Exception {
        this.grid = new int[16];
        grid[0] = 5;grid[1] = 1;grid[2] = 12;grid[3] = 7;
        grid[4] = 6;grid[5] = 10;grid[6] = 11;grid[7] = 3;
        grid[8] = 2;grid[9] = 15;grid[10] = 0;grid[11] = 13;
        grid[12] = 14;grid[13] = 4;grid[14] = 9;grid[15] = 8;
        this.state = new AStarState(grid, 5, 10, new CustomArrayList<String>());
        this.solver = new IDASolver();
        solver.setGridSize(4);
        solver.setGrid(grid);
    }

    @Test
    public void solve() {
        int[] grid = new int[9];
        grid[0] = 1;
        grid[1] = 2;
        grid[2] = 3;
        grid[3] = 0;
        grid[4] = 4;
        grid[5] = 6;
        grid[6] = 7;
        grid[7] = 5;
        grid[8] = 8;

        int[] solvedGrid = new int[9];
        solvedGrid[0] = 1; solvedGrid[1] = 2; solvedGrid[2] = 3; solvedGrid[3] = 4; solvedGrid[4] = 5; solvedGrid[5] = 6; solvedGrid[6] = 7; solvedGrid[7] = 8; solvedGrid[8] = 0;

        AStarState solved = solver.solve(grid);
        assertEquals(new AStarState(solvedGrid, 3, 8), solved);
    }

    @Test
    public void findNextMoves() {
    }

    @Test
    public void checkLeftWhenLegal() {
        assertTrue(solver.checkLeft(state, 10));
    }

    @Test
    public void checkLeftWorksIllegal() {
        grid[8] = 0;
        grid[10] = 2;
        AStarState state2 = new AStarState(grid, 2, 8, new CustomArrayList<String>());


        assertFalse(solver.checkLeft(state2, 8));
    }

    @Test
    public void checkRightWorksLegal() {
        assertTrue(solver.checkRight(state, 10));
    }

    @Test
    public void checkRightWorksIllegal() {
        grid[11] = 0;
        grid[10] = 13;
        AStarState state2 = new AStarState(grid, 2, 11, new CustomArrayList<String>());


        assertFalse(solver.checkRight(state2, 11));
    }

    @Test
    public void checkDownWorksLegal() {
        assertTrue(solver.checkDown(state, 10));
    }

    @Test
    public void checkDownWorksIllegal() {
        grid[12] = 0;
        grid[10] = 14;
        AStarState state2 = new AStarState(grid, 2, 12, new CustomArrayList<String>());


        assertFalse(solver.checkDown(state2, 12));
    }

    @Test
    public void checkUpWorksLegal() {
        assertTrue(solver.checkUp(state, 10));
    }

    @Test
    public void checkUpWorksIllegal() {
        grid[2] = 0;
        grid[10] = 2;
        AStarState state2 = new AStarState(grid, 2, 2, new CustomArrayList<String>());


        assertFalse(solver.checkUp(state2, 2));
    }

    @Test
    public void arrayEquals() {
    }
}