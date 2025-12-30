package DSA.ch1_logic.logic.basic;
/**
 * @author onyxwizard
 * @date 30-12-2025
 */

public class CloseN {
  int closestNumber(int n, int m) {
    int q = n / m;
    
    int first = q * m;
    int mul = n*m;
    int sec = mul> 0 ? (q + 1) * m : (q - 1) * m;
    
    if(Math.abs(first - n) < Math.abs(sec-n)){
      return first;
    }
    return sec;
  }
  public static void main(String[] args) {
    CloseN c = new CloseN();
    int res = c.closestNumber(-15, 6);
    System.out.println(res);

    res = c.closestNumber(13, 4);
    System.out.println(res);
  }
}
