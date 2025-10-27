**Problem Statement:** Given an integer **N,** print the following pattern : 

![](https://static.takeuforward.org/wp/uploads/2023/02/image-19.png)

Here, N = 5.

**Examples**:

**Input Format**: N = 3
**Result**: 
A B C
A B
A

**Input Format**: N = 6
**Result**:   
A B C D E F
A B C D E 
A B C D
A B C
A B
A

```java
public class Pattern15 {  
    public static void patternDecode(int n) {  
        System.out.println("---------------------------------------------------");  
        System.out.printf(" Pattern 15: Reverse Letter Triangle Pattern -> %d\n", n);  
        System.out.println("--------------------------------------------------");  
        for(int i=n;i>=1;i--){  
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