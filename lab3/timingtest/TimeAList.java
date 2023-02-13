package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * Created by hug.
 */
public class TimeAList {
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

        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        int[] sizes = {0, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        AList<Integer> Ns = new AList<Integer>();
        AList<Double> times = new AList<Double>();
        Stopwatch sw = new Stopwatch();
        AList<Integer> opCounts = new AList<Integer>();
        int count = 0;
        AList<Object> targetList = new AList<Object>();


        for (int i = 1; i < sizes.length; i++) {
            Ns.addLast(sizes[i]);
        }


        for (int i = 0; i < sizes.length - 1; i++) {
            for (int j = sizes[i]; j < sizes[i + 1]; j++) {
                targetList.addLast(j);
                count++;
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
            opCounts.addLast(count);
        }

        printTimingTable(Ns, times, opCounts);
    }
}
