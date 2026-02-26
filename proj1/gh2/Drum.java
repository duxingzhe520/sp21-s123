package gh2;

import deque.ArrayDeque;
import deque.Deque;

/**
 *
 * @author duxingzhe520
 * */

public class Drum {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 1.0; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public Drum(double frequency) {
        buffer = new ArrayDeque<>();
        int capacity = (int) Math.round((double)SR / frequency);
        for (int i = 0; i < capacity; ++i) {
            buffer.addLast(0.0);
        }
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int size = buffer.size();
        for (int i = 0; i < size; ++i) {
            buffer.removeLast();
            double r = Math.random() - 0.5;
            buffer.addFirst(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double firstElement = buffer.removeFirst();
        double newElement = DECAY * (firstElement + buffer.get(0)) / 2;
        double r = Math.random();
        if (r >= 0.5) {
            buffer.addLast(newElement);
        } else {
            buffer.addLast(-newElement);
        }
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}
