# Number Theory & Arithmetic – A Comprehensive Guide with Problems & Homework 🚀

Welcome to the magical world of numbers! This guide covers six fundamental topics in number theory and arithmetic, each explained in detail with real‑world analogies, step‑by‑step methodologies, and Java code examples. After each topic, you’ll find a **practice problem** (exactly as you provided) and a **homework section** with additional problems to solidify your understanding. By working through these, you’ll build a strong foundation for interviews, competitive programming, and advanced DSA.

---

## 1. Prime Numbers & Factorization 🧩

### Detailed Explanation

**What are Prime Numbers?**  
A prime number is a natural number greater than 1 that has no positive divisors other than 1 and itself. Think of them as the **indivisible atoms** of the number world – they cannot be broken into smaller equal groups.  
Examples: 2, 3, 5, 7, 11, 13, …

**Composite Numbers** are made by multiplying primes together. For example, 12 = 2 × 2 × 3.

**Why do we care?**

- **Cryptography** (RSA) uses large primes to secure data.
- **Hashing** often uses prime table sizes to reduce collisions.
- **Optimization** – many algorithms rely on prime properties.

**Prime Testing (Is it prime?)**  
To check if a number `n` is prime:

- If `n < 2`, it’s not prime.
- Check divisibility from 2 up to `√n`. If any divisor is found, it’s composite.
- **Why √n?** If `n = a × b` and both `a` and `b` are greater than `√n`, then `a × b > n`. So at least one factor must be ≤ `√n`.

**Sieve of Eratosthenes – Find all primes up to N**  
This is an ancient but brilliant algorithm:

1. Create a list of numbers from 2 to N, all marked as prime.
2. Start with the smallest prime (2). Mark all its multiples as not prime.
3. Move to the next unmarked number and repeat.
4. The numbers still marked are primes.  
   Time complexity: O(N log log N), space: O(N).

**Prime Factorization – Breaking a number into primes**  
Repeatedly divide the number by the smallest prime that divides it, until you get 1. For example:  
84 ÷ 2 = 42  
42 ÷ 2 = 21  
21 ÷ 3 = 7  
7 ÷ 7 = 1  
So 84 = 2 × 2 × 3 × 7.

### Practice Problem: Find All Prime Factors

**Task:** Given a positive integer `n` (1 ≤ n ≤ 10⁶), return a list of all **unique** prime factors in ascending order.  
**Example:** `n = 30` → output `[2, 3, 5]`

**Java Solution Template:**

```java
import java.util.*;

public class PrimeFactors {
    public static List<Integer> uniquePrimeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        // Check divisibility by 2 separately
        if (n % 2 == 0) {
            factors.add(2);
            while (n % 2 == 0) n /= 2;
        }
        // Check odd divisors from 3 to √n
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                factors.add(i);
                while (n % i == 0) n /= i;
            }
        }
        // If n > 1, it's a prime factor
        if (n > 1) factors.add(n);
        return factors;
    }
}
```

### 🏠 Homework / Learning Outcomes

Solve these problems to master prime numbers:

1. **Check if a number is prime** – write a function `isPrime(n)`.
2. **Generate all primes up to 100** using the Sieve of Eratosthenes.
3. **Find all prime factors (including repetitions)** of a given number.
4. **Count the number of distinct prime factors** of a number.
5. **Given two numbers, find their common prime factors**.
6. **Product of primes** – given an integer, find the product of its distinct prime factors.

---

## 2. GCD and LCM 🤝

### Detailed Explanation

**Greatest Common Divisor (GCD)**  
The GCD of two numbers is the largest number that divides both without leaving a remainder.  
Example: GCD(24, 36) = 12 because 12 divides both, and no larger number does.  
**Real‑world analogy:** You have 24 red and 36 blue balloons. You want to make identical bouquets with the same number of red and blue balloons in each, using all balloons. The largest bouquet size is the GCD.

**Least Common Multiple (LCM)**  
The LCM is the smallest positive number that is a multiple of both numbers.  
Example: LCM(4, 6) = 12 because 12 is the first number that appears in both multiplication tables.  
**Real‑world analogy:** Two friends visit every 4 days and every 6 days. They will meet again after LCM(4,6) = 12 days.

