package DSA.ch10_String.Easy;
/**
 * @author onyxwizard
 * @date 24-12-2025
 */

public class BinaryString {
  boolean isBinary(String s) {
        // Your code here
        for (int i = 0; i < s.length(); i++) {
          if (s.charAt(i) != '0' && s.charAt(i) != '1') {
            return false;
          }
        }
        return true;
    }
}
