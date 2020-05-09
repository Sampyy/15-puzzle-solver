package Solver.DataStructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomArrayListTest {
    private CustomArrayList<Integer> list;
    @Before
    public void setUp() {
        this.list = new CustomArrayList<>();
    }

    @Test
    public void add() {
        list.add(5);
        assertEquals(5, list.get(0));
    }

    @Test
    public void grow() {
        for (int i = 0; i < 12; i++) {
            list.add(i);
        }
        assertEquals(14, list.getList().length);
    }

    @Test
    public void get() {
        list.add(3);
        list.add(6);
        assertEquals(3, list.get(0));
        assertEquals(6, list.get(1));
    }

    @Test
    public void size() {
        list.add(4);
        list.add(2);
        list.add(6);

        assertEquals(3, list.size());
    }

    @Test
    public void contains() {
        list.add(3);
        list.add(7);
        list.add(4);
        assertTrue(list.contains(3));
        assertFalse(list.contains(8));
    }
}