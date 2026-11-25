package ec.edu.uce.algorithms;

/**
 * Iterative versions of factorial, array sum and array maximum.
 */

public class Iteration {
    public static class OperationMetric<T> {
        public final T result;
        public final long operations;
        public final long timeNs;

        public OperationMetric(T result, long operations, long timeNs) {
            this.result = result;
            this.operations = operations;
            this.timeNs = timeNs;
        }
    }

    public static OperationMetric<Long> iterativeFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The factorial is not defined for negative numbers.");
        }
        if (n > 20) {
            throw new IllegalArgumentException("n > 20 exceeds the maximum range of the long type.");
        }
        long start = System.nanoTime();
        long result = 1L;
        long ops = 0;
        for (int i = 2; i <= n; i++) {
            result *= i;
            ops++;
        }
        long end = System.nanoTime();
        return new OperationMetric<>(result, ops, end - start);
    }

    public static OperationMetric<Long> iterativeArraySum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("The array cannot be null or empty.");
        }
        long start = System.nanoTime();
        long sum = 0L;
        long ops = 0;
        for (int value : arr) {
            sum += value;
            ops++;
        }
        long end = System.nanoTime();
        return new OperationMetric<>(sum, ops, end - start);
    }

    public static OperationMetric<Integer> iterativeArrayMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("The array cannot be null or empty.");
        }
        long start = System.nanoTime();
        int max = arr[0];
        long ops = 0;
        for (int i = 1; i < arr.length; i++) {
            ops++;
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        long end = System.nanoTime();
        return new OperationMetric<>(max, ops, end - start);
    }
}
