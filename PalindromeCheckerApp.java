/**
 * MAIN CLASS - UC12PalindromeCheckerApp
 *
 * Use Case 12: Strategy Pattern for Palindrome Algorithms
 *
 * Description:
 * This class demonstrates how different palindrome
 * validation algorithms can be selected dynamically
 * at runtime using the Strategy Design Pattern.
 *
 * At this stage, the application:
 * - Defines a common PalindromeStrategy interface
 * - Implements a concrete Stack-based strategy
 * - Injects the strategy at runtime
 * - Executes the selected algorithm
 *
 * No performance comparison is done in this use case.
 * The focus is purely on algorithm interchangeability.
 *
 * The goal is to teach extensible algorithm design.
 *
 * @author Akshat
 * @version 12.0
 */

import java.util.Scanner;

public class PalindromeCheckerApp {

    /**
     * Application entry point for UC12.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String -> ");
        String input = sc.nextLine();

        // Inject strategy at runtime
        PalindromeStrategy strategy = new StackStrategy();

        boolean result = strategy.check(input);

        System.out.println("Input : " + input);
        System.out.println("Is Palindrome? : " + result);

        sc.close();
    }
}

/**
 * INTERFACE - PalindromeStrategy
 *
 * This interface defines a contract for all
 * palindrome checking algorithms.
 *
 * Any new algorithm must implement this interface
 * and provide its own validation logic.
 */
interface PalindromeStrategy {

    /**
     * Checks whether input string is a palindrome.
     *
     * @param input String to validate
     * @return true if palindrome, false otherwise
     */
    boolean check(String input);
}

/**
 * CLASS - StackStrategy
 *
 * This class provides a Stack-based implementation
 * of the PalindromeStrategy interface.
 *
 * It uses LIFO behavior to reverse characters
 * and compare them with the original sequence.
 */
class StackStrategy implements PalindromeStrategy {

    /**
     * Implements palindrome validation using Stack.
     *
     * @param input String to validate
     * @return true if palindrome, false otherwise
     */
    public boolean check(String input) {

        // Normalize input (optional but consistent with UC10/UC11)
        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Create a stack to store characters
        java.util.Stack<Character> stack = new java.util.Stack<>();

        // Push each character onto the stack
        for (char c : normalized.toCharArray()) {
            stack.push(c);
        }

        // Compare characters by popping from stack
        for (char c : normalized.toCharArray()) {
            if (c != stack.pop()) {
                return false;
            }
        }

        return true;
    }
}