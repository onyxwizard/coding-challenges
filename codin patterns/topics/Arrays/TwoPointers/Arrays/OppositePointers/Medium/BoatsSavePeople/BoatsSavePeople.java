package Arrays.OppositePointers.Medium.BoatsSavePeople;

import java.util.*;
public class BoatsSavePeople {
    /**
     * Returns the minimum number of boats required to carry all people,
     * given that each boat can carry at most two people and has a weight limit.
     *
     * Strategy:
     * - Sort the array to enable greedy two-pointer pairing.
     * - Pair the lightest and heaviest person if their combined weight <= limit.
     * - If not, the heaviest must go alone.
     */
    public static int numRescueBoats(int[] people, int limit) {
        // Step 1: Sort to enable greedy pairing
        Arrays.sort(people);

        int left = 0;                // Lightest remaining person
        int right = people.length - 1; // Heaviest remaining person
        int boats = 0;               // Boat counter

        // Step 2: Two-pointer traversal
        while (left <= right) {
            // If lightest + heaviest can share a boat
            if (people[left] + people[right] <= limit) {
                left++;  // Lightest gets on board
            }
            // Heaviest always gets on board (either alone or with lightest)
            right--;
            boats++;
        }

        return boats;
    }

    public static void main(String[] args) {
        int[] people = {1,2};
        int limit = 3;
        int res = numRescueBoats(people,limit);
        System.out.println(res);

        people = new int[]{5,1,4,2};
        limit = 6;
        res = numRescueBoats(people,limit);
        System.out.println(res);
    }
}
