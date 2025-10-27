**Problem statement:** Given a number ‘N’, find out the sum of the first N natural numbers.

**Examples:**

**Example 1:**
**Input:** N=5
**Output:** 15
**Explanation:** 1+2+3+4+5=15

**Example 2:**
**Input:** N=6
**Output:** 21
**Explanation:** 1+2+3+4+5+6=15

```java
public class Recursion3 {
    public static int sumNaturalNumbers(int sum,int n){
        if(n==0){
            return sum;
        }

        return sumNaturalNumbers(sum+n,n-1);

    }
    public static void main(String[] args) {
        int n=5;
        int result = sumNaturalNumbers(0,n);
        System.out.println(result);
    }
}


```