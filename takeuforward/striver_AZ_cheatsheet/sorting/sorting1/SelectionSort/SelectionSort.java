package SelectionSort;

import java.util.Arrays;

public class SelectionSort {
    public static void selectionSort(int[] nums) {
        for(int i =0;i<nums.length;i++){
            int idx = i;
            for(int j=i;j<nums.length;j++){
                if(nums[idx] > nums[j]){
                    idx = j;
                }
            }
            if(idx != i)  swap(nums,idx,i);
        }
    }

    private static void swap(int[] arr , int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {7, 4, 1, 5, 3};
        System.out.println("Selection Sort");
        System.out.println("-----------------------------------------");
        System.out.println("Before sorting : "+Arrays.toString(arr));
        selectionSort(arr);
        System.out.println("After sorting : "+Arrays.toString(arr));

    }
}
