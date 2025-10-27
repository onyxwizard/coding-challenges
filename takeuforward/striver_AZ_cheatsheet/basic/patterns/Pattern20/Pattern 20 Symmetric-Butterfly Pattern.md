**Problem Statement:** Given an integer **N,** print the following pattern : 

![](https://static.takeuforward.org/wp/uploads/2023/02/image-24.png)

Here, N = 5.

**Examples**:

**Input Format**: N = 3
**Result**: 
```java
*    *
**  **
******
**  **
*    *
```

**Input Format**: N = 6
**Result**:   
```java
*          *
**        **
***      ***
****    ****
*****  *****
************
*****  *****
****    ****
***      ***
**        **
*          *
```

```java
public class Pattern20 {  
    public static void patternDecode(int n) {  
        System.out.println("---------------------------------------------------");  
        System.out.printf(" Pattern 20: Symmetric-Butterfly Pattern-> %d\n", n);  
        System.out.println("--------------------------------------------------");  
  
        for(int i=1;i<=n;i++){  
            for(int j=1;j<=i;j++){  
                System.out.print("*");  
            }  
  
            for(int j=n;j>i;j--){  
                System.out.print("  ");  
            }  
  
            for(int j=1;j<=i;j++){  
                System.out.print("*");  
            }  
            System.out.println();  
        }  
  
        //right part  
        for(int i=n-1;i>=1;i--){  
            for(int j=i;j>=1;j--){  
                System.out.print("*");  
            }  
  
            for(int j=n;j>i;j--){  
                System.out.print("  ");  
            }  
  
            for(int j=1;j<=i;j++){  
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