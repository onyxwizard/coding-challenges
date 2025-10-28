**Problem Statement:** Given an array of **N integers**, write a program to implement the Bubble Sorting algorithm.

**Examples:**

**Example 1:**
**Input:** N = 6, array[] = {13,46,24,52,20,9}
**Output:** 9,13,20,24,46,52
**Explanation:** After sorting we get 9,13,20,24,46,52

**Input:** N = 5, array[] = {5,4,3,2,1}
**Output:** 1,2,3,4,5
**Explanation:** After sorting we get 1,2,3,4,5

```java
package BubbleSort;

import java.util.Arrays;

public class BubbleSort {

    private static void swap(int[] arr , int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void bubbleSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            boolean flag = false;
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {13,46,24,52,20,9};
        System.out.println("Before sorting : "+ Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("After sorting : "+Arrays.toString(arr));
    }
}

```