package threaddemo;

import java.util.Scanner;

/**
 *线程基础：
 *1、线程的六种状态
 *	NEW、RUNNABLE、BLOCKED、WAITING、WAITINT_TIME、TERMINATED
 *	NEW：在线程还未start之前的状态。
 *	RUNNABLE：可运行状态，也许正在运行，也许正在等待处理器，I/O状态下的线程状态也是此状态。
 *	BLOCKED：线程被monitor锁定，
 *	WAITING：
 *
 *2、线程常用方法
 *	start：启动线程
 *	interrupt：中断线程（该方法只是在线程中设置一个中断标记位，并不会真正中断线程，由本地方法interrupt0设置）
 *	Thread.interrupted：类方法，返回当前线程的中断状态，并且重置中断标记。
 *	isInterrupted：返回线程的中断状态，中断标记不受影响。
 */
public class _1ThreadBasis {

	public static void main(String[] args) throws InterruptedException {
		//使用方法引用的方法传入
		Thread thread = new Thread(_1ThreadBasis::sayHello);
		//New
		System.out.println("befor：" + thread.getState());
		
		//启动线程
		thread.start();
		 
		//在thread线程I/O时，输出的状态还是RUNNABLE
		int i = 0;
		while(thread.getState() != Thread.State.TERMINATED) {
			System.out.println(thread.getState());
			Thread.sleep(1000);
			if(thread.getState() == Thread.State.WAITING && ++i == 3) {
				//如果是wait状态，并且已经休眠10秒，则唤醒
				System.out.println("唤醒thread");
				synchronized(thread) {
					thread.notify();
					int p = 0;
					//空转，延长执行时间，证明唤醒的线程并未立即获得CPU时间片，而是需要等待同步块执行完毕。
					while(p++ <= 300000000L);
					System.out.println("退出唤醒同步块");
				}
			}
		}

//		//中断线程，事实上，只设置了个标记位，没有真正中断
//		thread.interrupt();
//		System.out.println("interrup：" + thread.getState());
	}

	public static void sayHello() {
		Thread currentThread = Thread.currentThread();
		System.out.println("线程状态：" + currentThread.getState());
		int i = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("线程I/O");
		sc.nextLine();
		System.out.println("子线程I/O完成");
		
		//wait，wait后，thread的状态将变为WAIT
		synchronized (currentThread) {
			try {
				currentThread.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("thread的状态：" + currentThread.getState().name()); 
		}
		
		while(i++ < 10000000L) {
			//什么都不做，空转，延长线程时间
		}
	}
	
	private static Object lock = new Object();
	
	private static void run() {
		Thread ct = Thread.currentThread();
		synchronized(lock) {
			System.out.println(ct.getState().name());
			Scanner sc = new Scanner(System.in);
			System.out.printf("线程%s正在I/O：", ct.getName());
			sc.nextLine();
		}
	}
}
