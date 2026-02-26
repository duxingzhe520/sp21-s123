package deque;

import java.util.Comparator;

/**
 *
 * @author duxingzhe520
 * */

public class MaxArrayDeque<Term> extends ArrayDeque<Term>{
    Comparator<Term> comparator;

    /** Creates an MaxArrayDeque with a given comparator c. */
    public MaxArrayDeque(Comparator<Term> c) {
        comparator = c;
    }

    /** Returns the max element, using the comparator. If there are multiple
     *  max elements, return either of them. */
    public Term max() {
        if (isEmpty()) {
            return null;
        }
        Term toReturn = get(0);
        for (Term i : this) {
            if (comparator.compare(toReturn, i) < 0) {
                toReturn = i;
            }
        }
        return toReturn;
    }

    /** Returns the max element, using the c (given by arg). If there are
     *  multiple max elements, return either of them.*/
    public Term max(Comparator<Term> c) {
        if (isEmpty()) {
            return null;
        }
        Term toReturn = get(0);
        for (Term i : this) {
            if (c.compare(toReturn, i) < 0) {
                toReturn = i;
            }
        }
        return toReturn;
    }
}
