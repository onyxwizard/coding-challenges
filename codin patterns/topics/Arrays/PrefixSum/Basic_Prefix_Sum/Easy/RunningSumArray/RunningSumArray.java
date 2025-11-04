package PrefixSum.Basic_Prefix_Sum.Easy.RunningSumArray;

import java.util.Arrays;

public class RunningSumArray {
    public static int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println("Before : " +Arrays.toString(nums));
        runningSum(nums);
        System.out.println("After : " +Arrays.toString(nums));
    }
}
