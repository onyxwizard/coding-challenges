# 🧪 **Generics Mastery Problem Tracker**  
*“I don’t just avoid warnings — I design APIs where type errors are impossible.”*

| Tier                                    | Focus                                                          | Problems | Goal                                           |
| --------------------------------------- | -------------------------------------------------------------- | -------- | ---------------------------------------------- |
| **Tier 1: Syntax & Safety Basics**      | Declare, use, and avoid raw types                              | 5        | Eliminate `ClassCastException` at compile time |
| **Tier 2: PECS in Action**              | Apply Producer-Extends, Consumer-Super correctly               | 6        | Design flexible, reusable APIs                 |
| **Tier 3: Erasure & Advanced Patterns** | Work with type erasure, wildcards, recursive bounds            | 7        | Solve problems IDEs can’t auto-fix             |
| **Tier 4: Production-Grade Generics**   | Build safe varargs, builders, and framework-level abstractions | 4        | Architect like a library author                |

## 🔤 **Tier 1: Syntax & Safety Basics**

| # | Problem | Task | Expected Behavior / Fix | Why It Matters |
|---|---------|------|--------------------------|----------------|
| 1 | **Raw Type Danger** | Write code that compiles with raw `List`, then causes `ClassCastException` at runtime. | ```List list = new ArrayList(); list.add("a"); list.add(1); String s = (String) list.get(1); // 💥``` | Shows why generics exist |
| 2 | **Parameterized Safety** | Fix the above using generics. | `List<Object> list = …` or better: separate lists (`List<String>`, `List<Integer>`) | Type safety enforced at compile time |
| 3 | **Generic Method Declaration** | Write a method `printAll(List<?> list)` that safely prints all elements. | ```void printAll(List<?> list) { for (Object o : list) System.out.println(o); }``` | `?` = “unknown type” — safe for read-only |
| 4 | **`var` + Generics** | Use `var` to declare `new ArrayList<String>()`. What’s the inferred type? | `var list = new ArrayList<String>();` → `ArrayList<String>` | Modern Java reduces verbosity without losing safety |
| 5 | **Record Generics** | Define a generic `record Pair<T, U>(T first, U second)` and use it. | `Pair<String, Integer> p = new Pair<>("age", 30);` | Records + generics = safe, concise DTOs |

✅ **Tier 1 Mastery Signal**:  
> _“I never type `List list = …` — and I know why `List<?>` is safer than `List<Object>`.”_

---

## 🔄 **Tier 2: PECS in Action**

| # | Problem | Code Snippet | Task | Correct Fix |
|---|---------|--------------|------|-------------|
| 6 | **Broken Copy Method** | `void copy(List<T> dest, List<T> src)` — why can’t you pass `List<Number>` and `List<Integer>`? | Signature too rigid | `void copy(List<? super T> dest, List<? extends T> src)` |
| 7 | **Stack Push/Pop** | Design a generic `Stack<T>` with `push(T item)` and `T pop()`. | Standard generic class | `class Stack<T> { private Deque<T> dq = new ArrayDeque<>(); … }` |
| 8 | **Max Finder** | Write `max(List<? extends Comparable<?>> list)` — why doesn’t it compile? | `Comparable<?>` breaks comparison | `public static <T extends Comparable<? super T>> T max(List<T> list)` |
| 9 | **Flexible `addAll`** | Why does `List<Number>.addAll(List<Integer>)` work? | `addAll(Collection<? extends E>)` uses `? extends` | JCF uses PECS internally — understand it to use it |
|10| **Producer Misuse** | Given `List<? extends Number> nums`, which are valid?<br>`nums.add(1)`<br>`nums.add(null)`<br>`Number n = nums.get(0)` | Only `add(null)` and `get()` | `? extends` = read-only (except `null`) |
|11| **Consumer Misuse** | Given `List<? super Integer> ints`, which are valid?<br>`ints.add(1)`<br>`ints.add(1L)`<br>`Integer i = ints.get(0)` | Only `add(1)`; `get()` returns `Object` | `? super` = write-only |

✅ **Tier 2 Mastery Signal**:  
> _“I instinctively type `? extends T` when reading, `? super T` when writing — and I can explain why.”_

---

## 🌀 **Tier 3: Erasure & Advanced Patterns**