**Euclidean Algorithm – A clever way to find GCD**  
Instead of checking every divisor, use the property:  
`gcd(a, b) = gcd(b, a % b)`  
Repeat until remainder is 0. The last non‑zero remainder is the GCD.  
This is extremely fast – O(log min(a,b)).

**Example:**  
gcd(48, 18)  
48 % 18 = 12 → gcd(18, 12)  
18 % 12 = 6 → gcd(12, 6)  
12 % 6 = 0 → gcd is 6.

**LCM from GCD**  
There is a simple relation:  
`lcm(a, b) = (a × b) / gcd(a, b)`  
This works because the product includes all prime factors, and dividing by the GCD removes the overlap.

**Extended GCD (for advanced use)**  
It finds integers `x` and `y` such that `ax + by = gcd(a, b)`. Used in solving linear Diophantine equations and modular inverses.

### Practice Problem: GCD and LCM Calculator

**Task:** Given two positive integers A and B (1 ≤ A, B ≤ 10⁶), output their GCD and LCM.  
**Example:** `12 18` → output `6 36`

**Java Solution Template:**

```java
public class GcdLcm {
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        return (a / gcd(a, b)) * b; // avoid overflow by dividing first
    }

    public static void main(String[] args) {
        int a = 12, b = 18;
        System.out.println(gcd(a, b) + " " + lcm(a, b));
    }
}
```

### 🏠 Homework / Learning Outcomes

1. **Compute GCD of three numbers**.
2. **Check if two numbers are coprime** (GCD = 1).
3. **Find LCM of an array of numbers**.
4. **Given two numbers, find the smallest number divisible by both** (just LCM).
5. **Solve a word problem**: Two traffic lights turn red every 45 seconds and 60 seconds. If they both turn red now, after how many seconds will they turn red together again?
6. **Extended GCD** – implement it and find `x, y` for `ax + by = gcd(a, b)`.

---

## 3. Modular Arithmetic 🕰️

### Detailed Explanation

**What is Modulo?**  
The modulo operation `a mod m` gives the remainder when `a` is divided by `m`. For example, 17 mod 5 = 2 because 17 = 5×3 + 2.  
Think of a clock: after 12, it wraps around – that’s modulo 12 arithmetic.

**Key Properties**

- `(a + b) mod m = ((a mod m) + (b mod m)) mod m`
- `(a − b) mod m = ((a mod m) − (b mod m) + m) mod m`
- `(a × b) mod m = ((a mod m) × (b mod m)) mod m`
- **Exponentiation**: compute `(a^b) mod m` efficiently using modular exponentiation.

**Why is it important?**

- Prevents integer overflow in computations with large numbers.
- Used in **cryptography** (RSA, Diffie‑Hellman), **hashing**, and **random number generators**.
- Many coding problems ask for results modulo `10⁹+7` to keep answers manageable.

**Modular Exponentiation (Fast Exponentiation under Modulus)**  
Naively computing `a^b` then taking modulo is impossible for huge `b`. Instead, use the binary method:

```
result = 1
base = a % m
while exponent > 0:
    if exponent is odd: result = (result * base) % m
    base = (base * base) % m
    exponent = exponent >> 1
```

This runs in O(log b) time.

**Handling Negative Numbers**  
In Java, `(-3) % 5 = -3`. To get a positive remainder, use `((a % m) + m) % m`.

### Practice Problem: Modular Power Sum

**Task:** Compute `(a^b + c^d) mod m` efficiently, even when exponents are huge (up to 10⁹).  
**Example:** `2 10 3 5 100` → `2^10 + 3^5 = 1024 + 243 = 1267`, `1267 mod 100 = 67`

**Java Solution Template:**

```java
public class ModularSum {
    public static long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    public static long solve(long a, long b, long c, long d, long m) {
        long part1 = modPow(a, b, m);
        long part2 = modPow(c, d, m);
        return (part1 + part2) % m;
    }
}
```

### 🏠 Homework / Learning Outcomes

1. **Compute `(123456789 × 987654321) mod 1000000007`** using properties.
2. **Find the last digit of `7^1000`** (hint: mod 10).
3. **Modular exponentiation** – implement both recursive and iterative versions.
4. **Given a large exponent, compute `(a^b) mod m` where b is a string** (e.g., b = "12345678901234567890").
5. **Solve a word problem**: A number when divided by 5 gives remainder 3, when divided by 7 gives remainder 2. Find the smallest positive number (use Chinese Remainder Theorem).
6. **Modular inverse** – using extended Euclidean algorithm, find `x` such that `ax ≡ 1 (mod m)` (if `gcd(a,m)=1`).

