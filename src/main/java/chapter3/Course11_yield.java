package chapter3;

/**
 * yield
 * 一、yield作用是什么？
 * A hint to the scheduler that the current thread is willing to yield its current use of a processor. The scheduler is free to ignore this hint.
 * 向调度器发出一个提示，当前线程将会愿意放弃当前对处理器的使用。然而调度器可以忽略此提示。
 *
 * 二、yield会使线程休眠吗？
 * 不会。
 */
public class Course11_yield {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getState().name());
        Thread.yield();
        System.out.println(Thread.currentThread().getState().name());
        System.out.println(1);
    }
}
