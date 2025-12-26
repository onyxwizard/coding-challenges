package DSA.ch10_String.Medium;
/**
 * @author onyxwizard
 * @date 25-12-2025
 */

public class FirstNonRepChar {
  public char nonRepeatingChar(String s) {
        // code here
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
          int idx = s.charAt(i);
          counter[idx - 'a'] += 1;
        }
        for (int i = 0; i < s.length(); i++) {
          if (counter[s.charAt(i) - 'a'] == 1) {
            return s.charAt(i);
          }
        }
        return '$';
    }
}
