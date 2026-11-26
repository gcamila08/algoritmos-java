package ec.edu.uce.algorithms;

import java.util.*;

/**
 * Activity 4: benchmark that compares the four sorting algorithms
 * with 10, 100, 1,000 and 10,000 elements and five data types.
 * - Each combination runs 5 times on identical copies and the average time is calculated.
 * - Before measuring, each combination runs once on a copy without being measured (JVM warm-up).
 * - Only the sorting is measured (data generation and printing are excluded).
 * Results: console and results/tables/benchmark_results.csv
 */
public class Benchmark {

    public enum DataType {
        ASCENDING,
        DESCENDING,
        RANDOM,
        REPEATED,
        NEARLY_SORTED
    }

    private static final int[] SIZES = {10, 100, 1000, 10000};
    private static final String[] ALGORITHMS = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Shell Sort"};
    private static final int REPETITIONS = 5;

    public static int[] generateArray(int n, DataType type, long seed) {
        int[] arr = new int[n];
        Random rand = new Random(seed);

        switch (type) {
            case ASCENDING:
                for (int i = 0; i < n; i++) arr[i] = i + 1;
                break;
            case DESCENDING:
                for (int i = 0; i < n; i++) arr[i] = n - i;
                break;
            case RANDOM:
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt(n * 10 + 1);
                break;
            case REPEATED:
                int pool = Math.max(1, Math.min(5, n / 2));
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt(pool);
                break;
            case NEARLY_SORTED:
                for (int i = 0; i < n; i++) arr[i] = i + 1;
                int swaps = Math.max(1, n / 10);
                for (int k = 0; k < swaps && n > 1; k++) {
                    int idx1 = rand.nextInt(n);
                    int idx2 = rand.nextInt(n);
                    int tmp = arr[idx1];
                    arr[idx1] = arr[idx2];
                    arr[idx2] = tmp;
                }
                break;
        }
        return arr;
    }

    public static void runBenchmarkAndExportCsv() {
        System.out.println("\n--- ACTIVITY 4: BENCHMARK ---");
        System.out.println("Machine: " + System.getProperty("os.name")
                + " | Java " + System.getProperty("java.version")
                + " | Cores: " + Runtime.getRuntime().availableProcessors()
                + " | Max memory: " + (Runtime.getRuntime().maxMemory() / (1024 * 1024)) + " MB");

        // General JVM warm-up on fresh copies
        int[] warmupBase = generateArray(1000, DataType.RANDOM, 42L);
        for (int w = 0; w < 10; w++) {
            for (String alg : ALGORITHMS) {
                runAlgorithm(alg, Arrays.copyOf(warmupBase, warmupBase.length));
            }
        }

        List<String> rows = new ArrayList<>();
        System.out.println("Algorithm,n,Data type,Comparisons,Movements,Average time (ms)");

        for (int n : SIZES) {
            for (DataType type : DataType.values()) {
                int[] base = generateArray(n, type, 1000L + n);

                for (String alg : ALGORITHMS) {
                    // Warm-up run for this combination (not measured, keeps base intact)
                    runAlgorithm(alg, Arrays.copyOf(base, base.length));

                    long comparisons = 0;
                    long movements = 0;
                    long totalTimeNs = 0;

                    for (int r = 0; r < REPETITIONS; r++) {
                        int[] copy = Arrays.copyOf(base, base.length);
                        Sorting.SortResult res = runAlgorithm(alg, copy);
                        checkSorted(alg, res.sortedArray);
                        comparisons = res.comparisons;
                        movements = res.movements;
                        totalTimeNs += res.timeNs;
                    }

                    double averageMs = (totalTimeNs / (double) REPETITIONS) / 1_000_000.0;
                    String line = String.format(Locale.US, "%s,%d,%s,%d,%d,%.6f",
                            alg, n, type.name(), comparisons, movements, averageMs);
                    rows.add(line);

                    if (n == 100 && type == DataType.RANDOM) {
                        System.out.println(line); // Table required by the assignment
                    }
                }
            }
        }

        CsvWriter.write("benchmark_results.csv",
                "Algorithm,n,Data type,Comparisons,Movements,Average time (ms)", rows);
    }

    // Kept so the old call in Main still works (the CSV is always saved in results/tables/)
    public static void runBenchmarkAndExportCsv(String csvPath) {
        runBenchmarkAndExportCsv();
    }

    public static Sorting.SortResult runAlgorithm(String alg, int[] data) {
        return switch (alg) {
            case "Bubble Sort" -> Sorting.bubbleSort(data);
            case "Selection Sort" -> Sorting.selectionSort(data);
            case "Insertion Sort" -> Sorting.insertionSort(data);
            case "Shell Sort" -> Sorting.shellSort(data);
            default -> throw new IllegalArgumentException("Unsupported algorithm: " + alg);
        };
    }

    private static void checkSorted(String alg, int[] arr) {
        for (int k = 1; k < arr.length; k++) {
            if (arr[k - 1] > arr[k]) {
                throw new IllegalStateException(alg + " did not sort the array correctly");
            }
        }
    }

    public static void main(String[] args) {
        runBenchmarkAndExportCsv();
    }
}