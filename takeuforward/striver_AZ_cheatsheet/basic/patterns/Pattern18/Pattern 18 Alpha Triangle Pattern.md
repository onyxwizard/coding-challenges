**Problem Statement:** Given an integer **N,** print the following pattern : 

![](https://static.takeuforward.org/wp/uploads/2023/02/image-22.png)

Here, N = 5.

**Examples**:

**Input Format**: N = 3
**Result**: 
C
B C
A B C

**Input Format**: N = 6
**Result**:   
F
E F
D E F
C D E F
B C D E F
A B C D E F

```java
public class Pattern18 {  
    public static void patternDecode(int n) {  
        System.out.println("---------------------------------------------------");  
        System.out.printf(" Pattern 18: Alpha-Triangle Pattern -> %d\n", n);  
        System.out.println("--------------------------------------------------");  
  
        for(int i=n-1;i>=0;i--){  
            for(int j=i;j<n;j++){  
                System.out.printf("%c",(char) (65+j));  
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