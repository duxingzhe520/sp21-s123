package deque;

/** Double ended list based on link list.
 *
 * @param <Term>
 *
 * @author duxingzhe520
 *
 */

public class LinkedListDeque<Term> {
    private class ItemNode<Item> {
        Item first;
        ItemNode<Item> next;
        ItemNode<Item> last;

        public ItemNode(){
            this.first = null;
            this.next = null;
            this.last = null;
        }

        public ItemNode(Item first) {
            this.first = first;
            this.next = null;
            this.last = null;
        }
    }

    private int size;
    private ItemNode<Term> sentinel;

    /** Creates a Deque with single Term first.*/
    public LinkedListDeque(Term first) {
        size = 1;
        this.sentinel = new ItemNode<Term>();
        ItemNode<Term> firstItem = new ItemNode<>(first);

        this.sentinel.next = firstItem;
        this.sentinel.last = firstItem;
        firstItem.last = this.sentinel;
        firstItem.next = this.sentinel;
    }

    /** Creates an empty Deque with its structure kept.*/
    public LinkedListDeque() {
        size = 0;
        this.sentinel = new ItemNode<Term>();
        this.sentinel.next = this.sentinel;
        this.sentinel.last = this.sentinel;
    }

    /** Returns the last node of the Deque.*/
    private ItemNode<Term> getLastNode() {
        return this.sentinel.last;
    }

    /** Returns the first node of the Deque.*/
    private ItemNode<Term> getFirstNode() {
        return this.sentinel.next;
    }

    /** Adds an Item t to the first place of the Deque.*/
    public void addFirst(Term t) {
        this.size += 1;
        ItemNode<Term> newFirst = new ItemNode<>(t);
        ItemNode<Term> oldFirst = getFirstNode();

        newFirst.next = oldFirst;
        newFirst.last = this.sentinel;

        oldFirst.last = newFirst;
        this.sentinel.next = newFirst;
    }

    /** Adds an Item t to the last place of the Deque.*/
    public void addLast(Term t) {
        this.size += 1;
        ItemNode<Term> newLast = new ItemNode<>(t);
        ItemNode<Term> oldLast = getLastNode();

        newLast.last = oldLast;
        newLast.next = this.sentinel;

        oldLast.next = newLast;
        this.sentinel.last = newLast;
    }

    /** Returns True if the Deque is empty.*/
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /** Returns the size of the Deque. If it is empty, then it will return 0.*/
    public int size() {
        return this.size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line. */
    public void printDeque() {
        ItemNode<Term> tmp = this.sentinel.next;
        while (tmp.first != null) {
            System.out.print(tmp.first);
            System.out.print(' ');
            tmp = tmp.next;
        }
        System.out.print('\n');
    }

    /** Removes and returns the front term of the Deque.*/
    public Term removeFirst() {
        if (isEmpty()) {
            return null;
        }

        this.size -= 1;
        ItemNode<Term> oldFirst = getFirstNode();
        ItemNode<Term> newFirst = oldFirst.next;
        this.sentinel.next = newFirst;
        newFirst.last = sentinel;
        return oldFirst.first;
    }

    /** Removes and returns the last term of the Deque.*/
    public Term removeLast() {
        if (isEmpty()) {
            return null;
        }

        this.size -= 1;
        ItemNode<Term> oldLast = getLastNode();
        ItemNode<Term> newLast = oldLast.last;
        this.sentinel.last = newLast;
        newLast.next = this.sentinel;
        return oldLast.first;
    }

    /** Gets the item at the given index.*/
    public Term get(int index) {
        ItemNode<Term> tmp = getFirstNode();
        int cnt_tmp = 0;

        while (cnt_tmp < index) {
            if (tmp == this.sentinel) {
                return null;
            }
            tmp = tmp.next;
            cnt_tmp += 1;
        }
        return tmp.first;
    }
}
