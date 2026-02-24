package deque;

/** Double ended list based on array.
 *
 * @author duxingzhe520
 */

public class ArrayDeque<Term> {
    private Term[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty Deque with its structure kept.*/
    public ArrayDeque() {
        this.size = 0;
        this.items = (Term[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }

    /** Creates a Deque with single Term first.*/
    public ArrayDeque(Term t) {
        this.size = 1;
        this.items = (Term[]) new Object[8];
        items[3] = t;
        nextFirst = 2;
        nextLast = 4;
    }

    /** Returns the index's real value in case of array's bound limit.*/
    private int validIndex(int oldIndex) {
        return Math.floorMod(oldIndex, items.length);
    }

    /** Returns the first term's index of the Deque.*/
    private int firstIndex() {
        return validIndex(nextFirst + 1);
    }

    /** Returns the last term's index of the Deque.*/
    private int lastIndex() {
        return validIndex(nextLast - 1);
    }

    /** Change the size of items to capacity when necessary.*/
    private void resize(int capacity) {
        Term[] newItems = (Term[]) new Object[capacity];
        int firstIndex = firstIndex();
        int lastIndex = lastIndex();
        if (firstIndex <= lastIndex) {
            System.arraycopy(items, firstIndex, newItems, 0, size);
        } else {
            System.arraycopy(items, firstIndex, newItems, 0, items.length - firstIndex);
            System.arraycopy(items, 0, newItems, items.length - firstIndex, lastIndex + 1);
        }
        nextFirst = -1;
        nextLast = size;
        items = newItems;
    }

    /** Adds an Item t to the first place of the Deque.*/
    public void addFirst(Term t) {
        if (size == items.length) {
            resize((int) (size * 1.125));
        }
        size += 1;
        items[validIndex(nextFirst)] = t;
        nextFirst = validIndex(nextFirst - 1);
    }

    /** Adds an Item t to the last place of the Deque.*/
    public void addLast(Term t) {
        if (size == items.length) {
            resize((int) (size * 1.125));
        }
        size += 1;
        items[validIndex(nextLast)] = t;
        nextLast = validIndex(nextLast + 1);
    }

    /** Returns True if the Deque is empty.*/
    public boolean isEmpty() {
        return this.size == 0;
    }

    /** Returns the size of the Deque. If it is empty, then it will return 0.*/
    public int size() {
        return this.size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line. */
    public void printDeque() {
        int firstIndex = firstIndex();
        int lastIndex = lastIndex();
        int i = firstIndex;
        while (items[i] != null) {
            System.out.print(items[i]);
            if (i == lastIndex) {
                System.out.print('\n');
                break;
            }
            System.out.print(' ');
            i = validIndex(i + 1);
        }
    }

    /** Removes and returns the front term of the Deque.*/
    public Term removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int firstIndex = firstIndex();
        Term toReturn = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        size -= 1;
        return toReturn;
    }

    /** Removes and returns the last term of the Deque.*/
    public Term removeLast() {
        if (isEmpty()) {
            return null;
        }
        int lastIndex = lastIndex();
        Term toReturn = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size -= 1;
        return toReturn;
    }

    /** Gets the item at the given index.*/
    public Term get(int index) {
        if (index >= size) {
            return null;
        }
        int realIndex = validIndex(firstIndex() + index);
        return items[realIndex];
    }
}
