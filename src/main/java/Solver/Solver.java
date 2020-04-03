package Solver;

import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.util.*;

/**
 * A solver for 15-puzzle (or other sized puzzles) (https://en.wikipedia.org/wiki/15_puzzle) implementing A* algorithm (https://en.wikipedia.org/wiki/A*_search_algorithm)
 */
public class Solver {
    private int gridSize;
    private CustomPriorityQueue<AStarState> openSet;
    private ArrayDeque<AStarState> validSteps;

    public String getGridAsString() {
        return asString(grid);
    }

    private int[] grid;
    private int startingPos;

    public String getSolvedAsString() {
        return asString(solved);
    }

    private int[] solved;

    public Solver() {
    }

    /**
     *
     * @param grid the puzzle to be solved as a list
     * @return returns a list of moves for completion
     */
    public ArrayList<Integer> solve(int[] grid) {
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
        return findSolution(grid, startingPos);
    }

    /**
     * Finds the solution using A* algorithm
     * @param currGrid grid to solve
     * @param currPos Position of the blank tile(0), calculated in solve
     * @return returns the list of moves for solution
     */
    private ArrayList<Integer> findSolution (int[] currGrid, int currPos) {
        this.openSet = new CustomPriorityQueue();
        ArrayList<AStarState> closedSet = new ArrayList<>();
        openSet.add(new AStarState(currGrid, 0, currPos, new ArrayList<>()));
        this.validSteps = new ArrayDeque<>();

        while(!openSet.isEmpty()) {
            AStarState currentState = openSet.poll();
            //System.out.println("set\n" + asString(currentState.getState()));
            if (Arrays.equals(currentState.getState(), solved)) {
                return currentState.getMoves();
            }
            //System.out.println(currentState.getfValue());
            checkValidSteps(currentState);
            //System.out.print("Adding states: ");
            while (!validSteps.isEmpty()) {
                AStarState nextStep = validSteps.poll();
                //System.out.print("\nAdding step: " + nextStep.toString());
                openSet.add(nextStep);
                /*System.out.println("\nState numbers: ");
                for(int i = 0; i < openSet.size(); i++) {
                    System.out.print(openSet.get(i) + ", ");
                }*/
            }
        }
        return null;
    }

    /**
     * Checks which moves are allowed to take on a particular gamestate
     * @param state the current state of the board
     * @return returns a queue with all the valid steps
     */
    public ArrayDeque<AStarState> checkValidSteps (AStarState state)  {
        int currPos = state.getCurrPos();
        if (checkLeft(state, currPos)) {
            ArrayList<Integer> moves = (ArrayList<Integer>) state.getMoves().clone();
            AStarState newState = new AStarState(state.getState(), state.getGValue()+1, state.getCurrPos(), moves);
            newState.setParent(state);
            newState.makeMove((currPos-1), currPos);
            validSteps.add(newState);
        }
        if (checkRight(state, currPos)) {
            ArrayList<Integer> moves = (ArrayList<Integer>) state.getMoves().clone();
            AStarState newState = new AStarState(state.getState(), state.getGValue()+1, state.getCurrPos(), moves);
            newState.setParent(state);
            newState.makeMove((currPos+1), currPos);
            validSteps.add(newState);
        }
        if (checkDown(state, currPos)) {
            ArrayList<Integer> moves = (ArrayList<Integer>) state.getMoves().clone();
            AStarState newState = new AStarState(state.getState(), state.getGValue() + 1, state.getCurrPos(), moves);
            newState.setParent(state);
            newState.makeMove(currPos + gridSize, currPos);
            // System.out.println("new state \n" + asString(newState.getState()));
            validSteps.add(newState);
        }
        if (checkUp(state, currPos)) {
            ArrayList<Integer> moves = (ArrayList<Integer>) state.getMoves().clone();
            AStarState newState = new AStarState(state.getState(), state.getGValue()+1, state.getCurrPos(), moves);
            newState.setParent(state);
            newState.makeMove( currPos-gridSize, currPos);
            //System.out.println("new state \n" + asString(newState.getState()));
            validSteps.add(newState);
        }
        return validSteps;
    }

    public Boolean checkLeft(AStarState state, int currPos) {
        return ((currPos-1)%gridSize < currPos%gridSize && currPos > 0 && (state.getParent() == null || (currPos-1) != state.getParent().getCurrPos()));
    }

    public Boolean checkRight(AStarState state, int currPos) {
        return ((currPos+1)%gridSize > currPos%gridSize && currPos < grid.length-1 && (state.getParent() == null || (currPos+1) != state.getParent().getCurrPos()));
    }
    public Boolean checkDown(AStarState state, int currPos) {
        return (currPos+gridSize < grid.length && (state.getParent() == null || currPos+gridSize != state.getParent().getCurrPos()));
    }

    public Boolean checkUp(AStarState state, int currPos) {
        return (currPos-gridSize >= 0 && ( state.getParent() == null ||(currPos-gridSize) != state.getParent().getCurrPos()));
    }

    public void setGridSize(int size) {
        this.gridSize = size;
    }
    public void setGrid(int[] grid) {
        this.grid = grid;
    }

    /**
     * used to make the move, moved to AStarState
     * @param gridToReturn
     * @return
     */
    /*public int[] makeMove(int[] state, int move, int pos) {
        int[] newGrid = new int[state.length];
        for(int i = 0; i < state.length; i++) {
            newGrid[i] = state[i];
        }
        newGrid[pos] = state[move];
        newGrid[move] = 0;
        return newGrid;
    }*/

    /**
     * Used for troubleshooting, returns game state as string.
     * @param gridToReturn
     * @return
     */
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
