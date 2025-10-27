**Problem Statement:** Given an integer **N,** print the following pattern : 

![](https://static.takeuforward.org/wp/uploads/2023/02/image-18.png)

Here, N = 5.

**Examples**:

**Input Format**: N = 3
**Result**: 
A
A B
A B C

**Input Format**: N = 6
**Result**:   
A
A B
A B C
A B C D
A B C D E
A B C D E F

```java
public class Pattern14 {  
    public static void patternDecode(int n) {  
        System.out.println("---------------------------------------------------");  
        System.out.printf(" Pattern 14: Increasing Letter Triangle Pattern -> %d\n", n);  
        System.out.println("--------------------------------------------------");  
        for(int i=1;i<=n;i++){  
            for(int j =0;j<i;j++){  
                System.out.printf("%c ",(char) (65+j));  
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