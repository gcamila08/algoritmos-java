package ec.edu.uce.algorithms;

public class Recursion {
    private static class Counter {
        long operations = 0;
    }

    public static Iteration.OperationMetric<Long> recursiveFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The factorial is not defined for negative numbers.");
        }
        if (n > 20) {
            throw new IllegalArgumentException("n > 20 exceeds the maximum range of the long type.");
        }
        Counter counter = new Counter();
        long start = System.nanoTime();
        long result = factorialHelper(n, counter);
        long end = System.nanoTime();
        return new Iteration.OperationMetric<>(result, counter.operations, end - start);
    }

    private static long factorialHelper(int n, Counter counter) {
        counter.operations++;
        if (n == 0 || n == 1) {          // base case
            return 1L;
        }
        return n * factorialHelper(n - 1, counter);   // recursive case
    }

    public static Iteration.OperationMetric<Long> recursiveArraySum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("The array cannot be null or empty.");
        }
        Counter counter = new Counter();
        long start = System.nanoTime();
        long result = sumHelper(arr, 0, counter);
        long end = System.nanoTime();
        return new Iteration.OperationMetric<>(result, counter.operations, end - start);
    }

    private static long sumHelper(int[] arr, int index, Counter counter) {
        counter.operations++;
        if (index == arr.length - 1) {   // base case: last element
            return arr[index];
        }
        return arr[index] + sumHelper(arr, index + 1, counter);
    }

    public static Iteration.OperationMetric<Integer> recursiveArrayMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("The array cannot be null or empty.");
        }
        Counter counter = new Counter();
        long start = System.nanoTime();
        int result = maxHelper(arr, 0, counter);
        long end = System.nanoTime();
        return new Iteration.OperationMetric<>(result, counter.operations, end - start);
    }

    private static int maxHelper(int[] arr, int index, Counter counter) {
        counter.operations++;
        if (index == arr.length - 1) {   // base case: last element
            return arr[index];
        }
        int maxOfRest = maxHelper(arr, index + 1, counter);
        return Math.max(arr[index], maxOfRest);
    }
}
