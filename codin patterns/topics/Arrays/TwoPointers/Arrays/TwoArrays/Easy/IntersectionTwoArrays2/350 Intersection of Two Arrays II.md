Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.

 

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]

Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.

 

Constraints:

    1 <= nums1.length, nums2.length <= 1000
    0 <= nums1[i], nums2[i] <= 1000

 

Follow up:

    What if the given array is already sorted? How would you optimize your algorithm?
    What if nums1's size is small compared to nums2's size? Which algorithm is better?
    What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
### ✅ Is your current solution optimized?

**For the general case (unsorted arrays):**  
- **Time:** `O(n log n + m log m)` due to sorting.  
- **Space:** `O(min(n, m))` for the output (plus `O(1)` extra if we ignore sort’s stack space).  
- **Approach:** Two-pointer after sorting — **this is acceptable and commonly used**.

However, **it’s not the most optimal in all cases**. There’s a **hash map (frequency count)** approach that runs in **`O(n + m)` time** (linear) but uses **`O(n)` extra space**.

#### Hash Map Alternative (Better for unsorted, small `nums1`):
```java
public int[] intersect(int[] nums1, int[] nums2) {
    Map<Integer, Integer> freq = new HashMap<>();
    for (int num : nums1) freq.put(num, freq.getOrDefault(num, 0) + 1);

    List<Integer> result = new ArrayList<>();
    for (int num : nums2) {
        if (freq.getOrDefault(num, 0) > 0) {
            result.add(num);
            freq.put(num, freq.get(num) - 1);
        }
    }

    return result.stream().mapToInt(i -> i).toArray();
}
```
- **Time:** `O(n + m)`  
- **Space:** `O(n)` for the map + `O(k)` for result (`k` = intersection size)

👉 So: **If arrays are unsorted**, the hash map method is **faster asymptotically**.

---

## Now, let’s answer your **follow-up questions**:



### 🔹 1. **What if the given arrays are already sorted? How would you optimize your algorithm?**

✅ **Your two-pointer approach becomes optimal!**  
- No need to sort → **Time drops to `O(n + m)`**  
- Space: `O(1)` extra (excluding output)  
- This is **better than the hash map approach** in space and avoids overhead of hashing.

➡️ **Use your two-pointer method directly—skip sorting.**

---

### 🔹 2. **What if `nums1`'s size is small compared to `nums2`'s size? Which algorithm is better?**

✅ **Prefer the hash map approach with the smaller array as the map.**  
- Build frequency map of **`nums1`** (smaller one) → less memory  
- Then iterate through `nums2` (even if huge) and check against the map

**Why?**
- Time: `O(n + m)` where `n = nums1.length` (small)  
- Space: `O(n)` → minimal  
- Two-pointer would still require sorting **both** arrays → `O(m log m)` dominated by large `nums2`

➡️ **Hash map with small array as dictionary is better here.**

---

### 🔹 3. **What if elements of `nums2` are stored on disk, and memory is limited (can’t load all at once)?**

This is a **classic external memory / streaming scenario**.

✅ **Best strategy:**
1. **Load `nums1` into memory** (assuming it fits; if not, you’d need multi-pass or external sort).
2. **Build a frequency hash map of `nums1`**.
3. **Stream `nums2` in chunks from disk**:
   - For each element in the chunk, check against the map.
   - If present and count > 0, add to result and decrement count.
4. **Output the intersection as you go** (or buffer small results).

**Why not two-pointer?**  
- Requires **random access** to sorted `nums2`, but if it’s on disk and unsorted, you’d need to **sort it externally** (expensive: multi-pass merge sort on disk).
- Even if sorted on disk, two-pointer would require **sequential access**, but you’d still need to **store output** and manage pointers across chunks — more complex.

➡️ **Hash map + streaming `nums2` is the practical choice** for disk-based/large `nums2`.

---

### ✅ Summary Table

| Scenario                                      | Best Approach                     | Time             | Space       |
|----------------------------------------------|-----------------------------------|------------------|-------------|
| General unsorted                             | Hash map (smaller as dict)        | `O(n + m)`       | `O(min(n,m))` |
| **Already sorted**                           | ✅ Two-pointer (no sort)          | `O(n + m)`       | `O(1)`      |
| `nums1` << `nums2` (size)                    | Hash map on `nums1`               | `O(n + m)`       | `O(n)`      |
| `nums2` on disk (memory-limited)             | Hash map on `nums1` + stream `nums2` | `O(n + m)`    | `O(n)`      |

---



```java

```