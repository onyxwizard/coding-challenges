# ⚖️ **2. Core Principles**  
*The Unbreakable Contracts That Make Java Collections Work*

> 🔑 **Core Insight**:  
> The Java Collections Framework doesn’t *enforce* correctness — it *assumes* it.  
> Its power comes from **shared contracts** (`equals`, `hashCode`, `compareTo`) and **clear safety semantics** (fail-fast vs fail-safe).  
> Break these, and collections *appear* to work — until they silently corrupt data, leak memory, or explode in production.


## 🧩 **2.1 Fail-Fast vs Fail-Safe Iterators — The Safety Spectrum**

### 🔍 **The Problem**
When a collection is modified *during iteration*, should the iterator:
- **Crash immediately** (expose the bug)? → **Fail-Fast**
- **Keep going** (avoid disruption)? → **Fail-Safe**

Java provides **both**, depending on use case.

### 📊 **Side-by-Side Comparison**

| Property | Fail-Fast | Fail-Safe |
|---------|-----------|-----------|
| **Mechanism** | `modCount` counter + `expectedModCount` check | Snapshot (e.g., copy-on-write) or weakly consistent view |
| **Throws `ConcurrentModificationException`?** | ✅ Yes — immediately on `next()`/`remove()` | ❌ No |
| **Data Consistency** | Strong: reflects state *at start of iteration* or fails | Weak: may reflect *partial updates* (stale or mixed state) |
| **Performance** | Zero overhead (just an `int` check) | Higher memory/CPU (copying or atomic reads) |
| **Use Case** | Single-threaded code — *find bugs early* | High-read concurrent systems — *prioritize availability* |

### 🧪 **Which Collections Use Which?**

| Collection | Iterator Type | Why? |
|-----------|----------------|------|
| `ArrayList`, `HashMap`, `HashSet`, `TreeMap`, `LinkedList` | **Fail-Fast** | Default for correctness in sequential code |
| `CopyOnWriteArrayList`, `CopyOnWriteArraySet` | **Fail-Safe** | Full copy on write → iteration sees *consistent snapshot* |
| `ConcurrentHashMap.keySet()`, `.values()`, `.entrySet()` | **Fail-Safe (weakly consistent)** | No locks; reflects state *at some point* during iteration |
| `ConcurrentLinkedQueue`, `ConcurrentLinkedDeque` | **Fail-Safe** | Lock-free design |

> 🚨 **Critical Interview Insight**:  
> *“Fail-fast is a debugging aid — not a concurrency control mechanism. It does **not** make collections thread-safe.”*

---

## 🧨 **2.2 `ConcurrentModificationException` & Structural Modifications**

### 🔍 **What Triggers CME?**
A **structural modification** — any operation that changes the *size* or *internal structure* of the collection.

| Operation | Structural? | Why? |
|----------|-------------|------|
| `list.add(e)`, `list.remove(i)` | ✅ Yes | Changes `size`, shifts elements |
| `map.put(k, v)` (**new key**) | ✅ Yes | Adds new mapping |
| `map.put(k, v)` (**existing key**) | ❌ No | Replaces value — structure unchanged |
| `list.set(i, e)` | ❌ No | Overwrites — no size change |
| `iterator.remove()` | ❌ No | *Designed* for safe removal during iteration |

### 💥 **Classic CME Example**
```java
List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
for (String s : list) {         // uses iterator internally
    if (s.equals("b")) {
        list.remove(s);          // 💥 ConcurrentModificationException!
    }
}
```

### ✅ **Safe Fixes**
| Approach | Code | When to Use |
|---------|------|-------------|
| **`Iterator.remove()`** | `Iterator<String> it = list.iterator(); while (it.hasNext()) if (it.next().equals("b")) it.remove();` | Single-threaded, in-place filtering |
| **`removeIf()` (Java 8+)** | `list.removeIf(s -> s.equals("b"));` | Clean, expressive, internally safe |
| **Copy + Filter** | `list = list.stream().filter(s -> !s.equals("b")).collect(Collectors.toList());` | Functional style, immutable result |

> ✅ **Pro Tip**:  
> Prefer `removeIf()` — it’s atomic, readable, and avoids CME *by design*.

---

## 🔐 **2.3 The Object Contract Triad: `equals()`, `hashCode()`, `compareTo()`**

These methods are **not optional** — they are the *foundation* of `Set`, `Map`, and sorted collections.

### 📜 **The `equals()` & `hashCode()` Contract**  
*(For `HashSet`, `HashMap`, `LinkedHashMap`)*

