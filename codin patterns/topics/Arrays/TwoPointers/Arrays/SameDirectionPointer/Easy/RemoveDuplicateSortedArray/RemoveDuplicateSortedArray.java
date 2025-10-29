package SameDirectionPointer.Easy.RemoveDuplicateSortedArray;

import java.util.Arrays;

public class RemoveDuplicateSortedArray {

    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n<=1){
            return n;
        }

        int left = 0, right = 1;
        while (right < n){
            if(nums[left] != nums[right]){
                swap(nums,left+1,right);
                left++;
            }
            right++;
        }
        return left+1;
    }



    public static void swap(int[] arr, int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2};
        int idx = removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
    }
}
