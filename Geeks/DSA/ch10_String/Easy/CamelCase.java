package DSA.ch10_String.Easy;
/**
 * @author onyxwizard
 * @date 24-12-2025
 */

import java.util.Arrays;

public class CamelCase {
  public static String convertToCamelCase(String s) {
    // code here
    int n = s.length();

    //Early exit
    if (n == 1)
      return s;

    boolean flag = false;
    StringBuilder sb = new StringBuilder(n);
    for (int i = 0; i < n; i++) {
      char c = s.charAt(i);
      if (c == ' ') {
        flag = true;
      } else if (flag) {
        sb.append(Character.toUpperCase(c));
        flag = false;
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    String s = "Hello  Sir";
    String res = CamelCase.convertToCamelCase(s);
    System.out.println(res);
    }
}
