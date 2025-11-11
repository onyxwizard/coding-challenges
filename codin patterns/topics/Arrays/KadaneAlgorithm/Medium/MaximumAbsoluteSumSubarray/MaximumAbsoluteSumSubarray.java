package KadaneAlgorithm.Medium.MaximumAbsoluteSumSubarray;

public class MaximumAbsoluteSumSubarray {
    public int maxAbsoluteSum(int[] nums) {
        int maxSum = nums[0], curMaxSum = nums[0], minSum = nums[0], curMinSum = nums[0];

        for(int i=1;i< nums.length;i++){
            curMaxSum = Math.max(nums[i],curMaxSum+nums[i]);
            curMinSum = Math.min(nums[i],curMinSum + nums[i]);

            maxSum = Math.max(maxSum,curMaxSum);
            minSum = Math.min(minSum, curMinSum);
        }
        return Math.max(maxSum, -minSum);
    }
}
