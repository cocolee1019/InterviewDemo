package threaddemo;

import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport类的使用
 *
 */
public class _1ThreadBasis_LockSupport {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(_1ThreadBasis_LockSupport::sayHello);
		thread.start();
		int i = 0;
		while(thread.getState() != Thread.State.TERMINATED) {
			System.out.println("当前线程的状态：" + thread.getState().name());
			Thread.sleep(1000);
			if(thread.getState() == Thread.State.WAITING && i++ == 5) { 
				System.out.println("为线程解锁" + thread.getName());
				
				LockSupport.unpark(thread);
			}
		}
	}
	
	public static void sayHello() {
		Thread currentThread = Thread.currentThread();
		System.out.println("线程状态：" + currentThread.getState());
		System.out.println("锁定线程：" + currentThread.getName());
		LockSupport.park();
		System.out.println("hello");
	}
}
