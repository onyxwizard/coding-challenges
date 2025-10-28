**Problem Statement:** Given an array of N integers, write a program to implement the Recursive Insertion Sort algorithm.

**Examples:**

**Example 1:**
**Input:** N = 6, array[] = {13,46,24,52,20,9}
**Output:** 9,13,20,24,46,52
**Explanation:** After sorting we get 9,13,20,24,46,52

**Example 2:**
**Input:** N = 5, array[] = {5,4,3,2,1}
**Output:** 1,2,3,4,5
**Explanation:** After sorting we get 1,2,3,4,5

```java
package RecursiveInsertionSort;

import java.util.Arrays;

public class RecursiveInsertionSort {
    private static void insertionSort(int[] arr, int pt){
        if(pt >= arr.length) return;
        int start = pt;
        while (start>0){
            if(arr[start] < arr[start-1]){
                swap(arr,start,start-1);
            }else {
                break;
            }
            start--;
        }
        insertionSort(arr,pt+1);
    }

    private static void swap(int[] arr, int right, int left){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private static void recursiveInsertionSort(int[] arr){
        if(arr == null || arr.length <= 1) return;
        int pointer = 1;
        insertionSort(arr,pointer);

    }
    public static void main(String[] args) {
        int arr[]={3,2,8,5,1,4,23};
        System.out.println("Recursive Insertion Sort");
        System.out.println("-----------------------------------------");
        System.out.println("Before sorting : "+ Arrays.toString(arr));
        recursiveInsertionSort(arr);
        System.out.println("After sorting : "+Arrays.toString(arr));
    }
}

```