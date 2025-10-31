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