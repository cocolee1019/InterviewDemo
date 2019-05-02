package ioBasis;

import java.nio.ByteBuffer;

/**
 * Buffer的使用。
 * 从1.4之后，Java提供了7类Buffer，分别是：
 * ByteBuffer、ShortBuffer、IntegerBuffer、LongBuffer、FloatBuffer、DoubleBuffer、CharBuffer
 *
 */
public class BufferDemo {

	public static void main(String[] args) {
		
		ByteBuffer buff = ByteBuffer.allocate(10);
		System.out.printf("容量：%d, 上限：%d，位置：%d\n", buff.capacity(), buff.limit(), buff.position());
		buff.put((byte)10);
		buff.put((byte)10);
		buff.put((byte)10);
		buff.put((byte)10);
		buff.put((byte)10);
		System.out.printf("容量：%d, 上限：%d，位置：%d\n", buff.capacity(), buff.limit(), buff.position());
		//flip:
		//flip会将limit的位置重置到position,position的位置重置到0
		buff.flip();
		System.out.printf("容量：%d, 上限：%d，位置：%d\n", buff.capacity(), buff.limit(), buff.position());
		buff.put((byte)12);
		System.out.printf("容量：%d, 上限：%d，位置：%d\n", buff.capacity(), buff.limit(), buff.position());

	}
}
