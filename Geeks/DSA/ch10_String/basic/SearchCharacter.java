package DSA.ch10_String.basic;
/**
 * @author onyxwizard
 * @date 24-12-2025
 */

public class SearchCharacter {
  public int findChar(String s, char c) {
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (Character.compare(c, ch) == 0) {
        return i;
      }
    }
    return -1;
  }
}
