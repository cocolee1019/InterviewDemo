package chapter9;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author luwu
 * @date 2022/2/10 16:22
 */
public class Course8_SpeedPriorityBlockingQueue {

    public static void main(String[] args) {

        Random random = new Random();
        SpeedPriorityBlockingQueue<Integer> queue = new SpeedPriorityBlockingQueue(1L, 1L); //1秒1个
        for (int i = 0; i < 100; i++) {
            queue.offer(random.nextInt(10000));
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(queue.poll());
        }
    }
}


class SpeedPriorityBlockingQueue<E> extends PriorityBlockingQueue<E> {

    /**
     * 时间(默认：秒)
     */
    private Long time;

    /**
     * 速度
     */
    private Long speed;

    private long initTime = 0L;

    private long initSpeed = 0L;



    public SpeedPriorityBlockingQueue(Long time, Long speed) {
        this.time = time;
        this.speed = speed;
    }

    @Override
    public E poll() {
        long currentTimeMillis = System.currentTimeMillis();
        long targetTime = initTime + time * 1000;
        if (targetTime < currentTimeMillis) {
            initSpeed = 0L;
            initTime = currentTimeMillis;
        }

        if (initSpeed >= speed) {
            try {
                Thread.sleep(targetTime - currentTimeMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return poll();
        }
        initSpeed ++;
        return super.poll();
    }
}
