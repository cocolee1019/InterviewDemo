package threaddemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock：JDK1.5后提供API层面的互斥锁。
 * 常用方法：
 * void lock()：请求锁。当前线程请求获得锁。
 * void unlock()：释放锁。当前线程请求释放锁。
 * 
 * 问题1：lock的实现原理是什么？
 * 	  答：当前线程调用lock之后，先使用CAS操作，获取将指定地址的值从0转为1,表示当前线程持有锁。
 * 	     如果修改失败，则尝试获得锁（对应Acquire操作，默认非公平锁NonfairSync），
 * 		 如果获取失败，则加入queue。queue(由AbstractQueuedSynchronizer维护的队列)
 */
public class ReentrantLockDemo {

	static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {
		
		Thread t1 = new Thread(ReentrantLockDemo::doSomethins);
		Thread t2 = new Thread(ReentrantLockDemo::doSomethins);
		
		t1.start();
		t2.start();
	}
	
	
	public static void doSomethins() {
		
		Thread currentThread = Thread.currentThread();
		
		
		try {
			lock.lock();
			System.out.println(currentThread.getName() + "获得锁");
			lock.unlock();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(lock.isLocked()) 
				lock.unlock();
		}
		
	}
}
