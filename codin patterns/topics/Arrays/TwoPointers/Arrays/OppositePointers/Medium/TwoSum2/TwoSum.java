package Arrays.OppositePointers.Medium.TwoSum2;

public class TwoSum {
    public static int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length-1;

        while (left < right){
            int add = numbers[left] + numbers[right];
            if(target == add){
                return new int[]{left+1,right+1};
            } else if (add < target) {
                left++;

            }else right--;
        }
        return new int[]{0,0};
    }

    public static void main(String[] args) {
        int[] numbers = {2,7,11,15};
        int target = 9;
        twoSum(numbers,target);
    }
}
