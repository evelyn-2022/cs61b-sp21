package deque;

import static java.lang.System.arraycopy;
import static java.lang.System.in;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int startIndex = 3;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = startIndex + 1;
        nextLast = startIndex + 2;
        size = 0;
    }

    private void expand(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        arraycopy(items, 0, temp, 0, startIndex);
        arraycopy(items, startIndex, temp, startIndex + size, size - startIndex);
        nextLast = startIndex;
        startIndex += size;
        nextFirst = startIndex - 1;
        items = temp;
    }
    private void shrink(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        int origLen = capacity * 4;
        int tailCount = origLen - nextFirst - 1;
        int headCount = nextLast;
        arraycopy(items, (nextFirst + 1) % origLen, temp, capacity - tailCount, tailCount);
        arraycopy(items, 0, temp, 0, headCount);
        nextFirst = capacity - tailCount - 1;
        startIndex = startIndex - capacity * 3;
        items = temp;
    }
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            expand(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            expand(size * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (T item : items) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && size * 4 < items.length) {
            shrink(items.length / 4);
        }
        int firstIndex = (nextFirst + 1) % items.length;
        T first = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        size--;
        return first;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && (size - 1) * 4 < items.length) {
            shrink(items.length / 4);
        }
        int lastIndex = (nextLast - 1 + items.length) % items.length;
        T last = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size--;
        return last;
    }

    @Override
    public T get(int i) {
        if (i < 0 || i >= size) {
            return null;
        }
        return items[arrIndex(i)];
    }

    private int arrIndex(int index) {
        return (nextFirst + index + 1 + items.length) % items.length;
    }
}
