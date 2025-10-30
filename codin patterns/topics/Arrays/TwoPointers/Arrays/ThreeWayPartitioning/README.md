# 🎨 Three-Way Partition: Core Concept

The **Three-Way Partition** is a clever strategy for **reorganizing an array into three distinct groups in a single pass**. It’s especially useful when every element in the array belongs to **one of three categories**—for example, "small," "medium," and "large" (or commonly: `0`, `1`, and `2`).

Think of it like sorting a row of colored balls—red, white, and blue—so that all reds come first, then whites, then blues, **without using extra space** and **without making multiple passes**.


## 🧩 The Big Idea: Divide and Conquer with Boundaries

Instead of sorting the whole array, we **maintain three dynamic boundaries** that split the array into four zones as we scan:

1. **Left zone**: All elements are of **type A** (e.g., `0` or "red") — ✅ *already sorted*  
2. **Middle zone**: All elements are of **type B** (e.g., `1` or "white") — ✅ *already sorted*  
3. **Right zone**: All elements are of **type C** (e.g., `2` or "blue") — ✅ *already sorted*  
4. **Middle-unprocessed zone**: Elements we **haven’t looked at yet**

We use **three pointers** to track the edges of these zones:
- **`low`**: just after the last "type A" element  
- **`mid`**: the current element we’re examining  
- **`high`**: just before the first "type C" element

Initially:
- The "type A" and "type C" zones are empty.
- The entire array is unprocessed (`mid` starts at the beginning, `high` at the end).


## 🔍 How It Works: One Element at a Time

We move through the array using the `mid` pointer. For each element, we ask: **Which group does it belong to?**

### Case 1: It belongs in the **left group** (type A)
- Swap it to the beginning of the unprocessed section (just after the current "type A" zone).
- Expand the "type A" zone by moving `low` forward.
- Since the swapped-in element has already been checked (or is from the left), we can safely move `mid` forward too.

### Case 2: It belongs in the **middle group** (type B)
- It’s already where it should be—right between the A and C zones.
- Just leave it there and move `mid` forward.

### Case 3: It belongs in the **right group** (type C)
- Swap it to the end of the unprocessed section (just before the current "type C" zone).
- Shrink the unprocessed zone by moving `high` backward.
- **Crucially**: we **do not move `mid` forward**, because the element we just swapped in from the right **hasn’t been examined yet**—it could be any type!

We repeat this until the unprocessed zone disappears (`mid` passes `high`).



## 🎯 Why It’s Efficient

- **Single pass**: Every element is looked at **at most once**.
- **In-place**: No extra arrays or memory needed—just swaps.
- **Optimal**: Runs in **O(n) time** and **O(1) space**.



## 🌈 Real-World Analogy: Sorting Socks

Imagine you’re holding a mixed pile of **black, gray, and white socks** in a line. You want them grouped: black → gray → white.

You start at the left, and with three mental markers:
- “End of black socks”
- “Current sock I’m holding”
- “Start of white socks”

As you go:
- If it’s **black**, you tuck it into the black section and step forward.
- If it’s **gray**, you leave it in the middle and step forward.
- If it’s **white**, you swap it with the sock at the white boundary—but now you have a *new* sock in your hand, so you **check it again** before stepping.

In one sweep, everything ends up sorted!



## 💡 Key Insight

> **The algorithm doesn’t sort by comparing elements—it sorts by *knowing* which of three buckets each element belongs to, and placing it directly into the right zone using smart swapping and boundary management.**

This makes it **faster and simpler** than general sorting when you only have three categories.
