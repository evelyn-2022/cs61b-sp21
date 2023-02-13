package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<Integer>();
        AList<Integer> Ns2 = new AList<Integer>();
        AList<Double> times = new AList<Double>();
        AList<Integer> opCounts = new AList<Integer>();
        SLList<Object> targetList = new SLList<Object>();
        int M = 10000;

        Ns2.addLast(0);
        for (double i = 0.0; i <= 7.0; i++) {
            int n = (int) (1000 * Math.pow(2.0, i));
            Ns.addLast(n);
            Ns2.addLast(n);
            opCounts.addLast(M);
        }


        for (int i = 0; i < Ns.size(); i++) {
            for (int j = Ns2.get(i); j < Ns2.get(i + 1); j++) {
                targetList.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for (int k = 0; k < M; k++) {
                targetList.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }

        printTimingTable(Ns, times, opCounts);
    }

}
