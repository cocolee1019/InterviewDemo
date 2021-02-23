package chapter3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic相关类解析
 *
 * 一、AtomicInteger解析
 * 1、常见方法:
 * get()、set()、getAndSet()、compareAndSet()、getAndIncrement()、getAndDecrement()
 * 内部数据结构使用一个int原子类型的数据存储，get、set方法比较简单，直接返回该int值
 * getAndSet等其它方法使用UnSafe类的CAS操作，进行原子性修改。
 */
public class Course9_Atomic {

    AtomicInteger atomicInteger = new AtomicInteger();

}
