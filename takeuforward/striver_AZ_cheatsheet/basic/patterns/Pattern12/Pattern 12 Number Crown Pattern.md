**Problem Statement:** Given an integer **N,** print the following pattern : 

![](https://static.takeuforward.org/wp/uploads/2023/02/image-16.png)

Here, N = 5.

**Examples**:

**Input Format**: N = 3
**Result**: 
1    1
12  21
123321

**Input Format**: N = 6
**Result**:   
1          1
12        21
12       321
1234    4321
12345  54321
123456654321

```java
public class Pattern12 {  
    public static void patternDecode(int n) {  
        System.out.println("---------------------------------------------------");  
        System.out.printf(" Pattern 12: Number Crown Pattern -> %d\n", n);  
        System.out.println("--------------------------------------------------");  
  
        for(int i=1;i<=n;i++){  
            //Left  
            for(int j=1;j<=i;j++) {  
                System.out.print(j);  
            }  
              
            //middle  
            for(int j=n;j>i;j--){  
                System.out.print("  ");  
            }  
  
            //right  
            for(int j=i;j>=1;j--){  
                System.out.print(j);  
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