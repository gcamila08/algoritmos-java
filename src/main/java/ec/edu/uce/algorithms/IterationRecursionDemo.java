package ec.edu.uce.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IterationRecursionDemo {
    private static final int REPETITIONS = 5;

    public static void run() {
        List<String> rows = new ArrayList<>();

        // JVM warm-up: run every method a few times before measuring
        int[] warmup = createArray(1000);
        for (int w = 0; w < 200; w++) {
            Iteration.iterativeFactorial(20);
            Recursion.recursiveFactorial(20);
            Iteration.iterativeArraySum(warmup);
            Recursion.recursiveArraySum(warmup);
            Iteration.iterativeArrayMax(warmup);
            Recursion.recursiveArrayMax(warmup);
        }

        System.out.println("\n--- ACTIVITY 2: ITERATIVE VS RECURSIVE COMPARISON ---");
        System.out.printf("%-10s %-8s %-10s %-22s %-8s %-14s %-12s%n",
                "Problem", "Input n", "Version", "Result", "Ops", "Avg time (ns)", "Same result");

        // Factorial with different values of n
        for (int n : new int[]{5, 10, 20}) {
            var iter = averageFactorial(n, true);
            var rec = averageFactorial(n, false);
            boolean same = iter.result.equals(rec.result);
            rows.add(printRow("Factorial", n, "Iterative", iter.result, iter.operations, iter.timeNs, same));
            rows.add(printRow("Factorial", n, "Recursive", rec.result, rec.operations, rec.timeNs, same));
        }

        // Sum and maximum: required test case and bigger arrays
        int[][] arrays = {{8, 3, 10, 2, 7}, createArray(100), createArray(1000), createArray(5000)};
        for (int[] arr : arrays) {
            var sumIter = averageSum(arr, true);
            var sumRec = averageSum(arr, false);
            boolean sameSum = sumIter.result.equals(sumRec.result);
            rows.add(printRow("Sum", arr.length, "Iterative", sumIter.result, sumIter.operations, sumIter.timeNs, sameSum));
            rows.add(printRow("Sum", arr.length, "Recursive", sumRec.result, sumRec.operations, sumRec.timeNs, sameSum));

            var maxIter = averageMax(arr, true);
            var maxRec = averageMax(arr, false);
            boolean sameMax = maxIter.result.equals(maxRec.result);
            rows.add(printRow("Max", arr.length, "Iterative", maxIter.result, maxIter.operations, maxIter.timeNs, sameMax));
            rows.add(printRow("Max", arr.length, "Recursive", maxRec.result, maxRec.operations, maxRec.timeNs, sameMax));
        }

        CsvWriter.write("iteration_recursion_results.csv",
                "Problem,Input n,Version,Result,Operations,Average time (ns),Same result", rows);
    }

    // Repeats each method several times and returns the average time
    private static Iteration.OperationMetric<Long> averageFactorial(int n, boolean iterative) {
        Iteration.OperationMetric<Long> last = null;
        long total = 0;
        for (int r = 0; r < REPETITIONS; r++) {
            last = iterative ? Iteration.iterativeFactorial(n) : Recursion.recursiveFactorial(n);
            total += last.timeNs;
        }
        return new Iteration.OperationMetric<>(last.result, last.operations, total / REPETITIONS);
    }

    private static Iteration.OperationMetric<Long> averageSum(int[] arr, boolean iterative) {
        Iteration.OperationMetric<Long> last = null;
        long total = 0;
        for (int r = 0; r < REPETITIONS; r++) {
            last = iterative ? Iteration.iterativeArraySum(arr) : Recursion.recursiveArraySum(arr);
            total += last.timeNs;
        }
        return new Iteration.OperationMetric<>(last.result, last.operations, total / REPETITIONS);
    }

    private static Iteration.OperationMetric<Integer> averageMax(int[] arr, boolean iterative) {
        Iteration.OperationMetric<Integer> last = null;
        long total = 0;
        for (int r = 0; r < REPETITIONS; r++) {
            last = iterative ? Iteration.iterativeArrayMax(arr) : Recursion.recursiveArrayMax(arr);
            total += last.timeNs;
        }
        return new Iteration.OperationMetric<>(last.result, last.operations, total / REPETITIONS);
    }

    private static int[] createArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (i * 37) % 101;   // repeatable values between 0 and 100
        }
        return arr;
    }

    private static String printRow(String problem, int n, String version, Object result,
                                   long ops, long timeNs, boolean same) {
        String sameText = same ? "YES" : "NO";
        System.out.printf(Locale.US, "%-10s %-8d %-10s %-22s %-8d %-14d %-12s%n",
                problem, n, version, result, ops, timeNs, sameText);
        return String.format(Locale.US, "%s,%d,%s,%s,%d,%d,%s",
                problem, n, version, result, ops, timeNs, sameText);
    }

    public static void main(String[] args) {
        run();
    }
}
