
class LinkedListDeque<T> {
    private int size;
    private final Node<T> sentinelNode = new Node((Object)null);

    public LinkedListDeque() {
        this.sentinelNode.prev = this.sentinelNode;
        this.sentinelNode.rest = this.sentinelNode;
        this.size = 0;
    }

    public LinkedListDeque(T item) {
        Node<T> tempNode = new Node(item, this.sentinelNode, this.sentinelNode);
        this.sentinelNode.rest = tempNode;
        this.sentinelNode.prev = tempNode;
        this.size = 1;
    }

    public void addFirst(T item) {
        Node<T> tempNode = new Node(item, this.sentinelNode, this.sentinelNode.rest);
        this.sentinelNode.rest.prev = tempNode;
        this.sentinelNode.rest = tempNode;
        ++this.size;
    }

    public void addLast(T item) {
        Node<T> tempNode = new Node(item, this.sentinelNode.prev, this.sentinelNode);
        this.sentinelNode.prev.rest = tempNode;
        this.sentinelNode.prev = tempNode;
        ++this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for(Node<T> ptr = this.sentinelNode.rest; ptr != this.sentinelNode; ptr = ptr.rest) {
            System.out.print(ptr.first);
            if (ptr.rest != this.sentinelNode) {
                System.out.print(" ");
            }
        }

    }

    public T removeFirst() {
        Node<T> firstNode = this.sentinelNode.rest;
        if (firstNode != this.sentinelNode) {
            this.sentinelNode.rest = firstNode.rest;
            firstNode.rest.prev = this.sentinelNode;
            firstNode.rest = null;
            firstNode.prev = null;
            --this.size;
            return firstNode.first;
        } else {
            return null;
        }
    }

    public T removeLast() {
        Node<T> lastNode = this.sentinelNode.prev;
        if (lastNode != this.sentinelNode) {
            this.sentinelNode.prev = lastNode.prev;
            lastNode.prev.rest = this.sentinelNode;
            lastNode.rest = null;
            lastNode.prev = null;
            --this.size;
            return lastNode.first;
        } else {
            return null;
        }
    }

    public T getIndex(int index) {
        if (index < this.size && index >= 0) {
            Node<T> ptr = this.sentinelNode.rest;

            for(int count = 0; ptr != this.sentinelNode; ++count) {
                if (count == index) {
                    return ptr.first;
                }

                ptr = ptr.rest;
            }

            return null;
        } else {
            return null;
        }
    }

    private T getRecursive(Node<T> sentinel, Node<T> current, int index) {
        if (current == sentinel) {
            return null;
        } else {
            return index == 0 ? current.first : this.getRecursive(sentinel, current.rest, index - 1);
        }
    }

    public T getRecursive(int index) {
        return this.getRecursive(this.sentinelNode, this.sentinelNode.rest, index);
    }

    private static class Node<T> {
        public T first;
        public Node<T> prev;
        public Node<T> rest;

        public Node(T first) {
            this.first = first;
            this.prev = null;
            this.rest = null;
        }

        public Node(T first, Node<T> prev, Node<T> rest) {
            this.first = first;
            this.prev = prev;
            this.rest = rest;
        }
    }
}
