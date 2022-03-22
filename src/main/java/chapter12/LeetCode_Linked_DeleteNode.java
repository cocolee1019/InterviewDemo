package chapter12;

import java.util.Objects;

public class LeetCode_Linked_DeleteNode {

    public static void main(String[] args) {
    }

    /**
     * 这个题目没弄懂
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {}
    ListNode(int val) { this.val = val; }

    ListNode(int x, ListNode node) {
        val = x;
        this.next = node;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return val + (next != null ? "->" + next.toString() : "");
    }
}