package chapter9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 阿里面试题：
 * 一、ArrayList和LinkedList的差别是什么？
 * 1、ArrayList底层数据结构是Object数组，LinkedList底层结构是Node结点双向链表。
 * 2、应用场景不同：ArrayList适用于顺序删写、随机读改操作，LinkedList适用于随机删写操作。
 *    LinkedList实现了Deque(double ended queue)接口，可当作FIFO队列或LIFO栈使用。
 *
 *二、链表既然查询性能不足，在保持链表的基本特殊上，有没有方法可以改造链表的查询速度。
 *答：使用分段思想，持有中间N个节点，通过算法快速定位index位置在N个节点中，哪个最近。
 *标准答案：跳表
 * 平均查找与插入时间复杂度均为O(log n)
 *
 * 三、快排、堆排和归并都是O(nlog n)的时间复杂度，为何Arrays.sort()使用的是快速排序法？
 * 答：1、快排是原址排序  2、快排O(nlog n)隐含的常数因子最小   3、Arrays.sort是改进后的快排：双轴快排序法。
 *
 * 扩展知识：ArrayList扩容机制是什么？
 * 1、触发时机：当前size + 1 > objects.length
 * 2、扩容长度：objects.length += objects.length >> 2
 * 3、使用Arrays.copy()复制到新数组中
 *
 * 三、ArrayList相比于LinkedList少了或多了哪些方法?
 * 1、多了一些操作链表的方法：linkFirst【使成为头结点】、linkBefore【使在某个结点之后新增】、addFirst【在头部添加】、addLast【尾部添加】
 *
 * 四、遇见的面试题：
 * 1、ArrayList和LinkedList都在尾部添加元素，你认为哪个速度更快？
 * 答：list.add()方法，默认行为就是在尾部添加，对于ArrayList的add行为，可以分为2部分，一是判断容量是否足够。二是在size位置赋值。
 * 对于LinkedList.add()方法，直接移动last结点指向即可。所以我认为是LinkedList会更快。
 */
public class ArrayListAndLindedListDemo {

    public static void main(String[] args) {
        ArrayList<String> arrays = new ArrayList<>();
        List<String> list = new LinkedList<>();
        list.remove(11);

        arrays.add("1");
        arrays.remove("");

        //快速排序
        Arrays.sort(new int[]{1,2,34});

    }
}
