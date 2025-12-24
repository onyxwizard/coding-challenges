package DSA.ch10_String.basic;

public class PalindromeString {
  boolean isPalindrome(String s) {
        // code here
        int left = 0, right = s.length() - 1;
        while (left < right) {
          if (s.charAt(right) != s.charAt(left))
            return false;
          left++;
          right--;
        }
        return true;
    }
}
