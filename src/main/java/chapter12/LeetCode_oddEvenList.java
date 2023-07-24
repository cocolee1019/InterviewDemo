package chapter12;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定单链表的头节点  head  ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
 *
 * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为  偶数 ，以此类推。
 *
 * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 *
 * 你必须在  O(1)  的额外空间复杂度和  O(n)  的时间复杂度下解决这个问题。
 *
 *  @author luwu
 * @date 2022/3/23 10:55
 */
public class LeetCode_oddEvenList {



    public static ListNode oddEvenList(ListNode head) {
        ListNode p, np, tail = head.next;
        while ((p = head) != null) {
            np = p.next;
            if (np != null) {
                p.next = np.next;
            }
        }
return null;
    }

    public static void main(String[] args) {
        List<List<Long>> list = new ArrayList<>();
        ArrayList<Long> list1 = new ArrayList<>();
        ArrayList<Long> list2 = new ArrayList<>();
        ArrayList<Long> list3 = new ArrayList<>();
        list1.add(1L);
        list1.add(2L);
        list1.add(3L);

        list2.add(2L);
        list2.add(3L);
        list2.add(4L);

        list3.add(1L);
        list3.add(3L);

        list.add(list1);
        list.add(list2);

        List<Long> already = new ArrayList<>();
        for (List<Long> longs : list) {
            longs.removeAll(already);
            if (longs.isEmpty()) {
                continue;
            }
            System.out.println(longs);
            already.addAll(longs);
        }

        System.out.println(already);

    }
}
