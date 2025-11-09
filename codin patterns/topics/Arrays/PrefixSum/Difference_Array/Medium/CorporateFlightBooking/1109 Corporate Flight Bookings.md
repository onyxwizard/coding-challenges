There are `n` flights that are labeled from `1` to `n`.

You are given an array of flight bookings `bookings`, where `bookings[i] = [firsti, lasti, seatsi]` represents a booking for flights `firsti` through `lasti` (**inclusive**) with `seatsi` seats reserved for **each flight** in the range.

Return _an array_ `answer` _of length_ `n`_, where_ `answer[i]` _is the total number of seats reserved for flight_ `i`.

**Example 1:**

**Input:** bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
**Output:** [10,55,45,25,25]
**Explanation:**
Flight labels:        1   2   3   4   5
Booking 1 reserved:  10  10
Booking 2 reserved:      20  20
Booking 3 reserved:      25  25  25  25
Total seats:         10  55  45  25  25
Hence, answer = [10,55,45,25,25]

**Example 2:**

**Input:** bookings = [[1,2,10],[2,2,15]], n = 2
**Output:** [10,25]
**Explanation:**
Flight labels:        1   2
Booking 1 reserved:  10  10
Booking 2 reserved:      15
Total seats:         10  25
Hence, answer = [10,25]

**Constraints:**

- `1 <= n <= 2 * 104`
- `1 <= bookings.length <= 2 * 104`
- `bookings[i].length == 3`
- `1 <= firsti <= lasti <= n`
- `1 <= seatsi <= 104`

# 📘 **1109. Corporate Flight Bookings – Comprehensive Solution**

---

## 📋 **Problem Statement**

You are managing flight seat bookings where each booking reserves seats for a **range of consecutive flights**. Given multiple bookings, you need to calculate the **total seats reserved for each flight**.

Each booking is represented as `[first, last, seats]` meaning:
- Reserve `seats` seats for each flight from `first` to `last` (inclusive)

### 🎯 **Key Requirements**:
- **Range updates**: Each booking affects a contiguous range of flights
- **Point queries**: Return total seats for each individual flight
- Handle up to **20,000 flights** and **20,000 bookings**
- **Efficient solution** required (brute force would be O(n × bookings))

---

## 🧠 **Core Insight: Difference Array (Range Update Optimization)**

### 🔑 **The Fundamental Idea**:

Instead of updating each flight in the range `[first, last]` individually (O(n) per booking), use a **difference array** technique:

1. For booking `[first, last, seats]`:
   - Add `seats` at index `first-1` (since flights are 1-indexed)
   - Subtract `seats` at index `last` (if `last < n`)
2. After processing all bookings, compute the **prefix sum** to get actual seat counts

### 💡 **Why This Works**:

The difference array stores the **changes** between consecutive elements:
- `diff[i] = answer[i] - answer[i-1]`
- To add `x` to range `[l, r]`: `diff[l] += x`, `diff[r+1] -= x`
- Prefix sum of `diff` gives the final answer

### 📊 **Example Walkthrough**:
`bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5`

| Step | Operation | diff array |
|------|-----------|------------|
| Init | - | `[0, 0, 0, 0, 0]` |
| [1,2,10] | `diff[0] += 10`, `diff[2] -= 10` | `[10, 0, -10, 0, 0]` |
| [2,3,20] | `diff[1] += 20`, `diff[3] -= 20` | `[10, 20, -10, -20, 0]` |
| [2,5,25] | `diff[1] += 25`, `diff[5]` (out of bounds) | `[10, 45, -10, -20, 0]` |
| Prefix Sum | - | `[10, 55, 45, 25, 25]` |

✅ Final result = `[10, 55, 45, 25, 25]`

---

## ✅ **Optimal Solution: Difference Array**

### 🧩 **Algorithm Steps**:
1. Initialize difference array of size `n` with zeros
2. For each booking `[first, last, seats]`:
   - `diff[first - 1] += seats`
   - `if (last < n) diff[last] -= seats`
3. Compute prefix sum of difference array to get final answer

---

## 🚀 **Complete Implementation with Test Cases**

