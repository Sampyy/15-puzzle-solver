package Solver;

import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.util.*;

public class Solver {
    private int gridSize;

    public String getGridAsString() {
        return asString(grid);
    }

    private int[] grid;
    private int startingPos;

    public String getSolvedAsString() {
        return asString(solved);
    }

    private int[] solved;

    public Solver(int[] grid) {
        this.grid = grid;
        this.gridSize = (int) Math.sqrt(grid.length);
        this.solved = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            solved[i] = i+1;
            if (grid[i] == 0) {
                startingPos = i;
            }
        }
        solved[grid.length-1] = 0;
    }

    public ArrayList<Integer> solve() {
        return findSolution(grid, startingPos);
    }

    private ArrayList<Integer> findSolution (int[] currGrid, int currPos) {
        PriorityQueue<AStarState> openSet = new PriorityQueue<>();
        ArrayList<AStarState> closedSet = new ArrayList<>();
        openSet.add(new AStarState(currGrid, 0, currPos, new ArrayList<>()));

        while(!openSet.isEmpty()) {
            AStarState currentState = openSet.poll();
            //System.out.println("set\n" + asString(currentState.getState()));
            if (Arrays.equals(currentState.getState(), solved)) {
                return currentState.getMoves();
            }
            System.out.println(currentState.getfValue());
            closedSet.add(currentState);
            ArrayDeque<AStarState> validSteps = checkValidSteps(currentState);
            while (!validSteps.isEmpty()) {
                AStarState nextStep = validSteps.poll();
                //System.out.println("Current step: " + nextStep);
                //System.out.println("Current state: \n" + asString(newGrid));
                //System.out.println(newState.getfValue());
                if (!closedSet.contains(nextStep)) {
                    openSet.add(nextStep);
                }
            }
        }
        return null;
    }

    private ArrayDeque<AStarState> checkValidSteps (AStarState state) {
        ArrayDeque<AStarState> validSteps = new ArrayDeque<>();
        int currPos = state.getCurrPos();
       // System.out.println("currpos 1: " + currPos);
        if ((currPos-1)%gridSize < currPos%gridSize && currPos > 0 && (state.getParent() == null || (currPos-1) != state.getParent().getCurrPos())) {
            AStarState newState = new AStarState(state.getState(), state.getGValue()+1, state.getCurrPos(), state.getMoves());
            newState.setParent(state);
            newState.makeMove((currPos-1), currPos);
           // System.out.println("curr pos : " + currPos);
           // System.out.println("new state \n" + asString(newState.getState()));

            validSteps.add(newState);
        }
        if ((currPos+1)%gridSize > currPos%gridSize && currPos < grid.length-1 && (state.getParent() == null || (currPos+1) != state.getParent().getCurrPos())) {
            AStarState newState = new AStarState(state.getState(), state.getGValue()+1, state.getCurrPos(), state.getMoves());
            newState.setParent(state);
            newState.makeMove((currPos+1), currPos);
           // System.out.println("new state \n" + asString(newState.getState()));
            validSteps.add(newState);
        }
        if (currPos+gridSize < grid.length && (state.getParent() == null || currPos+gridSize != state.getParent().getCurrPos())) {
            AStarState newState = new AStarState(state.getState(), state.getGValue()+1, state.getCurrPos(), state.getMoves());
            newState.setParent(state);
            newState.makeMove(currPos+gridSize, currPos);
           // System.out.println("new state \n" + asString(newState.getState()));
            validSteps.add(newState);
        }

        if (currPos-gridSize >= 0 && ( state.getParent() == null ||(currPos-gridSize) != state.getParent().getCurrPos())) {
            AStarState newState = new AStarState(state.getState(), state.getGValue()+1, state.getCurrPos(), state.getMoves());
            newState.setParent(state);
            newState.makeMove( currPos-gridSize, currPos);
            //System.out.println("new state \n" + asString(newState.getState()));
            validSteps.add(newState);
        }
        return validSteps;
    }

    /*public int[] makeMove(int[] state, int move, int pos) {
        int[] newGrid = new int[state.length];
        for(int i = 0; i < state.length; i++) {
            newGrid[i] = state[i];
        }
        newGrid[pos] = state[move];
        newGrid[move] = 0;
        return newGrid;
    }*/

    public String asString(int[] gridToReturn) {
        String stringToReturn = new String();
        for(int i = 0; i < gridSize; i++) {
            for(int j = 0; j < gridSize; j++) {
                stringToReturn+= gridToReturn[i*gridSize+j] + ",";
            }
            stringToReturn += "\n";
        }
        return stringToReturn;
    }
}