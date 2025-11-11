package KadaneAlgorithm.Medium.MaximumSubarraySumCircular;

public class MaximumSubarraySumCircular {
    public int maxSubarraySumCircular(int[] nums) {
        // Max Sum
        int maxSum = kadane(nums);

        // Compute total sum
        int totalSum = 0;
        for(int i: nums) totalSum+= i;

        //Compute min sum
        int minSum = minKadane(nums);
        int maxCircularSum = totalSum - minSum;

        return (maxCircularSum == 0) ? maxSum : Math.max(maxSum,maxCircularSum);

    }

    private int kadane(int[] arr){
        int cur = arr[0], best = arr[0];

        for(int i=1;i<arr.length;i++){
            cur = Math.max(arr[i],cur+arr[i]);
            best = Math.max(cur,best);
        }

        return best;
    }

    private int minKadane(int[] arr){
        int cur = arr[0], best = arr[0];

        for(int i=1;i<arr.length;i++){
            cur = Math.min(arr[i],cur+arr[i]);
            best = Math.min(cur,best);
        }

        return best;
    }
}
