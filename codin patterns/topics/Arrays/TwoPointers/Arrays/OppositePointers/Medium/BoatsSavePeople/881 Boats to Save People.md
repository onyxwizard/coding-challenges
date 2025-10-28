You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.

Return the minimum number of boats to carry every given person.

 

Example 1:

Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)

Example 2:

Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)

Example 3:

Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)

 

Constraints:

    1 <= people.length <= 5 * 104
    1 <= people[i] <= limit <= 3 * 104



```java
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

```