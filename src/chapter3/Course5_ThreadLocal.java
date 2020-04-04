package chapter3;

import jdk.internal.org.objectweb.asm.tree.FrameNode;

import java.util.Random;

/**
 * 2020-04-01
 * ThreadLocal学习
 *
 * 1、使用场景。
 *  文档：Creates a thread local variable.(创建一个本地变量)
 *  在可能存在并发的代码片断中，设置“当前线程”私有值，在线程运行的过程中，可随时读取出来。
 *
 *  如果是方法私有变量，那么本身其 变量本身就是线程安全，所以ThreadLocal肯定是作为全局可用的。
 *  try it.
 */
public class Course5_ThreadLocal {

    public static void main(String[] args) {
        ThreadLocal<String> str = new ThreadLocal<>();
        str.set("aaaaaaaaaaaa");
        System.out.println(str.get());

        int i = 1;
        while(i++<10){
            new Thread(Course5_ThreadLocal::doIt).start();
        }
    }

    private static ThreadLocal<Integer> local = new ThreadLocal<>();
    private static Random random = new Random();

    private static void doIt() {
        int r = random.nextInt();
        System.out.println("设置值" + Thread.currentThread().getName() + " :" + r);
        local.set(r);
        System.out.println("输出值" + Thread.currentThread().getName() + " :" + local.get());
    }
}
