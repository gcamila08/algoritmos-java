package ec.edu.uce.algorithms;

import java.util.Arrays;

/**
 * Activity 3: sorting algorithms implemented from scratch.
 *
 * Counting criterion:
 * - Comparison: each time two elements of the array are compared.
 * - Movement: each write of an element. A swap (Bubble, Selection) counts as 3 movements.
 *   In Insertion and Shell Sort: 1 when the key is saved, 1 for each shift and 1 when the key is placed.
 */
public class Sorting {

    public static class SortResult {
        public final int[] sortedArray;
        public final long comparisons;
        public final long movements;
        public final long timeNs;

        public SortResult(int[] sortedArray, long comparisons, long movements, long timeNs) {
            this.sortedArray = sortedArray;
            this.comparisons = comparisons;
            this.movements = movements;
            this.timeNs = timeNs;
        }
    }

    public static SortResult bubbleSort(int[] original) {
        validateArray(original);
        int[] arr = Arrays.copyOf(original, original.length);
        long comparisons = 0;
        long movements = 0;

        long start = System.nanoTime();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    movements += 3;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        long end = System.nanoTime();
        return new SortResult(arr, comparisons, movements, end - start);
    }

    public static SortResult selectionSort(int[] original) {
        validateArray(original);
        int[] arr = Arrays.copyOf(original, original.length);
        long comparisons = 0;
        long movements = 0;

        long start = System.nanoTime();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                movements += 3;
            }
        }
        long end = System.nanoTime();
        return new SortResult(arr, comparisons, movements, end - start);
    }

    public static SortResult insertionSort(int[] original) {
        validateArray(original);
        int[] arr = Arrays.copyOf(original, original.length);
        long comparisons = 0;
        long movements = 0;

        long start = System.nanoTime();
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            movements++;
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    movements++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            movements++;
        }
        long end = System.nanoTime();
        return new SortResult(arr, comparisons, movements, end - start);
    }

    public static SortResult shellSort(int[] original) {
        validateArray(original);
        int[] arr = Arrays.copyOf(original, original.length);
        long comparisons = 0;
        long movements = 0;

        long start = System.nanoTime();
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                movements++;
                int j = i;
                while (j >= gap) {
                    comparisons++;
                    if (arr[j - gap] > temp) {
                        arr[j] = arr[j - gap];
                        movements++;
                        j -= gap;
                    } else {
                        break;
                    }
                }
                arr[j] = temp;
                movements++;
            }
        }
        long end = System.nanoTime();
        return new SortResult(arr, comparisons, movements, end - start);
    }

    private static void validateArray(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("The array cannot be null.");
        }
    }
}