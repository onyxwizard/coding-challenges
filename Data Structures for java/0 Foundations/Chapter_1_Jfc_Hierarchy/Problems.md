## 🧭 **JCF Hierarchy Mastery Roadmap**

| Tier | Goal | Problems | Time Estimate |
|------|------|----------|---------------|
| **Tier 1: Recognition & Declaration** | Identify interfaces vs implementations; declare variables correctly | 5 | 30–45 min |
| **Tier 2: Behavior & Contract Testing** | Predict runtime behavior, null handling, ordering, duplicates | 7 | 60–90 min |
| **Tier 3: Selection & Justification** | Choose the *optimal* type for a spec; defend your choice | 6 | 60 min |
| **Tier 4: API Design & Evolution** | Design robust, future-proof APIs using JCF hierarchy principles | 4 | 60–90 min |
| **✅ Total** | **Full hierarchy fluency** | **22 problems** | **~4 hours** |

---

## 📌 **Tier 1: Recognition & Declaration**  
*“I can name the interface, not just the class.”*

| # | Problem | Task | Expected Output / Behavior | Learning Objective |
|---|---------|------|----------------------------|--------------------|
| 1 | **Hierarchy Sketch** | Draw the full JCF hierarchy (from `Iterable` down) *from memory*, including `Map`. Label which are interfaces, which are concrete classes. | Correct tree with `Collection ↦ List/Set/Queue`, `Map` separate. | Structural mental model |
| 2 | **Declaration Drill** | For each line, rewrite using the *most abstract possible interface*:<br>`ArrayList<String> a = new ArrayList<>();`<br>`HashSet<Integer> b = new HashSet<>();`<br>`HashMap<String, List<Integer>> c = new HashMap<>();`<br>`Stack<Integer> d = new Stack<>();` | `List<String> a = …`<br>`Set<Integer> b = …`<br>`Map<String, List<Integer>> c = …`<br>`Deque<Integer> d = new ArrayDeque<>();` | Prefer interfaces; avoid legacy |
| 3 | **Which Is Not a Collection?** | Which of these *do not* extend `Collection<E>`?<br>`List`, `Set`, `Queue`, `Deque`, `Map`, `SortedSet`, `NavigableMap` | `Map`, `NavigableMap` | Understand `Map` is parallel branch |
| 4 | **Method Signature Fix** | A method returns `ArrayList<Integer>`. Refactor to return the most appropriate interface. Justify. | `List<Integer>` — preserves mutability intent, hides impl | API design hygiene |
| 5 | **Legacy Replacement** | Replace all legacy types in this code:<br>`Vector v = new Vector(); Stack s = new Stack(); Hashtable h = new Hashtable();` | `List v = new ArrayList(); Deque s = new ArrayDeque(); Map h = new HashMap();` | Modernization awareness |

✅ **Tier 1 Mastery Check**:  
> _“I instinctively type `List<X> list = new ArrayList<>();`, never `ArrayList<X> list = …`.”_

---

## 🔍 **Tier 2: Behavior & Contract Testing**  
*“I can predict what the code does — and why.”*

| # | Problem | Code Snippet | Question | Key Insight |
|---|---------|--------------|----------|-------------|
| 6 | **Null in Sets** | `Set<String> s1 = new HashSet<>(); s1.add(null); s1.add(null);`<br>`Set<String> s2 = new TreeSet<>(); s2.add(null);` | What is `s1.size()`? What happens with `s2`? | `HashSet`: 1 null allowed; `TreeSet`: `NullPointerException` |
| 7 | **Order in Lists vs Sets** | Insert `"c", "a", "b"` into:<br>`List`, `HashSet`, `LinkedHashSet`, `TreeSet` | What does `toString()` print for each? | `List`: insertion order; `LinkedHashSet`: insertion; `TreeSet`: sorted; `HashSet`: undefined |
| 8 | **Duplicates Allowed?** | After `add("x")` twice, what is size of:<br>`ArrayList`, `LinkedList`, `HashSet`, `LinkedHashSet`, `TreeSet` | 2, 2, 1, 1, 1 | `List` allows dups; `Set` does not |
| 9 | **Map Views Mutability** | `Map<String, Integer> m = new HashMap<>(); m.put("a", 1); Set<String> keys = m.keySet(); keys.remove("a");`<br>What is `m.size()`? | `0` — `keySet()` is a live view | Views are *backed* by map |
|10| **`Deque` as Stack** | `Deque<Integer> d = new ArrayDeque<>(); d.push(1); d.push(2); System.out.println(d.pop());` | `2` (LIFO) | `push`/`pop` = `addFirst`/`removeFirst` |
|11| **`entrySet()` vs `keySet()` Loop** | Compare performance of:<br>`for (K k : map.keySet()) V v = map.get(k);`<br>`for (Map.Entry<K,V> e : map.entrySet()) { K k = e.getKey(); V v = e.getValue(); }` | Second is O(n); first is O(n²) for `LinkedHashMap` (O(n) per `get`) | `entrySet()` avoids double lookup |
|12| **`EnumSet` Magic** | `enum Color { RED, GREEN, BLUE }`<br>`Set<Color> s = EnumSet.of(RED, BLUE); s.add(GREEN);`<br>What is `s`? Is it sorted? | `[RED, GREEN, BLUE]` — natural order (ordinal) | `EnumSet` is sorted, compact, fast |

