package SameDirectionPointer.Easy.RemoveElement;

import java.lang.reflect.Array;
import java.util.Arrays;

public class RemoveElement {

    public static int removeElement(int[] nums, int val) {
        if(nums.length <= 0){
            return 0;
        }
        int left=0,right=0;
        while(right < nums.length){
            if(nums[right] != val){
                swap(nums, left,right);
                left++;
            }
            right++;
        }
        return left;
    }

    public static void swap(int[] arr, int left,int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        int res = removeElement(nums, val);
        int[] arr = Arrays.copyOfRange(nums,0,res);
        System.out.println(Arrays.toString(arr));
    }
}
