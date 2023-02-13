package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  @Test
  public void testThreeAddThreeRemove() {
    AListNoResizing<Integer> lis = new AListNoResizing<Integer>();
    BuggyAList<Integer> buggyLis = new BuggyAList<Integer>();
    lis.addLast(4);
    buggyLis.addLast(4);
    lis.addLast(5);
    buggyLis.addLast(5);
    lis.addLast(6);
    buggyLis.addLast(6);
    lis.removeLast();
    buggyLis.removeLast();
    for (int i = 0; i < lis.size(); i++) {
      assertEquals(lis.get(i), buggyLis.get(i));
    }
    lis.removeLast();
    buggyLis.removeLast();
    for (int i = 0; i < lis.size(); i++) {
      assertEquals(lis.get(i), buggyLis.get(i));
    }
    lis.removeLast();
    buggyLis.removeLast();
    for (int i = 0; i < lis.size(); i++) {
      assertEquals(lis.get(i), buggyLis.get(i));
    }
  }

  @Test
  public void randomizedTest() {
    AListNoResizing<Integer> L = new AListNoResizing<Integer>();
    BuggyAList<Integer> B = new BuggyAList<Integer>();

    int N = 500;
    for (int i = 0; i < N; i += 1) {
      int operationNumber = StdRandom.uniform(0, 4);
      if (operationNumber == 0) {
        // addLast
        int randVal = StdRandom.uniform(0, 100);
        L.addLast(randVal);
        B.addLast(randVal);
        System.out.println("addLast(" + randVal + ")");
      } else if (operationNumber == 1) {
        // size
        int size = L.size();
        int sizeB = B.size();
        System.out.println("size: " + size);
        assertEquals(size,sizeB);
      } else if (operationNumber == 2) {
        // getLast
        if (L.size() == 0) continue;
        int last = L.getLast();
        int lastB = B.getLast();
        System.out.println("last: " + last);
        assertEquals(last, lastB);
      } else if (operationNumber == 3) {
        // removeLast
        if (L.size() == 0) continue;
        int last = L.removeLast();
        int lastB = B.removeLast();
        System.out.println("removed: " + last + lastB);
        assertEquals(last, lastB);
      }
    }
  }
}
