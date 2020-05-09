package Solver.DataStructures;
import java.util.Iterator;

/**
 * List, grows when needed.
 * @param <O>
 */
public class CustomArrayList<O> implements Iterable<O> {
    private Object[] list;
    private int pointer;

    public CustomArrayList() {
        this.list = new Object[10];
        this.pointer = 0;
    }

    public CustomArrayList(Object[] list, int pointer) {
        this.list = list;
        this.pointer = pointer;
    }

    public void add(Object o) {
        if (pointer >= list.length) {
            grow();
        }
        list[pointer] = o;
        pointer++;
    }

    /**
     * Grows the list when it gets full
     */
    public void grow() {
        Object[] newList = new Object[(int) (1.4 * list.length)];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        this.list = newList;
    }

    /**
     * copies the list
     * @return A list that has the same content as the list this is called on
     */
    public CustomArrayList<O> clone() {
        Object[] copiedList = new Object[list.length];
        for (int i = 0; i < list.length; i++) {
            copiedList[i] = list[i];
        }
        int newPointer = pointer;
        return new CustomArrayList(copiedList, newPointer);
    }

    public Object get(int i) {
        return list[i];
    }

    public int size() {
        return pointer;
    }

    public Iterator<O> iterator() {
        return new CustomIterator<O>(this);
    }

    public Object[] getList() {
        return this.list;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].equals(o)) {
                return true;
            }
        }
        return false;
    }


}

/**
 * Iterator for for each working on the list
 * @param <O>
 */
    class CustomIterator<O> implements Iterator<O> {
        Object current;
        int iteratorPointer = 0;
        Object[] list;

        CustomIterator(CustomArrayList<O> o) {
            list = o.getList();
            current = list[iteratorPointer];
        }

        public boolean hasNext() {
            return current != null;
        }

        public O next() {
            Object o = list[iteratorPointer];
            iteratorPointer ++;
            current = list[iteratorPointer];
            return (O) o;
        }

    }

