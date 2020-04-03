package Solver;

public class CustomPriorityQueue<O> {
    private Object[] list;
    private int nextFreeSpot;
    public CustomPriorityQueue() {
        this.list = new Object[10];
        nextFreeSpot = 1;
    }

    public void add(O o) {
        if (nextFreeSpot >= list.length) {
            grow();
        }
        if (nextFreeSpot == 1) {
            list[1] = o;
            nextFreeSpot++;
            return;
        }
        Comparable<O> objectToAdd = (Comparable<O>) o;
        int objectsSpot = nextFreeSpot;
        list[objectsSpot] = objectToAdd;
        //System.out.println(objectToAdd.compareTo((O) list[objectsSpot/2]));
        while (objectToAdd.compareTo((O) list[objectsSpot/2]) < 0) {
            list[objectsSpot] = list[objectsSpot/2];
            list[objectsSpot/2] = o;
            objectsSpot = objectsSpot/2;
        }
        nextFreeSpot = nextFreeSpot+1;
    }

    public O poll() {
        O returnObject = (O) list[1];
        int currentObject = 1;
        Comparable<O> objectToShiftDown = (Comparable<O>) list[nextFreeSpot-1];
        list[1] = objectToShiftDown;
        if (nextFreeSpot == 1) {
            return returnObject;
        }
        list[nextFreeSpot-1] = null;
        while(currentObject*2 < list.length && list[currentObject*2] != null && (objectToShiftDown.compareTo((O) list[currentObject*2]) > 0 || (list[currentObject*2+1] != null && objectToShiftDown.compareTo((O) list[currentObject*+2]) > 1))) {
            int nextSpot = 0;
            if (list[currentObject*2+1] != null && ((Comparable<O>) list[currentObject*2]).compareTo((O) list[currentObject*2+1]) > 0) {
                nextSpot = currentObject*2+1;
            }else {
                nextSpot = currentObject*2;
            }
            list[currentObject] = (O) list[nextSpot];
            list[nextSpot] = objectToShiftDown;
            currentObject = nextSpot;
        }
        nextFreeSpot --;
        return returnObject;
    }

    public void grow() {
        int newLength = (int) (1.2*list.length);
        Object[] newList = new Object[newLength];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        this.list = newList;
    }

    public Object get(int i) {
        return list[i];
    }

    public int size() {
        return this.list.length;
    }

    public Boolean isEmpty() {
        if (nextFreeSpot > 1) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        String returnString = "";
        for(int i = 0; i < list.length; i++) {
            returnString += list[i];
        }
        return returnString;
    }
}
