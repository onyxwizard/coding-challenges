# Sorting 1: Implementations of Classic Sorting Algorithms

This project contains clean, well-structured, and partially optimized implementations of three fundamental **O(N²)** comparison-based sorting algorithms:

- **Bubble Sort**
- **Insertion Sort**
- **Selection Sort**

These algorithms are essential for understanding the basics of sorting and are commonly asked in coding interviews and academic coursework.


## 📌 Algorithms Implemented

### 1. **Bubble Sort**
- **Approach**: Repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order.
- **Optimization**: Includes an early termination flag — if no swaps occur in a full pass, the array is already sorted, and the algorithm exits early.
- **Time Complexity**:
    - Best: **O(N)** (when array is already sorted)
    - Average/Worst: **O(N²)**
- **Space Complexity**: **O(1)** (in-place)

✅ **Your implementation is well-optimized** for Bubble Sort.



### 2. **Insertion Sort**
- **Approach**: Builds the final sorted array one item at a time by inserting each element into its correct position among the previously sorted elements.
- **Current Implementation**: Uses repeated swapping to move elements.
- **Time Complexity**: **O(N²)** in worst/average cases; **O(N)** for nearly sorted data.
- **Space Complexity**: **O(1)**

⚠️ **Note**: While correct, the current version can be made more efficient by **shifting elements instead of swapping** (see suggestions in code comments).



### 3. **Selection Sort**
- **Approach**: Finds the minimum element in the unsorted portion and swaps it with the first unsorted element.
- **Key Trait**: Performs **exactly (N−1) swaps**, minimizing write operations.
- **Time Complexity**: Always **O(N²)** (even for sorted input)
- **Space Complexity**: **O(1)**

⚠️ **Note**: Implementation is correct but includes a minor redundancy (tracking both `min` value and index). Can be simplified.



## 📁 Project Structure

```
sorting-1/
├── BubbleSort/
│   └── BubbleSort.java
├── InsertionSort/
│   └── InsertionSort.java
└── SelectionSort.java
```

Each file is self-contained with a `main` method for quick testing.



## ▶️ How to Run

Make sure you have **Java 8+** installed.

### Bubble Sort
```bash
cd BubbleSort
javac BubbleSort.java
java BubbleSort
```

### Insertion Sort
```bash
cd InsertionSort
javac InsertionSort.java
java InsertionSort
```

### Selection Sort
```bash
javac SelectionSort.java
java SelectionSort
```



## 🧪 Sample Output

**Input**: `[13, 46, 24, 52, 20, 9]`  
**Output**: `[9, 13, 20, 24, 46, 52]`

All algorithms produce the same correctly sorted result.



## 💡 Key Takeaways

- These algorithms are **not suitable for large datasets** due to O(N²) complexity.
- However, **Insertion Sort** is often used as a subroutine in advanced algorithms (e.g., **Quicksort** for small subarrays) because of its efficiency on small or nearly sorted data.
- **Bubble Sort** is mainly of educational value.
- **Selection Sort** is useful when **write operations are expensive** (minimizes swaps).




> ✨ **Note**: While modern applications use highly optimized sorts like **Timsort** (`Arrays.sort()` in Java), understanding these foundational algorithms builds strong problem-solving intuition.