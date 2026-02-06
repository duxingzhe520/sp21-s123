package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void addThreeRemoveThree() {
        AListNoResizing<Integer> test1 = new AListNoResizing<>();
        BuggyAList<Integer> test2 = new BuggyAList<>();

        test1.addLast(114514);
        test2.addLast(114514);

        test1.addLast(0);
        test2.addLast(0);

        test1.addLast(5);
        test2.addLast(5);

        int[] a1 = new int[]{test1.get(0), test1.get(1), test1.get(2)};
        int[] a2 = new int[]{test2.get(0), test2.get(1), test2.get(2)};

        assertArrayEquals(a1, a2);

        for (int i = 0; i < 3; ++i) {
            test1.removeLast();
            test2.removeLast();

            int[] b1 = new int[3];
            int[] b2 = new int[3];
            for (int j = 0; j < test1.size(); ++j) {
                b1[j] = test1.get(j);
                b2[j] = test2.get(j);
            }
            assertArrayEquals(b1, b2);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
                //System.out.println("L: addLast(" + randVal + ")");
                //System.out.println("BL: addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeBL = BL.size();
                assertEquals(sizeL, sizeBL);
                //System.out.println("size: " + sizeL);
                //System.out.println("size: " + sizeBL);
            } else if (operationNumber == 2) {
                assertEquals(L.size() > 0, BL.size() > 0);
                if (L.size() > 0) {
                    int x = L.getLast();
                    int y = BL.getLast();
                    assertEquals(x, y);
                    //System.out.println("L: getLast: " + x);
                    //System.out.println("BL: getLast: " + y);
                }
            } else if (operationNumber == 3) {
                assertEquals(L.size() > 0, BL.size() > 0);
                if (L.size() > 0 ) {
                    int x = L.removeLast();
                    int y = BL.removeLast();
                    assertEquals(x, y);
                    //System.out.println("L: removeLast: " + x);
                    //System.out.println("BL: removeLast: " + y);
                }
            }
        }
    }
}
