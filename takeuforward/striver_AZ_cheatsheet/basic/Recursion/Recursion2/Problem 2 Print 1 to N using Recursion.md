**Problem Description:** Given an integer N, write a program to print numbers from 1 to N.

**Examples**

Input: N = 4
Output: 1, 2, 3, 4
Explanation: All the numbers from 1 to 4 are printed.

Input: N = 1
Output: 1 
Explanation: This is the base case.
            

**Examples**

Input: N = 4
Output: 1, 2, 3, 4
Explanation: All the numbers from 1 to 4 are printed.

Input: N = 1
Output: 1 
Explanation: This is the base case.

```java
import java.util.ArrayList;  
import java.util.List;  
  
public class Recursion2 {  
    public static void printNForward(int i, int n){  
        System.out.println(i);  
        if(n==i){  
            return;  
        }  
        printNForward(i+1,n);  
    }  
  
    public static void printNBackward(int n){  
        if(n==1){  
            System.out.println(n);  
            return;  
        }  
        printNBackward(n-1);  
        System.out.println(n);  
    }  
  
    static void backtrack(int n, List<Integer> path) {  
        if (n == 0) {  
            System.out.println(path);  
            return;  
        }  
        path.add(n);  
        backtrack(n - 1, path);  
        path.remove(path.size() - 1); // ← THIS is backtracking!  
        System.out.println(path);  
    }  
  
    public static void main(String[] args) {  
        int n=3;  
        System.out.println("Forward recursion");  
        printNForward(1,n);  
        System.out.println("Backward recursion");  
        printNBackward(n);  
  
        System.out.println("Backtrack");  
        List<Integer> arr  = new ArrayList<>();  
        backtrack(n,arr);  
    }  
}
```