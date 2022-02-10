package chapter9;

import org.junit.jupiter.api.Test;

import java.util.TreeSet;

/**
 * @author luwu
 * @date 2022/2/9 14:41
 *
 * 有序Set
 *
 * 一、TreeSet低层数据结构是什么？
 * 在创建TreeSet时，会同时创建TreeMap。
 *
 * 二、TreeSet使用什么机制保证数据有序？
 * TreeMap可通过TreeSet构造方法设置comparator，若comparator为空，则object必须实现Comparable接口。
 *
 * 三、TreeSet是否可以存储null值？
 * 不可以
 */
public class Course6_TreeSet {

    @Test
    public void test() {
        TreeSet<TreeSetKey> integers = new TreeSet();

        integers.add(new TreeSetKey(18, "hhhh"));
        integers.add(new TreeSetKey(19, "hhxxhh"));
        integers.add(new TreeSetKey(11, "hhhhxx22"));

        System.out.println(integers);
    }

    static class TreeSetKey implements Comparable<TreeSetKey>{
        Integer age;
        String name;

        public TreeSetKey(Integer age, String name) {
            this.age = age;
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(TreeSetKey o) {
            return this.getAge() - o.getAge();
        }

        @Override
        public String toString() {
            return "TreeSetKey{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
