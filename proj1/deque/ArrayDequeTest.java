package deque;

import org.junit.Test;


import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void Visualizer() {
        ArrayDeque<Integer> arr = new ArrayDeque<>();
        arr.addLast(1);
        System.out.println(arr.get(0));
        arr.addLast(2);
        arr.addFirst(3);
        System.out.println(arr.get(0));
        System.out.println(arr.get(1));
        arr.addLast(4);
        arr.addFirst(5);
        System.out.println(arr.removeFirst());
        arr.addFirst(6);
        arr.addFirst(7);
        arr.addFirst(8);
        arr.addFirst(9);
        arr.addLast(10);
        arr.addLast(11);
        arr.addFirst(1);
        arr.addFirst(2);
        arr.addFirst(3);
        arr.addFirst(4);
        arr.addFirst(5);
        arr.addFirst(6);
        arr.addFirst(7);
        arr.addFirst(8);
        arr.addFirst(9);
        arr.addLast(10);
        arr.addLast(11);
        arr.removeFirst();
        arr.removeFirst();
        arr.removeFirst();
        arr.removeFirst();
        arr.removeFirst();
        arr.removeFirst();
        arr.removeFirst();
        arr.removeFirst();
        arr.removeLast();
        arr.removeLast();
        arr.removeLast();
        arr.removeLast();
        arr.removeLast();
        arr.removeLast();
        arr.removeLast();
        arr.addFirst(1);
        arr.addFirst(2);
        arr.addFirst(3);
        arr.addFirst(4);
        arr.addFirst(5);
        arr.addLast(10);
        arr.addLast(11);
    }
}

