//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class ArrayDeque<T> {
    private static final double THRESHOLD = 0.25;
    private int head;
    private int tail;
    private T[] data ;

    public ArrayDeque() {
        this.data = (T[])new Object[8];
        this.head = 0;
        this.tail = 0;
    }

    private int minusOne(int index) {
        return (index - 1 + this.data.length) % this.data.length;
    }

    private int plusOne(int index) {
        return (index + 1) % this.data.length;
    }

    private boolean isFull() {
        return this.plusOne(this.tail) == this.head;
    }

    private double usage() {
        return (double)this.size() / (double)this.data.length;
    }

    private boolean isShrink() {
        return this.usage() < THRESHOLD;
    }

    private void resize(double factor) {
        T[] newData = (T [])new Object[(int)((double)this.data.length * factor)];
        int ptr = this.head;
        int newPtr = 0;

        int oldSize = size();

        while (ptr % this.data.length != this.tail) {
            newData[newPtr] = this.data[ptr];
            this.data[ptr] = null;
            ++ptr;
            ++newPtr;
        }
        this.data = newData;
        this.head = 0;
        this.tail = oldSize + this.head;
    }

    public void addFirst(T item) {
        if (this.isFull()) {
            this.resize(2.0);
        }

        this.data[this.minusOne(this.head)] = item;
        this.head = this.minusOne(this.head);
    }

    public void addLast(T item) {
        if (this.isFull()) {
            this.resize(2.0);
        }

        this.data[this.tail] = item;
        ++this.tail;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return (this.tail + this.data.length - this.head) % this.data.length;
    }

    public void printDeque() {
        for(int ptr = this.head; ptr % this.data.length != this.tail; ptr = this.plusOne(ptr)) {
            System.out.print(this.data[ptr]);
            if (ptr % this.data.length != this.minusOne(this.tail)) {
                System.out.print(" ");
            }
        }

    }

    public T removeFirst() {
        if (!this.isEmpty()) {
            T result = this.data[this.head];
            this.head = this.plusOne(this.head);
            if (this.isShrink()) {
                this.resize(0.5);
            }
            return result;
        }
        return null;
    }

    public T removeLast() {
        if (!this.isEmpty()) {
            T result = this.data[this.tail - 1];
            this.tail = this.minusOne(this.tail);
            if (this.isShrink()) {
                this.resize(0.5);
            }
            return result;
        }
        return null;
    }

    public T getIndex(int index) {
        return this.data[(this.head + index) % this.data.length];
    }
}
