package threaddemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock：JDK1.5后提供API层面的互斥锁。
 * 常用方法：
 * void lock()：请求锁。当前线程请求获得锁。
 * void unlock()：释放锁。当前线程请求释放锁。
 * 
 * 问题1：lock的实现原理是什么？
 * 	  答：在”不公平锁“中，当前线程调用lock之后，先使用CAS操作，获取将指定地址的值从0转为1,表示当前线程持有锁。
 * 	     如果修改失败，则尝试获得锁（对应Acquire操作，默认非公平锁NonfairSync），
 * 		 如果获取失败，则加入queue(由AbstractQueuedSynchronizer维护的队列),
 * 		 加入队列后，如果Node.prev为head，则判断prev的状态是否为SIGNAL，如果为SIGNAL则在一轮循环后，
 * 		 park当前线程。
 * 
 * 问题2：在”不公平锁“中，queue中的Node，何时会被唤醒？
 * 答：当前线程调用unlock后，会将head的waitStatus设置为0（初始态），然后unpark head.next这个结点，
 * 如果head.next是空，或者waitStatus>0，则从tail开始唤醒。
 * 
 * 问题3：在“不公平锁“中，抢占成功的线程是否会建立Node结点？
 * 答：如果第一、二次便已抢占成功，则不会建立Node结点，在释放时，会判断head的waitStatus信号值，如果被设置成了
 *    小于0的值，则表示队列还有结点在等待，需要唤醒队列中的结点。
 *    如果当前线程进入队列，便需要排队了。
 * 
 * 
 * 大神关于ReentranLock的讲解：https://blog.csdn.net/lsgqjh/article/details/63685058
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
			Thread.sleep(500000);
			lock.unlock();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(lock.isLocked()) 
				lock.unlock();
		}
		
	}
}
