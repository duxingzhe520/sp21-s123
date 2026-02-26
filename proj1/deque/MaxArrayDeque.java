package deque;

import java.util.Comparator;

/**
 *
 * @author duxingzhe520
 * */

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    /** Creates an MaxArrayDeque with a given comparator c. */
    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    /** Returns the max element, using the comparator. If there are multiple
     *  max elements, return either of them. */
    public T max() {
        if (isEmpty()) {
            return null;
        }
        T toReturn = get(0);
        for (T i : this) {
            if (comparator.compare(toReturn, i) < 0) {
                toReturn = i;
            }
        }
        return toReturn;
    }

    /** Returns the max element, using the c (given by arg). If there are
     *  multiple max elements, return either of them.*/
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T toReturn = get(0);
        for (T i : this) {
            if (c.compare(toReturn, i) < 0) {
                toReturn = i;
            }
        }
        return toReturn;
    }
}
