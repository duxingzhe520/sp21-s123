package flik;

/**
 * @author duxingzhe520
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    /** Check in Flik's logic, 1, 128, 500 equal to themselves or not.
     *  Because Steve finds there might be some bugs in 128's equation.*/
    public void flikEqualTest() {
        assertTrue(Flik.isSameNumber(1, 1));
        assertTrue(Flik.isSameNumber(128, 128));
        assertTrue(Flik.isSameNumber(500, 500));
        assertFalse(Flik.isSameNumber(128, 500));
    }
}
