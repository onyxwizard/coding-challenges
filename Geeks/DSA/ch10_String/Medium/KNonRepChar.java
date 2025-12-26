package DSA.ch10_String.Medium;

public class KNonRepChar {
  public char nonRepeatingChar(String s, int k) {
        // code here
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
          int idx = s.charAt(i);
          counter[idx - 'a'] += 1;
        }

        for (int i = 0; i < s.length(); i++) {
          if (counter[s.charAt(i) - 'a'] == 1) {
            k--;
            if (k == 0) {
              return s.charAt(i);
            }
          }
        }
        return '0';
    }
}
