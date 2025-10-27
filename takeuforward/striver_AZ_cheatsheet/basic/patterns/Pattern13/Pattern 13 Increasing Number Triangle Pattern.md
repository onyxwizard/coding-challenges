**Problem Statement:** Given an integer **N,** print the following pattern : 

![](https://static.takeuforward.org/wp/uploads/2023/02/image-17.png)

Here, N = 5.

**Examples**:

**Input Format**: N = 3
**Result**: 
1
2 3
4 5 6

**Input Format**: N = 6
**Result**:   
1
2  3
4  5  6
7  8  9  10
11  12  13  14  15
16  17  18  19  20  21


```java
public class Pattern13 {  
    public static void patternDecode(int n) {  
        System.out.println("---------------------------------------------------");  
        System.out.printf(" Pattern 13: Increasing Number Triangle Pattern -> %d\n", n);  
        System.out.println("--------------------------------------------------");  
        int start=1;  
        for(int i=1;i<=n;i++){  
            for(int j =1;j<=i;j++){  
                System.out.printf("%d ",start);  
                start++;  
            }  
            System.out.println();  
        }  
  
  
        System.out.println("\n-------------------------------------------------\n");  
    }  
  
    public static void main(String[] args) {  
        patternDecode(3);  
        patternDecode(6);  
    }  
}
```