| # | Problem | Challenge | Solution Sketch | Key Insight |
|---|---------|-----------|------------------|-------------|
|12| **`instanceof` with Generics** | How to check if `obj` is `List<String>`? | Can’t — due to erasure.<br>Do: `if (obj instanceof List) { List<?> list = (List<?>) obj; if (!list.isEmpty() && list.get(0) instanceof String) … }` | Erasure prevents reified generics |
|13| **Generic Array Creation** | Implement `T[] toArray(Collection<T> c)` safely. | ```@SuppressWarnings("unchecked") T[] arr = (T[]) new Object[c.size()]; return c.toArray(arr);``` | Only safe if array not exposed |
|14| **Heap Pollution Demo** | Write a varargs method that corrupts a `List<String>` with `Integer`s. | ```void badAdd(List<String> list, T... ts) { list.addAll(Arrays.asList(ts)); } badAdd(stringList, 1, 2);``` | Varargs + generics = unchecked array |
|15| **`@SafeVarargs` Justification** | When is `@SafeVarargs` *actually* safe? | When method is `final`/`static`/`private`, doesn’t store `T...` in field, and doesn’t cast array elements | Prevents heap pollution |
|16| **Wildcard Capture** | Implement `swap(List<?> list, int i, int j)` without warnings. | Use helper: `<T> void swapHelper(List<T>, int, int)` | Converts `?` → named `T` inside scope |
|17| **Recursive Generics** | Design a fluent builder: `builder.setName("A").setAge(30).build()`. | `interface Builder<T extends Builder<T>> { T setName(String n); }` | Self-bounded type enables chaining |
|18| **Bounded Intersection** | Define a class `Box<T>` where `T` must be `Comparable<T>` *and* `Serializable`. | `class Box<T extends Comparable<T> & Serializable>` | Multiple bounds via `&` |

✅ **Tier 3 Mastery Signal**:  
> _“I know when `@SuppressWarnings("unchecked")` is safe — and I document why.”_

---

## 🛡️ **Tier 4: Production-Grade Generics**

| # | Task | Requirement | Pro-Level Solution | Why It’s Elite |
|---|------|-------------|---------------------|----------------|
|19| **Safe Factory Method** | `List<T> fromArray(T... elements)` — avoid heap pollution. | ```@SafeVarargs public static <T> List<T> fromArray(T... elements) { return Arrays.asList(elements); } // ✅ Safe: no mutation, no exposure``` | Uses `@SafeVarargs` correctly |
|20| **Immutable Generic DTO** | Create a thread-safe `Result<T>` with `success(T data)` and `error(String msg)`. | `record Result<T>(T data, String error) { static <T> Result<T> success(T d) { return new Result<>(d, null); } … }` | Records + generics + static factories |
|21| **Framework-Style Collector** | Write `Collector<T, ?, Map<K, List<T>>> groupBy(Function<T, K> keyMapper)` | Use `Collectors.groupingBy(keyMapper)` — but understand its signature: `<T, K> Collector<T, ?, Map<K, List<T>>>` | JCF-level abstraction design |
|22| **Type-Safe Event Bus** | `EventBus.register(Consumer<? super Event> handler)` — why `? super`? | Handlers should accept `Event` or supertypes (e.g., `Object`) — `? super Event` enables flexibility | PECS applied to event systems |

✅ **Tier 4 Mastery Signal**:  
> _“My generic APIs are as flexible as JCF’s — and as safe as `List.of()`.”_

---

## 📊 **Self-Assessment Rubric**

| Skill | Novice | Proficient | ✅ **Master** |
|------|--------|------------|---------------|
| **Basic Generics** | Uses `List<String>` | Avoids raw types | Uses `record<T>`, `var`, modern patterns |
| **PECS** | Knows the acronym | Applies in simple cases | Designs APIs *around* PECS (e.g., event handlers, builders) |
| **Erasure** | “Generics are erased” | Knows `instanceof` limitation | Writes safe varargs, wildcard capture, reified helpers |
| **Production Safety** | Adds `@SuppressWarnings` | Documents why | Proves safety via design (immutability, encapsulation) |

🎯 **Final Mastery Check**:  
You can whiteboard — and justify — the signature of `Collections.copy()`:
```java
public static <T> void copy(List<? super T> dest, List<? extends T> src)
```
... and explain why reversing the bounds would break type safety.

---

## 🎯 Bonus: Interview Lightning Round (5 Questions)

1. **Q**: What does `? extends T` mean?  
   **A**: “Some unknown subtype of `T`” — use for **producers** (read-only).

2. **Q**: Why can’t you do `new T()`?  
   **A**: Type erasure — `T` is not reified at runtime.

3. **Q**: When is `@SafeVarargs` safe?  
   **A**: When the method doesn’t store the varargs array or cast its elements unsafely.

4. **Q**: What’s the difference between `List<Object>` and `List<?>`?  
   **A**: `List<Object>` can `add(Object)`; `List<?>` can only `add(null)` — safer for unknown types.

5. **Q**: How do you safely convert `List<T>` to `T[]`?  
   **A**: Use `list.toArray(T[]::new)` (Java 11) or `list.toArray(new T[0])`.
