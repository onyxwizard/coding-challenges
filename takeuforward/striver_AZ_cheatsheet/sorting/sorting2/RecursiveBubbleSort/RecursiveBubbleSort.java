package RecursiveBubbleSort;

import java.util.Arrays;

public class RecursiveBubbleSort {

    private static void swap(int[] arr,int startIdx, int endIdx){
        int temp = arr[startIdx];
        arr[startIdx] = arr[endIdx];
        arr[endIdx] = temp;
    }

    private static void recBubbleSort(int[] arr, int end){
        // base case
        if(end <=0){
            return;
        }
        int left=0;
        boolean flag = false;
        while(left+1 < end){
            if(arr[left] > arr[left+1]){
                swap(arr,left,left+1);
                flag = true;
            }
            left++;
        }
        if(!flag) return;
        recBubbleSort(arr,end-1);

    }

    private static void recursiveBubbleSort(int[] arr){
        if (arr == null) return;

        int size = arr.length;
        recBubbleSort(arr,size);
    }

    public static void main(String[] args) {
        int arr[]={3,2,8,5,1,4,23};
        System.out.println("Recursive Bubble Sort");
        System.out.println("-----------------------------------------");
        System.out.println("Before sorting : "+ Arrays.toString(arr));
        recursiveBubbleSort(arr);
        System.out.println("After sorting : "+Arrays.toString(arr));
    }
}
