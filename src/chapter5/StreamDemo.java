package chapter5;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 流式编程
 * <p>
 * Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
 * <p>
 * 这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
 * <p>
 * 常用方法说明：
 * 暂时分为两类：一类是返回stream(中间操作)， 一为是返回值（终端操作）。
 * 第一类有limit、filter、map，这些方法返回stream，用于流式编程。
 * 第二类collect，可用于返回Map，List、Collect等。
 * <p>
 * parallelStream为并行流。
 * 在使用并行流进行Collect操作时，请记住一定要用线程安全容器，否则数据不准确。
 */
public class StreamDemo {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6};
        Arrays.stream(arr).map(i -> i * i).collect(Collectors.toList()).forEach(System.out::println);


    }
}
