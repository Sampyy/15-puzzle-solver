package Solver.DataStructures;

/**
 * A queue, you can add and remove objects from it.
 * @param <O>
 */
public class CustomArrayQueue<O> {
    private int firstPointer;
    private int lastPointer;
    private Object[] list;

    public CustomArrayQueue(int size) {
        this.list = new Object[size];
        this.firstPointer = 0;
        this.lastPointer = 0;
    }

    /**
     * adds an object to the queue and moves the lastpointer
     * @param o object to be added
     */
    public void add(Object o)  {
        this.list[lastPointer]=o;
        this.lastPointer ++;
        if (lastPointer == list.length) {
            grow();
        }
    }

    /**
     * Removes the first object, resets both pointers to 0 if they are equal = there are no objects in the queue
     * @return first object of the queue
     */
    public O poll() {
        if (this.firstPointer == this.lastPointer) {
            this.firstPointer = 0;
            this.lastPointer = 0;
            return null;
        }
        O objectToReturn = (O) list[firstPointer];
        this.firstPointer ++;

        return objectToReturn;
    }

    /**
     * Removes the last object, resets both pointers to 0 if they are equal = there are no objects in the queue
     * @return last object of the queue
     */
    public O pollLast() {
        if (this.firstPointer == this.lastPointer) {
            this.firstPointer = 0;
            lastPointer = 0;
            return null;
        }
        O objectToReturn = (O) list[lastPointer-1];
        lastPointer --;

        return objectToReturn;
    }

    public void grow() {
        Object[] newList = new Object[list.length * 2];
        for(int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        this.list = newList;
    }

    public boolean isEmpty() {
        if (this.firstPointer == this.lastPointer) {
            this.firstPointer = 0;
            this.lastPointer = 0;
            return true;
        }
        return false;
    }
}
