/**
 * ================================================
 * MAIN CLASS - UseCase3PalindromeCheckerApp
 * ================================================
 *
 * Use Case 3: Reverse String Based Palindrome Check
 *
 * Description:
 * This class checks whether a string is a palindrome
 * by reversing the string and comparing it with
 * the original value.
 *
 * At this stage, the application:
 * - Iterates the string in reverse order
 * - Builds a reversed version
 * - Compares original and reversed strings
 * - Displays the validation result
 *
 * Key Concepts:
 * - Loop (for loop)
 * - String Immutability
 * - String Concatenation (+)
 * - equals() Method
 * - Data Structure: String
 *
 * @author Developer
 * @version 3.0
 */
public class PalindromeCheckerApp_UseCase3 {

    public static void main(String[] args) {
        // Hardcoded string literal
        String input = "madam";

        // Build reversed string
        String reversed = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed = reversed + input.charAt(i); // concatenation
        }

        // Compare original and reversed
        boolean isPalindrome = input.equals(reversed);

        // Display result
        System.out.println("Input text: " + input);
        System.out.println("Reversed text: " + reversed);
        System.out.println("Is it a Palindrome? : " + isPalindrome);
    }
}