> 📜 **Joshua Bloch’s Rule (Effective Java, Item 11)**:  
> **If two objects are equal according to `equals()`, they *must* have the same `hashCode()`.**  
> *(The reverse is not required — hash collisions are allowed.)*

#### ✅ **Correct Implementation Pattern**
```java
public final class Person {
    private final String name;
    private final int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person p)) return false;
        return id == p.id && Objects.equals(name, p.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id); // uses 31 * name.hashCode() + id
    }
}
```

#### 🚫 **Common Violations**
| Mistake | Consequence |
|--------|-------------|
| `equals()` uses `name`, `hashCode()` uses `id` | Objects equal but different hash → `HashMap` can’t find them |
| Mutable fields in `hashCode()` | Insert into `HashMap`, then mutate → key “disappears” |
| `equals()` not symmetric/transitive | `HashSet` behaves unpredictably (e.g., `contains()` fails) |

> 🧪 **Real Bug Example**:  
> ```java
> Person p = new Person("Alice", 1);
> Map<Person, String> map = new HashMap<>();
> map.put(p, "data");
> p.setName("Alicia"); // mutates field used in hashCode()
> map.get(p); // returns null — hash bucket changed!
> ```

---

### 📈 **The `compareTo()` / `Comparator` Contract**  
*(For `TreeSet`, `TreeMap`)*

> 📜 **Critical Rule**:  
> **`TreeSet` and `TreeMap` use `compareTo()` (or `Comparator`) for *all* operations — including `contains()` and `add()`.**  
> → If `a.compareTo(b) == 0`, then `a` and `b` are considered *equal* — **even if `!a.equals(b)`**.

#### ✅ **Golden Rule for Sorted Collections**
> `a.compareTo(b) == 0` **must** imply `a.equals(b)`  
> *(Otherwise, you get silent data loss.)*

#### 🧪 **Silent Data Loss Demo**
```java
record BrokenItem(int id, int value) implements Comparable<BrokenItem> {
    public int compareTo(BrokenItem o) {
        return Integer.compare(this.value, o.value); // ignores id!
    }
    // equals() uses both id and value (default record behavior)
}

TreeSet<BrokenItem> set = new TreeSet<>();
set.add(new BrokenItem(1, 10));
set.add(new BrokenItem(2, 10)); // compareTo == 0 → rejected as duplicate!
System.out.println(set.size()); // 1 — but !item1.equals(item2)!
```

> ✅ **Fix**: Either:
> - Make `compareTo()` consistent with `equals()`, or  
> - Accept that `TreeSet` enforces *logical equivalence* — and design accordingly.

---

## 🧠 **Decision Flowchart: Which Safety Model & Contract Applies?**

```plaintext
Is the collection a Set/Map? ──Yes──→ You MUST implement equals()/hashCode() correctly.
            │
           No
            │
Is it a TreeSet/TreeMap? ──Yes──→ You MUST ensure compareTo() ≡ equals().
            │
           No
            │
Are you iterating and modifying? ──Yes──→ Use iterator.remove() or removeIf().
            │
           No
            │
Is it used across threads? ──Yes──→ Choose fail-safe (ConcurrentHashMap, COW) or external sync.
```

---

## 🛠️ **Core Principles — Mastery Tracker**

| # | Concept | Self-Test Question | Status ✅ | Notes 📝 |
|---|---------|--------------------|:--------:|----------|
| 1 | Fail-Fast | What triggers CME in `ArrayList`? Is `list.set(i, e)` structural? | ☐ | Only size-changing ops |
| 2 | Fail-Safe | Why doesn’t `ConcurrentHashMap.keySet().iterator()` throw CME? | ☐ | Weakly consistent view |
| 3 | `hashCode()` | If `a.equals(b)`, must `a.hashCode() == b.hashCode()`? | ☐ | **Yes — or `HashMap` breaks** |
| 4 | Mutability Risk | Can a mutable key cause `map.get(k)` to fail after insertion? | ☐ | Yes — if fields in `hashCode()` change |
| 5 | `TreeSet` Equality | Can two `!a.equals(b)` objects be considered equal in `TreeSet`? | ☐ | Yes — if `a.compareTo(b) == 0` |
| 6 | Safe Removal | How do you safely remove items during iteration? | ☐ | `Iterator.remove()`, `removeIf()` |
| 7 | `ConcurrentModificationException` | Is CME a sign of thread-unsafe code? | ☐ | Not necessarily — can happen in single-threaded loops |

✅ **Mastery Threshold**:  
You can explain *why* CME exists, *how* to avoid it, and *when* it’s the right behavior — and you never assume `TreeSet` respects `equals()`.