package DSA.ch10_String.Easy;
/**
 * @author onyxwizard
 * @date 24-12-2025
 */

public class ChackPanagram {
  // Approach1 : use Arrays[26]
  public static boolean checkPangram(String s) {
    // code here
    boolean[] seen = new boolean[26];
    for (int i = 0; i < s.length(); i++) {
      char t = s.charAt(i);
      if (!Character.isAlphabetic(t))
        continue;
      char c = Character.isUpperCase(t) ? Character.toLowerCase(t) : t;
      seen[c - 'a'] = true;
    }

    for (boolean f : seen) {
      if (!f) {
        return false;
      }
    }
    return true;
  }
  
  // Approach 2 : Bit Mask
  public static boolean checkPangramBitmask(String s) {
    int mask = 0;  // 32-bit integer can store 26 bits
    
    for (int i = 0; i < s.length(); i++) {
        char t = s.charAt(i);
        if (!Character.isAlphabetic(t))
            continue;
        char c = Character.toLowerCase(t);
        int bit = 1 << (c - 'a');  // Set the corresponding bit
        mask |= bit;
        
        // Early exit check: if all 26 bits are set
        if (mask == 0x3FFFFFF) {  // 0x3FFFFFF = 26 bits set
            return true;
        }
    }
    
    return mask == 0x3FFFFFF;
}
}
