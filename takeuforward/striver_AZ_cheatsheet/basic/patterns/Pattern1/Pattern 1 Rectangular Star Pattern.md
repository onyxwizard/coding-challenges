**Problem Statement:** Given an integer **N,** print the following pattern.

![](https://static.takeuforward.org/wp/uploads/2023/01/Screenshot-2023-01-23-000952.png)

**Examples:**

**Example 1:**
**Input:** N = 3
**Output:** 
```java
***
***
***
```
**Example 2:**
**Input:** N = 6
**Output**:
```java
******
******
******
******
******
******
```

```java
import javax.xml.transform.Source;  
import java.lang.module.ResolutionException;  
  
public class Pattern1 {  
    public static void patternDecode(int n){  
        System.out.println("-------------------------------------");  
        System.out.printf("             Pattern1 -> %d        \n",n);  
        System.out.println("-------------------------------------");  
        for(int i=0;i<n;i++){  
            for(int j=0;j<n;j++){  
                System.out.print("* ");  
            }  
            System.out.println();  
        }  
        System.out.println("-------------------------------------\n");  
    }  
  
  
    public static void main(String[] args) {  
        int n = 3;  
        Pattern1.patternDecode(n);  
        n = 6;  
        Pattern1.patternDecode(n);  
    }  
}
```