package ec.edu.uce.algorithms;

/**
 * Shows the edge cases, the rejected invalid inputs
 * and the stack overflow risk of the recursive solutions.
 * Results: console (take a screenshot for the evidence).
 */

public class ValidationDemo {

    public static void run() {
        System.out.println("\n--- ACTIVITY 2: EDGE CASES ---");
        System.out.println("Factorial(0)  iterative = " + Iteration.iterativeFactorial(0).result
                + " | recursive = " + Recursion.recursiveFactorial(0).result);
        System.out.println("Factorial(1)  iterative = " + Iteration.iterativeFactorial(1).result
                + " | recursive = " + Recursion.recursiveFactorial(1).result);
        System.out.println("Factorial(20) iterative = " + Iteration.iterativeFactorial(20).result
                + " | recursive = " + Recursion.recursiveFactorial(20).result
                + "  (largest value that fits in long)");
        int[] oneElement = {7};
        System.out.println("Sum of {7}    iterative = " + Iteration.iterativeArraySum(oneElement).result
                + " | recursive = " + Recursion.recursiveArraySum(oneElement).result);
        System.out.println("Max of {7}    iterative = " + Iteration.iterativeArrayMax(oneElement).result
                + " | recursive = " + Recursion.recursiveArrayMax(oneElement).result);
        int[] negatives = {-5, -2, -9};
        System.out.println("Max of {-5, -2, -9} iterative = " + Iteration.iterativeArrayMax(negatives).result
                + " | recursive = " + Recursion.recursiveArrayMax(negatives).result);

        System.out.println("\n--- ACTIVITY 2: INVALID INPUTS ---");
        test("Iterative factorial(-1)", () -> Iteration.iterativeFactorial(-1));
        test("Recursive factorial(-1)", () -> Recursion.recursiveFactorial(-1));
        test("Iterative factorial(21)", () -> Iteration.iterativeFactorial(21));
        test("Recursive factorial(21)", () -> Recursion.recursiveFactorial(21));
        test("Iterative sum of empty array", () -> Iteration.iterativeArraySum(new int[]{}));
        test("Recursive sum of empty array", () -> Recursion.recursiveArraySum(new int[]{}));
        test("Iterative max of null array", () -> Iteration.iterativeArrayMax(null));
        test("Recursive max of null array", () -> Recursion.recursiveArrayMax(null));

        System.out.println("\n--- ACTIVITY 2: STACK OVERFLOW RISK ---");
        int[] bigArray = new int[1_000_000];
        try {
            Recursion.recursiveArraySum(bigArray);
            System.out.println("Recursive sum with 1,000,000 elements -> no error");
        } catch (StackOverflowError e) {
            System.out.println("Recursive sum with 1,000,000 elements -> StackOverflowError "
                    + "(too many calls stored in the call stack)");
        }
        System.out.println("Iterative sum with 1,000,000 elements -> "
                + Iteration.iterativeArraySum(bigArray).result + " (no error, it uses O(1) memory)");
    }

    private static void test(String testCase, Runnable action) {
        try {
            action.run();
            System.out.println(testCase + " -> no error thrown (check!)");
        } catch (IllegalArgumentException e) {
            System.out.println(testCase + " -> rejected: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        run();
    }
}
