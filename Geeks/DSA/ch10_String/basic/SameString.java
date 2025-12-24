package DSA.ch10_String.basic;
/**
 * @author onyxwizard
 * @date 24-12-2025
 */

public class SameString {
  public static boolean areStringsSame(String s1, String s2) {
    // code here
    if (s1.length() != s2.length()) {
            return false;
        }

        // Compare character by character
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
