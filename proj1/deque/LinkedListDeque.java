package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private class TNode {
        public T item;
        public TNode prev;
        public TNode next;
        public TNode(T item, TNode prev, TNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private TNode sentinel;
    private int size;
    private static final Object DUMMY = new Object();

    public LinkedListDeque() {
        T dummy = (T) DUMMY;
        sentinel = new TNode(dummy, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        sentinel.next = new TNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        TNode n = sentinel;
        while (n.prev != sentinel) {
            System.out.print(n.item + " ");
            n = n.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        TNode first = sentinel.next;
        if (first == sentinel) return null;

        sentinel.next = first.next;
        first.next.prev = sentinel;
        size--;
        return first.item;
    }

    @Override
    public T removeLast() {
        TNode last = sentinel.prev;
        if (last == sentinel) return null;

        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        size--;
        return last.item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size || size == 0) {
            return null;
        }
        int i = 0;
        TNode n = sentinel.next;
        while (i < index) {
            n = n.next;
            i++;
        }
        return n.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size || size == 0) {
            return null;
        }
        return getNode(0, sentinel.next, index).item;
    }

    private TNode getNode(int i, TNode n, int index) {
        if (i == index) {
            return n;
        } else {
            return getNode(i + 1, n.next, index);
        }
    }
}
