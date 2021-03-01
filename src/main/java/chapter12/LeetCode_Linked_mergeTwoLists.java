package chapter12;

/**
 * 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 *
 * 输入：l1 = [], l2 = [0]
        * 输出：[0]
        */
public class LeetCode_Linked_mergeTwoLists {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7,new ListNode(8, new ListNode(9, new ListNode(19, new ListNode(20, null)))));
        ListNode l2 = new ListNode(1,new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(4, null)))));
        System.out.println(mergeTwoLists(l1, l2));
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2;
        ListNode head = new ListNode();
        ListNode t = head;
        while (p1 != null || p2 != null) {
            if (p1 == null) {
                t.next = p2;
                break;
            }
            if (p2 == null) {
                t.next = p1;
                break;
            }

            while (p2 != null && p1 != null && p1.val <= p2.val) {
                t.next = p1;
                t = p1;
                p1 = p1.next;
            }
            while (p2 != null && p1 != null && p1.val > p2.val) {
                t.next = p2;
                t = p2;
                p2 = p2.next;
            }
        }

        return head.next;
    }
}
