package DSA.ch10_String.Easy;
/**
 * @author onyxwizard
 * @date 25-12-2025
 */

public class AnagramStrings {
  public static boolean areAnagrams(String s1, String s2) {
        // code here
        int len1 = s1.length();
        int len2 = s2.length();
        
        //Early Exit
        if (len1 != len2)
          return false;

        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
          char c1 = s1.charAt(i);
          char c2 = s2.charAt(i);

          count[c1 - 'a'] += 1;
          count[c2 - 'a'] -= 1;
        }

        for (int i : count) {
          if (i != 0) {
            return false;
          }
        }
        return true;
    }
}
