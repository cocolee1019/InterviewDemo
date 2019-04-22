package threaddemo;

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
 *
 *	问题：
 *	1、调用Object.wait后，会释放当前monitor的锁吗？
 *	答：会释放Object的monitor，即表示其它线程的同步块使用了相同的Object为锁时，
 *		可以进入了。当其它线程调用Object.notify后，且其它线程已退出同步代码块或同步代码方法后，
 *		monitor所在线程状态会重新抢占CPU时间。
 *
 *	2、调用Thread.sleep()后，线程的状态是什么？如果在同步块中调用，是否会释放锁？
 *	答：调用Thread.sleep后，线程状态为TIMED_WAITING。在同步块中调用sleep方法，不会释放锁。
 */
public class _1ThreadBasis_CommonMethod {

	public static void main(String[] args) throws InterruptedException {
		//main1();
		main2();
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
			if(t1.getState() == Thread.State.WAITING && i++ == 3) {
				//阻塞三秒后解锁
				synchronized(lock) {
					//随机唤醒一个线程，可能会造成错误。
					lock.notify();
				}
			}
			
			if(t2.getState() == Thread.State.WAITING && j++ == 5) {
				synchronized(lock) {
					lock.notify();
				}
			}
		}
	}
	
	private static Object lock = new Object();
	/**
	 * 验证wait
	 */
	private static void sayHello() {
		Thread currentThread = Thread.currentThread();
		synchronized(lock) {
			try {
				System.out.println("使线程等待：" + currentThread.getName());
				lock.wait();
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
		//无锁
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
}
