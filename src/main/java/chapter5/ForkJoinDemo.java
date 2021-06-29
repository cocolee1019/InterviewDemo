package chapter5;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 多线程任务执行框架
 *
 * 前置知识：
 * ForkJoinTask：具体执行的任务
 *    常用两个实现类
 *        -- RecursiveTask 有返回值
 *        -- RecursiveAction 无返回值
 *    常用方法：
 *         compute: 计算
 */
public class ForkJoinDemo {

    static class MyTask extends RecursiveTask<Integer> {

        int data[];

        public MyTask(int[] data) {
            this.data = data;
        }

        @Override
        protected Integer compute() {
            //1、拆分
            if (data.length > 2) {
                MyTask left = new MyTask(Arrays.copyOfRange(data, 0, data.length / 2));
                MyTask right = new MyTask(Arrays.copyOfRange(data, data.length/2, data.length));
                invokeAll(left, right);
                Integer lv = left.join();
                Integer rv = right.join();
                return lv > rv ? lv : rv;
            }

            //2、输出结果
            if (data.length == 1) {
                return data[0];
            }

            return data[0] > data[1] ? data[0] : data[1];
        }
    }

    public static void main(String[] args) {
        int[] arrr = {1, 9 ,10 ,2 ,4, 55, 80};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(new MyTask(arrr)));
    }
}
