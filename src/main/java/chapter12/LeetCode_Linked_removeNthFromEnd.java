package chapter12;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * 解题要点：双指针，快慢指针
 */
public class LeetCode_Linked_removeNthFromEnd {

    public static void main(String[] args) {
        ListNode head = new ListNode(1,new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        System.out.println(removeNthFromEnd(head, 1));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode nHead = new ListNode();
        nHead.next = head;

        ListNode t = nHead;
        ListNode p = nHead;
        int step = -n;
        while(t.next != null) {
            t = t.next;
            if (++step > 0) {
                p = p.next;
            }
        }
        if (p.next != null) {
            p.next = p.next.next;
        }

        return nHead.next;


    }
}
