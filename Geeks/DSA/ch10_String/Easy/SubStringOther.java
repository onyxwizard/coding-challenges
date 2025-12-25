package DSA.ch10_String.Easy;
/**
 * @author onyxwizard
 * @date 25-12-2025
 */

public class SubStringOther {
  public boolean isSubSeq(String s1, String s2) {
        // code here
        int pt = 0;
        int size1 = s1.length();
        int size2 = s2.length();
        if(size1 > size2) return false;
        for (int i = 0; i < size2; i++) {
          if (pt < size1 && s1.charAt(pt) == s2.charAt(i)) {
            pt++;
          }
        }
        return pt == size1;
    }
}
