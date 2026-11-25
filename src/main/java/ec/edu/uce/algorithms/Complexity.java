package ec.edu.uce.algorithms;

public class Complexity {
    public static class ComplexityResult {
        public final long sum;
        public final long comparedPairs;
        public final long duplicatePairs;
        public final long halvingSteps;
        public final long dominantOperations;

        public ComplexityResult(long sum, long comparedPairs, long duplicatePairs,
                                long halvingSteps, long dominantOperations) {
            this.sum = sum;
            this.comparedPairs = comparedPairs;
            this.duplicatePairs = duplicatePairs;
            this.halvingSteps = halvingSteps;
            this.dominantOperations = dominantOperations;
        }
    }

    // Algorithm A: O(n) -> linear traversal that adds all the elements
    // Dominant operation: the addition inside the loop -> T(n) = n
    public static ComplexityResult linearSum(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The array cannot be null.");
        }
        long sum = 0;
        long ops = 0;
        for (int value : array) {
            sum += value;
            ops++;
        }
        return new ComplexityResult(sum, 0, 0, 0, ops);
    }

    // Algorithm B: O(n^2) -> nested loops that compare each element with the others
    // and count repeated grades. Dominant operation: the comparison -> T(n) = n(n-1)/2
    public static ComplexityResult nestedComparisons(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The array cannot be null.");
        }
        long comparisons = 0;
        long duplicates = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                comparisons++;
                if (array[i] == array[j]) {
                    duplicates++;
                }
            }
        }
        return new ComplexityResult(0, comparisons, duplicates, 0, comparisons);
    }

    // Algorithm C: O(log n) -> divides a positive number by 2 until it reaches 1
    // Dominant operation: the division -> T(n) = floor(log2 n)
    public static ComplexityResult halveUntilOne(long n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be a positive integer greater than or equal to 1.");
        }
        long steps = 0;
        long current = n;
        while (current > 1) {
            current = current / 2;
            steps++;
        }
        return new ComplexityResult(0, 0, 0, steps, steps);
    }
}
