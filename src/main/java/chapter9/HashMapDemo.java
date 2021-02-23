package chapter9;

import java.util.HashMap;

/**
 * hashMap源码笔记
 * 1、HashMap的底层数据结构是什么？
 *  Node结点数组：Node<K,V>[] table， 这个数组是延迟初始化的。
 *  Node链表
 *
 * 2、resize方法是什么逻辑？
 * 用于初始化table或者扩容，每次扩容的长度都是原长度的2倍，初始化是16（1<4）
 * 如果是初始化，则直接返回，如果是扩容，旧数组中的元素则需要迁移重新hash到新的数组。
 *
 * 3、当Node数组占的下标不为null时，HashMap是如何解决冲突的？
 * 当key的hash所对应的下标有值时，表示hash冲突了，HashMap有以下步骤解决冲突：
 *      3.1、判断是否为同一个key，如果为同一个key,则表示需要进行值替换，
 *           先用引用判断是否为同一个对象，再用equals判断。如果为同一个key，
 *           则只是将node里面的value替换，并将old value返回。
 *
 *      3.2、如果不是为同一个key，则表示是key的hash冲突了。
 *           在HashMap中，会用两种方式来解决hash冲突，一是链表，二是红黑树。
 *           代码先判断当前数组下的结点是否为树，如果是，则直接调用putTreeVal，加入树中，成为其结点。
 *           如果不是树，则会遍历整个链表，直到尾结点，最后将构造一个新node，加入链表，
 *           在这个过程中，可能会找到与其key相同的node，所以会提前中断。
 *           当数量大于等于8时，会将链表转为树。
 *
 *  4、就如3.2所说，在遍历链表时，可能会出现key相同的node，那么树是如何处理这种情况的呢？
 *     同链表一样，当key的引用值或key!=null && key.equals时就会将当前结点返回。
 *
 *  5、null值可以为HashMap的key吗？
 *     可以，当null作为key时，null的hash值为0。
 *
 *  6、resize链表结构在重构时是如何重新对旧元素定位的？
 *      假如Node的next为null，则重新用key的hash定位，如果Node的next不为null，则需要借助oldCap判断新的位置是否需要加一倍。
 *
 *  7、在处理hash冲突时，node结点是如何插入链表中的？
 *     红黑树结点插入方式：putTreeVal
 *     链表插入方式：jdk版本 >= 8.0时，是尾插法。 jdk 7是头插法（新结点放在头部）
 *
 */
public class HashMapDemo {

    public static void main(String[] args) {

        HashMap map = new HashMap(8);
        map.put("1", "");
        map.put("2", "");
        map.put("3", "");
        map.put("4", "");
        map.put("5", "");
        map.put("6", "");
        map.put("7", "");
        map.put("8", "");
        map.put("9", "");
        map.put("10", "");
        map.put("11", "");
        map.put("12", "");
        map.put("13", "");
        map.put("14", "");
        map.put("15", "");

        map.get("1");
        map.put("1", 2);
//        for (int i=11; i<1000; i++) {
//            map.put(i + "", "");
//        }
    }
}
