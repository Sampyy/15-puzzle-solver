package Solver;

import Solver.DataStructures.CustomPriorityQueue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomPriorityQueueTest {

    public class comparableInteger implements Comparable<comparableInteger> {
        public int number;

        public comparableInteger(int number) {
            this.number = number;
        }
        @Override
        public String toString() {
            return String.valueOf(this.number);
        }

        @Override
        public int compareTo (comparableInteger comp) {
            if (comp == null) {
                return 0;
            }
            return this.number-comp.number;
        }

        @Override
        public boolean equals (Object o) {
            comparableInteger other = (comparableInteger) o;
            return this.number == other.number;
        }
    }

    private CustomPriorityQueue<comparableInteger> queue;

    @Before
    public void setUp() throws Exception {
        this.queue = new CustomPriorityQueue();
    }

    @Test
    public void addWorksForFirst() {
        queue.add(new comparableInteger (5));

        assertEquals("null5nullnullnullnullnullnullnullnull", queue.toString());
    }

    @Test
    public void addWorksForTwo() {
        queue.add(new comparableInteger(4));
        queue.add(new comparableInteger(6));

        assertEquals("null46nullnullnullnullnullnullnull", queue.toString());
    }

    @Test
    public void addWorksForTwoSecondSmaller() {
        queue.add(new comparableInteger(6));
        queue.add(new comparableInteger(4));

        assertEquals("null46nullnullnullnullnullnullnull", queue.toString());
    }

    @Test
    public void addWorksForThree() {
        queue.add(new comparableInteger(7));
        queue.add(new comparableInteger(3));
        queue.add(new comparableInteger(1));

        assertEquals("null173nullnullnullnullnullnull", queue.toString());
    }

    @Test
    public void pollWorksForOne() {
        queue.add(new comparableInteger(5));

        assertTrue(new comparableInteger(5).equals(queue.poll()));
    }

    @Test
    public void pollWorksForTwo() {
        queue.add(new comparableInteger(4));
        queue.add(new comparableInteger(6));
        assertTrue(new comparableInteger(4).equals(queue.poll()));
        assertEquals("null6nullnullnullnullnullnullnullnull", queue.toString());
    }

    @Test
    public void pollWorksForFour() {
        queue.add(new comparableInteger(4));
        queue.add(new comparableInteger(6));
        queue.add(new comparableInteger(2));
        queue.add(new comparableInteger(3));

        assertEquals("null2346nullnullnullnullnull", queue.toString());

        assertTrue(new comparableInteger(2).equals(queue.poll()));
        assertEquals("null364nullnullnullnullnullnull", queue.toString());
    }

    @Test
    public void pollWorksForFourInverted() {
        queue.add(new comparableInteger(2));
        queue.add(new comparableInteger(4));
        queue.add(new comparableInteger(3));
        queue.add(new comparableInteger(6));

        assertEquals("null2436nullnullnullnullnull", queue.toString());

        assertTrue(new comparableInteger(2).equals(queue.poll()));

        assertEquals("null346nullnullnullnullnullnull", queue.toString());
    }

    @Test
    public void pollWorksForSix() {
        queue.add(new comparableInteger(1));
        queue.add(new comparableInteger(3));
        queue.add(new comparableInteger(5));
        queue.add(new comparableInteger(6));
        queue.add(new comparableInteger(5));
        queue.add(new comparableInteger(8));

        assertTrue(new comparableInteger(1).equals(queue.poll()));

        assertEquals("null35568nullnullnullnull", queue.toString());

        assertTrue(new comparableInteger(3).equals(queue.poll()));

        assertEquals("null5658nullnullnullnullnull", queue.toString());
    }
    @Test
    public void growWorks() {
        for(int i = 1; i < 11; i++) {
            queue.add(new comparableInteger(i));
        }

        assertEquals("null12345678910nullnullnullnull", queue.toString());
    }

    @Test
    public void isEmpty() {
        assertTrue(queue.isEmpty());
    }
}