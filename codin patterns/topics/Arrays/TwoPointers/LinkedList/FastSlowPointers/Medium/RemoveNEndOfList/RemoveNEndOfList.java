package LinkedList.FastSlowPointers.Medium.RemoveNEndOfList;

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

public class RemoveNEndOfList {

    // ======================================================================================
    // APPROACH 1: FAST-SLOW POINTERS (ONE-PASS, OPTIMAL)
    // ======================================================================================

    /**
     * Removes the nth node from the end of a linked list in **one logical pass** using fast-slow pointers.
     *
     * Strategy:
     * - Use a dummy node to simplify edge cases (e.g., removing the head).
     * - Move `fast` pointer `n+1` steps ahead → creates a fixed gap of `n+1` between `slow` and `fast`.
     * - Then move both until `fast` reaches null → `slow` lands just before the target node.
     * - Remove the target by skipping it: `slow.next = slow.next.next`.
     *
     * ✅ Why is this "one pass"?
     *   - The list is traversed **once continuously** from start to end.
     *   - No pointer ever resets to the head or restarts traversal.
     *   - Both pointers move forward only — total steps ≈ 2n, but in a single sweep.
     *
     * Time Complexity: O(n) — each node visited at most twice (still linear).
     * Space Complexity: O(1) — only a few extra pointers used.
     *
     * @param head The head of the linked list
     * @param n The 1-indexed position from the end to remove
     * @return The head of the modified list
     */
    public static ListNode removeNthFromEndApproach1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        // Advance fast by n+1 steps to create gap
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both until fast reaches end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove target node
        slow.next = slow.next.next;

        return dummy.next;
    }

    // ======================================================================================
    // APPROACH 2: TWO-PASS (COUNT THEN REMOVE)
    // ======================================================================================

    /**
     * Removes the nth node from the end using a classic two-pass method:
     * 1. First pass: count total number of nodes.
     * 2. Second pass: traverse to the (length - n)th node and remove the next node.
     *
     * ❌ Why is this "two passes"?
     *   - The algorithm **fully traverses the list once to count**.
     *   - Then **starts again from the head** for the second traversal.
     *   - This reset to head makes it a true two-pass solution.
     *
     * Time Complexity: O(n) — two full traversals (still linear, but 2n steps).
     * Space Complexity: O(1) — constant extra space.
     *
     * While correct, this is **less efficient in practice** and doesn't satisfy the "one-pass" follow-up.
     *
     * @param head The head of the linked list
     * @param n The 1-indexed position from the end to remove
     * @return The head of the modified list
     */
    public static ListNode removeNthFromEndApproach2(ListNode head, int n) {
        // First pass: count nodes
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        // Special case: remove head
        if (n == length) {
            return head.next;
        }

        // Second pass: go to (length - n - 1)th node
        curr = head;
        for (int i = 0; i < length - n - 1; i++) {
            curr = curr.next;
        }

        // Remove next node
        curr.next = curr.next.next;
        return head;
    }

    // ======================================================================================
    // HELPER METHODS FOR TESTING
    // ======================================================================================

    public static ListNode createList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        return head;
    }

    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        System.out.println("[" + sb + "]");
    }

    // ======================================================================================
    // MAIN METHOD — TESTS BOTH APPROACHES
    // ======================================================================================

    public static void main(String[] args) {
        // Test data
        int[][] testCases = {
                {1, 2, 3, 4, 5}, // Example 1
                {1},             // Example 2
                {1, 2}           // Example 3
        };
        int[] nValues = {2, 1, 1};

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("=== Test Case " + (i + 1) + " ===");
            ListNode head1 = createList(testCases[i]);
            ListNode head2 = createList(testCases[i]); // duplicate for Approach 2

            // Test Approach 1 (Fast-Slow / One-Pass)
            ListNode result1 = removeNthFromEndApproach1(head1, nValues[i]);
            System.out.print("Approach 1 (One-Pass): ");
            printList(result1);

            // Test Approach 2 (Two-Pass)
            ListNode result2 = removeNthFromEndApproach2(head2, nValues[i]);
            System.out.print("Approach 2 (Two-Pass):  ");
            printList(result2);

            System.out.println();
        }

        // ==================================================================================
        // 🏆 WHY APPROACH 1 IS BETTER:
        // ==================================================================================
        /*
         - Both are O(n) time and O(1) space, BUT:
         - Approach 1 makes **one continuous traversal** → better cache performance.
         - Approach 1 never restarts from head → more elegant and satisfies "one-pass" follow-up.
         - Approach 2 requires knowing the full length first → less adaptable in streaming scenarios.
         - In interviews, Approach 1 demonstrates deeper understanding of pointer techniques.
        */
    }
}