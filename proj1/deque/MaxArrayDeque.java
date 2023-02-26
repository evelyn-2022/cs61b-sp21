package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> dequeComparator;

    public MaxArrayDeque(Comparator<T> c) {
        dequeComparator = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }

        int maxDex = 0;
        for (int i = 0; i < size(); i++) {
            int cmp = dequeComparator.compare(get(i), get(maxDex));
            if (cmp > 0) {
                maxDex = i;
            }
        }
        return get(maxDex);
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }

        int maxDex = 0;
        for (int i = 0; i < size(); i++) {
            int cmp = c.compare(get(i), get(maxDex));
            if (cmp > 0) {
                maxDex = i;
            }
        }
        return get(maxDex);
    }
}
