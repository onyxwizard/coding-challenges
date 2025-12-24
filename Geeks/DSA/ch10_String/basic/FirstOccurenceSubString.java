package DSA.ch10_String.basic;

/**
 * @author onyxwizard
 * @date 24-12-2025
 */

public class FirstOccurenceSubString {
    int firstOccurence(String txt, String pat) {
        int n = txt.length();
        int m = pat.length();

        // Edge cases
        if (m == 0)
            return 0;
        if (n < m)
            return -1;

        // Main search - using character-by-character comparison
        for (int i = 0; i <= n - m; i++) {
            boolean found = true;

            // Compare pat with txt starting at position i
            for (int j = 0; j < m; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    found = false;
                    break;
                }
            }

            if (found) {
                return i;
            }
        }
        return -1;
    }
}
