package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode<K, V> root;
    private int size = 0;

    private class BSTNode<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private BSTNode<K, V> left, right;

        public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        private BSTNode<K, V> put(BSTNode<K, V> r, K key, V value) {
            if (r == null) {
                size += 1;
                return new BSTNode<>(key, value, null, null);
            }
            int cmp = r.key.compareTo(key);
            if (cmp > 0) {
                r.left = put(r.left, key, value);
            } else if (cmp < 0) {
                r.right = put(r.right, key, value);
            } else {
                r.value = value;
            }
            return r;
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
     }

    @Override
    public boolean containsKey(K key) {
        BSTNode<K, V> r = root;
        while (r != null) {
            int cmp = r.key.compareTo(key);
            if (cmp > 0) {
                r = r.left;
            } else if (cmp < 0) {
                r = r.right;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        BSTNode<K, V> r = root;
        while (r != null) {
            int cmp = r.key.compareTo(key);
            if (cmp > 0) {
                r = r.left;
            } else if (cmp < 0) {
                r = r.right;
            } else {
                return r.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new BSTNode<>(key, value, null, null);
            size += 1;
        } else {
            root = root.put(root, key, value);
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
