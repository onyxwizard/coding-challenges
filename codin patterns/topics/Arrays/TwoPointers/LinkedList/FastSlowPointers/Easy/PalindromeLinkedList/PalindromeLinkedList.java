package LinkedList.FastSlowPointers.Easy.PalindromeLinkedList;

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

public class PalindromeLinkedList {

    /**
     * Checks whether a singly linked list is a palindrome using O(1) extra space.
     *
     * Strategy:
     * 1. Use the fast-slow pointer technique to find the middle of the list.
     * 2. Reverse the second half of the list.
     * 3. Compare the first half with the reversed second half node by node.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head The head of the linked list
     * @return true if the linked list is a palindrome, false otherwise
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Step 1: Find the middle using fast and slow pointers
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half starting from 'slow'
        ListNode rev = reverse(slow);

        // Step 3: Compare first half and reversed second half
        ListNode top = head;
        while (rev != null) {
            if (rev.val != top.val) {
                return false;
            }
            rev = rev.next;
            top = top.next;
        }

        return true;
    }

    /**
     * Reverses a linked list iteratively.
     *
     * @param head Head of the list to reverse
     * @return New head of the reversed list
     */
    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nxt = current.next;
            current.next = prev;
            prev = current;
            current = nxt;
        }
        return prev;
    }

    /**
     * Helper method to create a linked list from an array of values.
     * Makes testing cleaner and more readable.
     */
    public static ListNode createList(int[] values) {
        if (values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        // Test Case 1: [1,2,2,1] → Palindrome
        ListNode list1 = createList(new int[]{1, 2, 2, 1});
        System.out.println("Test 1 - [1,2,2,1]: " + isPalindrome(list1)); // Expected: true

        // Test Case 2: [1,2] → Not a palindrome
        ListNode list2 = createList(new int[]{1, 2});
        System.out.println("Test 2 - [1,2]: " + isPalindrome(list2));       // Expected: false

        // Test Case 3: [1,2,3,2,1] → Palindrome (odd length)
        ListNode list3 = createList(new int[]{1, 2, 3, 2, 1});
        System.out.println("Test 3 - [1,2,3,2,1]: " + isPalindrome(list3)); // Expected: true

        // Test Case 4: [1] → Single node (palindrome)
        ListNode list4 = createList(new int[]{1});
        System.out.println("Test 4 - [1]: " + isPalindrome(list4));         // Expected: true

        // Test Case 5: [1,2,3,4] → Not a palindrome
        ListNode list5 = createList(new int[]{1, 2, 3, 4});
        System.out.println("Test 5 - [1,2,3,4]: " + isPalindrome(list5));   // Expected: false
    }
}