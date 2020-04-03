package Solver;

public class CustomArrayQueue<O> {
    private int firstPointer;
    private int lastPointer;
    private Object[] list;

    public CustomArrayQueue(int size) {
        this.list = new Object[size];
        this.firstPointer = 0;
        this.lastPointer = 0;
    }

    public void add(Object o)  {
        this.list[lastPointer]=o;
        this.lastPointer ++;
    }

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

    public boolean isEmpty() {
        if (this.firstPointer == this.lastPointer) {
            this.firstPointer = 0;
            this.lastPointer = 0;
            return true;
        }
        return false;
    }
}
