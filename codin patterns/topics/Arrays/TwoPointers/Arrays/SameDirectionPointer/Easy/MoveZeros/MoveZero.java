package SameDirectionPointer.Easy.MoveZeros;

import java.util.Arrays;

public class MoveZero {
    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        if(n == 1) return;
        int left = 0, right = 0;

        while (right < n){
            if(nums[right] !=0){
                swap(nums,left,right);
                left++;
            }
                right++;
        }
    }

    public static void swap(int[] arr, int left,int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        System.out.println("Before Swapping : "+Arrays.toString(nums));
        moveZeroes(nums);
        System.out.println("After Swapping : "+Arrays.toString(nums));

    }
}
