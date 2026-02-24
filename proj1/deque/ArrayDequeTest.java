package deque;

/** Test the class ArrayDeque.
 *
 * @author duxingzhe520
 *
 * */

import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;

public class ArrayDequeTest {
    @Test
    /** Create two mode of ArrayDeque and check their sizes.*/
    public void createSizeTest() {
        ArrayDeque<Integer> test1 = new ArrayDeque<>();
        assertTrue(test1.isEmpty());

        ArrayDeque<Integer> test2 = new ArrayDeque<>(1);
        assertTrue(test2.size() == 1);
    }

    @Test
    /** Add a few things to existed ArrayDeque, and check its size
     *  and its first and last item.*/
    public void addTest() {
        ArrayDeque<Integer> test1 = new ArrayDeque<>();
        test1.addFirst(1);
        assertEquals(1, test1.size());

        for (int i = 0; i < 8; ++ i) {
            test1.addLast(2);
            test1.addFirst(3);
            test1.printDeque();
        }
        assertEquals(17, test1.size());
        assertEquals(3, (int)test1.get(0));
    }

    @Test
    /** Check the printDeque() works well.*/
    public void printTest() {
        ArrayDeque<Integer> test1 = new ArrayDeque<>();
        test1.addFirst(1);
        test1.addLast(2);
        test1.addFirst(3);
        test1.addLast(4);
        test1.printDeque();
        assertEquals(4, test1.size());
    }

    @Test
    /** Add ten items to an empty arraydeque, and remove them.
     *  We check the size and sequence.*/
    public void addTenRemoveTenTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        assertTrue(test.isEmpty());

        for (int i = 0; i < 10; ++i) {
            assertEquals(i, test.size());
            test.addFirst(i + 1);
            test.printDeque();
        }

        for(int i = 0; i < 10; ++i) {
            assertEquals(10 - i, test.size());
            test.removeLast();
            test.printDeque();
        }
        assertTrue(test.isEmpty());
        assertTrue(null == test.removeLast());
        assertTrue(null == test.removeFirst());
        test.printDeque();
    }

    @Test
    /** Add ten items to an arraydeque with single element at first, and get
     *  them in an order.*/
    public void getTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>(21);
        assertFalse(test.isEmpty());
        assertTrue(21 == test.get(0));

        for (int i = 0; i < 10; ++i) {
            assertEquals(i + 1, test.size());
            test.addFirst(i + 1);
        }
        for(int i = 0;i < 10; ++i) {
            assertEquals(11, test.size());
            assertEquals(10 - i, (int)test.get(i));
        }
        assertTrue(21 == test.get(10));
        assertTrue(null == test.get(114514));
    }

    @Test
    /** We create a randomized number with its value in {0, 1, 2, 3, 4}, and
     *  go on different operation on two deque. If the outcome of two deque
     *  are always the same and there is no assertion false, it's ok.*/
    public void randomTest() {
        ArrayDeque<Integer> test1 = new ArrayDeque<>();
        LinkedListDeque<Integer> test2 = new LinkedListDeque<>();
        for (int i = 0; i < 10000; ++i) {
            int operatorNum = StdRandom.uniform(0, 5);
            if (operatorNum == 0) {
                test1.addLast(i);
                System.out.println("test1.addLast(" + i + ")");
                System.out.println("test1.size() = " + test1.size());

                test2.addLast(i);
                System.out.println("test2.addLast(" + i + ")");
                System.out.println("test2.size() = " + test2.size());

                assertEquals(test1.size(), test2.size());
            } else if (operatorNum == 1) {
                test1.addFirst(i);
                System.out.println("test1.addFirst(" + i + ")");
                System.out.println("test1.size() = " + test1.size());

                test2.addFirst(i);
                System.out.println("test2.addFirst(" + i + ")");
                System.out.println("test2.size() = " + test2.size());

                assertEquals(test1.size(), test2.size());
            } else if (operatorNum == 2) {
                if (!test1.isEmpty()){
                    int a_1 = test1.removeFirst();
                    int a_2 = test2.removeFirst();
                    System.out.println("test1.removeFirst()");
                    System.out.println("test2.removeFirst()");

                    assertEquals(a_1, a_2);
                }
            } else if (operatorNum == 3) {
                if (!test2.isEmpty()) {
                    int a_1 = test1.removeLast();
                    int a_2 = test2.removeLast();
                    System.out.println("test1.removeLast()");
                    System.out.println("test2.removeLast()");

                    assertEquals(a_1, a_2);
                }
            } else {
                assertEquals(test1.size(), test2.size());
                int index = test1.size() / 2;
                System.out.println("test1.get(" + index + ")");
                System.out.println("test2.get(" + index + ")");
                assertEquals(test1.get(index), test2.get(index));
            }
        }
    }
}
