package SquareSortedArray;

import java.util.Arrays;

public class SquareSortedArray {
    public static int[] sortedSquares(int[] nums) {

        int left=0, right = nums.length-1 , pt = nums.length-1;
        int[] res = new int[right+1];


        while (pt>-1){
            if(Math.abs(nums[left]) > Math.abs(nums[right])){
                res[pt--] = nums[left] * nums[left];
                left++;
            }else{
                res[pt--] = nums[right] * nums[right];
                right--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-4,-1,0,3,10};
        System.out.println(Arrays.toString(sortedSquares(nums)));

        nums = new int[]{-5, -3, -2, -1};
        System.out.println(Arrays.toString(sortedSquares(nums)));
    }
}
