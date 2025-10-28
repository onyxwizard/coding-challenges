**Problem Statement:** Given an array of size n, sort the array using **Merge Sort**.

**Examples**

Input : N=7,arr[]={3,2,8,5,1,4,23}
Output : {1,2,3,4,5,8,23}
Explanation : Given array is sorted in non-decreasing order.

Input : N=5, arr[]={4,2,1,6,7}
Output : {1,2,4,6,7}
Explanation : Given array is sorted in non-decreasing order.
            

**Examples**

Input : N=7,arr[]={3,2,8,5,1,4,23}
Output : {1,2,3,4,5,8,23}
Explanation : Given array is sorted in non-decreasing order.

Input : N=5, arr[]={4,2,1,6,7}
Output : {1,2,4,6,7}
Explanation : Given array is sorted in non-decreasing order.

```java
package MergerSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {
    private static void merger(int[] arr,int start,int mid, int end){
        List<Integer> temp = new ArrayList<>();
        int left = start, right = mid+1;

        while(left <= mid && right <= end){
            if(arr[left] <= arr[right]) temp.add(arr[left++]);
            else temp.add(arr[right++]);
        }

        while (left <= mid) temp.add(arr[left++]);

        while (right <= end) temp.add(arr[right++]);

        for(int i=start;i<=end;i++){
            arr[i] = temp.get(i-start);
        }
    }

    private static void mergeSort(int[] arr,int low, int high){
        if(low>=high){
            return;
        }
        int mid = (low + high)/2;
        mergeSort(arr,low,mid);
        mergeSort(arr,mid+1,high);
        merger(arr,low,mid,high);

    }

    
    public static void main(String[] args) {
        int arr[]={3,2,8,5,1,4,23};
        System.out.println("Merge Sort");
        System.out.println("-----------------------------------------");
        System.out.println("Before sorting : "+ Arrays.toString(arr));
        mergeSort(arr,0,arr.length-1);
        System.out.println("After sorting : "+Arrays.toString(arr));
    }
}


```