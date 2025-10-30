package Arrays.FastSlowPointers.Easy.HappyNumber;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    // Helper: Compute sum of squares of digits
    private static int getNext(int n) {
        int total = 0;
        while (n > 0) {
            int digit = n % 10;
            total += digit * digit;
            n /= 10;
        }
        return total;
    }

    // -------------------------------
    // METHOD 1: Iterative (Fast & Slow Pointers)
    // -------------------------------
    public static boolean isHappyIterative(int n) {
        int slow = n;
        int fast = getNext(n);

        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }

        return fast == 1;
    }

    // -------------------------------
    // METHOD 2: Recursive (with Set for cycle detection)
    // -------------------------------
    public static boolean isHappyRecursive(int n) {
        return isHappyRecursiveHelper(n, new HashSet<>());
    }

    private static boolean isHappyRecursiveHelper(int n, Set<Integer> seen) {
        if (n == 1) {
            return true;
        }
        if (seen.contains(n)) {
            return false; // cycle detected
        }

        seen.add(n);
        int next = getNext(n);
        return isHappyRecursiveHelper(next, seen);
    }

    // -------------------------------
    // Main method for testing
    // -------------------------------
    public static void main(String[] args) {
        int[] testCases = {99, 9, 11111, 7, 19};

        System.out.println("Using Iterative (Fast-Slow) Method:");
        for (int n : testCases) {
            System.out.println("isHappy(" + n + ") = " + isHappyIterative(n));
        }

        System.out.println("\nUsing Recursive (with Set) Method:");
        for (int n : testCases) {
            System.out.println("isHappy(" + n + ") = " + isHappyRecursive(n));
        }
    }
}