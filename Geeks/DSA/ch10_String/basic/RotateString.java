package DSA.ch10_String.basic;

public class RotateString {
  public static String rotate(String s, int d) {
    int n = s.length();

    // Handle the case where d > size of string
    d %= n;
    if (d == 0)
      return s;
    // Convert string to a character array
    char[] temp = s.toCharArray();

    // Reverse the first d elements
    reverse(temp, 0, d - 1);

    // Reverse the remaining n-d elements
    reverse(temp, d, n - 1);

    // Reverse the entire array
    reverse(temp, 0, n - 1);

    // Convert the array back to a string and return
    return new String(temp);
  }

  static void reverse(char[] temp, int start, int end) {
    while (start < end) {
      char c = temp[start];
      temp[start] = temp[end];
      temp[end] = c;
      start++;
      end--;
    }
  }
  public static void main(String[] args) {
    String s = RotateString.rotate("Hello", 1);
    System.out.println(s);
  }
}
