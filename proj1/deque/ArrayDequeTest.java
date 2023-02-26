package deque;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;


import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void randomMethodCall() {
        ArrayDeque<Integer> arr = new ArrayDeque<>();
        int s = 0;
        StringBuilder errMsg = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            int intBetweenZeroAndFour = StdRandom.uniform(4);
            switch (intBetweenZeroAndFour) {
                case 0 -> {
                    arr.addFirst(i);
                    s++;
                    errMsg.append("addFirst(%d)\n".formatted(i));
                    assertEquals(s, arr.size());
                    assertEquals(errMsg.toString(), i, (int) arr.get(0));
                }
                case 1 -> {
                    arr.addLast(i);
                    s++;
                    errMsg.append("addLast(%d)\n".formatted(i));
                    assertEquals(s, arr.size());
                    assertEquals(errMsg.toString(), i, (int) arr.get(arr.size() - 1));
                }
                case 2 -> {
                    if (arr.size() > 0) {
                        int expected = arr.get(0);
                        int removed = arr.removeFirst();
                        s--;
                        errMsg.append("removeFirst()\n");
                        assertEquals(s, arr.size());
                        assertEquals(errMsg.toString(), expected, removed);
                    }
                }
                case 3 -> {
                    if (arr.size() > 0) {
                        int expected = arr.get(arr.size() - 1);
                        int removed = arr.removeLast();
                        s--;
                        errMsg.append("removeLast()\n");
                        assertEquals(s, arr.size());
                        assertEquals(errMsg.toString(), expected, removed);
                    }
                }
            }
        }
    }
}

