package ec.edu.uce.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Activity 3: runs the four sorting algorithms with the required test data
 * and with the edge cases (empty, one element, repeated values and already sorted).
 * It checks that every result is sorted and that no element was lost or added.
 * Results: console and results/tables/sorting_edge_cases.csv
 */
public class SortingDemo {

    public static void run() {
        String[] algorithms = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Shell Sort"};
        String[] caseNames = {"Test data", "Empty", "One element", "Repeated", "Already sorted"};
        int[][] cases = {
                {45, 12, 90, 12, 3, 70, 45, 18},
                {},
                {99},
                {5, 5, 5, 5},
                {1, 2, 3, 4}
        };
        List<String> rows = new ArrayList<>();

        System.out.println("\n--- ACTIVITY 3: SORTING TESTS ---");
        for (int k = 0; k < cases.length; k++) {
            for (String alg : algorithms) {
                Sorting.SortResult r = sort(alg, cases[k]);
                String correct = isSorted(r.sortedArray) ? "YES" : "NO";
                String sameElements = sameElements(cases[k], r.sortedArray) ? "YES" : "NO";
                System.out.printf(Locale.US,
                        "%-15s %-15s %-35s comp=%-4d mov=%-4d time=%d ns  sorted=%s  sameElements=%s%n",
                        caseNames[k], alg, Arrays.toString(r.sortedArray),
                        r.comparisons, r.movements, r.timeNs, correct, sameElements);
                rows.add(String.format(Locale.US, "%s,%s,\"%s\",%d,%d,%d,%s,%s",
                        caseNames[k], alg, Arrays.toString(r.sortedArray),
                        r.comparisons, r.movements, r.timeNs, correct, sameElements));
            }
        }
        CsvWriter.write("sorting_edge_cases.csv",
                "Case,Algorithm,Result,Comparisons,Movements,Time (ns),Correctly sorted,Same elements", rows);
    }

    private static Sorting.SortResult sort(String alg, int[] data) {
        return switch (alg) {
            case "Bubble Sort" -> Sorting.bubbleSort(data);
            case "Selection Sort" -> Sorting.selectionSort(data);
            case "Insertion Sort" -> Sorting.insertionSort(data);
            case "Shell Sort" -> Sorting.shellSort(data);
            default -> throw new IllegalArgumentException("Unsupported algorithm: " + alg);
        };
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks that the sorted array has exactly the same values as the original
     * (same size and same number of times each value appears). It does not sort anything.
     */
    private static boolean sameElements(int[] original, int[] result) {
        if (original.length != result.length) {
            return false;
        }
        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int value : original) {
            counter.put(value, counter.getOrDefault(value, 0) + 1);
        }
        for (int value : result) {
            int count = counter.getOrDefault(value, 0);
            if (count == 0) {
                return false;
            }
            counter.put(value, count - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        run();
    }
}