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
