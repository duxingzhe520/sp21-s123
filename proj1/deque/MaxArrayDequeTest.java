package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;
import java.lang.String;

/**
 *
 * @author duxingzhe520
 * */

public class MaxArrayDequeTest {
    /* A helper comparator different from normal comparison rule.*/
    public static class ComparatorStringLength<Term> implements Comparator<Term> {
        @Override
        public int compare(Object o1, Object o2) {
            if (o1 == null || o2 == null) {
                throw new RuntimeException();
            }
            String c1 = (String) o1;
            String c2 = (String) o2;
            return c1.length() - c2.length();
        }
    }

    /* Another helper comparator of String. */
    public static class ComparatorStringCapital<Term> implements Comparator<Term> {
        @Override
        public int compare(Object o1, Object o2) {
            if (o1 == null || o2 == null) {
                throw new RuntimeException();
            }
            String c1 = (String) o1;
            String c2 = (String) o2;
            return c1.charAt(0) - c2.charAt(0);
        }
    }

    @Test
    public void comparatorTest() {
        ComparatorStringLength<String> comp1 = new ComparatorStringLength<>();
        ComparatorStringCapital<String> comp2 = new ComparatorStringCapital<>();
        MaxArrayDeque<String> test1 = new MaxArrayDeque<>(comp1);

        test1.addLast("Alice666");
        test1.addLast("Bob");
        test1.addLast("Carol");
        test1.addLast("Dave");
        //test1.addFirst(null);

        assertEquals("Alice666", test1.max());
        assertEquals("Dave", test1.max(comp2));
    }
}
