package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
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

    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = get(i);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = temp;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = nextFirst == 0 ? items.length - 1 : nextFirst - 1;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
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
        if (items.length >= 16 && (size - 1) * 4 < items.length) {
            resize(items.length / 2);
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
            resize(items.length / 2);
        }
        int lastIndex = (nextLast - 1 >= 0) ? nextLast - 1 : nextLast - 1 + items.length;
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
        if (nextFirst + index + 1 >= items.length) {
            return nextFirst + index + 1 - items.length;
        }
        return nextFirst + index + 1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int wizPos;

        ArrayIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T returnItem = get(wizPos);
            wizPos++;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof Deque)) {
            return false;
        }

        Deque<T> obj = (Deque<T>) o;
        if (obj.size() != this.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!obj.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }
}
