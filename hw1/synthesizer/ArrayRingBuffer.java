
package synthesizer;
import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[this.capacity + 1];

    }

    private int addOne(int index) {
        return (index + 1) % rb.length;
    }
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        } else {
            rb[last] = x;
            last = addOne(last);
            fillCount += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        } else {
            T result = rb[first];
            rb[first] = null;
            first = addOne(first);
            fillCount -= 1;
            return result;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return rb[first];
    }

    public Iterator<T> iterator() {
        return new ArrayRingIterator<>();
    }
    private class ArrayRingIterator<E> implements Iterator<E> {
        private int cursor = first;
        @Override
        public boolean hasNext() {
            return cursor != last;
        }

        @Override
        public E next() {
            E result = (E) rb[cursor];
            cursor = addOne(cursor);
            return result;
        }


    }
}
