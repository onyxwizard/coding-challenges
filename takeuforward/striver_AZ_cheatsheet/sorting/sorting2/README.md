# Sorting 2: Efficient & Recursive Sorting Algorithms

This project implements **four fundamental sorting algorithms** with a focus on **recursive design** and **correctness**:

- ✅ **Merge Sort** – Stable, O(N log N) guaranteed
- ✅ **Recursive Bubble Sort** – Educational, with early termination
- ✅ **Recursive Insertion Sort** – Efficient for small/nearly-sorted data
- ✅ **Quick Sort** – In-place, average O(N log N)

All implementations are **tested**, **readable**, and suitable for **interviews** or **academic use**.



## 📌 Algorithms Overview

### 1. **Merge Sort**
- **Approach**: Divide-and-conquer. Recursively splits array, then merges sorted halves.
- **Time Complexity**: **O(N log N)** in all cases.
- **Space Complexity**: **O(N)** (temporary storage for merging).
- **Stable**: Yes.
- **Use Case**: When stable sort and guaranteed performance are needed.

> 🔍 *Implementation uses `ArrayList` for merging (simple and clear). For performance-critical code, primitive arrays are preferred.*



### 2. **Recursive Bubble Sort**
- **Approach**: Bubbles largest unsorted element to the end in each pass, recursively.
- **Optimization**: Early termination if no swaps occur (best-case O(N)).
- **Time Complexity**:
    - Best: **O(N)** (already sorted)
    - Worst: **O(N²)**
- **Space Complexity**: **O(N)** (recursion stack).
- **Use Case**: Learning recursion; not used in practice.

> ✅ Your version correctly uses `end` as exclusive upper bound and avoids infinite recursion.



### 3. **Recursive Insertion Sort**
- **Approach**: Inserts each element into its correct position in the sorted left subarray.
- **Time Complexity**:
    - Best: **O(N)** (nearly sorted)
    - Worst: **O(N²)**
- **Space Complexity**: **O(N)** (recursion depth).
- **Stable**: Yes.
- **Use Case**: Small datasets; often used as subroutine in hybrid sorts (e.g., Timsort).

> ✅ Fixed the common bug: preserves original index for recursion while using a separate variable for insertion.



### 4. **Quick Sort**
- **Approach**: Partition around a pivot, then recursively sort left/right subarrays.
- **Pivot Choice**: Last element (simple; can be improved with randomization).
- **Time Complexity**:
    - Average: **O(N log N)**
    - Worst: **O(N²)** (e.g., already sorted with bad pivot)
- **Space Complexity**: **O(log N)** average (recursion stack).
- **In-place**: Yes.
- **Use Case**: General-purpose sorting (used in `Arrays.sort()` for primitives in Java).

> ⚠️ *Worst-case can be mitigated with randomized pivot or median-of-three.*



## 📁 Project Structure

```
sorting-2/
├── MergeSort/
│   └── MergeSort.java
├── RecursiveBubbleSort/
│   └── RecursiveBubbleSort.java
├── RecursiveInsertionSort/
│   └── RecursiveInsertionSort.java
└── QuickSort/
    └── QuickSort.java
```

Each class is self-contained with a `main()` method for quick testing.



## ▶️ How to Run

Ensure **Java 8+** is installed.

### Example: Merge Sort
```bash
cd MergeSort
javac MergeSort.java
java MergeSort
```

Repeat for other algorithms by navigating to their respective directories.



## 🧪 Sample Output

### Merge Sort
```
Merge Sort
-----------------------------------------
Before sorting : [3, 2, 8, 5, 1, 4, 23]
After sorting : [1, 2, 3, 4, 5, 8, 23]
```

### Recursive Bubble Sort
```
Recursive Bubble Sort
-----------------------------------------
Before sorting : [3, 2, 8, 5, 1, 4, 23]
After sorting : [1, 2, 3, 4, 5, 8, 23]
```

### Recursive Insertion Sort
```
Recursive Insertion Sort
-----------------------------------------
Before sorting : [3, 2, 8, 5, 1, 4, 23]
After sorting : [1, 2, 3, 4, 5, 8, 23]
```

### Quick Sort
```
Quick Sort
-----------------------------------------
Before sorting : [3, 2, 8, 5, 1, 4, 23]
After sorting : [1, 2, 3, 4, 5, 8, 23]
```

All algorithms produce the same correctly sorted result.



## 💡 Key Takeaways

| Algorithm | Best For | Stability | Space |
|---------|--------|----------|-------|
| **Merge Sort** | Guaranteed O(N log N), stable sort | ✅ Stable | O(N) |
| **Quick Sort** | General-purpose, in-place | ❌ Not stable | O(log N) avg |
| **Insertion Sort** | Small/nearly-sorted arrays | ✅ Stable | O(N) (recursion) |
| **Bubble Sort** | Learning recursion | ✅ Stable | O(N) (recursion) |

> 🚀 **Real-world note**: Java’s `Arrays.sort()` uses:
> - **Dual-Pivot Quick Sort** for primitives (fast, not stable)
> - **Timsort** (hybrid of Merge + Insertion) for objects (stable, adaptive)



## 🛠️ Possible Enhancements

- Add **randomized pivot** in Quick Sort to avoid worst-case.
- Replace `ArrayList` in Merge Sort with **primitive temp arrays** for speed.
- Add **input validation** and support for custom comparators.
- Include **performance benchmarks** (time vs. array size).


