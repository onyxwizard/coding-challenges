# 📘 **Master TOC: DSA & Advanced Programming Patterns**  
### 🧠 *Your Ultimate Roadmap to Ace Coding & System Design Interviews*

---

## **1️⃣ Arrays & Strings** `🔤`
### 🔁 **1.1 Two Pointers**  
- **Types**: Opposite pointers ↔️, Fast-slow 🐢🐇, Sliding window (see below)  
- **When to use**: Sorted arrays 📈, palindrome checks ↔️, pair sums ➕  
- **Problems**:  
  - [167. Two Sum II – Input Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)  
  - [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)  
  - [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/)

### 🪟 **1.2 Sliding Window**  
- **Fixed-size**: Max sum of subarray of size k ➕  
- **Variable-size**: Min window substring 🪟, longest substring without repeating chars 🚫🔁  
- **Problems**:  
  - [209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)  
  - [438. Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/)  
  - [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)

### ➕ **1.3 Prefix Sum / Cumulative Sum**  
- **Use**: Range sum queries 📊, subarray sum equals k ✅  
- **Problems**:  
  - [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)  
  - [303. Range Sum Query – Immutable](https://leetcode.com/problems/range-sum-query-immutable/)

### 📈 **1.4 Kadane’s Algorithm (Max Subarray)**  
- **Problems**:  
  - [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)  
  - [152. Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)

### 🔁 **1.5 Cyclic Sort**  
- **For**: Arrays with numbers in range [1, n] 🔢  
- **Problems**:  
  - [448. Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/)  
  - [442. Find All Duplicates in an Array](https://leetcode.com/problems/find-all-duplicates-in-an-array/)

### 🧪 **1.6 In-Place Modification**  
- **Tricks**: Use sign/negative marking ➖, index-as-hash 🗃️  
- **Problems**:  
  - [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/)  
  - [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive/)

> 💡 **Bonus**: Add **1.7 Dutch National Flag** for 3-way partitioning (e.g., [75. Sort Colors](https://leetcode.com/problems/sort-colors/)) 🎨

---

## **2️⃣ Linked Lists** `🔗`
### 🐢🐇 **2.1 Fast & Slow Pointers (Floyd’s Cycle)**  
- **Use**: Detect cycle 🔄, find middle 📍, palindrome check ↔️  
- **Problems**:  
  - [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)  
  - [234. Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/)

### 🔄 **2.2 Reversal Techniques**  
- Reverse entire list, reverse sublist, reverse in groups  
- **Problems**:  
  - [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/)  
  - [92. Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii/)  
  - [25. Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/)

### 🧵 **2.3 Dummy Head / Sentinel Node**  
- Avoid edge cases with head manipulation 👑  
- **Problems**:  
  - [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)  
  - [86. Partition List](https://leetcode.com/problems/partition-list/)

---

## **3️⃣ Trees** `🌳`
### 🧘 **3.1 DFS (Pre/In/Post-order)**  
- Recursive & iterative (using stack)  
- **Problems**:  
  - [144. Binary Tree Preorder Traversal](https://leetcode.com/problems/binary-tree-preorder-traversal/)  
  - [94. Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/)

### 🚶 **3.2 BFS (Level Order)**  
- Use queue; track levels with size or null markers 📦  
- **Problems**:  
  - [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)  
  - [103. Zigzag Level Order](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/)

### 🔍 **3.3 BST Properties**  
- Inorder = sorted 📋; validate, search, insert, delete  
- **Problems**:  
  - [98. Validate BST](https://leetcode.com/problems/validate-binary-search-tree/)  
  - [230. Kth Smallest Element in BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/)

### 🧩 **3.4 Tree Construction from Traversals**  
- From preorder+inorder, etc.  
- **Problem**: [105. Construct Binary Tree from Preorder and Inorder](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

> 💡 **Bonus**: Add **3.5 Morris Traversal** for O(1) space inorder traversal 🪄

---

## **4️⃣ Graphs** `🕸️`
### 🧭 **4.1 BFS vs DFS**  
- **BFS**: Shortest path (unweighted) 🛤️, level-based  
- **DFS**: Path existence 🧱, topological sort 📅, cycles 🔄  
- **Problems**:  
  - [200. Number of Islands](https://leetcode.com/problems/number-of-islands/)  
  - [752. Open the Lock](https://leetcode.com/problems/open-the-lock/) (BFS shortest path)

### 📅 **4.2 Topological Sort**  
- Kahn’s Algorithm (BFS) or DFS-based  
- **Problems**:  
  - [207. Course Schedule](https://leetcode.com/problems/course-schedule/)  
  - [210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii/)

### 🤝 **4.3 Union-Find (Disjoint Set Union)**  
- Detect cycles in undirected graphs, dynamic connectivity  
- **Problems**:  
  - [547. Number of Provinces](https://leetcode.com/problems/number-of-provinces/)  
  - [323. Number of Connected Components](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/)

### 🧩 **4.4 Backtracking on Graphs**  
- **Problems**:  
  - [79. Word Search](https://leetcode.com/problems/word-search/)  
  - [212. Word Search II](https://leetcode.com/problems/word-search-ii/) (with Trie)

---

## **5️⃣ Heaps & Priority Queues** `📊`
### ⬆️⬇️ **5.1 Min-Heap / Max-Heap**  
- Kth largest/smallest 🥇, merge k sorted lists 📚  
- **Problems**:  
  - [215. Kth Largest Element](https://leetcode.com/problems/kth-largest-element-in-an-array/)  
  - [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)

### 🧮 **5.2 Custom Comparators**  
- Sort by frequency 🔁, deadline ⏰, etc.  
- **Problem**: [621. Task Scheduler](https://leetcode.com/problems/task-scheduler/)

---

## **6️⃣ Hash Tables & Sets** `🔑`
### 📊 **6.1 Frequency Counting**  
- Anagrams 🔤, duplicates 🔄, longest harmonious subsequence  
- **Problems**:  
  - [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/)  
  - [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

### 🧠 **6.2 LRU / MRU Cache**  
- Use **HashMap + Doubly Linked List**  
- **Problem**: [146. LRU Cache](https://leetcode.com/problems/lru-cache/)

---

## **7️⃣ Dynamic Programming (DP)** `🧩`
### 📏 **7.1 1D DP**  
- Climbing stairs 🪜, house robber 🏠, jump game 🦘  
- **Problems**:  
  - [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)  
  - [198. House Robber](https://leetcode.com/problems/house-robber/)

### 🧱 **7.2 2D DP**  
- Grid paths 🗺️, edit distance ✏️, longest common subsequence  
- **Problems**:  
  - [62. Unique Paths](https://leetcode.com/problems/unique-paths/)  
  - [72. Edit Distance](https://leetcode.com/problems/edit-distance/)

### 📈 **7.3 State Machine DP**  
- Stock buy/sell with cooldown ❄️, transaction limits 💰  
- **Problems**:  
  - [309. Best Time to Buy and Sell Stock with Cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)  
  - [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)

### 🔐 **7.4 DP + Bitmasking (Advanced)**  
- TSP 🧳, subset cover 🧩  
- **Problem**: [1125. Smallest Sufficient Team](https://leetcode.com/problems/smallest-sufficient-team/)

---

## **8️⃣ Recursion & Backtracking** `🌀`
### 📦 **8.1 Subsets & Combinations**  
- **Problems**:  
  - [78. Subsets](https://leetcode.com/problems/subsets/)  
  - [39. Combination Sum](https://leetcode.com/problems/combination-sum/)

### 🎭 **8.2 Permutations**  
- With/without duplicates 🔄  
- **Problems**:  
  - [46. Permutations](https://leetcode.com/problems/permutations/)  
  - [47. Permutations II](https://leetcode.com/problems/permutations-ii/)

### ♟️ **8.3 Advanced Backtracking**  
- N-Queens ♛, Sudoku 🧩, Palindrome Partitioning ↔️  
- **Problems**:  
  - [51. N-Queens](https://leetcode.com/problems/n-queens/)  
  - [131. Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/)

---

## **9️⃣ Advanced Techniques** `⚡`
### 🔍 **9.1 Binary Search on Answer**  
- Minimize maximum ⬇️, maximize minimum ⬆️  
- **Problems**:  
  - [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/)  
  - [1011. Capacity To Ship Packages](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/)

### 📉 **9.2 Monotonic Stack/Queue**  
- Next greater element 🔮, sliding window max 🪟  
- **Problems**:  
  - [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)  
  - [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/)

### 🌳 **9.3 Trie (Prefix Tree)**  
- Autocomplete 💬, word search 🔍, IP routing 🌐  
- **Problems**:  
  - [208. Implement Trie](https://leetcode.com/problems/implement-trie-prefix-tree/)  
  - [212. Word Search II](https://leetcode.com/problems/word-search-ii/)

### 🔢 **9.4 Bit Manipulation**  
- XOR tricks 🧠, power set 📦, single number 🕵️  
- **Problems**:  
  - [136. Single Number](https://leetcode.com/problems/single-number/)  
  - [78. Subsets (bitmask version)](https://leetcode.com/problems/subsets/)

> 💡 **Bonus**: Add **9.5 Math & Number Theory** – GCD, LCM, modular arithmetic, prime sieve 🧮

---

## **🔟 Low-Level Design (LLD) Patterns** `🛠️`
- **OOP Principles**: Encapsulation 📦, Abstraction 🎭, Inheritance 🧬, Polymorphism 🦎  
- **SOLID**:  
  - **S**ingle Responsibility  
  - **O**pen-Closed  
  - **L**iskov Substitution  
  - **I**nterface Segregation  
  - **D**ependency Inversion  
- **Design Patterns**:  
  - **Creational**: Singleton 🏢, Factory 🏭, Builder 🧱  
  - **Structural**: Adapter 🔌, Decorator 🎨, Facade 🎭  
  - **Behavioral**: Observer 👀, Strategy 🎯, Command 📜  
- **Classic Systems**:  
  - Parking Lot 🅿️  
  - Elevator System 🛗  
  - Snake & Ladder 🎲  
  - Splitwise 💸  
  - Coffee Machine ☕

---

## **1️⃣1️⃣ High-Level Design (HLD) Patterns** `🌐`
- **Scalability**: Load balancing ⚖️, caching (Redis) 🗃️, CDNs 🌍, DB indexing 📚  
- **Database**: SQL vs NoSQL 🗃️, sharding 🔀, replication 🔄  
- **APIs**: REST 📡, gRPC 📦, rate limiting 🚦  
- **Systems to Design**:  
  - URL Shortener (TinyURL) 🔗  
  - Chat Application (WhatsApp) 💬  
  - Social Media Feed (Twitter) 🐦  
  - Video Streaming (YouTube) 📺

---

> ✅ **How to Use This TOC**:  
> 1️⃣ Pick **1 pattern per day**  
> 2️⃣ Study theory → solve 2–3 problems → revisit in 3 days (**spaced repetition**)  
> 3️⃣ After **6–8 weeks**, you’ll recognize **>90% of interview problems instantly** 🚀

---

Absolutely! Here's a **clean, printable checklist-style table** that maps **Main Topics** to their **Sub-Patterns**, based on your enhanced DSA & Advanced Programming Patterns TOC. You can use this to track your progress as you study each concept.

---

### ✅ **DSA & Advanced Programming Patterns – Master Checklist**

| **Main Topic**               | **Sub-Pattern / Technique**                        | **Done?** |
| ---------------------------- | -------------------------------------------------- | :-------: |
| **Arrays & Strings**         | Two Pointers                                       |    🟢     |
|                              | Sliding Window (Fixed & Variable)                  |    🟢     |
|                              | Prefix / Cumulative Sum                            |    🟡     |
|                              | Kadane’s Algorithm (Max Subarray)                  |     ☐     |
|                              | Cyclic Sort                                        |     ☐     |
|                              | In-Place Modification (Index-as-Hash, Negation)    |     ☐     |
|                              | Dutch National Flag (3-Way Partition) 🎨 *(bonus)* |     ☐     |
| **Linked Lists**             | Fast & Slow Pointers (Cycle Detection)             |     ☐     |
|                              | Reversal Techniques (Full, Sublist, k-Group)       |     ☐     |
|                              | Dummy Head / Sentinel Node                         |     ☐     |
| **Trees**                    | DFS (Pre/In/Post-order – Recursive & Iterative)    |     ☐     |
|                              | BFS (Level Order, Zigzag)                          |     ☐     |
|                              | BST Properties & Validation                        |     ☐     |
|                              | Tree Construction from Traversals                  |     ☐     |
|                              | Morris Traversal (O(1) Space) 🪄 *(bonus)*         |     ☐     |
| **Graphs**                   | BFS vs DFS (Use Cases)                             |     ☐     |
|                              | Topological Sort (Kahn’s / DFS)                    |     ☐     |
|                              | Union-Find (Disjoint Set)                          |     ☐     |
|                              | Backtracking on Graphs                             |     ☐     |
| **Heaps & Priority Queues**  | Min/Max Heap (Kth Element, Merging Lists)          |     ☐     |
|                              | Custom Comparators                                 |     ☐     |
| **Hash Tables & Sets**       | Frequency Counting                                 |     ☐     |
|                              | LRU / MRU Cache (HashMap + DLL)                    |     ☐     |
| **Dynamic Programming**      | 1D DP (Stairs, Robber, Jump Game)                  |     ☐     |
|                              | 2D DP (Grid Paths, Edit Distance, LCS)             |     ☐     |
|                              | State Machine DP (Stock Problems)                  |     ☐     |
|                              | DP + Bitmasking (TSP, Team Selection)              |     ☐     |
| **Recursion & Backtracking** | Subsets & Combinations                             |     ☐     |
|                              | Permutations (With/Without Duplicates)             |     ☐     |
|                              | Advanced Backtracking (N-Queens, Sudoku, etc.)     |     ☐     |
| **Advanced Techniques**      | Binary Search on Answer                            |     ☐     |
|                              | Monotonic Stack / Queue                            |     ☐     |
|                              | Trie (Prefix Tree)                                 |     ☐     |
|                              | Bit Manipulation                                   |     ☐     |
|                              | Math & Number Theory 🧮 *(bonus)*                  |     ☐     |
| **Low-Level Design (LLD)**   | OOP Principles & SOLID                             |     ☐     |
|                              | Creational Patterns (Singleton, Factory, Builder)  |     ☐     |
|                              | Structural Patterns (Adapter, Decorator, Facade)   |     ☐     |
|                              | Behavioral Patterns (Observer, Strategy, Command)  |     ☐     |
|                              | Classic System Designs (Parking Lot, Elevator…)    |     ☐     |
| **High-Level Design (HLD)**  | Scalability Concepts (Caching, LB, CDNs)           |     ☐     |
|                              | Database Design (SQL/NoSQL, Sharding, Replication) |     ☐     |
|                              | API Design (REST, gRPC, Rate Limiting)             |     ☐     |
|                              | Real-World System Designs (TinyURL, WhatsApp…)     |     ☐     |

---

### 📝 **How to Use This Table**
- Check ✅ each box **after you’ve studied the pattern + solved 2–3 problems**.
- Revisit unchecked items weekly.
- Use color coding (e.g., 🟡 In Progress, 🟢 Mastered) for extra clarity!
