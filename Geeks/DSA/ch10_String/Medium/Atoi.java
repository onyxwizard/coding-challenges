package DSA.ch10_String.Medium;
/**
 * @author onyxwizard
 * @date 26-12-2025
 */

public class Atoi {
    public int myAtoi(String s) {
        // Edge case
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        int index = 0;
        int result = 0;
        int sign = 1; // 1 for positive, -1 for negative
        
        // 1. Skip leading whitespaces
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }
        
        // Check if we've reached the end of string
        if (index >= n) {
            return 0;
        }
        
        // 2. Check for sign
        if (s.charAt(index) == '-') {
            sign = -1;
            index++;
        } else if (s.charAt(index) == '+') {
            sign = 1;
            index++;
        }
        
        // 3. Process digits
        while (index < n && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';
            
            // 4. Check for overflow before actually multiplying
            // For positive numbers: check if result > Integer.MAX_VALUE/10 
            // OR if result == Integer.MAX_VALUE/10 and digit > 7
            if (result > Integer.MAX_VALUE / 10 || 
                (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            
            result = result * 10 + digit;
            index++;
        }
        
        return result * sign;
    }
}