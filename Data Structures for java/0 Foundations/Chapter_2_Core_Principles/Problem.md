# 🧪 **Core Principles Mastery Problem Tracker**  
*“I don’t just avoid bugs — I design systems where they’re impossible.”*

| Tier | Focus | Problems | Goal |
|------|-------|----------|------|
| **Tier 1: CME & Iterator Safety** | Predict, reproduce, and fix `ConcurrentModificationException` | 6 | Debug like a JVM engineer |
| **Tier 2: `equals()` & `hashCode()`** | Implement, break, and fix object contracts | 6 | Prevent silent `HashMap` corruption |
| **Tier 3: Sorted Collection Pitfalls** | Diagnose and resolve `TreeSet`/`TreeMap` anomalies | 4 | Avoid silent data loss |
| **Tier 4: Production-Grade Defenses** | Build thread-safe, contract-compliant APIs | 4 | Architect resilience |


## 🔥 **Tier 1: CME & Iterator Safety**

| # | Problem | Task | Expected Behavior / Fix | Learning Objective |
|---|---------|------|--------------------------|--------------------|
| 1 | **CME Trigger Identification** | Which operations trigger CME in `ArrayList`?<br>`list.add("x")`<br>`list.set(0, "y")`<br>`list.remove(0)`<br>`iterator.remove()` | ✅ `add`, `remove`<br>❌ `set`, `iterator.remove()` | Know what counts as *structural modification* |
| 2 | **Classic CME Reproduction** | Write code that throws CME using enhanced `for` loop + `list.remove()`. | ```for (String s : list) if (s.equals("x")) list.remove(s); // CME``` | Recognize anti-pattern instantly |
| 3 | **Safe Removal (3 Ways)** | Remove all `"x"` from `List<String>` *without* CME. Provide 3 solutions. | 1. `iterator.remove()`<br>2. `list.removeIf("x"::equals)`<br>3. Stream filter + reassign | Know idiomatic fixes |
| 4 | **Fail-Safe Verification** | Does this throw CME?<br>`CopyOnWriteArrayList<String> list = …; for (String s : list) list.add("y");` | ❌ No — `CopyOnWriteArrayList` is **fail-safe** | Understand snapshot semantics |
| 5 | **ConcurrentHashMap Iterator Safety** | In multi-threaded code, can `map.keySet().iterator()` throw CME? | ❌ No — weakly consistent, fail-safe | Know modern concurrent behavior |
| 6 | **`modCount` Internals (Conceptual)** | How does `ArrayList` detect concurrent modification? Sketch the fields/methods involved. | `transient int modCount;`<br>`private class Itr { int expectedModCount = modCount; }`<br>`checkForComodification()` | Understand the mechanism, not just the symptom |

✅ **Tier 1 Mastery Signal**:  
> _“I never use `list.remove()` inside `for-each`; I default to `removeIf()` or `Iterator.remove()`.”_

---

## 🧩 **Tier 2: `equals()` & `hashCode()` Contract**

| # | Problem | Task | Expected Outcome | Pitfall Revealed |
|---|---------|------|------------------|------------------|
| 7 | **Broken `hashCode()`** | `Person` has `name`, `id`. `equals()` uses both. `hashCode()` uses only `name`. Insert two equal persons into `HashSet`. What’s the size? | `2` — hash collision → stored in same bucket, but `equals()` says equal → should be `1`, but *only if `hashCode()` matches!*<br>✅ Actually: `HashSet` checks `hashCode()` first — if different, skips `equals()` → both stored. | Inconsistent `hashCode()` → duplicates in `Set` |
| 8 | **Mutable Key Disaster** | `Person p = new Person("Alice", 1); map.put(p, "data"); p.setName("Alicia"); map.get(p)` | Returns `null` — `hashCode()` changed, so new hash bucket | Never use mutable fields in `hashCode()`/`equals()` |
| 9 | **`Objects.hash()` vs Manual** | Compare:<br>`return name.hashCode() * 31 + id;`<br>`return Objects.hash(name, id);` | Same result — `Objects.hash()` uses `31 * a + b + …` | Use `Objects.hash()` — null-safe, concise |
|10| **`record` Auto-Generation** | `record Point(int x, int y) {}` — does it satisfy the contract? | ✅ Yes — compiler generates `equals`/`hashCode` using all fields | Prefer `record` for immutable value types |
|11| **Inheritance Trap** | `class Point3D extends Point { int z; }` — override `equals()` to include `z`. Is symmetry preserved? | ❌ `p2d.equals(p3d)` may be `true`, but `p3d.equals(p2d)` is `false` | Avoid `equals()` in inheritable classes (Effective Java, Item 10) |
|12| **Unit Test for Contract** | Write a JUnit test that verifies `a.equals(b) ⇒ a.hashCode() == b.hashCode()`. | Use `assertEquals(a.hashCode(), b.hashCode())` after `assertTrue(a.equals(b))` | Test the contract explicitly |

