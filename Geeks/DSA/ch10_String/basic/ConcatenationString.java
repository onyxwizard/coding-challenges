package DSA.ch10_String.basic;
/**
 * @author onyxwizard
 * @date 24-12-2025
 */

public class ConcatenationString {
  public static String concat1(String s1, String s2) {
    //Approach 1:
    return s1 + s2;
  }

  public static String concat2(String s1, String s2) {
    //Approach 2:
    int len1 = s1.length();
    int len2 = s2.length();
    int total = len1 + len2;

    // Use StringBuilder (not synchronized, faster)
    StringBuilder sb = new StringBuilder(total);

    // First copy s1
    for (int i = 0; i < len1; i++) {
      sb.append(s1.charAt(i));
    }

    // Then copy s2
    for (int i = 0; i < len2; i++) {
      sb.append(s2.charAt(i));
    }

    return sb.toString();
  }
  
  public static String concat3(String s1, String s2) {
    int totalLength = s1.length() + s2.length();
    StringBuilder sb = new StringBuilder(totalLength);
    sb.append(s1).append(s2);  // Use append for whole strings
    return sb.toString();
  }
  public static void main(String[] args) {
    // Create large strings
        // 100 Mill -> 10 cr data (1cr = 10mill)
        String s1 = "A".repeat(100000000); 
        String s2 = "B".repeat(100000000);
        
        long start, end;
        
        // Test Approach 1
        start = System.currentTimeMillis();
        String r1 = s1 + s2;
        end = System.currentTimeMillis();
        System.out.println("Approach 1 (s1 + s2): " + (end - start) + "ms");
        
        // Test Approach 2
        start = System.currentTimeMillis();
        String r2 = concat2(s1, s2);
        end = System.currentTimeMillis();
        System.out.println("Approach 2 (char loop): " + (end - start) + "ms");
        
        // Test Optimized version
        start = System.currentTimeMillis();
        String r3 = concat3(s1, s2);
        end = System.currentTimeMillis();
        System.out.println("Optimized: " + (end - start) + "ms");

  }
}
