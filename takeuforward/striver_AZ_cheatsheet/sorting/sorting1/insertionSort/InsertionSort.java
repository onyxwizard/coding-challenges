package insertionSort;

import java.util.Arrays;

public class InsertionSort {

    private static void insertionSort(int[] arr){
        for(int i =1;i<arr.length;i++){
            for (int j=i;j>0;j--){
                if(arr[j]<arr[j-1]){
                    swap(arr,j,j-1);
                }
            }
        }
    }

    private static void swap(int[] arr , int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {13,46,24,52,20,9};
        System.out.println("Insertion Sort");
        System.out.println("-----------------------------------------");
        System.out.println("Before sorting : "+ Arrays.toString(arr));
        insertionSort(arr);
        System.out.println("After sorting : "+Arrays.toString(arr));
    }
}
