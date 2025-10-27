**Problem Statement:** Given an integer **N,** print the following pattern : 

![](https://static.takeuforward.org/wp/uploads/2023/02/image-14.png)

Here, N = 5.

**Examples**:

**Input Format**: N = 3
**Result**: 
```java
  *  
  **
  ***  
  **
  * 
```  
**Input Format**: N = 6
**Result**:   
```java
     *
     **
     *** 
     ****
     *****
     ******  
     *****
     ****
     ***    
     **
     *
```

```java
public class Pattern10 {
    public static void patternDecode(int n) {
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern 10: Full Diamond Star Pattern -> %d\n", n);
        System.out.println("--------------------------------------------------");

        // Top including middle
        for (int i = 1; i <= n; i++) {
            // Leading spaces: (n - i) single spaces
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            // Stars: 2*i - 1 stars with space
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // Bottom (excluding middle)
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("-------------------------------------------------\n");
    }

    public static void main(String[] args) {
        patternDecode(3);
        patternDecode(6);
    }
}
```