---

## 4. Binary Representation & Bit Tricks 💡

### Detailed Explanation

**Binary Numbers**  
Computers use binary (base‑2) because they have two states: on (1) and off (0). Each digit is a **bit**. For example, decimal 13 is `1101` in binary:  
1×8 + 1×4 + 0×2 + 1×1 = 8+4+0+1 = 13.

**Bitwise Operators**

- `&` (AND): `1 & 1 = 1`, else 0.
- `|` (OR): `0 | 0 = 0`, else 1.
- `^` (XOR): `1 ^ 0 = 1`, `0 ^ 1 = 1`, else 0 (different bits → 1).
- `~` (NOT): flips every bit (one’s complement).
- `<<` (left shift): shifts bits left, fills with 0 – equivalent to multiplying by 2.
- `>>` (right shift): shifts bits right – equivalent to integer division by 2 (for non‑negative numbers).

**Common Bit Tricks**

- **Check if a number is odd**: `(n & 1) == 1`.
- **Check if a number is a power of two**: `n > 0 && (n & (n - 1)) == 0`.
- **Set the k‑th bit**: `n | (1 << k)`.
- **Clear the k‑th bit**: `n & ~(1 << k)`.
- **Toggle the k‑th bit**: `n ^ (1 << k)`.
- **Count set bits (population count)**:
  ```java
  int count = 0;
  while (n != 0) {
      n &= (n - 1); // clears the lowest set bit
      count++;
  }
  ```
  This is Brian Kernighan’s algorithm, runs in O(number of set bits).

**Why Bit Tricks Matter**  
They are extremely fast (often one CPU cycle) and are used in low‑level programming, graphics, compression, and embedded systems. Many interview problems can be solved elegantly with bit manipulation.

### Practice Problem: Count Set Bits in Range

**Task:** Given L and R (0 ≤ L ≤ R ≤ 10⁵), return the total number of set bits (1s) in all numbers from L to R inclusive.  
**Example:** L=5, R=7 → numbers 5(101),6(110),7(111) → set bits = 2+2+3 = 7

**Java Solution Template:**

```java
public class CountSetBitsRange {
    // Count set bits in a single number
    public static int countBits(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    public static int totalBitsInRange(int L, int R) {
        int total = 0;
        for (int i = L; i <= R; i++) {
            total += countBits(i);
        }
        return total;
    }
}
```

### 🏠 Homework / Learning Outcomes

1. **Check if a number is a power of two** using bit trick.
2. **Find the only non‑repeating element** in an array where every other element appears twice (using XOR).
3. **Swap two numbers without a temporary variable** using XOR.
4. **Reverse bits of a given integer**.
5. **Count set bits for all numbers from 1 to N** efficiently (not one by one – use pattern).
6. **Given an array, find the two numbers that appear odd number of times** (all others even).

---

## 5. Fast Exponentiation (Binary Exponentiation) ⚡

### Detailed Explanation

**The Problem**  
Computing `a^b` by repeated multiplication takes O(b) time. When `b` is huge (millions or billions), this is impractical.

**The Insight**  
Observe that `a^b` can be broken down using the binary representation of `b`. For example, `3^13 = 3^(1101₂) = 3^8 × 3^4 × 3^1`.  
We can compute powers by repeated squaring:

- Start with `result = 1`.
- While `b > 0`:
  - If `b` is odd, multiply `result` by the current `base`.
  - Square the `base` (so it becomes `base^2`, `base^4`, etc.).
  - Divide `b` by 2 (right shift).

This uses O(log b) multiplications.

**Recursive vs Iterative**  
Both work. The iterative version avoids recursion overhead and is preferred in practice.

**Handling Large Numbers and Modulus**  
Often we need `(a^b) mod m`. Just apply `% m` after each multiplication to keep numbers small. This is **modular exponentiation** (covered earlier).

**Example**  
Compute 3^13:  
13 in binary = 1101.

