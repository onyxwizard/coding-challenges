**Problem Statement:** Given an integer **N,** print the following pattern : 

![](https://static.takeuforward.org/wp/uploads/2023/02/image-13.png)

Here, N = 5.

**Examples**:

**Input Format**: N = 3
**Result**: 
```java
    * 
  * * * 
* * * * * 
* * * * * 
  * * * 
    *
```
**Input Format**: N = 6
**Result**:   
```java
          * 
        * * * 
      * * * * * 
    * * * * * * * 
  * * * * * * * * * 
* * * * * * * * * * * 
* * * * * * * * * * * 
  * * * * * * * * * 
    * * * * * * * 
      * * * * * 
        * * * 
          *
```

```java
public class Pattern9 {  
    public static void patternDecode(int n){  
        System.out.println("---------------------------------------------------");  
        System.out.printf(" Pattern 9: Diamond Star Pattern-> %d\n",n);  
        System.out.println("--------------------------------------------------");  
        for(int i=1;i<=n;i++){  
            for(int j=n;j>i;j--){  
                System.out.print("  ");  
            }  
  
            for(int j=1;j<=i+(i-1);j++){  
                System.out.print("* ");  
            }  
            System.out.println();  
        }  
        for(int i=n;i>0;i--){  
            for(int j=i;j<n;j++){  
                System.out.print("  ");  
            }  
  
            for(int j=i+(i-1);j>=1;j--){  
                System.out.print("* ");  
            }  
            System.out.println();  
        }  
        System.out.println("-------------------------------------------------\n");  
    }  
  
  
    public static void main(String[] args) {  
        int n = 3;  
        Pattern9.patternDecode(n);  
        n = 6;  
        Pattern9.patternDecode(n);  
    }  
}
```