package deque;

import java.util.Iterator;

/** Double ended list based on array.
 *
 * @author duxingzhe520
 */

public class ArrayDeque<Term> implements Iterable<Term>, Deque<Term> {
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

    @Override
    /** Adds an Item t to the first place of the Deque.*/
    public void addFirst(Term t) {
        if (size == items.length) {
            resize((int) (size * 1.125));
        }
        size += 1;
        items[validIndex(nextFirst)] = t;
        nextFirst = validIndex(nextFirst - 1);
    }

    @Override
    /** Adds an Item t to the last place of the Deque. */
    public void addLast(Term t) {
        if (size == items.length) {
            resize((int) (size * 1.125));
        }
        size += 1;
        items[validIndex(nextLast)] = t;
        nextLast = validIndex(nextLast + 1);
    }

    @Override
    /** Returns the size of the Deque. If it is empty, then it will return 0.*/
    public int size() {
        return this.size;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    /** Gets the item at the given index.*/
    public Term get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int realIndex = validIndex(firstIndex() + index);
        return items[realIndex];
    }

    /** The class of Iterator made by ourselves, in order to return an
     *  iterator of ArrayDeque. */
    private class ADequeIterator<Type> implements Iterator<Type> {
        int position;

        @Override
        public boolean hasNext() {
            return this.position < size;
        }

        @Override
        public Type next() {
            Type toReturn = (Type)get(position);
            position += 1;
            return toReturn;
        }
    }

    @Override
    /** Returns an iterator, overriding the method of Iterable interface. */
    public Iterator<Term> iterator() {
        return new ADequeIterator<Term>();
    }

    @Override
    /** Returns true iff o is instance of Deque, and has the same items
     *  as this does, including type, value, and order. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() == this.getClass()) {
            ArrayDeque<Term> other = (ArrayDeque<Term>) o;
            if (size != other.size()) {
                return false;
            }
            for (int i = 0; i < size; ++i) {
                if (!get(i).equals(other.get(i))) {
                    return false;
                }
            }
            return true;
        } else if (o.getClass() == ArrayDeque.class) {
            LinkedListDeque<Term> other = (LinkedListDeque<Term>) o;
            if (size != other.size()) {
                return false;
            }
            for (int i = 0; i < size; ++i) {
                if (!get(i).equals(other.get(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
