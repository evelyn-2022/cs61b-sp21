package tester;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import static org.junit.Assert.*;

public class TestArrayDequeEC {
    @Test
    public void randomArrayTest() {
        StudentArrayDeque<Integer> stdArr = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> arrSol = new ArrayDequeSolution<>();
        StringBuilder errMsg = new StringBuilder();

        for (int i = 0; i < 1000; i += 1) {
            int intBetweenZeroAndFour = StdRandom.uniform(4);

            switch (intBetweenZeroAndFour) {
                case 0 -> {
                    stdArr.addFirst(i);
                    arrSol.addFirst(i);

                    errMsg.append("addFirst(%d)\n".formatted(i));
                    assertEquals(errMsg.toString(), arrSol.size(), stdArr.size());
                    assertEquals(errMsg.toString(), arrSol.get(0), stdArr.get(0));
                    assertEquals(errMsg.toString(), arrSol.get(arrSol.size() - 1), stdArr.get(stdArr.size() - 1));
                }
                case 1 -> {
                    stdArr.addLast(i);
                    arrSol.addLast(i);

                    System.out.println("----------------------------");
                    System.out.println("sizes: %d, %d".formatted(arrSol.size(), stdArr.size()));
                    System.out.println("solution: " + arrSol);

                    System.out.print("Student actual: ");
                    for (int j = 0; j < stdArr.size(); j++) {
                        System.out.print(stdArr.get(j) + " ");
                    }
                    System.out.println();

                    System.out.println("Student array size: " + stdArr.size() + "; Student array last: " + stdArr.get(stdArr.size() - 1));

                    errMsg.append("addLast(%d)\n".formatted(i));
                    assertEquals(errMsg.toString(), arrSol.size(), stdArr.size());
                    assertEquals(errMsg.toString(), arrSol.get(0), stdArr.get(0));
                    assertEquals(errMsg.toString(), arrSol.get(arrSol.size() - 1), stdArr.get(stdArr.size() - 1));
                }
                case 2 -> {
                    if (stdArr.size() > 0 && arrSol.size() > 0) {
                        stdArr.removeFirst();
                        arrSol.removeFirst();

                        errMsg.append("removeFirst()\n");
                        assertEquals(errMsg.toString(), arrSol.size(), stdArr.size());

                        if (arrSol.size() > 0) {
                            assertEquals(errMsg.toString(), arrSol.get(0), stdArr.get(0));
                            assertEquals(errMsg.toString(), arrSol.get(arrSol.size() - 1), stdArr.get(stdArr.size() - 1));
                        }
                    }
                }
                case 3 -> {
                    if (stdArr.size() > 0 && arrSol.size() > 0) {
                        stdArr.removeLast();
                        arrSol.removeLast();

                        errMsg.append("removeLast()\n");
                        assertEquals(errMsg.toString(), arrSol.size(), stdArr.size());

                        if (arrSol.size() > 0) {
                            assertEquals(errMsg.toString(), arrSol.get(0), stdArr.get(0));
                            assertEquals(errMsg.toString(), arrSol.get(arrSol.size() - 1), stdArr.get(stdArr.size() - 1));
                        }
                    }
                }
            }


        }
    }
}
