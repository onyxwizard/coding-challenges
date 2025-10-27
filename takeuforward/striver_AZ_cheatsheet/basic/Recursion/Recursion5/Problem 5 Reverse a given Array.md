**Problem Statement:** You are given an array. The task is to reverse the array and print it. 

**Examples:**

**Example 1:**
**Input:** N = 5, arr[] = {5,4,3,2,1}
**Output:** {1,2,3,4,5}
**Explanation:** Since the order of elements gets reversed the first element will occupy the fifth position, the second element occupies the fourth position and so on.

**Example 2:**
**Input:** N=6 arr[] = {10,20,30,40}
**Output:** {40,30,20,10}
**Explanation:** Since the order of elements gets reversed the first element will occupy the fifth position, the second element occupies the fourth position and so on.

```java
import java.util.Arrays;

public class Recursion5 {
    static void reverseArray(int n, int[] arr, int[] res,int i){
        if(n==-1){
            return;
        }
        int elem = arr[n];
        reverseArray(n-1 ,arr, res,i);
        res[i-n] = elem;
    }

    static void reverseArray2(int[] arr, int start,int end){
        if(start>end){
            return;
        }
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        reverseArray2( arr, start+1,end-1);
    }
    public static void main(String[] args) {
        int n=5;
        int[] arr = {5,4,3,2,1};
        int[] res = new int[arr.length];
        System.out.println("Before : "+ Arrays.toString(arr));
        int i=4;
        reverseArray(n-1,arr,res,i);
        System.out.println("After : "+ Arrays.toString(res));

        System.out.println("Before : "+ Arrays.toString(arr));
        reverseArray2(arr,0,n-1);
        System.out.println("After : "+ Arrays.toString(arr));

    }
}

```