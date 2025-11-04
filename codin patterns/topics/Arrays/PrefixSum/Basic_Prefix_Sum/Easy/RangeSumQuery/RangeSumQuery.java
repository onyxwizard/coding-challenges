package PrefixSum.Basic_Prefix_Sum.Easy.RangeSumQuery;

public class RangeSumQuery {
    static class NumArray {
        int[] nums;
        public NumArray(int[] nums) {
            for(int i=1;i<nums.length;i++){
                nums[i] += nums[i-1];
            }
            this.nums = nums;
        }

        public int sumRange(int left, int right) {
            // handle left value
            if(left==0) return this.nums[right];
            
            return this.nums[right] - this.nums[left+1];
        }
    }
}
