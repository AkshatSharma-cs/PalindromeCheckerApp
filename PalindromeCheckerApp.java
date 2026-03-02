/**
 * ================================================
 * MAIN CLASS - UseCase2PalindromeCheckerApp
 * ================================================
 *
 * Use Case 2: Hardcoded Palindrome Validation
 *
 * Description:
 * This class demonstrates basic palindrome validation
 * using a hardcoded string value.
 *
 * At this stage, the application:
 * - Stores a predefined string
 * - Compares characters from both ends
 * - Determines whether the string is a palindrome
 * - Displays the result on the console
 *
 * Key Concepts:
 * - Class
 * - Main Method
 * - Static Keyword
 * - String & String Literal
 * - Conditional Statement (if-else)
 * - Console Output
 *
 * @author Developer
 * @version 2.0
 */
public class PalindromeCheckerApp {

    public static void main(String[] args) {
        // Hardcoded string literal
        String input = "madam";

        boolean isPalindrome = true;

        // Loop only till half of the string length
        for (int i = 0; i < input.length() / 2; i++) {
            if (input.charAt(i) != input.charAt(input.length() - 1 - i)) {
                isPalindrome = false;
                break;
            }
        }

        // Print result to console
        System.out.println("Input text: " + input);
        System.out.println("Is it a Palindrome? : " + isPalindrome);
    }
}
