package DSA.ch1_logic.Interviews.ArithmeticNumberTheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author onyxwizard
 * @date 31-12-2025
 */

public class PowerSet {
  List<String> AllPossibleStrings(String s) {
    List<String> result = new ArrayList<>();
    int n = s.length();
    int total = 1 << n;

    for (int i = 0; i < total; i++) {
      StringBuilder subset = new StringBuilder();
      for (int j = 0; j < n; j++) {
        if ((i & (1 << j)) != 0) {
          subset.append(s.charAt(j));
        }
      }
      result.add(subset.toString());
    }

    // Optional: Sort lexicographically
    Collections.sort(result);
    return result;
  }

  public static void main(String[] args) {
    PowerSet ps = new PowerSet();
    ps.AllPossibleStrings("ab");
  }
}
