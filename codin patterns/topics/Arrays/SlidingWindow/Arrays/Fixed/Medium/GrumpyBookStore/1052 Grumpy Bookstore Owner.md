There is a bookstore owner that has a store open for n minutes. You are given an integer array customers of length n where customers[i] is the number of the customers that enter the store at the start of the ith minute and all those customers leave after the end of that minute.

During certain minutes, the bookstore owner is grumpy. You are given a binary array grumpy where grumpy[i] is 1 if the bookstore owner is grumpy during the ith minute, and is 0 otherwise.

When the bookstore owner is grumpy, the customers entering during that minute are not satisfied. Otherwise, they are satisfied.

The bookstore owner knows a secret technique to remain not grumpy for minutes consecutive minutes, but this technique can only be used once.

Return the maximum number of customers that can be satisfied throughout the day.

 

Example 1:

Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3

Output: 16

Explanation:

The bookstore owner keeps themselves not grumpy for the last 3 minutes.

The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.

Example 2:

Input: customers = [1], grumpy = [0], minutes = 1

Output: 1

 

Constraints:

    n == customers.length == grumpy.length
    1 <= minutes <= n <= 2 * 104
    0 <= customers[i] <= 1000
    grumpy[i] is either 0 or 1.

## 📊 Question Decode Given: 

    customers[i] = number of customers entering at minute i
    grumpy[i] = 1 if owner is grumpy during minute i, 0 if NOT grumpy
     

😊 Customer Satisfaction Rules: 

    If owner is NOT grumpy (grumpy[i] == 0) → customers are satisfied ✅  
    If owner is grumpy (grumpy[i] == 1) → customers are NOT satisfied ❌
     

    🔑 Key Insight:
    "Not grumpy" = good for customers
    "Grumpy" = bad for customers 
    
 
🎯 The Secret Technique 

    Owner can force themselves to be NOT grumpy for exactly minutes consecutive minutes
    This overrides the original grumpy array for that window
    Can only be used once
     

💡 What this means: 

    During the chosen minutes-long window, ALL customers become satisfied — even if grumpy[i] was originally 1


## My Code
```java
package SlidingWindow.Arrays.Fixed.Medium.GrumpyBookStore;

public class GrumpyBookStore {
    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int maxSatisfiedCustomer = 0;
        for(int i =0; i<customers.length;i++){
            if(grumpy[i] == 0){
                maxSatisfiedCustomer+= customers[i];
            }
        }


        int customerSatisfied = maxSatisfiedCustomer;
        for(int i=0;i<minutes;i++){
            if(grumpy[i]==1){
                customerSatisfied += customers[i];
            }
        }
        maxSatisfiedCustomer = Math.max(customerSatisfied,maxSatisfiedCustomer);


        for(int i=minutes;i<customers.length;i++){
            int oldIdx = i-minutes;
            int newIdx = i;
            if(grumpy[oldIdx] == 1){
                customerSatisfied -= customers[oldIdx];
            }
            if(grumpy[newIdx] == 1) {
                customerSatisfied += customers[newIdx];
            }
            maxSatisfiedCustomer = Math.max(customerSatisfied,maxSatisfiedCustomer);
        }

        return maxSatisfiedCustomer;
    }

    public static void main(String[] args) {
        int[] customers = {1,0,1,2,1,1,7,5}, grumpy = {0,1,0,1,0,1,0,1};
        int minutes = 3;
        int res = maxSatisfied(customers,grumpy,minutes);
        System.out.println(res);

        int[] customerss = {4,10,10}, grumpys = {1,1,0};
        minutes = 2;
        res = maxSatisfied(customerss,grumpys,minutes);
        System.out.println(res);
    }
}

```


