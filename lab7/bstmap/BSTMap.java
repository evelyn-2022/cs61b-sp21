package bstmap;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode<K, V> root;
    private int size = 0;
    private HashSet<K> keySet = new HashSet<>();

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
            keySet.add(key);
            size += 1;
        } else {
            root = put(root, key, value);
        }
    }

    private BSTNode<K, V> put(BSTNode<K, V> r, K key, V value) {
        if (r == null) {
            size += 1;
            keySet.add(key);
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

    @Override
    public V remove(K key) {
        if (root == null) {
            return null;
        }
        V returnVal = get(key);
        root = remove(root, key);
        return returnVal;
    }

    private BSTNode<K, V> remove(BSTNode<K, V> r, K key) {
        if (r == null) {
            return null;
        }
        int cmp = r.key.compareTo(key);
        if (cmp < 0) {
            r.right = remove(r.right, key);
        } else if (cmp > 0) {
            r.left = remove(r.left, key);
        } else {
            if (r.right == null) {
                return r.left;
            }
            if (r.left == null) {
                return r.right;
            }

            BSTNode<K, V> n = r;
            r = min(n.right);
            r.right = deleteMin(n.right);
            r.left = n.left;
        }
        return r;
    }

    private BSTNode<K, V> min(BSTNode<K, V> n) {
        if (n.left == null) {
            return n;
        }
        return min(n.left);
    }

    private BSTNode<K, V> deleteMin(BSTNode<K, V> n) {
        if (n.left == null) {
            return n.right;
        }
        n.left = deleteMin(n.left);
        return n;
    }

    @Override
    public V remove(K key, V value) {
        if (root == null || !get(key).equals(value)) {
            return null;
        }
        return remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        for (K key : keySet) {
            System.out.println("{" + key + ", " + get(key) + "}");
        }
    }
}
