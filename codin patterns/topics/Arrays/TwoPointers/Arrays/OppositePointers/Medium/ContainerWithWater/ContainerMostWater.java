package Arrays.OppositePointers.Medium.ContainerWithWater;

public class ContainerMostWater {
    public static int maxArea(int[] height) {
        int maxWater = 0, left = 0, right = height.length -1;

        while(left < right){
            int distance = right - left;
            int minHeight = Math.min(height[left],height[right]);
            maxWater = Math.max(maxWater, distance* minHeight);

            if(height[left] <= height[right]){
                left++;
            }else{
                right--;
            }

        }

        return maxWater;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int res = maxArea(height);
        System.out.println(res);
    }
}
