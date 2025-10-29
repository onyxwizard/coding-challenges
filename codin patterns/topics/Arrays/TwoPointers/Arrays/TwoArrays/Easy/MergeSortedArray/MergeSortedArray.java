package Arrays.TwoArrays.Easy.MergeSortedArray;

import java.util.Arrays;

public class MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m - 1;      // Last element in nums1's actual data
        int right = n - 1;     // Last element in nums2
        int pt = m + n - 1;    // Last position in nums1 (full capacity)

        while (left >= 0 && right >= 0) {
            if (nums1[left] > nums2[right]) {
                nums1[pt--] = nums1[left--];
            } else {
                nums1[pt--] = nums2[right--];
            }
        }

        // If there are remaining elements in nums2, copy them
        while (right >= 0) {
            nums1[pt--] = nums2[right--];
        }

        // No need to handle remaining nums1 elements—they're already in place
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0}, nums2 = {2,5,6};
        int m = 3, n = 3;
        merge(nums1,m,nums2,n);
        System.out.println(Arrays.toString(nums1));
    }
}