- Start: result = 1, base = 3, exponent = 13 (odd) → result = 1×3 = 3, base = 3² = 9, exponent = 6
- exponent = 6 (even) → result stays 3, base = 9² = 81, exponent = 3
- exponent = 3 (odd) → result = 3×81 = 243, base = 81² = 6561, exponent = 1
- exponent = 1 (odd) → result = 243×6561 = 1594323, base = 6561², exponent = 0  
  Done: 3^13 = 1594323.

### Practice Problem: Efficient Power Calculation

**Task:** Implement fast exponentiation to compute `a^b` (a and b are integers). If exponent is 0, return 1. If base is 0 and exponent positive, return 0.  
**Example:** `3 13` → output `1594323`

**Java Solution Template:**

```java
public class FastExponentiation {
    public static long fastPow(long base, long exponent) {
        long result = 1;
        while (exponent > 0) {
            if ((exponent & 1) == 1)   // if exponent is odd
                result *= base;
            base *= base;
            exponent >>= 1;
        }
        return result;
    }
}
```

### 🏠 Homework / Learning Outcomes

1. **Implement modular exponentiation** – modify the function to take a modulus.
2. **Compute `2^100` using fast exponentiation** (use `BigInteger` in Java if needed).
3. **Find `a^b mod m` when b is extremely large (e.g., up to 10^100000)** – use exponentiation by squaring with big integers.
4. **Matrix exponentiation** – compute the n‑th Fibonacci number using fast exponentiation of a 2×2 matrix.
5. **Solve a recurrence** – given `f(n) = f(n-1) + 2*f(n-2)`, find `f(n)` efficiently using fast exponentiation.
6. **Compare performance** – write a naïve exponentiation and compare runtime for large exponents.

---

## 6. Divisibility Logic 🔢

### Detailed Explanation

**What is Divisibility?**  
A number `a` is divisible by `b` if there exists an integer `k` such that `a = k × b`. In programming, we check with `a % b == 0`.  
Example: 15 is divisible by 3 because 15 % 3 == 0.

**Divisibility Rules (handy shortcuts)**

- **2**: last digit even.
- **3**: sum of digits divisible by 3.
- **4**: last two digits divisible by 4.
- **5**: last digit 0 or 5.
- **6**: divisible by both 2 and 3.
- **9**: sum of digits divisible by 9.
- **10**: last digit 0.

**Factors and Multiples**

- A **factor** of `n` divides `n` exactly.
- A **multiple** of `n` is `n × k`.
- The **smallest divisor > 1** of a number is always prime (unless the number itself is prime).

**Applications**

- **Leap year calculation**: year divisible by 4, but not by 100 unless also by 400.
- **Grouping items**: can we split N items into groups of size k? Check `N % k == 0`.
- **Sieve of Eratosthenes** uses divisibility to mark non‑primes.
- **Cryptography** often relies on divisibility properties of large numbers.

**Efficiently Finding the Smallest Divisor > 1**  
Instead of checking all numbers from 2 to N, check only up to √N. If no divisor is found, N itself is prime and the smallest divisor is N.  
Why? If N is composite, it has a factor ≤ √N.

### Practice Problem: Divisibility Checker – Find the First Divisor

**Task:** Given an integer N > 1, find the smallest divisor greater than 1. If N is prime, the answer is N itself.  
**Example:** `15` → output `3` (since 15 ÷ 3 = 5, no remainder)

**Java Solution Template:**

```java
public class SmallestDivisor {
    public static int smallestDivisor(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return i;
        }
        return n;   // n is prime
    }
}
```

### 🏠 Homework / Learning Outcomes

1. **Check if a year is a leap year** using divisibility rules.
2. **Find all divisors of a number** (both small and large).
3. **Count the number of divisors** of a number (e.g., 12 has 6 divisors: 1,2,3,4,6,12).
4. **Find the sum of all divisors** of a number.
5. **Check if a number is a perfect number** (sum of its proper divisors equals itself, e.g., 6 = 1+2+3).
6. **Given a list of numbers, find those divisible by 3 and 5** (i.e., divisible by 15).
7. **Solve a real‑world problem**: You have a chocolate bar with N pieces. Can you share it equally among 4 friends? Among 6 friends? Use divisibility to answer.

---

## 🎉 Final Words

You’ve just explored the core of number theory and arithmetic as used in computer science. Each topic builds on the others, and the practice problems and homework will help you internalize these concepts. Keep coding, keep exploring, and soon these ideas will become second nature. Happy problem‑solving! ☕💻
