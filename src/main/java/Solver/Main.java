package Solver;
import Solver.DataStructures.AStarState;
import Solver.AStarSolver.Solver;
import Solver.DataStructures.CustomArrayQueue;
import Solver.DataStructures.CustomArrayList;
import Solver.IDAStarSolver.IDASolver;
import java.util.Scanner;

import java.util.concurrent.TimeUnit;


public class Main {
    static Scanner scanner;
    public static void main(String[] args) {

       Solver solver = new Solver();
       IDASolver idaSolver = new IDASolver();
       scanner = new Scanner(System.in);
       while(true) {
           System.out.println("Anna ratkaistava ruudukko vasemmalta ylhäältä riveittäin oikealle alas, ruudut erotettu pilkuilla. Tyhjä ruutu on 0.\nPoistu syöttämällä x.");
           String gridToSolve = scanner.next();
           String[] split = gridToSolve.split(",");
           int[] grid = new int[split.length];
           if (gridToSolve.equals("x")) {
               break;
       }
           for (int i = 0; i < split.length; i++) {
               if (Integer.valueOf(split[i]) > split.length || Integer.valueOf(split[i]) < 0) {
                   System.out.println("Sopimaton ruudukko");
               } else {
                   grid[i] = Integer.valueOf(split[i]);
               }
           }
           solveUsingAStar(solver, grid);
           solveUsingIDAStar(idaSolver, grid);
       }
    }

    public static void solveUsingAStar (Solver solver, int[] grid) {
        long startTime = System.nanoTime();
        try {
            CustomArrayList<String> solved =solver.solve(grid);
            long endTime = System.nanoTime();
            System.out.println("Solved using A*, solution: ");
            for (String move : solved) {
                System.out.print(move + ", ");
            }
            System.out.println("\n Amount of moves: " + solved.size());
            System.out.println("A* Time: " + (double) TimeUnit.MILLISECONDS.convert(endTime-startTime, TimeUnit.NANOSECONDS)/1000 + "sec");
        } catch (OutOfMemoryError e) {
            System.out.println("Error " + e);
        }

    }

    public static void solveUsingIDAStar(IDASolver solver, int[] grid) {
        long startTime = System.nanoTime();
        AStarState solvedstate = solver.solve(grid);
        long endTime = System.nanoTime();

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
