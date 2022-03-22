package chapter12;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @author luwu
 * @date 2022/3/22 09:49
 */
public class LeetCode_addTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = 9;
        ListNode l2 = new ListNode();
        l2.val = 9;
        ListNode l5 = new ListNode();
        l5.val = 1;
        l2.next = l5;
        l1.next = l2;

        ListNode l3 = new ListNode();
        l3.val = 1;
        ListNode l4 = new ListNode();
        l4.val = 1;
        l4.next = null;
        l3.next = null;

        ListNode listNode = addTwoNumbers(l1, l3);
        System.out.println(listNode);
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, p = null;
        boolean j = false;
        while (l1 != null && l2 != null) {
            int val = l1.val + l2.val;
            if (j) {
                val++;
                j = false;
            }
            if (val >= 10) {
                j = true;
                val -= 10;
            }

            if (head == null) {
                head = new ListNode();
                head.val = val;
                head.next = null;
                p = head;
            } else {
              ListNode node = new ListNode();
              node.val = val;
              p.next = node;
              p = node;
              p.next = null;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int val = l1.val;
            if (j) {
                val++;
                j = false;
            }
            if (val >= 10) {
                j = true;
                val -= 10;
            }
            if (head == null) {
                head = new ListNode();
                head.val = val;
                head.next = null;
                p = head;
            } else {
                ListNode node = new ListNode();
                node.val = val;
                p.next = node;
                p = node;
                p.next = null;
            }
            l1 = l1.next;
        }

        while (l2 != null) {
            int val = l2.val;
            if (j) {
                val++;
                j = false;
            }
            if (val >= 10) {
                j = true;
                val -= 10;
            }
            if (head == null) {
                head = new ListNode();
                head.val = val;
                head.next = null;
                p = head;
            } else {
                ListNode node = new ListNode();
                node.val = val;
                p.next = node;
                p = node;
                p.next = null;
            }
            l2 = l2.next;
        }

        if (j) {
            if (head == null) {
                head = new ListNode();
                head.val = 1;
                head.next = null;
                p = head;
            } else {
                ListNode node = new ListNode();
                node.val = 1;
                p.next = node;
                p = node;
                p.next = null;
            }
        }

        return head;
    }
}
