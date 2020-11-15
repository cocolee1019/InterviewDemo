package chapter2;

import java.nio.ByteBuffer;

/**
 * Buffer(缓冲区)，在java Nio中负责数据的存取，缓冲区就是数组。用于存储不同的数据。
 *
 * Buffer的使用。
 * 从1.4之后，Java提供了7类Buffer，分别是：
 * ByteBuffer、ShortBuffer、IntegerBuffer、LongBuffer、FloatBuffer、DoubleBuffer、CharBuffer
 *
 * Buffer的四个核心概念：
 * 1、capacity: 容量，表示缓冲区的大小，一旦指定，不允许改变。
 * 2、limit：容量，表示缓冲区中可以操作数据的大小。
 * 3、position：位置，表示缓冲区中正在操作数据的位置。
 * 4、mark：标记位置，用于记录position修改之前的值。
 *
 * 直接缓冲区与非直接缓冲区：
 * 非直接缓冲区：通过allocate方法分配，将缓冲区建立在JVM的内存中。
 * 直接缓冲区：通过allocateDirect()方法直接分配缓冲区，将缓冲区建立在物理内存中。
 *
 * 问题1：Buffer的作用是什么?
 * NIO有三大概念：Buffer、Channel、Selector。
 * Buffer是在Channel中传输的数据的来源或目标，换句话说就是Buffer中的数据，将会在Channel中传输。
 *
 */
public class Course1_BufferDemo {

	public static void main(String[] args) {

		//1、分配一个指定大小的缓冲区
		ByteBuffer buff = ByteBuffer.allocate(10);
		String hello = "hello";
		System.out.println("initial buff容量：" + buff.capacity() + ", buff limit:" + buff.limit() + "， buff position: " + buff.position());

		//2、调用put方法写入数据
		buff.put(hello.getBytes());
		System.out.println("after put buff容量：" + buff.capacity() + ", buff limit:" + buff.limit() + "， buff position: " + buff.position());

		//3、调用flip, 将limit的位置变为position，position的位置变为0
		buff.flip();
		System.out.println("flip buff容量：" + buff.capacity() + ", buff limit:" + buff.limit() + "， buff position: " + buff.position());

		//4、可调用get方法读取数据
		byte[] barr = new byte[buff.limit()];
		buff.get(barr);
		System.out.println(new String(barr));
		System.out.println("after get buff容量：" + buff.capacity() + ", buff limit:" + buff.limit() + "， buff position: " + buff.position());

		//5、rewind， 使position的位置，重新回到0。
		buff.rewind();
		System.out.println("rewind buff容量：" + buff.capacity() + ", buff limit:" + buff.limit() + "， buff position: " + buff.position());

		//6、mark()，使mark的值等于position。
		buff.mark();

		//7、reset， 使position的值等于mark。
		buff.reset();

		//8、limit(n)，使limit的位置移动到n。
		buff.limit(3);

		//9、clear，清除数据。 position为0， limit为capacity。
		buff.clear();
	}
}
