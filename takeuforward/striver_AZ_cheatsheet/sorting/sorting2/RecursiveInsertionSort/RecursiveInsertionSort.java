package RecursiveInsertionSort;

import java.util.Arrays;

public class RecursiveInsertionSort {
    private static void insertionSort(int[] arr, int pt){
        if(pt >= arr.length) return;
        int start = pt;
        while (start>0){
            if(arr[start] < arr[start-1]){
                swap(arr,start,start-1);
            }else {
                break;
            }
            start--;
        }
        insertionSort(arr,pt+1);
    }

    private static void swap(int[] arr, int right, int left){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private static void recursiveInsertionSort(int[] arr){
        if(arr == null || arr.length <= 1) return;
        int pointer = 1;
        insertionSort(arr,pointer);

    }
    public static void main(String[] args) {
        int arr[]={3,2,8,5,1,4,23};
        System.out.println("Recursive Insertion Sort");
        System.out.println("-----------------------------------------");
        System.out.println("Before sorting : "+ Arrays.toString(arr));
        recursiveInsertionSort(arr);
        System.out.println("After sorting : "+Arrays.toString(arr));
    }
}
