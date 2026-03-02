/**
 * ================================================
 * MAIN CLASS - UseCase13PalindromeCheckerApp
 * ================================================
 *
 * Use Case 13: Performance Comparison
 *
 * Description:
 * This class measures and compares the execution
 * performance of palindrome validation algorithms.
 *
 * At this stage, the application:
 * - Uses multiple palindrome strategy implementations
 * - Captures execution start and end time
 * - Calculates total execution duration
 * - Displays benchmarking results
 *
 * The goal is to introduce benchmarking concepts.
 *
 * @author Akshat
 * @version 13.0
 */
public class PalindromeCheckerApp {

    // Strategy 1: String reversal
    public static boolean isPalindromeReverse(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        return input.equals(reversed);
    }

    // Strategy 2: Two-pointer approach
    public static boolean isPalindromeTwoPointer(String input) {
        int left = 0, right = input.length() - 1;
        while (left < right) {
            if (input.charAt(left) != input.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Strategy 3: Stack-based approach
    public static boolean isPalindromeStack(String input) {
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for (char c : input.toCharArray()) {
            stack.push(c);
        }
        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) {
            reversed.append(stack.pop());
        }
        return input.equals(reversed.toString());
    }

    /**
     * Application entry point for UC13.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        String testInput = "level";

        // Run and benchmark each algorithm
        benchmark("Reverse Method", () -> isPalindromeReverse(testInput));
        benchmark("Two-Pointer Method", () -> isPalindromeTwoPointer(testInput));
        benchmark("Stack Method", () -> isPalindromeStack(testInput));
    }

    // Benchmark utility method
    private static void benchmark(String methodName, java.util.function.Supplier<Boolean> algorithm) {
        long start = System.nanoTime();
        boolean result = algorithm.get();
        long end = System.nanoTime();
        long duration = end - start;

        System.out.println("Method: " + methodName);
        System.out.println("Input : level");
        System.out.println("Is Palindrome? : " + result);
        System.out.println("Execution Time : " + duration + " ns");
        System.out.println("-----------------------------------");
    }
}
