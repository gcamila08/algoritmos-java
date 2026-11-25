package ec.edu.uce.algorithms;
import java.util.Arrays;
/**
 * Main class: runs the four activities of the practical part.
 * The CSV files are saved in results/tables/.
 */
public class Main {
    public static void main(String[] args) {
        // ---------------- ACTIVITY 1 ----------------
        System.out.println("--- ACTIVITY 1: ALGORITHMIC COMPLEXITY ---");
        int[] data = {10, 20, 30, 40, 50};
        var resA = Complexity.linearSum(data);
        System.out.println("Algorithm A O(n) -> Sum: " + resA.sum + " | Ops: " + resA.dominantOperations);

        var resB = Complexity.nestedComparisons(data);
        System.out.println("Algorithm B O(n^2) -> Comparisons: " + resB.comparedPairs
                + " | Repeated pairs: " + resB.duplicatePairs);
        var resC = Complexity.halveUntilOne(16);
        System.out.println("Algorithm C O(log n) with n=16 -> Steps: " + resC.halvingSteps);
        ComplexityDemo.run();
        // ---------------- ACTIVITY 3 ----------------
        System.out.println("\n--- ACTIVITY 3: SORTING (required test case) ---");
        int[] dataToSort = {45, 12, 90, 12, 3, 70, 45, 18};
        var bRes = Sorting.bubbleSort(dataToSort);
        System.out.println("Bubble Sort:    " + Arrays.toString(bRes.sortedArray));
        var sRes = Sorting.selectionSort(dataToSort);
        System.out.println("Selection Sort: " + Arrays.toString(sRes.sortedArray));
        var iRes = Sorting.insertionSort(dataToSort);
        System.out.println("Insertion Sort: " + Arrays.toString(iRes.sortedArray));
        var shRes = Sorting.shellSort(dataToSort);
        System.out.println("Shell Sort:     " + Arrays.toString(shRes.sortedArray));

        SortingDemo.run();

        // ---------------- ACTIVITY 4 ----------------
        Benchmark.runBenchmarkAndExportCsv();
    }
}