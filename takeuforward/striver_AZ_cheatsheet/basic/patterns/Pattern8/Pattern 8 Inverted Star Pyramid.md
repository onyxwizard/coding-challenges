**Problem Statement:** Given an integer **N,** print the following pattern : 

![](https://static.takeuforward.org/wp/uploads/2023/02/image-12.png)

Here, N = 5.

**Examples**:

**Input Format**: N = 3
**Result**:

```java
* * * * * 
  * * * 
    * 
```

**Input Format**: N = 6
**Result**:
```java
* * * * * * * * * * * 
  * * * * * * * * * 
    * * * * * * * 
      * * * * * 
        * * * 
          * 
```

```java
public class Pattern8 {  
    public static void patternDecode(int n){  
        System.out.println("---------------------------------------------------");  
        System.out.printf(" Pattern 8: Inverted Star Pyramid-> %d\n",n);  
        System.out.println("--------------------------------------------------");  
        for(int i=n;i>0;i--){  
            for(int j=n;j>i;j--){  
                System.out.print("  ");  
            }  
  
            for(int j=i+(i-1);j>0;j--){  
                System.out.print("* ");  
            }  
            System.out.println();  
        }  
        System.out.println("-------------------------------------------------\n");  
    }  
  
  
    public static void main(String[] args) {  
        int n = 3;  
        Pattern8.patternDecode(n);  
        n = 6;  
        Pattern8.patternDecode(n);  
    }  
}
```