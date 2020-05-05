package chapter6;

/**
 * @author ljj
 * 2019/9/5 0005 13:49
 *
 * 一、关键字说明：
 *    synchronized，同步锁，java中的关键字，是利用monitor锁的机制来实现同步。
 *    被锁住的数据，拥有两个特性：
 *         1、互斥性：即在同一时间只允许一个线程持有某个对象锁，
 *          通过这种特性来实现多线程中的协调机制，这样在同一时
 *          间只有一个线程对需同步的代码块(复合操作)进行访问。
 *          互斥性我们也往往称为操作的原子性。
 *
 *         2、可见性：必须确保在锁被释放之前，对共享变量所做的
 *           修改，对于随后获得该锁的另一个线程是可见的（即在
 *           获得锁时应获得最新共享变量的值），否则另一个线程
 *           可能是在本地缓存的某个副本上继续操作从而引起不一致。
 *
 *  二、关键字修饰对象说明：
 *      1、代码块：
 *          synchronized(this|object) {
 *            .....
 *          }
 *         如果是this，则使用当前对象的monitor锁。
 *         如果是object，则使用object对象的monitor锁。
 *
 *      2、方法
 *          使用当前对象的monitor锁。
 *
 *      3、静态方法
 *          使用类对象的monitor锁。
 *
 *  三、问题：
 *      1、类A有两个带synchronized关键字的静态方法m1和m2，请问m1被调用期间m2能否进入方法体？
 *      答：不能，synchronized关键字修饰静态方法，使用的是Class的monitor锁，此时m1已占用Class的monitor锁，
 *          所以导致m2无法进入。
 *
 *      2、当类monitor被占用时，此类的对象的monitor锁是否还能进入。
 *      答：能使用。对象与类之间的锁互相独立。
 */
public class Course5_Synchronized {

    public static void main(String[] args) {

        //***************证明问题1  start***************
        //System.out.println("主线程开始");
        //new Thread(() -> A.m1()).start();
        //new Thread(() -> A.m2()).start();
        //System.out.println("主线程结束");
        //***************证明问题1  end***************


        //***************证明问题2  start***************
        System.out.println("主线程开始");
        A a = new A();
        new Thread(A::m1).start();
        new Thread(a::m3).start();
        System.out.println("主线程结束");
        //***************证明问题  end***************
    }

}


/**
 * 问题1的证明
 * main线程同时调用m1和m2，进入m1后，sleep不释放锁，所以m2会一直等待m1同步块的结束。
 * 假如m2不加synchronized关键字，则会立即进入m2，由此可以证明。
 */
class A {

    static synchronized void m1() {
        //模拟此方法使用了3s时间
        try {
            System.out.println("进入m1~");
            Thread.sleep(3000);
            System.out.println("离开m1~");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static synchronized void m2() {
        //模拟此方法使用了2s时间
        try {
            System.out.println("进入m2~");
            Thread.sleep(2000);
            System.out.println("离开m2~");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * m3的synchronized使用的是对象锁
     */
    synchronized void m3() {
        //模拟此方法使用了1s时间
        try {
            System.out.println("进入m3~");
            Thread.sleep(1000);
            System.out.println("离开m3~");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}