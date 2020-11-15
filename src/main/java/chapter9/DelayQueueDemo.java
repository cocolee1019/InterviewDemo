package chapter9;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列的使用
 * <p>
 * 1、为什么要使用延时队列？
 * <p>
 * 2、实现原理如何？
 *
 * 3、假如在等待过程中，有立即过期的数据加入，效果如何？
 */
public class DelayQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        DelayQueue<DelayedElement> queue = new DelayQueue<>();

        //一分钟后输出
//        queue.add(new DelayedElement(1));
        //两分钟后输出
//        queue.add(new DelayedElement(2));

        /**
         * 阻塞方法
         * take方法说明：
         * 获取并且移除队列中的头元素。如果“延迟元素”还未过期，则等待，直至可用。如果队列为空，
         * 也等待，直至获取到delay元素。
         */
        System.out.println(queue.take());

        /**
         * 非阻塞方法
         * 返回队列中的头元素，但不将元素从队列中移除。该方法无论元素是否过期，均返回。
         */
        //System.out.println(queue.peek());

        /**
         * 非阻塞方法。
         * 返回队列已过期的头元素，并且从队列中移除。如果队列为空或者元素未过期，则返回null。
         */
        System.out.println(queue.poll());
//
//        System.out.println(queue.take());
//
    }

}


class DelayedElement implements Delayed {

    private long mills;

    public DelayedElement(Integer min) {
        this.mills = System.currentTimeMillis() / 1000 + (60 * min);
    }

    /**
     * 当方法返回小于或等于0的数值时， 元素将会被消耗
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return mills - (System.currentTimeMillis() / 1000);
    }

    /**
     * DelayQueue底层是优先队列，需要实现compareTo方法，
     * 用于在入列的时候与容器中的数据进行比较，
     * 以便确定元素位置。
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        return (int) (mills - ((DelayedElement) o).getMills());
    }

    public long getMills() {
        return mills;
    }

    public void setMills(long mills) {
        this.mills = mills;
    }

    @Override
    public String toString() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + "   ->  DelayedElement{" +
                "mills=" + mills +
                '}';
    }
}