✅ **Tier 2 Mastery Check**:  
> _“I can explain why `TreeSet` throws on `null`, why `entrySet()` is faster, and what `keySet().remove()` does — without running the code.”_

---

## 🎯 **Tier 3: Selection & Justification**  
*“Given a spec, I pick the right collection — and explain why.”*

| # | Scenario | Candidate Types | Optimal Choice | Justification |
|---|----------|-----------------|----------------|---------------|
|13| Store unique user IDs (strings), order doesn’t matter. High throughput. | `ArrayList`, `HashSet`, `TreeSet`, `LinkedHashSet` | `HashSet` | O(1) ops, no ordering needed, low overhead |
|14| Maintain insertion order of visited URLs (deduped). | `List`, `HashSet`, `LinkedHashSet` | `LinkedHashSet` | Uniqueness + insertion order in O(1) |
|15| Implement LRU cache (evict least recently *accessed*). | `HashMap`, `LinkedHashMap`, `TreeMap` | `LinkedHashMap` (access-order + `removeEldestEntry`) | Built-in LRU support |
|16| Store enum flags (e.g., `Permission.READ`, `WRITE`). Fast union/intersect. | `HashSet`, `ArrayList`, `EnumSet` | `EnumSet` | Bit-vector ops: `O(1)`, minimal memory |
|17| High-concurrency counter: `increment(key)`, `get(key)`. | `HashMap`, `synchronizedMap`, `ConcurrentHashMap` | `ConcurrentHashMap` | Lock-free reads, atomic `compute()` |
|18| Stack for DFS in tree traversal. | `Stack`, `LinkedList`, `ArrayDeque` | `ArrayDeque` | Fastest, no legacy baggage, `Deque` interface |

✅ **Tier 3 Mastery Check**:  
> _“I don’t say ‘I use `HashMap`’ — I say ‘I use `ConcurrentHashMap` because the workload is read-heavy with occasional atomic updates, and I need linearizability without full synchronization.’”_

---

## 🛠️ **Tier 4: API Design & Evolution**  
*“I design APIs that are safe, flexible, and future-proof.”*

| # | Task | Requirement | Solution Sketch | Why It’s Pro-Level |
|---|------|-------------|------------------|-------------------|
|19| **Public Getter for Internal List** | `class User { private List<Role> roles; }`<br>Expose roles without allowing mutation. | `public List<Role> getRoles() { return List.copyOf(roles); }` | Deep immutability; no defensive copy boilerplate |
|20| **Flexible Input API** | Method accepts a collection of configs. Should work with `List`, `Set`, `Stream.toList()`. | `void processConfigs(Collection<? extends Config> configs)` | Uses `Collection` (not `List`), PECS-compliant |
|21| **Thread-Safe Builder** | Design a builder that accumulates items, then builds an immutable result. | Use `ArrayList` internally → `List.copyOf()` on `build()` | Encapsulation + immutability |
|22| **Backward-Compatible Return Type** | Old API returned `ArrayList`; new version should allow future change to `ImmutableList`. | Change return type to `List<T>`; keep impl as `ArrayList` or `List.of()` | Interface-based return enables evolution |

✅ **Tier 4 Mastery Check**:  
> _“My public APIs never expose implementation types, never leak mutable internals, and use generics correctly — and I can justify every choice.”_

---

## 📊 **Self-Assessment Rubric**

After each tier, rate yourself:

| Criteria | Novice | Proficient | **Master (Goal)** |
|---------|--------|------------|-------------------|
| **Declaration** | Uses concrete types (`ArrayList`) | Uses interfaces (`List`) | Prefers `Deque` over `Stack`, `Collection` over `List` when possible |
| **Behavior Prediction** | Runs code to check | Predicts basics (null, dups) | Predicts view mutability, ordering, concurrency edge cases |
| **Selection** | “I always use `HashMap`” | Chooses based on ordering/uniqueness | Chooses based on *performance profile*, *thread model*, *evolution needs* |
| **API Design** | Returns `ArrayList` | Returns `List` | Returns immutable views, uses PECS, avoids leaky abstractions |

🎯 **Master Threshold**:  
> ✅ All Tier 1–3 problems solved *without IDE*  
> ✅ Can whiteboard Tier 4 designs with justification  
> ✅ Can explain *why* `Map` isn’t a `Collection` in <30 seconds

---

## 🚀 Bonus: “Interview Lightning Round” (5 Questions)

Test yourself — answer aloud in <20 sec each:

1. **Q**: Why doesn’t `Map` extend `Collection`?  
   **A**: Because it models *key-value associations*, not *elements* — and `get(k)` is a core capability not expressible via iteration.

2. **Q**: When would you use `LinkedHashSet` over `HashSet`?  
   **A**: When you need uniqueness *and* predictable iteration order (insertion or access).

3. **Q**: Is `ArrayDeque` thread-safe?  
   **A**: No — use `ConcurrentLinkedDeque` or external synchronization.

4. **Q**: What’s wrong with `return new ArrayList<>(internalList);`?  
   **A**: It’s mutable — caller can modify the returned list and affect internal state. Prefer `List.copyOf()`.

5. **Q**: Which collection guarantees `O(1)` `add`, `contains`, and iteration *in order of insertion*?  
   **A**: `LinkedHashSet`.