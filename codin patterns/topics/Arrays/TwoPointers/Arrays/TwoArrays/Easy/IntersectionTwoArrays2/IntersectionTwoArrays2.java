package Arrays.TwoArrays.Easy.IntersectionTwoArrays2;

import java.util.*;

public class IntersectionTwoArrays2 {

    // Approach : Two pointer + sorting (n log n)
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] temp = new int[Math.min(nums1.length, nums2.length)]; // Max possible intersection size
        int left = 0, right = 0, pt = 0;

        while (left < nums1.length && right < nums2.length) {
            if (nums1[left] == nums2[right]) {
                temp[pt++] = nums1[left];
                left++;
                right++;
            } else if (nums1[left] < nums2[right]) {
                left++;
            } else {
                right++;
            }
        }

        // Return only the filled part
        return Arrays.copyOf(temp, pt);
    }

    // Approach : Hash map (n) | space = (n)
    public int[] intersects(int[] nums1, int[] nums2) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums1) freq.put(num, freq.getOrDefault(num, 0) + 1);

        List<Integer> result = new ArrayList<>();
        for (int num : nums2) {
            if (freq.getOrDefault(num, 0) > 0) {
                result.add(num);
                freq.put(num, freq.get(num) - 1);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1}, nums2 = {2,2};
        System.out.println(Arrays.toString(intersect(nums1,nums2)));
    }
}