✅ **Tier 2 Mastery Signal**:  
> _“I make keys immutable, use `record`, and unit-test `equals`/`hashCode` pairs.”_

---

## 🌪️ **Tier 3: Sorted Collection Pitfalls (`TreeSet`/`TreeMap`)**

| # | Scenario | Code | Question | Answer |
|---|----------|------|----------|--------|
|13| **Inconsistent `compareTo()`** | `record Item(int id, int value) implements Comparable<Item>`<br>`{ public int compareTo(Item o) { return Integer.compare(value, o.value); } }`<br>`TreeSet<Item> set = new TreeSet<>(); set.add(new Item(1,10)); set.add(new Item(2,10));` | What is `set.size()`? | `1` — `compareTo() == 0` → treated as duplicate |
|14| **`TreeSet.contains()` Logic** | After Q13, does `set.contains(new Item(3,10))` return `true`? | ✅ Yes — `compareTo() == 0`, so found | `TreeSet` uses `compareTo()`, not `equals()` |
|15| **Fixing Inconsistency** | How to allow duplicate *values* but unique *ids* in a sorted structure? | Use `TreeMap<Integer, Set<Item>>` (group by value), or custom `Comparator` that uses `id` as tie-breaker | Don’t force `TreeSet` to do what `HashMap` does better |
|16| **Null Handling** | `TreeSet<String> set = new TreeSet<>(); set.add(null);` | `NullPointerException` — natural ordering forbids `null` | Use `Comparator.nullsFirst()` if nulls needed |

✅ **Tier 3 Mastery Signal**:  
> _“I never assume `TreeSet` respects `equals()` — I audit `compareTo()` for consistency.”_

---

## 🛡️ **Tier 4: Production-Grade Defenses**

| # | Task | Requirement | Solution | Why It’s Pro |
|---|------|-------------|----------|-------------|
|17| **Thread-Safe Counter** | `ConcurrentFrequencyCounter.increment(String key)` from multiple threads. No locks. | `map.compute(key, (k, v) -> v == null ? 1 : v + 1);` | Atomic compound operation — no race condition |
|18| **Immutable Key Class** | Design `UserId` (string-based) for use as `HashMap` key. Must be safe forever. | `public record UserId(String value) { public UserId { Objects.requireNonNull(value); } }` | Immutable, null-safe, auto `equals`/`hashCode` |
|19| **Defensive Copy in Constructor** | `class Config { private final List<String> features; public Config(List<String> input) { this.features = ??? } }` | `this.features = List.copyOf(input);` | Prevents caller from mutating internal state |
|20| **Safe Serialization of Collection** | Serialize a `User` with `List<Role> roles` without exposing mutability. | Use `writeObject()` to serialize `List.copyOf(roles)`; `readObject()` to assign `List.copyOf(deserialized)` | Avoids deserialization attacks |

✅ **Tier 4 Mastery Signal**:  
> _“My APIs are contract-compliant, thread-aware, and mutation-proof by construction.”_

---

## 📊 **Self-Assessment Rubric**

| Skill | Novice | Proficient | ✅ **Master** |
|------|--------|------------|---------------|
| **CME Handling** | Catches in IDE | Predicts & fixes | Designs APIs where CME is impossible |
| **`equals`/`hashCode`** | Uses IDE gen | Tests contract | Uses `record`, immutable keys, audits third-party classes |
| **Sorted Collections** | Uses `TreeSet` blindly | Checks `compareTo()` | Chooses `HashMap` vs `TreeMap` based on *semantic need*, not habit |
| **Concurrency** | Uses `synchronized` | Uses `ConcurrentHashMap` | Uses atomic methods (`compute`, `merge`) correctly |

🎯 **Final Mastery Check**:  
You can explain — in <60 seconds — why this code is dangerous:
```java
Person p = new Person("Alice", 1);
map.put(p, "data");
p.setName("Alicia");
System.out.println(map.get(p)); // null!
```
And you know **three** ways to prevent it.

## 🎯 Bonus: Interview Lightning Round (5 Questions)

1. **Q**: What’s the difference between fail-fast and fail-safe?  
   **A**: Fail-fast throws `CME` on concurrent mod (debug aid); fail-safe tolerates it (concurrent systems).

2. **Q**: Does `list.set(i, e)` cause CME during iteration?  
   **A**: No — it’s not a *structural* modification.

3. **Q**: If `a.equals(b)` is `true`, must `a.hashCode() == b.hashCode()`?  
   **A**: **Yes — or `HashMap`/`HashSet` will break.**

4. **Q**: Can two `!a.equals(b)` objects be considered equal in `TreeSet`?  
   **A**: Yes — if `a.compareTo(b) == 0`.

5. **Q**: How do you safely remove elements during iteration?  
   **A**: `Iterator.remove()`, `Collection.removeIf()`, or stream filter + reassign.
