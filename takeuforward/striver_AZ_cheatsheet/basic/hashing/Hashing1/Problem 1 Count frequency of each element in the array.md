Problem statement: Given an array, we have found the number of occurrences of each element in the array.

Examples:

Example 1:
Input: arr[] = {10,5,10,15,10,5};
Output: 10  3
	 5  2
        15  1
Explanation: 10 occurs 3 times in the array
	      5 occurs 2 times in the array
              15 occurs 1 time in the array

Example2: 
Input: arr[] = {2,2,3,4,4,2};
Output: 2  3
	3  1
        4  2
Explanation: 2 occurs 3 times in the array
	     3 occurs 1 time in the array
             4 occurs 2 time in the array


```java
import java.util.*;

public class Hashing1 {

    public static void frequencyMapPrint(HashMap<Integer,Integer> freqMap){

        for(Map.Entry<Integer,Integer> entry : freqMap.entrySet()){
            System.out.printf("Key : %d | Count : %d",entry.getKey(),entry.getValue());
            System.out.println();
        }
    }
    private static void frequencyCount(int[] arr){
        HashMap<Integer,Integer> freqMap = new HashMap<>();
        for(int elem : arr){
            freqMap.put(elem,freqMap.getOrDefault(elem,0)+1);
        }
        frequencyMapPrint(freqMap);

    }
    public static void main(String[] args) {
        int[] arr =  {10,5,10,15,10,5};
        frequencyCount(arr);
    }
}

```