package DSA.ch10_String.basic;

public class ReverseString {
  // Time O(n) space : O(n)
  public static String reverseString(String s) {
    // code here
    StringBuilder sb = new StringBuilder();
    for (int i = s.length() - 1; i > -1; i--) {
      sb.append(s.charAt(i));
    }
    return sb.toString();
    }
}