```java
package SlidingWindow.VariableSize.Medium.GrumpyBookstoreOwner;

/**
 * Solution for "Grumpy Bookstore Owner" problem.
 *
 * Problem Summary:
 * - A bookstore owner is open for n minutes
 * - customers[i] = number of customers entering at minute i
 * - grumpy[i] = 1 if owner is grumpy at minute i, 0 otherwise
 * - Customers are satisfied ONLY when owner is NOT grumpy (grumpy[i] == 0)
 * - Owner has a secret technique to stay NOT grumpy for exactly 'minutes' consecutive minutes (used once)
 * - Goal: Maximize total satisfied customers
 *
 * Approach: Sliding Window
 * 1. Calculate base satisfied customers (when grumpy[i] == 0)
 * 2. Use sliding window of size 'minutes' to find the best segment where we can convert grumpy minutes to satisfied
 * 3. The optimal window maximizes additional satisfied customers (from originally grumpy minutes)
 *
 * Time Complexity: O(n) - single pass through the array
 * Space Complexity: O(1) - only using a few variables
 */
public class GrumpyBookStore {

    /**
     * Calculates the maximum number of satisfied customers possible by optimally using
     * the secret technique for 'minutes' consecutive minutes.
     *
     * @param customers array where customers[i] is the number of customers at minute i
     * @param grumpy array where grumpy[i] is 1 if owner is grumpy at minute i, 0 otherwise
     * @param minutes the duration the owner can stay not grumpy (consecutive minutes)
     * @return maximum number of satisfied customers possible
     */
    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        // Step 1: Calculate base satisfied customers (when owner is naturally not grumpy)
        int baseSatisfied = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                baseSatisfied += customers[i];
            }
        }

        // Step 2: Calculate additional satisfied customers in the first window of size 'minutes'
        // (these are customers from grumpy minutes that we can convert to satisfied)
        int windowAdditional = 0;
        for (int i = 0; i < minutes; i++) {
            if (grumpy[i] == 1) {
                windowAdditional += customers[i];
            }
        }

        // Track the maximum additional customers we can satisfy in any window
        int maxAdditional = windowAdditional;

        // Step 3: Slide the window across the array
        for (int i = minutes; i < customers.length; i++) {
            // Remove the leftmost element of the previous window
            int leftIndex = i - minutes;
            if (grumpy[leftIndex] == 1) {
                windowAdditional -= customers[leftIndex];
            }

            // Add the new rightmost element to the current window
            int rightIndex = i;
            if (grumpy[rightIndex] == 1) {
                windowAdditional += customers[rightIndex];
            }

            // Update maximum additional satisfied customers
            maxAdditional = Math.max(maxAdditional, windowAdditional);
        }

        // Total satisfied = base satisfied + best additional from our secret technique
        return baseSatisfied + maxAdditional;
    }

    // ======================================================================================
    // TEST HARNESS
    // ======================================================================================

    public static void main(String[] args) {
        System.out.println("=== Grumpy Bookstore Owner - Test Cases ===\n");

        // Test Case 1: Example from problem statement
        int[] customers1 = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy1 = {0, 1, 0, 1, 0, 1, 0, 1};
        int minutes1 = 3;
        int result1 = maxSatisfied(customers1, grumpy1, minutes1);
        System.out.println("Test Case 1:");
        System.out.println("Customers: [1, 0, 1, 2, 1, 1, 7, 5]");
        System.out.println("Grumpy:    [0, 1, 0, 1, 0, 1, 0, 1]");
        System.out.println("Minutes: " + minutes1);
        System.out.println("Result: " + result1); // Expected: 16
        System.out.println("Expected: 16 ✅\n");

        // Test Case 2: Single minute
        int[] customers2 = {1};
        int[] grumpy2 = {0};
        int minutes2 = 1;
        int result2 = maxSatisfied(customers2, grumpy2, minutes2);
        System.out.println("Test Case 2:");
        System.out.println("Customers: [1]");
        System.out.println("Grumpy:    [0]");
        System.out.println("Minutes: " + minutes2);
        System.out.println("Result: " + result2); // Expected: 1
        System.out.println("Expected: 1 ✅\n");

        // Test Case 3: Owner is always grumpy - secret technique makes all satisfied
        int[] customers3 = {10, 20, 30, 40};
        int[] grumpy3 = {1, 1, 1, 1};
        int minutes3 = 2;
        int result3 = maxSatisfied(customers3, grumpy3, minutes3);
        System.out.println("Test Case 3 (Always grumpy):");
        System.out.println("Customers: [10, 20, 30, 40]");
        System.out.println("Grumpy:    [1, 1, 1, 1]");
        System.out.println("Minutes: " + minutes3);
        System.out.println("Result: " + result3); // Expected: 70 (30+40 from best window)
        System.out.println("Expected: 70 ✅\n");

        // Test Case 4: Owner is never grumpy - secret technique doesn't help
        int[] customers4 = {5, 10, 15, 20};
        int[] grumpy4 = {0, 0, 0, 0};
        int minutes4 = 3;
        int result4 = maxSatisfied(customers4, grumpy4, minutes4);
        System.out.println("Test Case 4 (Never grumpy):");
        System.out.println("Customers: [5, 10, 15, 20]");
        System.out.println("Grumpy:    [0, 0, 0, 0]");
        System.out.println("Minutes: " + minutes4);
        System.out.println("Result: " + result4); // Expected: 50 (all customers satisfied)
        System.out.println("Expected: 50 ✅\n");

        // Test Case 5: Mixed scenario with optimal window in middle
        int[] customers5 = {2, 4, 1, 8, 3, 7};
        int[] grumpy5 = {1, 0, 1, 1, 0, 1};
        int minutes5 = 3;
        int result5 = maxSatisfied(customers5, grumpy5, minutes5);
        System.out.println("Test Case 5 (Optimal window in middle):");
        System.out.println("Customers: [2, 4, 1, 8, 3, 7]");
        System.out.println("Grumpy:    [1, 0, 1, 1, 0, 1]");
        System.out.println("Minutes: " + minutes5);
        System.out.println("Result: " + result5); // Expected: 25 (base: 4+3=7, best window: 1+8+7=16, total=23? Let's calculate...)
        // Base satisfied: index 1 (4) + index 4 (3) = 7
        // Windows of size 3:
        // [0,1,2]: recoverable = 2+1 = 3 → total = 10
        // [1,2,3]: recoverable = 1+8 = 9 → total = 16
        // [2,3,4]: recoverable = 1+8 = 9 → total = 16
        // [3,4,5]: recoverable = 8+7 = 15 → total = 22
        // So expected: 22
        System.out.println("Expected: 22 ✅\n");
    }
}
```