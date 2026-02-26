package DSA.ch1_logic.Interviews.ArithmeticNumberTheory;
/**
 * @author onyxwizard
 * @date 30-12-2025
 */

public class ArmstrongNumbers {
  int count(int n) {
    int c = 0;
    while (n > 0) {
      n /= 10;
      c++;
    }
    return c;
  }

  int validAmstrong(int c, int n) {
    int res = 0;
    while (n > 0) {
      int rem = n % 10;
      res += Math.pow(rem, c);
      n /= 10;
    }
    return res;
  }
  public boolean armstrong(int n) {
    int digitCount = count(n);
    return validAmstrong(digitCount, n) == n;
  }
  public static void main(String[] args) {
    ArmstrongNumber a = new ArmstrongNumber();
    System.out.println(a.armstrong(123));
  }
}
