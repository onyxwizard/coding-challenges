**Problem Statement:** Given an array of n integers, sort the array using the Quicksort method.

**Examples**

Input: N = 5, Arr[] = {4,1,7,9,3}
Output: {1, 3, 4, 7, 9}
Explanation: After sorting the array in ascending order it becomes 1, 3, 4, 7, 9

Input: N = 8, Arr[] = {4,6,2,5,7,9,1,3}
Output: {1, 2, 3, 4, 5, 6, 7, 9}
Explanation: After sorting the array in ascending order it becomes 1, 2, 3, 4, 5, 6, 7, 9
            

**Examples**

Input: N = 5, Arr[] = {4,1,7,9,3}
Output: {1, 3, 4, 7, 9}
Explanation: After sorting the array in ascending order it becomes 1, 3, 4, 7, 9

Input: N = 8, Arr[] = {4,6,2,5,7,9,1,3}
Output: {1, 2, 3, 4, 5, 6, 7, 9}
Explanation: After sorting the array in ascending order it becomes 1, 2, 3, 4, 5, 6, 7, 9

```java
package QuickSort;

import java.util.Arrays;

public class QuickSort {

    private static void swap(int[] arr,int startIdx, int endIdx){
        int temp = arr[startIdx];
        arr[startIdx] = arr[endIdx];
        arr[endIdx] = temp;
    }

    private static int quickSortPartition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int left = start;
        int right = end - 1;

        while (left <= right) { // ✅ allow left == right
            if (arr[left] < pivot) {
                left++;
            } else if (arr[left] > pivot) {
                swap(arr, left, right);
                right--;
            } else {
                // arr[left] == pivot → skip it (treat as "in place")
                left++; // ✅ or you could do right--; but left++ is safe
            }
        }

        // Now, 'left' is the first index > pivot → place pivot here
        swap(arr, left, end);
        return left;
    }

    private static void quickSortMerge(int[] arr, int start, int end){
        if(start>=end) {
            return;
        }
        int partition = quickSortPartition(arr, start, end);
        quickSortMerge(arr, start, partition - 1);
        quickSortMerge(arr, partition + 1, end);


    }

    private static void quickSort(int[] arr){
        if(arr == null || arr.length <=1) return;
        quickSortMerge(arr,0,arr.length-1);
    }

    public static void main(String[] args) {
        int arr[]={3,2,8,5,1,4,23};
        System.out.println("Quick Sort");
        System.out.println("-----------------------------------------");
        System.out.println("Before sorting : "+ Arrays.toString(arr));
        quickSort(arr);
        System.out.println("After sorting : "+Arrays.toString(arr));
    }
}

```