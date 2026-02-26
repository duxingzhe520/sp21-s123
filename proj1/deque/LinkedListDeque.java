package deque;

import java.util.Iterator;

/** Double ended list based on link list.
 *
 * @param <T>
 *
 * @author duxingzhe520
 *
 */

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class ItemNode<Item> {
        Item first;
        ItemNode<Item> next;
        ItemNode<Item> last;

        ItemNode() {
            this.first = null;
            this.next = null;
            this.last = null;
        }

        ItemNode(Item first) {
            this.first = first;
            this.next = null;
            this.last = null;
        }
    }

    private int size;
    private ItemNode<T> sentinel;

    /** Creates a Deque with single Term first.*/
    public LinkedListDeque(T first) {
        size = 1;
        this.sentinel = new ItemNode<T>();
        ItemNode<T> firstItem = new ItemNode<>(first);

        this.sentinel.next = firstItem;
        this.sentinel.last = firstItem;
        firstItem.last = this.sentinel;
        firstItem.next = this.sentinel;
    }

    /** Creates an empty Deque with its structure kept.*/
    public LinkedListDeque() {
        size = 0;
        this.sentinel = new ItemNode<T>();
        this.sentinel.next = this.sentinel;
        this.sentinel.last = this.sentinel;
    }

    /** Returns the last node of the Deque.*/
    private ItemNode<T> getLastNode() {
        return this.sentinel.last;
    }

    /** Returns the first node of the Deque.*/
    private ItemNode<T> getFirstNode() {
        return this.sentinel.next;
    }

    @Override
    /** Adds an Item t to the first place of the Deque.*/
    public void addFirst(T t) {
        this.size += 1;
        ItemNode<T> newFirst = new ItemNode<>(t);
        ItemNode<T> oldFirst = getFirstNode();

        newFirst.next = oldFirst;
        newFirst.last = this.sentinel;

        oldFirst.last = newFirst;
        this.sentinel.next = newFirst;
    }

    @Override
    /** Adds an Item t to the last place of the Deque. */
    public void addLast(T t) {
        this.size += 1;
        ItemNode<T> newLast = new ItemNode<>(t);
        ItemNode<T> oldLast = getLastNode();

        newLast.last = oldLast;
        newLast.next = this.sentinel;

        oldLast.next = newLast;
        this.sentinel.last = newLast;
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
        ItemNode<T> tmp = this.sentinel.next;
        while (tmp.first != null) {
            System.out.print(tmp.first);
            System.out.print(' ');
            tmp = tmp.next;
        }
        System.out.print('\n');
    }

    @Override
    /** Removes and returns the front term of the Deque.*/
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        this.size -= 1;
        ItemNode<T> oldFirst = getFirstNode();
        ItemNode<T> newFirst = oldFirst.next;
        this.sentinel.next = newFirst;
        newFirst.last = sentinel;
        return oldFirst.first;
    }

    @Override
    /** Removes and returns the last term of the Deque.*/
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        this.size -= 1;
        ItemNode<T> oldLast = getLastNode();
        ItemNode<T> newLast = oldLast.last;
        this.sentinel.last = newLast;
        newLast.next = this.sentinel;
        return oldLast.first;
    }

    @Override
    /** Gets the item at the given index.*/
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        ItemNode<T> tmp = getFirstNode();
        int cntTmp = 0;

        while (cntTmp < index) {
            if (tmp == this.sentinel) {
                return null;
            }
            tmp = tmp.next;
            cntTmp += 1;
        }
        return tmp.first;
    }

    /** Gets the item at the given index, but uses recursion.*/
    public T getRecursive(int index) {
        if (isEmpty() || size <= index || index < 0) {
            return null;
        }
        return getTermFromNode(sentinel.next, index);
    }

    /** Gets the index's item counted from node, as a helper of getRecursive(). */
    private T getTermFromNode(ItemNode<T> node, int index) {
        if (index < 0) {
            return null;
        }
        if (index == 0) {
            return node.first;
        }
        return getTermFromNode(node.next, index - 1);
    }

    /** The class of Iterator made by ourselves, in order to return an
     *  iterator of LinkedListDeque. */
    private class LLDequeIterator<Type> implements Iterator<Type> {
        int position;

        @Override
        public boolean hasNext() {
            return this.position < size;
        }

        @Override
        public Type next() {
            Type toReturn = (Type) get(position);
            position += 1;
            return toReturn;
        }
    }

    @Override
    /** Returns an iterator, overriding the method of Iterable interface. */
    public Iterator<T> iterator() {
        return new LLDequeIterator<T>();
    }

    @Override
    /** Returns true iff o is instance of Deque, and has the same items
     *  as this does, including type, value, and order. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() == this.getClass()) {
            LinkedListDeque<T> other = (LinkedListDeque<T>) o;
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
            ArrayDeque<T> other = (ArrayDeque<T>) o;
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
