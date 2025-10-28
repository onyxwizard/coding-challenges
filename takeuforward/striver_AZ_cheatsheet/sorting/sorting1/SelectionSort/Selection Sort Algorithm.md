**Problem Statement:** Given an array of **N integers**, write a program to implement the Selection sorting algorithm.

**Examples:**

**Example 1:**
**Input:** N = 6, array[] = {13,46,24,52,20,9}
**Output:** 9,13,20,24,46,52
**Explanation:** After sorting the array is: 9, 13, 20, 24, 46, 52

**Example 2:**
**Input:** N=5, array[] = {5,4,3,2,1}
**Output:** 1,2,3,4,5
**Explanation:** After sorting the array is: 1, 2, 3, 4, 5

```java
import java.util.Arrays;

public class SelectionSort {
    public static void selectionSort(int[] nums) {
        for(int i =0;i<nums.length;i++){
            int min = nums[i];
            int idx = i;
            for(int j=i;j<nums.length;j++){
                if(min > nums[j]){
                    idx = j;
                    min = nums[j];
                }
            }
            swap(nums,idx,i);
        }
    }

    private static void swap(int[] arr , int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {7, 4, 1, 5, 3};
        System.out.println("Before sorting : "+Arrays.toString(arr));
        selectionSort(arr);
        System.out.println("After sorting : "+Arrays.toString(arr));

    }
}

```