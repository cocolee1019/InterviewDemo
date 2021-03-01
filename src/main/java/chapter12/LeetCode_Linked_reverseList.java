package chapter12;

/**
 * 反转一个单链表。
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class LeetCode_Linked_reverseList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1,new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        System.out.println(reverseList(head));
    }

    /**
     * 递归反转
     * @param node
     * @return
     */
    public static ListNode reverseList(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        ListNode head = reverseList(node.next);
        node.next.next = node;
        node.next = null;
        return head;
    }
}
