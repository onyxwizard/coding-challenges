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
