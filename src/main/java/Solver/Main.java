package Solver;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
       int[] grid = new int[16];
       int[] grid2 = new int[16];
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

        grid2[0] = 14;
        grid2[1] = 2;
        grid2[2] = 1;
        grid2[3] = 15;
        grid2[4] = 3;
        grid2[5] = 13;
        grid2[6] = 7;
        grid2[7] = 9;
        grid2[8] = 6;
        grid2[9] = 12;
        grid2[10] = 0;
        grid2[11] = 4;
        grid2[12] = 8;
        grid2[13] = 10;
        grid2[14] = 11;
        grid2[15] = 5;

        /*AStarState state = new AStarState(grid, 0, 9);
        System.out.println(state.getfValue());
        System.out.println(state.getGValue());*/
        Solver solver = new Solver(grid);
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
        ArrayList<Integer> solved =solver.solve();
        for (Integer move: solved) {
            System.out.println(move);
        }
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
