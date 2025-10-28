**Problem Statement:** Given an array of **N integers**, write a program to implement the Insertion sorting algorithm.

**Examples:**

**Example 1:**
**Input:** N = 6, array[] = {13,46,24,52,20,9}
**Output:** 9,13,20,24,46,52
**Explanation:** 
After sorting the array is: 9,13,20,24,46,52

**Example 2:**
**Input:** N=5, array[] = {5,4,3,2,1}
**Output:** 1,2,3,4,5
**Explanation:** After sorting the array is: 1,2,3,4,5

```java
package insertionSort;

import java.util.Arrays;

public class InsertionSort {

    private static void insertionSort(int[] arr){
        for(int i =1;i<arr.length;i++){
            for (int j=i;j>0;j--){
                if(arr[j]<arr[j-1]){
                    swap(arr,j,j-1);
                }
            }
        }
    }

    private static void swap(int[] arr , int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {13,46,24,52,20,9};
        System.out.println("Insertion Sort");
        System.out.println("-----------------------------------------");
        System.out.println("Before sorting : "+ Arrays.toString(arr));
        insertionSort(arr);
        System.out.println("After sorting : "+Arrays.toString(arr));
    }
}

```