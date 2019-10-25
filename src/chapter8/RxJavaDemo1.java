package chapter8;

import io.reactivex.rxjava3.core.Flowable;

/**
 * 响应式编程学习 <br>
 * 开始学习响应式编程时，带着下面几个疑问：<br>
 * 1、响应式编程能为我们带来什么好处？<br>
 * 2、响应式编程的使用场景。
 * <p></p>
 * 本次学习响应式编程的框架是Rxjava，Rxjava是对java vm响应式编程的扩展，该库用一个可观察的序列
 * 来组成<b>异步</b>和<b>基于事件</b>的程序。
 * 它扩展了 观察者模式 以支持数据/事件序列，并且添加了允许以声明方式将序列组合在一起的运算符，同时
 * 消除了如低级线程、同步、线程安全和并发数据结构之类问题的担忧。
 * <p>
 *
 * RxJavaDemo1为入门demo的实现。主要是用于对RxJava3的用法有一个初步了解。
 *
 * 术语：
 * upstream： 指中间操作，表示还能再产生流的操作，upstream往右看是上游，往左看是下游。
 * downstream： 指终端操作，是upstream的消费者。
 * backpressure：背压，
 */
public class RxJavaDemo1 {

    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);
    }
}
