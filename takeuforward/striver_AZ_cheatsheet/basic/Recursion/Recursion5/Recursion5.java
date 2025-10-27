import java.util.Arrays;

public class Recursion5 {
    static void reverseArray(int n, int[] arr, int[] res,int i){
        if(n==-1){
            return;
        }
        int elem = arr[n];
        reverseArray(n-1 ,arr, res,i);
        res[i-n] = elem;
    }

    static void reverseArray2(int[] arr, int start,int end){
        if(start>end){
            return;
        }
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        reverseArray2( arr, start+1,end-1);
    }
    public static void main(String[] args) {
        int n=5;
        int[] arr = {5,4,3,2,1};
        int[] res = new int[arr.length];
        System.out.println("Before : "+ Arrays.toString(arr));
        int i=4;
        reverseArray(n-1,arr,res,i);
        System.out.println("After : "+ Arrays.toString(res));

        System.out.println("Before : "+ Arrays.toString(arr));
        reverseArray2(arr,0,n-1);
        System.out.println("After : "+ Arrays.toString(arr));

    }
}
