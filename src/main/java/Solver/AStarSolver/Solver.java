package Solver.AStarSolver;

import Solver.DataStructures.AStarState;
import Solver.DataStructures.CustomArrayQueue;
import Solver.DataStructures.CustomPriorityQueue;
import Solver.DataStructures.CustomArrayList;


/**
 * A solver for 15-puzzle (or other sized puzzles) (https://en.wikipedia.org/wiki/15_puzzle) implementing A* algorithm (https://en.wikipedia.org/wiki/A*_search_algorithm)
 */
public class Solver {
    private int gridSize;
    private CustomPriorityQueue<AStarState> openSet;
    private CustomArrayQueue<AStarState> validSteps;
    private CustomArrayList<AStarState> closedSet;

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
    public CustomArrayList<String> solve(int[] grid) {
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
    private CustomArrayList<String> findSolution (int[] currGrid, int currPos) {
        this.openSet = new CustomPriorityQueue();
        this.closedSet = new CustomArrayList<>();
        openSet.add(new AStarState(currGrid, 0, currPos, new CustomArrayList<>()));
        this.validSteps = new CustomArrayQueue(5);

        while(!openSet.isEmpty()) {
            AStarState currentState = openSet.poll();
            if (arrayEquals(currentState.getState(), solved)) {
                return currentState.getMoves();
            }
            checkValidSteps(currentState);
            while (!validSteps.isEmpty()) {
                AStarState nextStep = validSteps.poll();
                if (!closedSet.contains(nextStep)) {
                    openSet.add(nextStep);
                }
            }
        }
        return null;
    }

    /**
     * Checks which moves are allowed to take on a particular gamestate
     * @param state the current state of the board
     * @return returns a queue with all the valid steps
     */
    public CustomArrayQueue<AStarState> checkValidSteps (AStarState state)  {
        int currPos = state.getCurrPos();
        if (checkLeft(state, currPos)) {
            CustomArrayList<String> moves = (CustomArrayList<String>) state.getMoves().clone();
            AStarState newState = new AStarState(state.getState(), state.getGValue()+1, state.getCurrPos(), moves);
            newState.setParent(state);
            newState.makeMove((currPos-1), currPos, "l");
            validSteps.add(newState);
        }
        if (checkRight(state, currPos)) {
            CustomArrayList<String> moves = (CustomArrayList<String>) state.getMoves().clone();
            AStarState newState = new AStarState(state.getState(), state.getGValue()+1, state.getCurrPos(), moves);
            newState.setParent(state);
            newState.makeMove((currPos+1), currPos, "r");
            validSteps.add(newState);
        }
        if (checkDown(state, currPos)) {
            CustomArrayList<String> moves = (CustomArrayList<String>) state.getMoves().clone();
            AStarState newState = new AStarState(state.getState(), state.getGValue() + 1, state.getCurrPos(), moves);
            newState.setParent(state);
            newState.makeMove(currPos + gridSize, currPos, "d");
            // System.out.println("new state \n" + asString(newState.getState()));
            validSteps.add(newState);
        }
        if (checkUp(state, currPos)) {
            CustomArrayList<String> moves = (CustomArrayList<String>) state.getMoves().clone();
            AStarState newState = new AStarState(state.getState(), state.getGValue()+1, state.getCurrPos(), moves);
            newState.setParent(state);
            newState.makeMove( currPos-gridSize, currPos, "u");
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

    public Boolean arrayEquals(int[] array1, int[] array2){
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
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
