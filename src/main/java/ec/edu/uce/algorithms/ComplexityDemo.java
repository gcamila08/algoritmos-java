package ec.edu.uce.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Activity 1: runs algorithms A, B and C with different input sizes and compares
 * the real number of operations with the theoretical T(n).
 * Results: console and results/tables/complexity_results.csv
 */

public class ComplexityDemo {
    public static void run() {
        int[] sizes = {10, 100, 1000, 10000};
        List<String> rows = new ArrayList<>();

        // JVM warm-up: run the three algorithms before measuring
        int[] warmup = new int[1000];
        for (int w = 0; w < 20; w++) {
            Complexity.linearSum(warmup);
            Complexity.nestedComparisons(warmup);
            Complexity.halveUntilOne(1000);
        }

        System.out.println("\n--- ACTIVITY 1: REAL OPERATIONS VS THEORETICAL T(n) ---");
        System.out.printf("%-8s %-10s %-14s %-14s %-8s %-12s%n",
                "n", "Algorithm", "Real ops", "Theoretical", "Match", "Time (ms)");

        for (int n : sizes) {
            int[] data = new int[n];
            for (int i = 0; i < n; i++) {
                data[i] = i + 1;
            }

            long start = System.nanoTime();
            var a = Complexity.linearSum(data);
            long timeA = System.nanoTime() - start;

            start = System.nanoTime();
            var b = Complexity.nestedComparisons(data);
            long timeB = System.nanoTime() - start;

            start = System.nanoTime();
            var c = Complexity.halveUntilOne(n);
            long timeC = System.nanoTime() - start;

            long theoA = n;                                           // T(n) = n
            long theoB = (long) n * (n - 1) / 2;                      // T(n) = n(n-1)/2
            long theoC = (long) Math.floor(Math.log(n) / Math.log(2)); // T(n) = floor(log2 n)

            rows.add(printRow(n, "A O(n)", a.dominantOperations, theoA, timeA));
            rows.add(printRow(n, "B O(n^2)", b.dominantOperations, theoB, timeB));
            rows.add(printRow(n, "C O(log n)", c.dominantOperations, theoC, timeC));
        }

        CsvWriter.write("complexity_results.csv",
                "n,Algorithm,Real operations,Theoretical T(n),Match,Time (ms)", rows);

        invalidInputTests();
    }

    /** Algorithm C only accepts n >= 1. These calls must be rejected. */
    private static void invalidInputTests() {
        System.out.println("\n--- ACTIVITY 1: INVALID INPUT TESTS (Algorithm C) ---");
        long[] invalidValues = {0, -5};
        for (long value : invalidValues) {
            try {
                Complexity.halveUntilOne(value);
                System.out.println("halveUntilOne(" + value + ") -> ERROR: it was accepted");
            } catch (IllegalArgumentException e) {
                System.out.println("halveUntilOne(" + value + ") -> REJECTED: " + e.getMessage());
            }
        }
        System.out.println("halveUntilOne(1) -> steps = " + Complexity.halveUntilOne(1).halvingSteps
                + " (valid minimum value)");
    }

    private static String printRow(int n, String algorithm, long real, long theoretical, long timeNs) {
        String match = (real == theoretical) ? "YES" : "NO";
        double timeMs = timeNs / 1_000_000.0;
        System.out.printf(Locale.US, "%-8d %-10s %-14d %-14d %-8s %-12.6f%n",
                n, algorithm, real, theoretical, match, timeMs);
        return String.format(Locale.US, "%d,%s,%d,%d,%s,%.6f",
                n, algorithm, real, theoretical, match, timeMs);
    }

    public static void main(String[] args) {
        run();
    }
}
