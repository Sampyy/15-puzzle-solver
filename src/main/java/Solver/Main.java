package Solver;
import jdk.nashorn.internal.ir.debug.ASTWriter;

import java.util.ArrayList;

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

        /*AStarState state = new AStarState(grid, 0, 9);
        System.out.println(state.getfValue());
        System.out.println(state.getGValue());*/
        Solver solver = new Solver();
        AStarState state = new AStarState(grid, 0, 9, new ArrayList<>());
        AStarState state2 = new AStarState(grid2, 6, 9 , new ArrayList<>());
        ArrayList<AStarState> lista= new ArrayList<>();
        lista.add(state);

        if (lista.contains(state2)) {
            System.out.println("kylä");
        }else {
            System.out.println("no");
        }
        System.out.println("Starting grid in Solver.java: ");
        System.out.println(solver.getGridAsString());
        System.out.println("Grid solver will try to move towards :");
        System.out.println(solver.getSolvedAsString());
        System.out.println(solver.getGridAsString());







        ArrayList<Integer> solved =solver.solve(grid2);
        for (int move : solved) {
            System.out.print(move + ", ");
        }
        System.out.println("\n Amount of moves: " + solved.size());







        CustomPriorityQueue<AStarState> que = new CustomPriorityQueue();
        AStarState state1 = new AStarState(grid3, 0, 3, new ArrayList<>());
        int[] grid4 = grid3.clone();
        grid4[4] = 0;
        grid4[3] = 4;
        AStarState state3 = new AStarState(grid4, 1, 4, new ArrayList<>());
        int[] grid5 = grid3.clone();
        grid5[4] = 4;
        grid5[3] = 7;
        grid5[6] = 0;
        AStarState state4 = new AStarState(grid5, 1, 6, new ArrayList<>());
        state4.compareTo(null);
        System.out.println("state1: "+ state1);
        System.out.println(state1.getfValue());
        System.out.println("state3: " + state3);
        System.out.println(state3.getfValue());
        System.out.println("state4: " + state4);
        System.out.println(state4.getfValue());
        que.add(state4);
        System.out.println(que);
        que.add(state3);
        System.out.println(que);
        que.add(state1);
        System.out.println(que);
        que.poll();
        System.out.println(que);
        que.add(state3);
        System.out.println(que);
        System.out.println(solver.checkLeft(state, 12));
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
