**Problem Statement:** Given an integer **N,** print the following pattern : 

![](https://static.takeuforward.org/wp/uploads/2023/02/image-15.png)

Here, N = 5.

**Examples**:

**Input Format**: N = 3
**Result**: 
1
01
101

**Input Format**: N = 6
**Result**:   
1
01
101
0101
10101
010101

```java
public class Pattern11 {  
    public static void patternDecode(int n) {  
        System.out.println("---------------------------------------------------");  
        System.out.printf(" Pattern 11: Binary Number Triangle Pattern -> %d\n", n);  
        System.out.println("--------------------------------------------------");  
  
        for(int i=1;i<=n;i++){  
            int start = i%2==0 ? 0 : 1;  
            for(int j=1;j<=i;j++){  
                System.out.print(start);  
                if(start==0){  
                    start = 1;  
                }else{  
                    start = 0;  
                }  
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