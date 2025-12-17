public class ValidMountain {
  public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        if (n < 3) return false;
        
        int left = 0;
        // Walk up from left
        while (left + 1 < n && arr[left] < arr[left + 1]) {
            left++;
        }
        
        int right = n - 1;
        // Walk down from right
        while (right > 0 && arr[right] < arr[right - 1]) {
            right--;
        }
        
        // Check if they meet at the same point (peak)
        // And ensure peak is not at edges
        return left > 0 && right < n - 1 && left == right;
    }
}
