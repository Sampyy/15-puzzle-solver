package Solver.IDAStarSolver;
import Solver.DataStructures.AStarState;
import Solver.DataStructures.CustomArrayQueue;

public class IDASolver {
    private int gridSize;
    private int[] grid;
    private int startingPos;
    private int[] solved;
    private int bound;
    private int min;
    public IDASolver() {
    }

    /**
     * Solve given grid
     * @param grid to solve
     * @return final state, which is linked to all previous states through the AStarState parent
     */
    public AStarState solve(int[] grid) {
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
        return idaStar(new AStarState(grid, 0, startingPos));
    }

    /**
     * Does a depth first search from given state, which is branched according to bound. Bound is inreased by 1 everytime no solution is found.
     * @param state starting state
     * @return returns completed state
     */
    public AStarState idaStar(AStarState state) {
        bound = state.gethValue();
        while (true) {
            min = Integer.MAX_VALUE;
            AStarState t = search(state, 0, bound);
            if (t != null) {
                return t;
            }
            bound =  min;
        }
    }

    /**
     * Searches next steps from state given as parameter
     * @param state state currently in use
     * @param g current g-value (distance from root)
     * @param bound current depth to use as cutoff
     * @return state if goal state is found, null otherwise
     */
    public AStarState search(AStarState state, int g, int bound) {
        int f = g + state.gethValue();
        if (f > bound) {
            if (f < min) {
                min = f;
            }
            return null;
        }

        if (arrayEquals(state.getState(), solved)) {
            return state;
        }
        CustomArrayQueue<AStarState> validSteps = findNextMoves(state);
        while (!validSteps.isEmpty()) {
            AStarState t = search(validSteps.poll(), g+1, bound);
            if (t != null) {
                return t;
            }
        }
        return null;
    }

    /**
     * checks all possible next states from current state
     * @param state state to search from
     * @return Queue of possible next states
     */
    public CustomArrayQueue<AStarState> findNextMoves(AStarState state) {
        int currPos = state.getCurrPos();
        CustomArrayQueue<AStarState> validSteps = new CustomArrayQueue(10);
        if (checkLeft(state, currPos)) {
            AStarState newState = new AStarState(state.getState(), state.getGValue()+1, state.getCurrPos());
            newState.setParent(state);
            newState.makeMove((currPos-1), currPos, "l");
            validSteps.add(newState);
        }
        if (checkRight(state, currPos)) {
            AStarState newState = new AStarState(state.getState(), state.getGValue()+1, state.getCurrPos());
            newState.setParent(state);
            newState.makeMove((currPos+1), currPos, "r");
            validSteps.add(newState);
        }
        if (checkDown(state, currPos)) {
            AStarState newState = new AStarState(state.getState(), state.getGValue() + 1, state.getCurrPos());
            newState.setParent(state);
            newState.makeMove(currPos + gridSize, currPos, "d");
            // System.out.println("new state \n" + asString(newState.getState()));
            validSteps.add(newState);
        }
        if (checkUp(state, currPos)) {
            AStarState newState = new AStarState(state.getState(), state.getGValue()+1, state.getCurrPos());
            newState.setParent(state);
            newState.makeMove( currPos-gridSize, currPos, "u");
            //System.out.println("new state \n" + asString(newState.getState()));
            validSteps.add(newState);
        }
        return validSteps;
    }

    /**
     * Check whether going left is a possible move
     * @param state state to check
     * @param currPos location of the blank tile (0)
     * @return true if possible, false otherwise
     */
    public Boolean checkLeft(AStarState state, int currPos) {
        return ((currPos-1)%gridSize < currPos%gridSize && currPos > 0 && (state.getParent() == null || (currPos-1) != state.getParent().getCurrPos()));
    }
    /**
     * Check whether going right is a possible move
     * @param state state to check
     * @param currPos location of the blank tile (0)
     * @return true if possible, false otherwise
     */
    public Boolean checkRight(AStarState state, int currPos) {
        return ((currPos+1)%gridSize > currPos%gridSize && currPos < grid.length-1 && (state.getParent() == null || (currPos+1) != state.getParent().getCurrPos()));
    }
    /**
     * Check whether going down is a possible move
     * @param state state to check
     * @param currPos location of the blank tile (0)
     * @return true if possible, false otherwise
     */
    public Boolean checkDown(AStarState state, int currPos) {
        return (currPos+gridSize < grid.length && (state.getParent() == null || currPos+gridSize != state.getParent().getCurrPos()));
    }
    /**
     * Check whether going up is a possible move
     * @param state state to check
     * @param currPos location of the blank tile (0)
     * @return true if possible, false otherwise
     */
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
     * checks if 2 arrays are the same
     * @param array1
     * @param array2
     * @return
     */
    public Boolean arrayEquals(int[] array1, int[] array2){
        if (array1 == null || array2 == null) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
}
