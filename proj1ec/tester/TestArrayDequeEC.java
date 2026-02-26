package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

/**
 *
 * @author duxingzhe520
 * */

public class TestArrayDequeEC {
    @Test
    /* We create a randomized number with its value in {0, 1, 2, 3}, and
       go on different operation on two deque. If the outcome of two deque
       are always the same and there is no assertion false, it's ok.*/
    public void randomTest() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> standard = new ArrayDequeSolution<>();
        for (int i = 0; i < 10000; ++i) {
            int operatorNum = StdRandom.uniform(0, 4);
            if (operatorNum == 0) {
                student.addLast(i);
                standard.addLast(i);
                assertEquals("addLast(" + i +")\n", standard.size(), student.size());
            } else if (operatorNum == 1) {
                student.addFirst(i);
                standard.addFirst(i);
                assertEquals("addFirst(" + i +")\n", standard.size(), student.size());
            } else if (operatorNum == 2) {
                if (!student.isEmpty()){
                    Integer a_1 = student.removeFirst();
                    Integer a_2 = standard.removeFirst();
                    assertEquals("removeFirst()\n", a_2, a_1);
                }
            } else if (operatorNum == 3) {
                if (!standard.isEmpty()) {
                    Integer a_1 = student.removeLast();
                    Integer a_2 = standard.removeLast();
                    assertEquals("removeLast()\n", a_2, a_1);
                }
            }
        }
    }
}
