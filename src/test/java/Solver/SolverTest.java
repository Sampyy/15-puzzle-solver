package Solver;

import Solver.AStarSolver.AStarState;
import Solver.AStarSolver.Solver;
import Solver.DataStructures.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SolverTest {
    private AStarState state;
    private int[] grid;
    private Solver solver;
    @Before
    public void setUp() throws Exception {
        this.grid = new int[16];
        grid[0] = 5;grid[1] = 1;grid[2] = 12;grid[3] = 7;
        grid[4] = 6;grid[5] = 10;grid[6] = 11;grid[7] = 3;
        grid[8] = 2;grid[9] = 15;grid[10] = 0;grid[11] = 13;
        grid[12] = 14;grid[13] = 4;grid[14] = 9;grid[15] = 8;
        this.state = new AStarState(grid, 5, 10, new CustomArrayList<Integer>());
        this.solver = new Solver();
        solver.setGridSize(4);
        solver.setGrid(grid);
    }

    @Test
    public void checkUpWorksWhenLegal() {
        assertTrue(solver.checkUp(state, 10));
    }

    @Test
    public void checkUpDoesntWorkWhenIllegal() {
        grid[2] = 0;
        grid[10] = 2;
        AStarState state2 = new AStarState(grid, 2, 2, new CustomArrayList<Integer>());


        assertFalse(solver.checkUp(state2, 2));
    }

    @Test
    public void checkDownWorksWhenLegal() {
        assertTrue(solver.checkDown(state, 10));
    }

    @Test
    public void checkDownDoesntWorkWhenIllegal() {
        grid[12] = 0;
        grid[10] = 14;
        AStarState state2 = new AStarState(grid, 2, 12, new CustomArrayList<Integer>());


        assertFalse(solver.checkDown(state2, 12));
    }
    @Test
    public void checkRightWorksWhenLegal() {
        assertTrue(solver.checkRight(state, 10));
    }

    @Test
    public void checkRightDoesntWorkWhenIllegal() {
        grid[11] = 0;
        grid[10] = 13;
        AStarState state2 = new AStarState(grid, 2, 11, new CustomArrayList<Integer>());


        assertFalse(solver.checkRight(state2, 11));
    }

    @Test
    public void checkLeftWorksWhenLegal() {
        assertTrue(solver.checkLeft(state, 10));
    }

    @Test
    public void checkLeftDoesntWorkWhenIllegal() {
        grid[8] = 0;
        grid[10] = 2;
        AStarState state2 = new AStarState(grid, 2, 8, new CustomArrayList<Integer>());


        assertFalse(solver.checkLeft(state2, 8));
    }
    @Test
    public void getSolvedAsString() {
    }

    @Test
    public void solve() {
    }

    /*@Test
    public void checkValidSteps() {
        ArrayDeque<AStarState> correctList  = new ArrayDeque<>();
        grid[9] = 0;
        grid[10] = 15;
        ArrayList<Integer> moves1 = new ArrayList<>();
        moves1.add(9);
        AStarState state1 = new AStarState(grid, 6, 9, moves1);
        correctList.add(state1);

        grid[9] = 15;
        grid[10] = 13;
        grid[11] = 0;
        ArrayList<Integer> moves2 = new ArrayList<>();
        moves2.add(11);
        AStarState state2 = new AStarState(grid, 6, 11, moves1);
        correctList.add(state2);

        grid[11] = 13;
        grid[10] = 9;
        grid[14] = 0;
        ArrayList<Integer> moves3 = new ArrayList<>();
        moves3.add(11);
        AStarState state3 = new AStarState(grid, 6, 14, moves1);
        correctList.add(state3);

        grid[14] = 9;
        grid[10] = 11;
        grid[6] = 0;
        ArrayList<Integer> moves4 = new ArrayList<>();
        moves4.add(6);
        AStarState state4 = new AStarState(grid, 6, 6, moves1);
        correctList.add(state4);

        ArrayDeque<AStarState> validSteps = solver.checkValidSteps(state);
        Boolean check = true;
        while (!correctList.isEmpty()) {
            if (!correctList.poll().equals(validSteps.poll())){
                check = false;
            }
        }
        assertTrue(check);
    }*/

    @Test
    public void solveWorks() {
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

        int[] correctList = new int[3];
        correctList[0] = 4; correctList[1] = 7; correctList[2] = 8;
        CustomArrayList<Integer> solvedList = solver.solve(grid);
        int i = 0;
        Boolean check = true;
        for(int number : correctList) {
            if (correctList[i] != number) {
                check = false;
            }
            i++;
        }
        assertTrue(check);
    }

}