```java
public class CorporateFlightBookings {

    /**
     * Calculates total seats reserved for each flight using difference array technique.
     * 
     * Approach: Difference Array (Range Update Optimization)
     * 
     * Key Insights:
     * 1. For range update [l, r] with value x:
     *    - diff[l] += x
     *    - diff[r+1] -= x (if r+1 < n)
     * 2. Prefix sum of diff array gives actual values
     * 3. O(1) per booking update, O(n) final computation
     * 
     * Time Complexity: O(bookings + n)
     * Space Complexity: O(n)
     * 
     * @param bookings array of [first, last, seats] bookings
     * @param n number of flights
     * @return array where answer[i] = total seats for flight i+1
     */
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        // Difference array: diff[i] represents change from flight i to i+1
        int[] diff = new int[n];
        
        // Process each booking
        for (int[] booking : bookings) {
            int first = booking[0];   // 1-indexed
            int last = booking[1];    // 1-indexed
            int seats = booking[2];
            
            // Add seats to start of range
            diff[first - 1] += seats;
            
            // Subtract seats after end of range (if within bounds)
            if (last < n) {
                diff[last] -= seats;
            }
        }
        
        // Compute prefix sum to get actual seat counts
        for (int i = 1; i < n; i++) {
            diff[i] += diff[i - 1];
        }
        
        return diff;
    }

    // ==================== Alternative: Direct Array Approach (Less Efficient) ====================
    /**
     * Naive approach for comparison - O(n × bookings) time
     * Included for educational purposes only
     */
    public static int[] corpFlightBookingsNaive(int[][] bookings, int n) {
        int[] answer = new int[n];
        
        for (int[] booking : bookings) {
            int first = booking[0];
            int last = booking[1];
            int seats = booking[2];
            
            // Update each flight in the range
            for (int i = first - 1; i <= last - 1; i++) {
                answer[i] += seats;
            }
        }
        
        return answer;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Corporate Flight Bookings ===\n");

        // Example 1: Standard case
        int[][] bookings1 = {{1,2,10}, {2,3,20}, {2,5,25}};
        int n1 = 5;
        System.out.println("Test 1: bookings = " + java.util.Arrays.deepToString(bookings1) + ", n = " + n1);
        int[] result1 = corpFlightBookings(bookings1, n1);
        System.out.println("Output: " + java.util.Arrays.toString(result1)); 
        // Expected: [10, 55, 45, 25, 25]

        // Example 2: Overlapping ranges
        int[][] bookings2 = {{1,2,10}, {2,2,15}};
        int n2 = 2;
        System.out.println("\nTest 2: bookings = " + java.util.Arrays.deepToString(bookings2) + ", n = " + n2);
        int[] result2 = corpFlightBookings(bookings2, n2);
        System.out.println("Output: " + java.util.Arrays.toString(result2)); 
        // Expected: [10, 25]

        // Edge Case 1: Single flight, single booking
        int[][] bookings3 = {{1,1,100}};
        int n3 = 1;
        System.out.println("\nTest 3: bookings = " + java.util.Arrays.deepToString(bookings3) + ", n = " + n3);
        int[] result3 = corpFlightBookings(bookings3, n3);
        System.out.println("Output: " + java.util.Arrays.toString(result3)); 
        // Expected: [100]

        // Edge Case 2: Booking covers all flights
        int[][] bookings4 = {{1,5,10}};
        int n4 = 5;
        System.out.println("\nTest 4: bookings = " + java.util.Arrays.deepToString(bookings4) + ", n = " + n4);
        int[] result4 = corpFlightBookings(bookings4, n4);
        System.out.println("Output: " + java.util.Arrays.toString(result4)); 
        // Expected: [10, 10, 10, 10, 10]

        // Edge Case 3: Multiple bookings for same range
        int[][] bookings5 = {{1,3,5}, {1,3,3}, {1,3,2}};
        int n5 = 3;
        System.out.println("\nTest 5: bookings = " + java.util.Arrays.deepToString(bookings5) + ", n = " + n5);
        int[] result5 = corpFlightBookings(bookings5, n5);
        System.out.println("Output: " + java.util.Arrays.toString(result5)); 
        // Expected: [10, 10, 10]

        // Edge Case 4: Non-overlapping ranges
        int[][] bookings6 = {{1,1,10}, {2,2,20}, {3,3,30}};
        int n6 = 3;
        System.out.println("\nTest 6: bookings = " + java.util.Arrays.deepToString(bookings6) + ", n = " + n6);
        int[] result6 = corpFlightBookings(bookings6, n6);
        System.out.println("Output: " + java.util.Arrays.toString(result6)); 
        // Expected: [10, 20, 30]

        // Edge Case 5: Large numbers
        int[][] bookings7 = {{1,10000,10000}};
        int n7 = 10000;
        System.out.println("\nTest 7: Large booking (10000 flights, 10000 seats)");
        long startTime = System.currentTimeMillis();
        int[] result7 = corpFlightBookings(bookings7, n7);
        long endTime = System.currentTimeMillis();
        System.out.println("First 5: " + java.util.Arrays.toString(java.util.Arrays.copyOf(result7, 5)));
        System.out.println("Time: " + (endTime - startTime) + " ms");
        // Expected: all 10000

        // Performance Comparison: Difference Array vs Naive
        System.out.println("\n=== Performance Comparison ===");
        int[][] largeBookings = new int[20000][3];
        for (int i = 0; i < 20000; i++) {
            largeBookings[i] = new int[]{1, 100, 1}; // Small ranges
        }
        int largeN = 100;
        
        // Difference Array
        long start1 = System.currentTimeMillis();
        corpFlightBookings(largeBookings, largeN);
        long end1 = System.currentTimeMillis();
        
        // Naive approach (only for small n to avoid timeout)
        long start2 = System.currentTimeMillis();
        corpFlightBookingsNaive(largeBookings, largeN);
        long end2 = System.currentTimeMillis();
        
        System.out.println("Difference Array (20000 bookings, n=100): " + (end1 - start1) + " ms");
        System.out.println("Naive Approach (20000 bookings, n=100): " + (end2 - start2) + " ms");
    }
}
```

