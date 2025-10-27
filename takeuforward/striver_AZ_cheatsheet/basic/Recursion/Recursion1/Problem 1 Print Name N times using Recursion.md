**Problem Description:** Given an integer N, write a program to print your name N times.

**Examples**

Input: N = 3
Output: Ashish Ashish Ashish 
Explanation: Name is printed 3 times.

Input: N = 1
Output: Ashish 
Explanation: Name is printed once.
            

**Examples**

Input: N = 3
Output: Ashish Ashish Ashish 
Explanation: Name is printed 3 times.

Input: N = 1
Output: Ashish 
Explanation: Name is printed once.

```java
public class Recursion1 {  
    public static void printName(int n,String name){  
        if(n==1){  
            System.out.println(name);  
            return;  
        }  
        System.out.println(name);  
        printName(n-1,name);  
    }  
    public static void main(String[] args) {  
        int n=3;  
        String name = "AK";  
        printName(n,name);  
    }  
}
```