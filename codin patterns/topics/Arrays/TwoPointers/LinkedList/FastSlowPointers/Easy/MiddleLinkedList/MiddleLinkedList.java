package LinkedList.FastSlowPointers.Easy.MiddleLinkedList;

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

public class MiddleLinkedList {

    /**
     * Finds the middle node of a singly-linked list using the Fast & Slow Pointer technique.
     *
     * - The slow pointer moves one step at a time.
     * - The fast pointer moves two steps at a time.
     * - When the fast pointer reaches the end, the slow pointer will be at the middle.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head The head of the linked list
     * @return The middle node of the linked list
     */
    public static ListNode middleNode(ListNode head) {
        // Initialize both pointers at the head
        ListNode slow = head;
        ListNode fast = head;

        // Traverse the list: fast moves 2 steps, slow moves 1 step
        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move slow pointer by one node
            fast = fast.next.next;      // Move fast pointer by two nodes
        }

        // When loop ends, slow is at the middle
        return slow;
    }

    /**
     * Main method to test the middleNode function.
     * Constructs a linked list: 1 -> 2 -> 3 -> 4 -> 5
     * Expected middle node: 3 (for odd-length list)
     */
    public static void main(String[] args) {
        // Build a linked list with 5 nodes: 1 → 2 → 3 → 4 → 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // Find the middle node
        ListNode middle = middleNode(head);

        // Output the value of the middle node
        System.out.println("Middle node value: " + middle.val); // Expected output: 3
    }
}