---

## 📊 **Complexity Analysis**

| Approach | Time Complexity | Space Complexity | When to Use |
|----------|----------------|------------------|-------------|
| **Difference Array** | O(bookings + n) | O(n) | **Optimal** - production code |
| **Naive Approach** | O(bookings × n) | O(n) | Educational only, small inputs |

### For constraints (n ≤ 20000, bookings ≤ 20000):
- **Difference Array**: ~40,000 operations
- **Naive Approach**: up to 400,000,000 operations → likely timeout

---

## 🔍 **Why Difference Array Works So Well**

### 🎯 **Mathematical Foundation**:

Let `answer[i]` be seats for flight `i`, and `diff[i] = answer[i] - answer[i-1]` for `i > 0`.

When we add `x` seats to range `[l, r]`:
- `answer[l] += x` → `diff[l] += x`
- `answer[r+1] -= x` (if `r+1 < n`) → `diff[r+1] -= x`
- All intermediate differences remain unchanged

After all updates, `answer[i] = diff[0] + diff[1] + ... + diff[i]`

### 📈 **Performance Impact**:
- **Before**: Each booking takes O(range length) time
- **After**: Each booking takes O(1) time
- **Speedup**: Up to O(n) times faster per booking

---

## ⚠️ **Common Mistakes to Avoid**

### ❌ **Mistake 1: 0-indexed vs 1-indexed Confusion**
```java
// Wrong: not adjusting for 1-indexed flights
diff[first] += seats;  // should be diff[first-1]
```

### ❌ **Mistake 2: Array Out of Bounds**
```java
// Wrong: accessing diff[last] when last == n
diff[last] -= seats;  // should check if (last < n)
```

### ✅ **Correct Implementation**:
```java
diff[first - 1] += seats;
if (last < n) {
    diff[last] -= seats;
}
```

---

## 💡 **Real-World Applications**

- **Resource Allocation**: CPU time, memory, network bandwidth
- **Financial Systems**: Range-based interest calculations
- **Gaming**: Area-of-effect damage/resistance
- **Geographic Information Systems**: Elevation changes, population density
- **Database Systems**: Range updates in columnar storage

---

## 🎯 **Key Takeaways**

1. **Difference array** is the go-to technique for **range update + point query** problems
2. **O(1) per range update** makes it extremely efficient for large datasets
3. **Understanding the mathematical relationship** between original array and difference array is crucial
4. **This pattern appears in many problems**: Range Addition, Range Sum Query Mutable, etc.
5. **Always check bounds** when implementing difference array (especially array size)

This problem demonstrates how a **simple mathematical insight** can transform an O(n²) problem into an O(n) solution! 