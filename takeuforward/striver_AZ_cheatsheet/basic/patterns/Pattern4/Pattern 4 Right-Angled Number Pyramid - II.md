**Problem Statement:** Given an integer **N,** print the following pattern : 

![](https://static.takeuforward.org/wp/uploads/2023/02/image-8.png)

Here, N = 5.

**Examples**:

**Input Format**: N = 3
**Result:** 
1
2 2 
3 3 3

**Input Format**: N = 6
**Result**:
1
2 2
3 3 3
4 4 4 4
5 5 5 5 5
6 6 6 6 6 6

```java
public class Pattern4 {
    public static void patternDecode(int n){
        System.out.println("-------------------------------------");
        System.out.printf("Pattern 4:Right-Angled Number Pyramid - II | n= %d\n",n);
        System.out.println("-------------------------------------");
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.printf("%d ",i);
            }
            System.out.println();
        }
        System.out.println("-------------------------------------\n");
    }


    public static void main(String[] args) {
        int n = 3;
        Pattern4.patternDecode(n);
        n = 6;
        Pattern4.patternDecode(n);
    }
}

```