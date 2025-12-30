package DSA.ch1_logic.logic.basic;
/**
 * @author onyxwizard
 * @date 30-12-2025
 */

public class SwapNumber {
  public static void main(String[] args) {
    int a = 2, b = 3;
    System.out.println("a = " + a + " b = " + b);

    // Swap a and b using temp variable
    int temp = a;
    a = b;
    b = temp;

    System.out.println("a = " + a + " b = " + b);
    
    
    // Approach using Arithemetic swapping
    a = a + b;
    b = a - b;
    a = a - b;

    System.out.println("a = " + a + " b = " + b);
    
    // Approach using XOR
    a = a ^ b;
    b = a ^ b;
    a = a ^ b;
    
    System.out.println("a = " + a + " b = " + b);
  }
    
}
