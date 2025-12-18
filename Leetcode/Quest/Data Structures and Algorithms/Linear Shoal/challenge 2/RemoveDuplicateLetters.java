import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * This class provides a solution for removing duplicate letters from a string
 * to obtain the smallest lexicographical order while preserving all unique
 * characters.
 * 
 * Problem: Given a string s, remove duplicate letters so that every letter
 * appears
 * exactly once in the result, and the result is the smallest in lexicographical
 * order
 * among all possible results.
 * 
 * Time Complexity: O(n) where n is the length of the input string
 * Space Complexity: O(1) since we use fixed-size data structures (max 26
 * characters)
 * 
 * Example:
 * Input: "bcabc" → Output: "abc"
 * Input: "cbacdcbc" → Output: "acdb"
 */
public class RemoveDuplicateLetters {

  /**
   * Removes duplicate letters from the input string and returns the smallest
   * lexicographical string containing all unique characters exactly once.
   * 
   * Algorithm Overview:
   * 1. Count frequency of each character in the string
   * 2. Use a stack to build the result string character by character
   * 3. Use a set to track characters already included in the result
   * 4. For each character:
   * a. Decrement its remaining count
   * b. Skip if already in result
   * c. While stack top > current character AND top appears later in string,
   * pop from stack and remove from set (allowing us to get a smaller sequence)
   * d. Push current character to stack and add to set
   * 5. Convert stack to string and return
   *
   * @param s Input string containing only lowercase English letters
   * @return The smallest lexicographical string with all unique characters
   *         exactly once
   * 
   * @implNote This algorithm implements a greedy approach with backtracking
   *           capability.
   *           The stack maintains characters in increasing order when possible,
   *           while ensuring we don't lose any unique characters permanently.
   */
  public String removeDuplicateLetters(String s) {
    // Stack to build the result in correct order
    // We use Stack for LIFO operations needed for backtracking
    Stack<Character> stack = new Stack<>();

    // Set to track characters already included in the result
    // This ensures each character appears exactly once
    Set<Character> seen = new HashSet<>();

    // Array to count remaining occurrences of each character
    // Index 0-25 correspond to 'a'-'z'
    int[] count = new int[26];

    // Phase 1: Calculate initial frequency of each character
    // This tells us how many times each character appears in the remaining string
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      count[c - 'a']++;
    }

    // Phase 2: Process each character to build the result
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      // Decrement the remaining count of current character
      // This represents that we've processed one occurrence
      count[c - 'a']--;

      // If character is already in our result, skip it
      // We maintain only one occurrence of each character
      if (seen.contains(c)) {
        continue;
      }

      // Greedy optimization: While the stack's top character is:
      // 1. Lexicographically larger than current character (stack.peek() > c)
      // 2. And will appear again later in the string (count[stack.peek() - 'a'] > 0)
      // Then we can safely remove it now and add it later for a smaller sequence
      while (!stack.isEmpty() &&
          stack.peek() > c &&
          count[stack.peek() - 'a'] > 0) {
        char removed = stack.pop();
        seen.remove(removed);
      }

      // Add current character to the result
      stack.push(c);
      seen.add(c);
    }

    // Phase 3: Convert stack to string
    // The stack contains characters in the correct order (bottom to top)
    StringBuilder sb = new StringBuilder();
    for (char c : stack) {
      sb.append(c);
    }

    return sb.toString();
  }
}