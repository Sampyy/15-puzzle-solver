package Solver.DataStructures;

/**
 * Implementation for states in A* algorithm
 */


public class AStarState implements Comparable<AStarState> {
    private int gValue;
    private int fValue;
    private int hValue;
    private int gridSize;
    public double hValueInflation = 1;
    private AStarState parent;
    private int currPos;
    private int[] state;
    private CustomArrayList<String> moves;

    /**
     *
     * @param state state of the grid
     * @param gvalue amount of steps to reach this state
     * @param currPos current position of the blank tile
     * @param moves move list to reach current position
     */
    public AStarState(int[] state, int gvalue, int currPos, CustomArrayList<String> moves) {
        this.currPos = currPos;
        this.state = state;
        this.gValue = gvalue;
        this.gridSize = (int) Math.sqrt(this.state.length);
        this.moves = moves;
        this.hValue = hvalue();
        this.fValue = hValue + gvalue;
    }

    public AStarState(int[] state, int gvalue, int currPos) {
        this.currPos = currPos;
        this.state = state;
        this.gValue = gvalue;
        this.gridSize = (int) Math.sqrt(this.state.length);
        this.hValue = hvalue();
        this.fValue = hValue + gvalue;
        this.moves = new CustomArrayList();
    }

    /**
     * calculates hvalue of current state, which is the amount of steps required at the least
     * to get to end state, calculated as the sum of every tiles manhattan distance from its
     * goal tile. If state has a parent, use parent's hvalue to speed calculation up
     * @return hvalue of current state
     */
    private int hvalue() {
        int hvalue = 0;
        if (parent == null) {
            for (int i = 0; i < state.length; i++) {
                if (state[i] != i+1){
                    hvalue+= getDistance(i);
                }
            }
        } else {
            hvalue = parent.gethValue();
            int x = currPos % gridSize;
            int y = currPos / gridSize;

            int xp = parent.currPos % gridSize;
            int yp = parent.currPos / gridSize;

            int swappedPiece = parent.getState()[yp*gridSize + xp];
            int correctX = swappedPiece % gridSize;
            int correctY = swappedPiece / gridSize;
            if (x > xp) {
                if (correctX > xp) {
                    hvalue ++;
                } else {
                    hvalue --;
                }
            } else if (x < xp) {
                if (correctX < xp) {
                    hvalue ++;
                } else {
                    hvalue--;
                }

            } else if (y > yp) {
                if (correctY > yp) {
                    hvalue++;
                } else {
                    hvalue--;
                }
            } else {
                if (correctY < yp) {
                    hvalue++;
                } else {
                    hvalue--;
                }
            }
        }
        return (int) this.hValueInflation*hvalue;
    }

    /**
     * calculates the manhattan distance of given tile from its goal position
     * @param tile tile to calculate the distance for
     * @return distance
     */
    //Since we can only move in one direction at once, and every up/down/right/left move is permitted
    //the distance is |x1-x2| + |y1-y2|
    private int getDistance(int tile) {
        //System.out.println("state: " + asString(state));
        if (state[tile] == 0) {
            //System.out.println("nolla");
            return 0;
        }
        int finalLocation = state[tile]-1;
        int xdistance = Math.abs((tile%gridSize)-(finalLocation%gridSize));
        int ydistance = Math.abs((tile/gridSize)-(finalLocation/gridSize));
        int distance = xdistance + ydistance;
        //System.out.println("Luvut: tile = " + tile + " luku = " + state[tile] + " , xdistance = " +xdistance + " ydistance = " + ydistance);
        return distance;
    }

    /**
     * Makes a move on the current state (moves blank tile to adjacent tile, and adjacent tile to blank tiles old position)
     * @param move which tile to move blank to
     * @param pos blank tiles current position
     */
    public void makeMove(int move, int pos, String moveS) {
        int[] newGrid = new int[state.length];
        for(int i = 0; i < state.length; i++) {
            newGrid[i] = state[i];
        }
        newGrid[pos] = state[move];
        newGrid[move] = 0;
        this.state = newGrid;
        this.currPos = move;
        this.fValue = gValue + hvalue();
        this.moves.add(moveS);
    }

    /**
     *
     * @return current state as a list
     */
    public int[] getState() {
        int[] returnState = new int[state.length];
        for (int i = 0; i < state.length; i++) {
            returnState[i] = state[i];
        }
        return returnState;
    }

    public CustomArrayList<String> getMoves() {
        return moves;
    }

    public AStarState getParent() {
        return parent;
    }

    public void setParent(AStarState parent) {
        this.parent = parent;
    }

    public int getGValue() {
        return gValue;
    }

    public int getfValue() {
        return fValue;
    }
    public int gethValue() {
        return hValue;
    }

    public int getCurrPos() {
        return currPos;
    }

    @Override
    public boolean equals(Object o) {
        AStarState comparing = (AStarState) o;
        int[] compareState = comparing.getState();
        if (state == null || comparing.getState() == null) {
            return false;
        }
        for (int i = 0; i < state.length; i++) {
            if (state[i] != compareState[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Comparison using fvalue as the means of comparing
     * @param state the state to compare to
     * @return negative if current state fvalue < fvalue of object to compare to
     */
    @Override
    public int compareTo(AStarState state) {
        if (state == null) {
            return 0;
        }
        return this.fValue - state.getfValue();
    }

    @Override
    public String toString() {
        return String.valueOf(this.fValue);
    }

    public String toString(int[] gridToReturn) {
        String stringToReturn = new String();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                stringToReturn+= gridToReturn[i*4+j] + ",";
            }
            stringToReturn += "\n";
        }
        return stringToReturn;
    }

}
