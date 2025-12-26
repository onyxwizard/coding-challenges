package DSA.ch10_String.Medium;
/**
 * @author onyxwizard
 * @date 25-12-2025
 */

public class StringRotation {
  public static boolean areRotations(String s1, String s2) {
        // code here
        /**
         * Approach 1: Knuth Morris Patt Algorithm
         */
        int[] lps = lpsArray(s2);
        boolean flag = searchPattern(s1+s1,s2, lps);
        return flag;

    }

    public static int[] lpsArray(String s2) {
      int n = s2.length();
      int[] lps = new int[n];
      lps[0] = 0;
      int len = 0, pt = 1;
      while (pt < n) {
        if (s2.charAt(len) == s2.charAt(pt)) {
          len++;
          lps[pt] = len;
          pt++;
        } else {
          if (len != 0) {
            len = lps[len - 1];
          } else {
            lps[pt] = 0;
            pt++;
          }
        }
      }
      return lps;

    }

    public static boolean searchPattern(String s, String pat, int[] lps) {
      int n = s.length();
      int m = pat.length();
      int i = 0;
      int len = 0;
      while (i < n) {
        if (s.charAt(i) == pat.charAt(len)) {
          len++;
          i++;
        }
        if (len == m)
          return true;
        else if (i < n && pat.charAt(len) != s.charAt(i)) {
          if (len != 0) {
            len = lps[len - 1];
          } else {
            i++;
          }
        }
      }
      return false;
    }
    public static void main(String[] args) {
      boolean res = StringRotation.areRotations("AAAABCAEAAABCBDDAAAABC", "AAABC");
      System.out.println(res);
    }
}
