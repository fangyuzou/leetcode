/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)  return head;
        /* each time we take the first ring of the chain and hock it up to the new chain */
        ListNode prev = null;
        while (head.next != null) {
            ListNode curr = head;
            head = head.next;
            curr.next = prev;
            prev = curr;
        }
        head.next = prev;
        return head;
    }
}
