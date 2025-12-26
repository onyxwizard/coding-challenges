package DSA.ch10_String.Medium;

/**
 * @author onyxwizard
 * @date 25-12-2025
 */


public class FirstRepChar {
  public static String firstRepChar(String s) {
    // code here
    // Works only for lowercase alphabets
    boolean[] counter = new boolean[26];
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      int idx = c - 'a';
      if (counter[idx])
        return Character.toString(c);
      counter[idx] = true;
    }
    return "-1";
  }

  public static void main(String[] args) {
    String s = FirstRepChar.firstRepChar("hhello");
    System.out.println(s);
  }
}
