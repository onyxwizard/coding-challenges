Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.

 

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

Example 2:

Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.

Example 3:

Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.

 

Constraints:

    The number of the nodes in the list is in the range [0, 104].
    -105 <= Node.val <= 105
    pos is -1 or a valid index in the linked-list.

 

Follow up: Can you solve it using O(1) (i.e. constant) memory?
✅ How It Works: 

    Slow pointer moves one node at a time.
    Fast pointer moves two nodes at a time.
    If there’s a cycle, the fast pointer will eventually "lap" the slow pointer, and they will meet.
    If there’s no cycle, the fast pointer reaches null, and we exit safely.
     

✅ Why O(1) Space? 

    Only two extra pointers (slow and fast) are used — no recursion, no hash set, no extra data structures.
     

✅ Edge Cases Handled: 

    Empty list (head == null) → returns false
    Single node with no cycle → returns false
    Two nodes forming a cycle → correctly returns true
     

This is the standard interview solution and meets all constraints. 


```java
package LinkedList.FastSlowPointers.Easy.LinkedListCycle;

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class LinkedListCycle {

    /**
     * Detects if a linked list has a cycle using Floyd's Cycle Detection Algorithm (Fast & Slow Pointers).
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head The head of the linked list
     * @return true if a cycle exists, false otherwise
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        // Move slow by 1 step, fast by 2 steps
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true; // Cycle detected
            }
        }

        return false; // Reached end → no cycle
    }

    public static void main(String[] args) {
        // Build linked list: 1 → 2 → 3 → 4 → 5
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        // Create a cycle: 5 → 2 (cycle starts at node2)
        node5.next = node2;

        // Test cycle detection
        boolean hasCycle = hasCycle(node1);
        System.out.println("Has cycle: " + hasCycle); // Expected: true
    }
}
```