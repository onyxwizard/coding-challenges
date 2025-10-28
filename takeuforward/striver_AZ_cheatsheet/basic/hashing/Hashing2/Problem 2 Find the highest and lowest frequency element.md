**Problem Statement:** Given an array of size N. Find the highest and lowest frequency element.

**Pre-requisite:** [**Hashing Theory**](https://www.youtube.com/watch?v=KEs5UyBJ39g&t=1394s) and  [**Counting frequencies of array elements**](https://takeuforward.org/data-structure/count-frequency-of-each-element-in-the-array/)

**Examples:**

**Example 1:**
**Input:** array[] = {10,5,10,15,10,5};
**Output**: 10 15
**Explanation:** The frequency of 10 is 3, i.e. the highest and the frequency of 15 is 1 i.e. the lowest.

**Example 2:**
**Input:** array[] = {2,2,3,4,4,2};
**Output**: 2 3
**Explanation:** The frequency of 2 is 3, i.e. the highest and the frequency of 3 is 1 i.e. the lowest.

```java
import java.util.HashMap;
import java.util.Map;

public class Hashing2 {


    public static void frequencyHighLowPrint(HashMap<Integer,Integer> freqMap){
        int key1 = 0, count1 = Integer.MIN_VALUE, key2 = 0, count2 = Integer.MAX_VALUE;
        for(Map.Entry<Integer,Integer> entry : freqMap.entrySet()){
            if(count1 < entry.getValue()){
                key1 = entry.getKey();
                count1 = entry.getValue();
            }

            if(count2 > entry.getValue()){
                key2 = entry.getKey();
                count2 = entry.getValue();
            }
        }
            System.out.printf("The Max Value %d with Count %d\nThe Min Value %d with Count %d",key1,count1,key2,count2);
            System.out.println();
    }

    private static void frequencyCount(int[] arr){
        HashMap<Integer,Integer> freqMap = new HashMap<>();
        for(int elem : arr){
            freqMap.put(elem,freqMap.getOrDefault(elem,0)+1);
        }
        frequencyHighLowPrint(freqMap);

    }


    public static void main(String[] args) {
        int[] arr =  {10,5,10,15,10,5};
        frequencyCount(arr);
    }
}

```