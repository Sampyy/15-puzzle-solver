package Solver;
import Solver.AStarSolver.AStarState;
import Solver.AStarSolver.Solver;
import Solver.DataStructures.CustomArrayQueue;
import Solver.DataStructures.CustomPriorityQueue;
import Solver.DataStructures.CustomArrayList;
import Solver.IDAStarSolver.IDASolver;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
       int[] grid = new int[16];
       int[] grid2 = new int[25];
       int[] grid3 = new int[9];

       grid[0] = 15;
       grid[1] = 14;
       grid[2] = 1;
       grid[3] = 6;
       grid[4] = 9;
       grid[5] = 11;
       grid[6] = 4;
       grid[7] = 12;
       grid[8] = 0;
       grid[9] = 10;
       grid[10] = 7;
       grid[11] = 3;
       grid[12] = 13;
       grid[13] = 8;
       grid[14] = 5;
       grid[15] = 2;



       grid2[0] = 19;
       grid2[1] = 4;
       grid2[2] = 1;
       grid2[3] = 6;
       grid2[4] = 10;
       grid2[5] = 21;
       grid2[6] = 17;
       grid2[7] = 15;
       grid2[8] = 3;
       grid2[9] = 8;
       grid2[10] = 13;
       grid2[11] = 2;
       grid2[12] = 16;
       grid2[13] = 22;
       grid2[14] = 9;
       grid2[15] = 24;
       grid2[16] = 11;
       grid2[17] = 12;
       grid2[18] = 14;
       grid2[19] = 23;
       grid2[20] = 5;
       grid2[21] = 20;
       grid2[22] = 18;
       grid2[23] = 7;
       grid2[24] = 0;

       grid3[0] = 1;
       grid3[1] = 2;
       grid3[2] = 3;
       grid3[3] = 0;
       grid3[4] = 4;
       grid3[5] = 6;
       grid3[6] = 7;
       grid3[7] = 5;
       grid3[8] = 8;

        Solver solver = new Solver();
        AStarState state = new AStarState(grid, 0, 9, new CustomArrayList());
        AStarState state2 = new AStarState(grid2, 6, 9 , new CustomArrayList<>());


        long startTime = System.nanoTime();
        CustomArrayList<Integer> solved =solver.solve(grid3);
        long endTime = System.nanoTime();
        System.out.println("Solved using A*, solution: ");
        for (int move : solved) {
           System.out.print(move + ", ");
        }
        System.out.println("\n Amount of moves: " + solved.size());
        System.out.println("A* Time: " + (double) TimeUnit.MILLISECONDS.convert(endTime-startTime, TimeUnit.NANOSECONDS)/1000 + "sec");
        IDASolver idasolver = new IDASolver();
        startTime = System.nanoTime();
        AStarState solvedstate = idasolver.solve(grid3);
        endTime = System.nanoTime();

        AStarState parent = solvedstate;
        CustomArrayQueue<Integer> solvedList = new CustomArrayQueue(10);
        while (parent != null) {
            solvedList.add(parent.getMoves().get(0));
            parent = parent.getParent();
        }
        System.out.println("Solved using IDA*, moves: ");
        while (!solvedList.isEmpty()) {
            System.out.print(solvedList.pollLast() + ", ");
        }

        System.out.println("\n IDA* time: " + (double) TimeUnit.MILLISECONDS.convert(endTime-startTime, TimeUnit.NANOSECONDS)/1000 + "sec");
        System.out.println("\n amount of moves: " + solvedstate.getGValue());

    }

    public static int[] setGrid(String gridString) {
        String[] numbers = gridString.split(",");
        int[] grid = new int[16];
        for (int i = 0; i < gridString.length(); i++) {
            grid[i] = Integer.valueOf(numbers[i]);
        }
        return grid;
    }
}
