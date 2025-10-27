**Problem Statement:** Given an integer **N,** print the following pattern : 

![](https://static.takeuforward.org/wp/uploads/2023/02/image-7.png)

Here, N = 5.

**Examples**:

**Input Format**: N = 3
**Result**: 
1
1 2 
1 2 3

**Input Format**: N = 6
**Result**:
1
1 2
1 2 3
1 2 3 4
1 2 3 4 5
1 2 3 4 5 6

```java
public class Pattern3 {  
    public static void patternDecode(int n){  
        System.out.println("-------------------------------------");  
        System.out.printf("             Pattern3 -> %d        \n",n);  
        System.out.println("-------------------------------------");  
        for(int i=1;i<=n;i++){  
            for(int j=1;j<=i;j++){  
                System.out.printf("%d ",j);  
            }  
            System.out.println();  
        }  
        System.out.println("-------------------------------------\n");  
    }  
  
  
    public static void main(String[] args) {  
        int n = 3;  
        Pattern3.patternDecode(n);  
        n = 6;  
        Pattern3.patternDecode(n);  
    }  
}
```