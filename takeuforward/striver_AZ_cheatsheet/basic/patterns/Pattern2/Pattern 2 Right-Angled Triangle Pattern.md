Pattern-2: Right-Angled Triangle Pattern
**Problem Statement:** Given an integer **N,** print the following pattern : 

![](https://static.takeuforward.org/wp/uploads/2023/02/image-6.png)

Here, N = 5.

**Examples**:

**Input Format:** N = 3
**Result:** 
```java
* 
* * 
* * *
```
* * *

**Input Format:** N = 6
**Result:**
```java
* 
* * 
* * *
* * * *
* * * * *
* * * * * *
```

```java
public class Pattern2 {
    public static void patternDecode(int n){
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern 2: Right-Angled Triangle Pattern | n = %d\n",n);
        System.out.println("--------------------------------------------------");
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------------------------\n");
    }


    public static void main(String[] args) {
        int n = 3;
        Pattern2.patternDecode(n);
        n = 6;
        Pattern2.patternDecode(n);
    }
}

```