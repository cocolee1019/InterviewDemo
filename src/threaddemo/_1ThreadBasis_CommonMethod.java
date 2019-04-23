package threaddemo;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 线程基础之常用方法：
 *	start：启动线程
 *	interrupt：中断线程（该方法只是在线程中设置一个中断标记位，并不会真正中断线程，由本地方法interrupt0设置）
 *	Thread.interrupted：类方法，返回当前线程的中断状态，并且重置中断标记。
 *	isInterrupted：返回线程的中断状态，中断标记不受影响。
 *	Object.wait：使拥有当前对象的monitor的线程进行WAITING状态，并且释放当前锁。需要在同步代码块或同步代码方法中使用。
 *	Object.notify：随机唤醒调用Object.wait方法的线程。需要在同步代码块中使用。
 *	Object.notifyAll：唤醒所有调用Object.wait方法的线程。需要在同步代码块中使用。
 *	Thread.sleep：使线程睡眠。
 *	Thread.suspend：阻塞一个线程，并且不会释放锁。
 *	Thread.resume：使一个被suspend的线程恢复。
 *	Thread.stop：停止当前线程，原理是通过抛出ThreadDeath异常，使线程中止。
 *
 *
 *	问题：
 *	1、调用Object.wait后，会释放当前monitor的锁吗？
 *	答：会释放Object的monitor，即表示其它线程的同步块使用了相同的Object为锁时，
 *		可以进入了。当其它线程调用Object.notify后，且其它线程已退出同步代码块或同步代码方法后，
 *		monitor所在线程状态会重新抢占CPU时间。
 *
 *	2、Object.wait(timeout)会释放当前锁？
 *	答：会释放Object的monitor。同1问一样。
 *
 *	3、调用Thread.sleep()后，线程的状态是什么？如果在同步块中调用，是否会释放锁？
 *	答：调用Thread.sleep后，线程状态为TIMED_WAITING。在同步块中调用sleep方法，不会释放锁。
 *
 *	4、为什么Thread.stop、Thread.suspend和Thread.resume会过期？
 *	答：suspend不会释放monitor锁，容易造成线程死锁的状况。例如a线程锁定Object后，被suspend，b线程要resume a线程
 *		但是在resume之前，又要请求Object的锁，此时便会造成死锁。stop会在线程任何位置抛出异常，使线程中止。这便会出现
 *		很多问题，比如在线程finally中释放资源时抛出ThreadDeath异常，会导致资源释放失败，比如在synchronized中抛出异常
 *		会使锁提前被释放，而这种释放是不可控的。
 */
public class _1ThreadBasis_CommonMethod {

	public static void main(String[] args) throws InterruptedException {
//		main1();
//		main2();
//		main3();
		main4();
	}
	
	private static Object lock = new Object();
	
	/**
	 * 重现stop抛出的ThreadDeath异常
	 */
	private static void main4() throws InterruptedException {
		Thread t1 = new Thread(()->{
			try {
				synchronized (lock) {
						System.out.println("线程1开始休眠3秒");
						TimeUnit.SECONDS.sleep(3);
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		});
		
		Thread t2 = new Thread(()->{
			synchronized (lock) {
				System.out.println("线程2获得锁");
			}
			System.out.println("线程2退出");
		});
		
		t1.start();
		TimeUnit.SECONDS.sleep(1);
		t1.stop();
		t2.start();
		int i = 0;
		while(i++ <= 100000000) {
			TimeUnit.SECONDS.sleep(1);
			System.out.println("主线程工作");
		}
	}
	
	//suspend和resume
	private static void main3() {
		Thread thread = new Thread(_1ThreadBasis_CommonMethod::sayHello3);
		thread.start();
		int i = 0;
		while(thread.getState() != Thread.State.TERMINATED) {
			System.out.println("线程的状态：" + thread.getState().name());
			if(thread.getState() == Thread.State.RUNNABLE && i++ == 1) {
//				System.out.println("阻塞线程");
//				thread.suspend();
				System.out.println("停止线程");
				thread.stop();
			}
			int k = 0;
			while(k++ <= 1900000000L) ;
		}
	
	}
	
	//解答第2问
	private static void main2() {
		Thread t1 = new Thread(_1ThreadBasis_CommonMethod::sayHello2);
		Thread t2 = new Thread(_1ThreadBasis_CommonMethod::sayHello2);
		t1.start();
		t2.start();
		while(t1.getState() != Thread.State.TERMINATED) {
			
			int k = 0;
			while(k++ <= 1000000000L) ;
			
			System.out.printf("线程%s的状态%s\n", t1.getName(), t1.getState().name());
			System.out.printf("线程%s的状态%s\n", t2.getName(), t2.getState().name());
		}
	}
	
	//main1，解答第1问
	private static void main1() {
		Thread t1 = new Thread(_1ThreadBasis_CommonMethod::sayHello);
		Thread t2 = new Thread(_1ThreadBasis_CommonMethod::sayHello);
		t1.start();
		t2.start();
		int i = 0, j = 0;
		while(t1.getState() != Thread.State.TERMINATED || t2.getState() != Thread.State.TERMINATED) {
			
			int k = 0;
			while(k++ <= 1000000000L) ;
			
			System.out.printf("线程%s的状态%s\n", t1.getName(), t1.getState().name());
			System.out.printf("线程%s的状态%s\n", t2.getName(), t2.getState().name());
//			if(t1.getState() == Thread.State.WAITING && i++ == 3) {
//				//阻塞三秒后解锁
//				synchronized(lock) {
//					//随机唤醒一个线程，可能会造成错误。
//					lock.notify();
//				}
//			}
//			
//			if(t2.getState() == Thread.State.WAITING && j++ == 5) {
//				synchronized(lock) {
//					lock.notify();
//				}
//			}
		}
	}
	
	/**
	 * 验证wait
	 */
	private static void sayHello() {
		Thread currentThread = Thread.currentThread();
		synchronized(lock) {
			try {
				System.out.println("使线程等待：" + currentThread.getName());
				lock.wait(5000);
				System.out.println(currentThread.getName() + "被唤醒，状态为：" + currentThread.getState().name());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("hello");
		System.out.println(currentThread.getName() + "结束～");
	}
	
	private static byte[] lockB = new byte[1];
	/**
	 * 验证sleep时的状态
	 */
	private static void sayHello2() {
		try {
			synchronized(lockB) {
				System.out.println(Thread.currentThread().getName() + "进入");
				Thread.sleep(5000);
				System.out.println(Thread.currentThread().getName() + "结束");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 调用suspend和resume
	 */
	public static void sayHello3() {
		System.out.println("开始工作");
		int i = 0;
		while(i++ <= 10000000000000L) {
			System.out.println("正在工作");
			int k = 0;
			while(k++ <= 1900000000L) ;
		}
		System.out.println("工作完成");
		System.out.println("hello~");
	}
}
