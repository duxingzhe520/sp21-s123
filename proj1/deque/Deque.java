package deque;

public interface Deque<Term> {
    void addFirst(Term item);

    void addLast(Term item);

    default boolean isEmpty() {
        return this.size() == 0;
    }

    int size();

    void printDeque();

    Term removeFirst();

    Term removeLast();

    Term get(int index);
}
