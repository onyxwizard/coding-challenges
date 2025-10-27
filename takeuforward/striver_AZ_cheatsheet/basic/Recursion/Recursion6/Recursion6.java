public class Recursion6 {

    public static boolean isPalindrome(String word) {
        if (word == null || word.isEmpty()) {
            return true; // Convention: empty string is a palindrome
        }
        return isPalindromeRecursive(word, 0, word.length() - 1);
    }

    private static boolean isPalindromeRecursive(String word, int left, int right) {
        // Base case: pointers have met or crossed
        if (left >= right) {
            return true;
        }

        // Check characters
        if (word.charAt(left) != word.charAt(right)) {
            return false;
        }

        // Move toward center
        return isPalindromeRecursive(word, left + 1, right - 1);
    }

    public static void main(String[] args) {
        String word = "ABBA";
        System.out.println(isPalindrome(word)); // true

        word = "ABCBA";
        System.out.println(isPalindrome(word)); // true

        word = "ABCA";
        System.out.println(isPalindrome(word)); // false

        word = "";
        System.out.println(isPalindrome(word)); // true

        word = " ";
        System.out.println(isPalindrome(word)); // true
    }
}