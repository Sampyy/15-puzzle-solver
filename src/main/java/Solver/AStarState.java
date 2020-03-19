package Solver;

import jdk.nashorn.internal.ir.debug.ASTWriter;

import java.util.ArrayList;
import java.util.Arrays;

public class AStarState implements Comparable<AStarState> {
    private int gValue;
    private int fValue;
    private int gridSize;

    public AStarState getParent() {
        return parent;
    }

    public void setParent(AStarState parent) {
        this.parent = parent;
    }

    private AStarState parent;

    public int getCurrPos() {
        return currPos;
    }

    private int currPos;

    public int[] getState() {
        int[] returnState = new int[state.length];
        for (int i = 0; i < state.length; i++) {
            returnState[i] = state[i];
        }
        return returnState;
    }

    private int[] state;

    public ArrayList getMoves() {
        return moves;
    }

    private ArrayList moves;

    public int getGValue() {
        return gValue;
    }

    public int getfValue() {
        return fValue;
    }


    public AStarState(int[] state, int gvalue, int currPos, ArrayList<Integer> moves) {
        this.currPos = currPos;
        this.state = state;
        this.gValue = gvalue;
        this.gridSize = (int) Math.sqrt(this.state.length);
        this.moves = moves;
    }

    public int hvalue() {
        int hvalue = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] != i+1){
                hvalue+= getDistance(i);
            }
        }
        return hvalue;
    }
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

    public void makeMove(int move, int pos) {
        int[] newGrid = new int[state.length];
        for(int i = 0; i < state.length; i++) {
            newGrid[i] = state[i];
        }
        newGrid[pos] = state[move];
        newGrid[move] = 0;
        this.state = newGrid;
        this.currPos = move;
        this.fValue = gValue + hvalue();
    }
    @Override
    public boolean equals(Object o) {
        AStarState equalsState = (AStarState) o;
        if (Arrays.equals(equalsState.getState(), this.state)) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(AStarState o) {
        return this.fValue - o.getfValue();
    }
    public String asString(int[] gridToReturn) {
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
